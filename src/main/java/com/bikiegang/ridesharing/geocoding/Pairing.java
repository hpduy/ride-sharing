package com.bikiegang.ridesharing.geocoding;


import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.geocoding.GoogleRoutingObject.Bound;
import com.bikiegang.ridesharing.pojo.LinkedLocation;
import com.bikiegang.ridesharing.pojo.PlannedTrip;
import com.bikiegang.ridesharing.pojo.Route;
import com.bikiegang.ridesharing.pojo.User;
import com.bikiegang.ridesharing.utilities.daytime.DateTimeUtil;

import java.util.*;

/**
 * Created by hpduy17 on 6/23/15.
 */
public class Pairing {
    private final double ACCEPTABLE_TIME = 24 * DateTimeUtil.HOURS;
    private final double REMOVEABLE_TIME = DateTimeUtil.DAYS;
    private Database database = Database.getInstance();
    /*
    * MAIN ------------------------------------------------------------
    * */

    // with the plannedTrip is exits
    public HashMap<Integer, List<PlannedTrip>> pair(PlannedTrip plannedTrip) throws Exception {
        HashMap<Integer, List<PlannedTrip>> paringResult = new HashMap<>();

        switch (plannedTrip.getRole()) {
            case User.PASSENGER:
                paringResult.put(User.DRIVER, getDriversCompatible(plannedTrip));
                break;
            case User.DRIVER:
                paringResult.put(User.PASSENGER, getPassengersCompatible(plannedTrip));
                break;
            default:
                paringResult.put(User.DRIVER, getDriversCompatible(plannedTrip));
                paringResult.put(User.PASSENGER, getPassengersCompatible(plannedTrip));
                break;
        }
        return paringResult;
    }

    // with the fake plannedTrip
    public HashMap<Integer, List<PlannedTrip>> pair(PlannedTrip plannedTrip, Route route, List<LinkedLocation> linkedLocations) throws Exception {
        HashMap<Integer, List<PlannedTrip>> paringResult = new HashMap<>();
        if (linkedLocations != null && linkedLocations.size() >= 2) {
            LinkedLocation src = linkedLocations.get(0);
            LinkedLocation des = linkedLocations.get(linkedLocations.size() - 1);
            switch (plannedTrip.getRole()) {
                case User.PASSENGER:
                    paringResult.put(User.DRIVER, getDriversCompatible(plannedTrip, route, src, des));
                    break;
                case User.DRIVER:
                    paringResult.put(User.PASSENGER, getPassengersCompatible(plannedTrip, route, linkedLocations));
                    break;
                default:
                    paringResult.put(User.DRIVER, getDriversCompatible(plannedTrip, route, src, des));
                    paringResult.put(User.PASSENGER, getPassengersCompatible(plannedTrip, route, linkedLocations));
                    break;
            }
        }
        return paringResult;
    }

    /*
    * DRIVER ------------------------------------------------------------
    * */
    private List<PlannedTrip> getDriversCompatible(PlannedTrip plannedTrip) throws Exception {
        List<Long> locationIds = database.getPlannedTripIdRFLinkedLocations().get(plannedTrip.getId());
        if (null == locationIds || locationIds.size() < 2)
            throw new Exception("List location is null or less than 2 location");
        LinkedLocation src = database.getLinkedLocationHashMap().get(locationIds.get(0));
        LinkedLocation des = database.getLinkedLocationHashMap().get(locationIds.get(locationIds.size() - 1));
        Route route = database.getRouteHashMap().get(plannedTrip.getRouteId());
        return getDriversCompatible(plannedTrip, route, src, des);
    }

