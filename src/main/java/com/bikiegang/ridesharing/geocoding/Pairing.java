package com.bikiegang.ridesharing.geocoding;


import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.geocoding.GoogleRoutingObject.Bound;
import com.bikiegang.ridesharing.pojo.LinkedLocation;
import com.bikiegang.ridesharing.pojo.Route;
import com.bikiegang.ridesharing.pojo.User;
import com.bikiegang.ridesharing.utilities.DateTimeUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by hpduy17 on 6/23/15.
 */
public class Pairing {
    private final double ACCEPTABLE_TIME = 30 * DateTimeUtil.MINUTES;
    private final double REMOVEABLE_TIME = DateTimeUtil.DAYS;
    private Database database = Database.getInstance();
    /*
    * MAIN ------------------------------------------------------------
    * */

    // with the route is exits
    public HashMap<Integer, List<Route>> pair(Route route) throws Exception {
        HashMap<Integer, List<Route>> paringResult = new HashMap<>();
        switch (route.getRole()) {
            case User.PASSENGER:
                paringResult.put(User.DRIVER, getDriversCompatible(route));
                break;
            case User.DRIVER:
                paringResult.put(User.PASSENGER, getPassengersCompatible(route));
                break;
            default:
                paringResult.put(User.DRIVER, getDriversCompatible(route));
                paringResult.put(User.PASSENGER, getPassengersCompatible(route));
                break;
        }
        return paringResult;
    }

    // with the fake route
    public HashMap<Integer, List<Route>> pair(Route route, List<LinkedLocation> linkedLocations) throws Exception {
        HashMap<Integer, List<Route>> paringResult = new HashMap<>();
        if (linkedLocations != null && linkedLocations.size() >= 2) {
            LinkedLocation src = linkedLocations.get(0);
            LinkedLocation des = linkedLocations.get(linkedLocations.size() - 1);
            switch (route.getRole()) {
                case User.PASSENGER:
                    paringResult.put(User.DRIVER, getDriversCompatible(route, src, des));
                    break;
                case User.DRIVER:
                    paringResult.put(User.PASSENGER, getPassengersCompatible(route, linkedLocations));
                    break;
                default:
                    paringResult.put(User.DRIVER, getDriversCompatible(route, src, des));
                    paringResult.put(User.PASSENGER, getPassengersCompatible(route, linkedLocations));
                    break;
            }
        }
        return paringResult;
    }

    /*
    * DRIVER ------------------------------------------------------------
    * */
    private List<Route> getDriversCompatible(Route route) throws Exception {
        List<Long> locationIds = database.getRouteIdRFLinkedLocations().get(route.getId());
        if (null == locationIds || locationIds.size() < 2)
            throw new Exception("List location is null or less than 2 location");
        LinkedLocation src = database.getLinkedLocationHashMap().get(locationIds.get(0));
        LinkedLocation des = database.getLinkedLocationHashMap().get(locationIds.get(locationIds.size() - 1));
        return getDriversCompatible(route, src, des);
    }