    private List<PlannedTrip> getDriversCompatible(PlannedTrip plannedTrip, Route route, LinkedLocation src, LinkedLocation des) throws Exception {
        List<PlannedTrip> listDriverPlannedTripResult = new ArrayList<>();
        // you are passenger -> get start and end location of passenger
        GeoCell<Long> geoCell = database.getGeoCellDriver();
        long passengerEpochday = plannedTrip.getDepartureTime()/ DateTimeUtil.SECONDS_PER_DAY;
        // get all plannedTrip id in cell and neighbor cell
        List<Long> srcNeighborList = new ArrayList<>(geoCell.getIdsInCellAndNeighbor(src.getLat(), src.getLng()));
        List<Long> desNeighborList = new ArrayList<>(geoCell.getIdsInCellAndNeighbor(des.getLat(), des.getLng()));

        // remover duplicate location
        srcNeighborList.remove(String.valueOf(src.getId()));
        desNeighborList.remove(String.valueOf(des.getId()));
        desNeighborList.removeAll(srcNeighborList);

        //TODO START TO PARE

        for (long srcId : srcNeighborList) {

            // parse to long
            LinkedLocation nearSrcLocation = database.getLinkedLocationHashMap().get(srcId);
            for (long desId : desNeighborList) {

                LinkedLocation nearDesLocation = database.getLinkedLocationHashMap().get(desId);

                // check same route ? -> that means route via location near des and src or not
                // check index -> that means check direction
                // check same epoch day -> check time
                if (nearSrcLocation.getRefId() == nearDesLocation.getRefId() && src.getIndex() < des.getIndex()
                        && database.getRouteRFPlannedTripsByDay().get(nearSrcLocation.getRefId()).containsKey(passengerEpochday)) {

                    // check condition to pare between passenger plannedTrip and drivers plannedTrip
                    long driverPlannedTripId = database.getRouteRFPlannedTripsByDay().get(nearSrcLocation.getRefId()).get(passengerEpochday);
                    PlannedTrip driverPlannedTrip = database.getPlannedTripHashMap().get(driverPlannedTripId);

                    // plannedTrip is not null and not own by passenger
                    if (null != driverPlannedTrip && !driverPlannedTrip.getCreatorId().equals(plannedTrip.getCreatorId()) && driverPlannedTrip.getRequestId() == 0) {
                        long plannedTripGoTime = plannedTrip.getDepartureTime();
                        long driverTripGoTime = driverPlannedTrip.getDepartureTime();
                        // get time driver reach a location near passenger
                        long timeDriveReachNearSrcLocation = driverTripGoTime + nearSrcLocation.getEstimatedTime();

                        // check this time in acceptable time?
                        if (DateTimeUtil.timeDistance(timeDriveReachNearSrcLocation, plannedTripGoTime) <= ACCEPTABLE_TIME) {
                            //Get this plannedTrip
                            if (!listDriverPlannedTripResult.contains(driverPlannedTrip)) {
                                // check helmet
                                if (plannedTrip.isHasHelmet() || driverPlannedTrip.isHasHelmet())
                                    listDriverPlannedTripResult.add(driverPlannedTrip);
                            }
                        } else {
                            //TODO: WARNING WHEN REMOVE HERE
                            // if it expired -> remove from GeoCell to reduce algorithm cost
                            if (timeDriveReachNearSrcLocation - DateTimeUtil.now() > REMOVEABLE_TIME) {
                                //TODO REMOVE ALL LOCATION IN GEOCELL
                            }
                        }
                    }

                }
            }

        }
        return listDriverPlannedTripResult;

    }

    /*
    * PASSENGER ------------------------------------------------------------
    * */

    /**
     * Concept:
     * 1/Get boundaries of driver plannedTrip -> get list passenger plannedTrip in this frame
     * 2/Get src and des of each passenger plannedTrip -> get neighbor cell code of its
     * 3/Check cell code is contain in list cell code of driver plannedTrip or not
     * 4/If yes, check index of cell code near src and cell code near des in list cell code of driver to check direction
     */

    private List<PlannedTrip> getPassengersCompatible(PlannedTrip plannedTrip, Route route, List<LinkedLocation> linkedLocations) throws Exception {
        List<PlannedTrip> listPassengerPlannedTripResult = new ArrayList<>();
        long driverEpochday = plannedTrip.getDepartureTime() / DateTimeUtil.SECONDS_PER_DAY;
        GeoCell<Long> geoCellPassenger = database.getGeoCellPassenger();
        // get list cell code of driver plannedTrip
        List<String> driverPlannedTripCellCodes = geoCellPassenger.getCellCodesFromLinkLocation(linkedLocations);

        //get bound of driver plannedTrip
        Bound bound = new FetchingDataFromGoogleRouting().getBoundFromRoutingResult(route);

        // get list passenger route in frame
        List<Long> passengersRouteLinkedLocationIds = geoCellPassenger.getIdsInFrame(bound.getNortheast(), bound.getSouthwest());

        //get distinct passenger plannedTrips from route
        Set<Long> passengerPlannedTripIds = new HashSet<>();
        for (Long locationId : passengersRouteLinkedLocationIds) {
            // parse to long
            LinkedLocation location = database.getLinkedLocationHashMap().get(locationId);
            if (location != null && null != database.getRouteRFPlannedTripsByDay().get(location.getRefId()).get(driverEpochday)) {
                long passengerPlannedTripId = database.getRouteRFPlannedTripsByDay().get(location.getRefId()).get(driverEpochday);
                passengerPlannedTripIds.add(passengerPlannedTripId);
            }
        }

        //TODO START TO PARE
        for (long passengerPlannedTripId : passengerPlannedTripIds) {
            PlannedTrip passengerPlannedTrip = database.getPlannedTripHashMap().get(passengerPlannedTripId);
            // plannedTrip is not null and not own by driver and not requested
            if (passengerPlannedTrip != null && !plannedTrip.getCreatorId().equals(passengerPlannedTrip.getCreatorId()) && passengerPlannedTrip.getRequestId() == 0) {
                List<Long> passengerPlannedTripLinkedLocationIds = database.getPlannedTripIdRFLinkedLocations().get(passengerPlannedTrip.getId());
                if (passengerPlannedTripLinkedLocationIds != null && passengerPlannedTripLinkedLocationIds.size() >= 2) {
                    LinkedLocation srcPassengerLocation = null;
                    LinkedLocation desPassengerLocation = null;
                    //get src and des
                    int i = 0;
                    while (srcPassengerLocation == null && i < passengerPlannedTripLinkedLocationIds.size()) {
                        long locationId = passengerPlannedTripLinkedLocationIds.get(i);
                        srcPassengerLocation = database.getLinkedLocationHashMap().get(locationId);
                        i++;
                    }
                    int j = passengerPlannedTripLinkedLocationIds.size() - 1;
                    while (desPassengerLocation == null && j > 0) {
                        long locationId = passengerPlannedTripLinkedLocationIds.get(j);
                        desPassengerLocation = database.getLinkedLocationHashMap().get(locationId);
                        j--;
                    }
                    // begin paring 2 plannedTrip
                    if (i < j && desPassengerLocation != null && srcPassengerLocation != null) {

                        //get passenger cell code and its neighbor
                        List<String> srcPassengerCellCodes = geoCellPassenger.getCellCodesNeighbor(srcPassengerLocation.getLat(), srcPassengerLocation.getLng());
                        List<String> desPassengerCellCodes = geoCellPassenger.getCellCodesNeighbor(desPassengerLocation.getLat(), desPassengerLocation.getLng());

                        // check have exist pair of cell code src and des in cell codes of driver plannedTrip ?
                        for (String srcPassengerCellCode : srcPassengerCellCodes) {
                            for (String desPassengerCellCode : desPassengerCellCodes) {

                                // srcCellCode and desCellCode contained in driver cell code ,
                                // that mean driver cell code through over 2 cell,
                                if (driverPlannedTripCellCodes.contains(srcPassengerCellCode) && driverPlannedTripCellCodes.contains(desPassengerCellCode)) {

                                    // check direction , if srcIndex < desIndex in driver Planned Trip -> same direction

                                    if (driverPlannedTripCellCodes.indexOf(srcPassengerCellCode) < driverPlannedTripCellCodes.indexOf(desPassengerCellCode)) {
                                        long passengerGoTime = passengerPlannedTrip.getDepartureTime();
                                        long plannedTripGoTime = plannedTrip.getDepartureTime();
                                        int indexOfCellNearPassengerSrc = driverPlannedTripCellCodes.indexOf(srcPassengerCellCode);
                                        LinkedLocation nearPassengerSrcLocation = linkedLocations.get(indexOfCellNearPassengerSrc);
                                        long timeDriveReachNearSrcLocation = plannedTripGoTime + nearPassengerSrcLocation.getEstimatedTime();
                                        // if same direction => check time
                                        if (DateTimeUtil.timeDistance(timeDriveReachNearSrcLocation, passengerGoTime) <= ACCEPTABLE_TIME) {
                                            if (!listPassengerPlannedTripResult.contains(passengerPlannedTrip)) {
                                                if (plannedTrip.isHasHelmet() || passengerPlannedTrip.isHasHelmet())
                                                    listPassengerPlannedTripResult.add(passengerPlannedTrip);
                                            }
                                        } else {
                                            //TODO: WARNING WHEN REMOVE HERE
                                            // if it expired -> remove from GeoCell to reduce algorithm cost
                                            if (passengerGoTime - DateTimeUtil.now() > REMOVEABLE_TIME) {
                                                //TODO REMOVE ALL LOCATION IN GEOCELL
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                }
            }


        }
        return listPassengerPlannedTripResult;
    }

    private List<PlannedTrip> getPassengersCompatible(PlannedTrip plannedTrip) throws Exception {
        List<Long> driverPlannedTripLinkedLocationIds = database.getPlannedTripIdRFLinkedLocations().get(plannedTrip.getId());
        List<LinkedLocation> linkedLocations = new ArrayList<>();
        for (long llId : driverPlannedTripLinkedLocationIds) {
            LinkedLocation location = database.getLinkedLocationHashMap().get(llId);
            if (location != null) {
                linkedLocations.add(location);
            }
        }
        Route route = database.getRouteHashMap().get(plannedTrip.getRouteId());
        return getPassengersCompatible(plannedTrip, route, linkedLocations);
    }
}