    private List<Route> getDriversCompatible(Route route, LinkedLocation src, LinkedLocation des) throws Exception {
        List<Route> listDriverRouteResult = new ArrayList<>();
        // you are passenger -> get start and end location of passenger
        GeoCell geoCell = database.getGeoCellDriver();

        // get all route id in cell and neighbor cell
        List<String> srcNeighborList = new ArrayList<>(geoCell.getIdsInCellAndNeighbor(src.getLat(), src.getLng()));
        List<String> desNeighborList = new ArrayList<>(geoCell.getIdsInCellAndNeighbor(des.getLat(), des.getLng()));

        // remover duplicate location
        srcNeighborList.remove(String.valueOf(src.getId()));
        desNeighborList.remove(String.valueOf(des.getId()));
        desNeighborList.removeAll(srcNeighborList);

        //TODO START TO PARE

        for (String srcStringId : srcNeighborList) {

            // parse to long
            long srcId = Long.parseLong(srcStringId);
            LinkedLocation nearSrcLocation = database.getLinkedLocationHashMap().get(srcId);
            for (String desStringId : desNeighborList) {

                // parse to long
                long desId = Long.parseLong(desStringId);
                LinkedLocation nearDesLocation = database.getLinkedLocationHashMap().get(desId);

                // check same route ? -> that means route via location near des and src or not
                // check index -> that means check direction
                if (nearSrcLocation.getRefId() == nearDesLocation.getRefId() && src.getIndex() < des.getIndex()) {

                    // check condition to pare between passenger route and drivers route
                    Route driverRoute = database.getRouteHashMap().get(src.getRefId());

                    // route is not null and not own by passenger
                    if (null != driverRoute && !driverRoute.getCreatorId().equals(route.getCreatorId())) {

                        // get time driver reach a location near passenger
                        long timeDriveReachNearSrcLocation = route.getGoTime() + nearSrcLocation.getEstimatedTime();

                        // check this time in acceptable time?
                        if (DateTimeUtil.timeDistance(timeDriveReachNearSrcLocation, route.getGoTime()) <= ACCEPTABLE_TIME) {
                            //Get this route
                            if (!listDriverRouteResult.contains(driverRoute)) {
                                listDriverRouteResult.add(driverRoute);
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
        return listDriverRouteResult;

    }

    /*
    * PASSENGER ------------------------------------------------------------
    * */

    /**
     * Concept:
     * 1/Get boundaries of driver route -> get list passenger route in this frame
     * 2/Get src and des of each passenger route -> get neighbor cell code of its
     * 3/Check cell code is contain in list cell code of driver route or not
     * 4/If yes, check index of cell code near src and cell code near des in list cell code of driver to check direction
     */

    private List<Route> getPassengersCompatible(Route route, List<LinkedLocation> linkedLocations) throws Exception {
        List<Route> listPassengerRouteResult = new ArrayList<>();
        GeoCell geoCellPassenger = database.getGeoCellPassenger();
        // get list cell code of driver route
        List<String> driverRouteCellCodes = geoCellPassenger.getCellCodesFromLinkLocation(linkedLocations);

        //get bound of driver route
        Bound bound = new FetchingDataFromGoogleRouting().getBoundFromRoutingResult(route);

        // get list passenger route in frame
        List<String> passengersRouteLinkedLocationIds = geoCellPassenger.getIdsInFrame(bound.getNortheast(), bound.getSouthwest());

        //get distinct passenger routes
        List<Long> passengerRouteIds = new ArrayList<>();
        for (String locationStringId : passengersRouteLinkedLocationIds) {
            // parse to long
            long locationId = Long.parseLong(locationStringId);
            LinkedLocation location = database.getLinkedLocationHashMap().get(locationId);
            if (location != null)
                passengerRouteIds.add(location.getRefId());
        }

        //TODO START TO PARE
        for (long passengerRouteId : passengerRouteIds) {
            Route passengerRoute = database.getRouteHashMap().get(passengerRouteId);
            // route is not null and not own by driver
            if (passengerRoute != null && !route.getCreatorId().equals(passengerRoute.getCreatorId())) {
                List<Long> passengerRouteLinkedLocationIds = database.getRouteIdRFLinkedLocations().get(passengerRoute.getId());
                if (passengerRouteLinkedLocationIds != null && passengerRouteLinkedLocationIds.size() >= 2) {
                    LinkedLocation srcPassengerLocation = null;
                    LinkedLocation desPassengerLocation = null;
                    //get src and des
                    int i = 0;
                    while (srcPassengerLocation == null && i < passengerRouteLinkedLocationIds.size()) {
                        long locationId = passengerRouteLinkedLocationIds.get(i);
                        srcPassengerLocation = database.getLinkedLocationHashMap().get(locationId);
                        i++;
                    }
                    int j = passengerRouteLinkedLocationIds.size() - 1;
                    while (desPassengerLocation == null && j > 0) {
                        long locationId = passengerRouteLinkedLocationIds.get(j);
                        desPassengerLocation = database.getLinkedLocationHashMap().get(locationId);
                        j--;
                    }
                    // begin paring 2 route
                    if (i < j && desPassengerLocation != null && srcPassengerLocation != null) {

                        //get passenger cell code and its neighbor
                        List<String> srcPassengerCellCodes = geoCellPassenger.getCellCodesNeighbor(srcPassengerLocation.getLat(), srcPassengerLocation.getLng());
                        List<String> desPassengerCellCodes = geoCellPassenger.getCellCodesNeighbor(desPassengerLocation.getLat(), desPassengerLocation.getLng());

                        // check have exist pair of cell code src and des in cell codes of driver route ?
                        for (String srcPassengerCellCode : srcPassengerCellCodes) {
                            for (String desPassengerCellCode : desPassengerCellCodes) {

                                // srcCellCode and desCellCode contained in driver cell code ,
                                // that mean driver cell code through over 2 cell,
                                if (driverRouteCellCodes.contains(srcPassengerCellCode) && driverRouteCellCodes.contains(desPassengerCellCode)) {

                                    // check direction , if srcIndex < desIndex in driver Route -> same direction

                                    if (driverRouteCellCodes.indexOf(srcPassengerCellCode) < driverRouteCellCodes.indexOf(desPassengerCellCode)) {

                                        int indexOfCellNearPassengerSrc = driverRouteCellCodes.indexOf(srcPassengerCellCode);
                                        LinkedLocation nearPassengerSrcLocation = linkedLocations.get(indexOfCellNearPassengerSrc);
                                        long timeDriveReachNearSrcLocation = route.getGoTime() + nearPassengerSrcLocation.getEstimatedTime();
                                        // if same direction => check time
                                        if (DateTimeUtil.timeDistance(timeDriveReachNearSrcLocation, passengerRoute.getGoTime()) <= ACCEPTABLE_TIME) {
                                            if (!listPassengerRouteResult.contains(passengerRoute)) {
                                                listPassengerRouteResult.add(passengerRoute);
                                            }
                                        } else {
                                            //TODO: WARNING WHEN REMOVE HERE
                                            // if it expired -> remove from GeoCell to reduce algorithm cost
                                            if (passengerRoute.getGoTime() - DateTimeUtil.now() > REMOVEABLE_TIME) {
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


        return listPassengerRouteResult;
    }

    private List<Route> getPassengersCompatible(Route route) throws Exception {
        ;
        List<Long> driverRouteLinkedLocationIds = database.getRouteIdRFLinkedLocations().get(route.getId());
        List<LinkedLocation> linkedLocations = new ArrayList<>();
        for (long llId : driverRouteLinkedLocationIds) {
            LinkedLocation location = database.getLinkedLocationHashMap().get(llId);
            if (location != null) {
                linkedLocations.add(location);
            }
        }
        return getPassengersCompatible(route, linkedLocations);
    }
}
