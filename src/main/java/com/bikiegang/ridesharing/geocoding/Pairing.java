//package com.bikiegang.ridesharing.geocoding;
//
//
//import com.bikiegang.ridesharing.database.Database;
//import com.bikiegang.ridesharing.pojo.CurrentLocation;
//import com.bikiegang.ridesharing.pojo.LinkedLocation;
//import com.bikiegang.ridesharing.pojo.Route;
//import com.bikiegang.ridesharing.pojo.User;
//import com.bikiegang.ridesharing.utilities.DateTimeUtil;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
///**
// * Created by hpduy17 on 6/23/15.
// */
//public class Pairing {
//    private final double ACCEPTABLE_TIME_PASSENGER = 30 * DateTimeUtil.MINUTES;
//    private final double ACCEPTABLE_TIME_DRIVER = 60 * DateTimeUtil.MINUTES;//second, distance between 2 goTime,
//    private final double ACCEPTABLE_DISTANCE = 1.5 * 1000; // metres (1,5km)
//    private final double ACCEPTABLE_DISTANCE_TIME = 10 * DateTimeUtil.MINUTES;
//    private Database database = Database.getInstance();
//
//    public HashMap<String,Long> getListDriverCompatible(Route route) throws Exception {
//        GeoCell geoCell;
//        geoCell = database.getGeoCellDriver();
//        //get source and des in route
//        List<Long> locationIds = database.getRouteIdRFLinkedLocations().get(route.getId());
//        if (null == locationIds || locationIds.size() < 2)
//            throw new Exception("List location is null or less than 2");
//        LinkedLocation src = database.getLinkedLocationHashMap().get(locationIds.get(0));
//        LinkedLocation des = database.getLinkedLocationHashMap().get(locationIds.get(locationIds.size() - 1));
//        //get all neighbor
//        List<Long> srcNeighborList = new ArrayList<>(geoCell.getLocationInCellAndNeighbor(src.getLat(), src.getLng()));
//        List<Long> desNeighborList = new ArrayList<>(geoCell.getLocationInCellAndNeighbor(des.getLat(), des.getLng()));
//        //remove self location
//        srcNeighborList.remove(src.getId());
//        // srcNeighborList.removeAll(desNeighborList);
//        desNeighborList.remove(des.getId());
//        desNeighborList.removeAll(srcNeighborList);
//        // get route list around
//        return getListUserByDirectionAndTime(route, src, srcNeighborList, desNeighborList);
//
//    }
//
//    private HashMap<String,Long> getListUserByDirectionAndTime(Route myRoute, LinkedLocation mySrc, List<Long> srcNeighborList, List<Long> desNeighborList) {
//        HashMap<String,Long>  userList = new HashMap<>();
//        for (long srcId : srcNeighborList) {
//            LinkedLocation src = database.getLinkedLocationHashMap().get(srcId);
//            for (long desId : desNeighborList) {
//                LinkedLocation des = database.getLinkedLocationHashMap().get(desId);
//                // get a route through out this src and this des
//                if (src.getRefId() == des.getRefId() && src.getIndex() < des.getIndex()) {
//                    //check route type
//                    Route route = database.getRouteHashMap().get(src.getRefId());
//                    if (route != null) {
//                        if (!route.getCreatorId().equals(myRoute.getCreatorId())) {
//                            long timeDistance = DateTimeUtil.timeDistance(myRoute.getGoTime(), src.getEstimatedTime());
//                            // check within ACCEPTABLE TIME
//                            if (timeDistance <= ACCEPTABLE_TIME_PASSENGER) {
//                                // my route is single feature
//                                if (myRoute.getGoTime() > DateTimeUtil.now()) {
//                                    userList.put(route.getCreatorId(), route.getId());
//                                } else {
//                                    // my route is instant
//                                    if (src.getEstimatedTime() >= DateTimeUtil.now()) // feature driver route
//                                        userList.put(route.getCreatorId(), route.getId());
//                                    else {// past driver route
//                                        User creator = database.getUserHashMap().get(route.getCreatorId());
//                                        if (creator != null) {
//                                            CurrentLocation currentLocation = database.getCurrentLocationHashMap().get(creator.getCurrentLocationId());
//                                            if (currentLocation != null) {
//                                                double distance = currentLocation.distanceInMetres(mySrc);
//                                                long time = DateTimeUtil.timeDistance(currentLocation.getCreatedTime(), DateTimeUtil.now());
//                                                if (distance <= ACCEPTABLE_DISTANCE && time <= ACCEPTABLE_DISTANCE_TIME) {
//                                                    userList.put(route.getCreatorId(), route.getId());
//                                                }
//                                            }
//                                        }
//
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        return userList;
//    }
//
//
//    public HashMap<String,Long>  getListPassengerCompatible(Route route) throws Exception {
//        HashMap<String,Long>  passengerList = new HashMap<>();
//        GeoCell geoCellPassenger = database.getGeoCellPassenger();
//        // get cell code of all location in driver route
//        //Hash Map cellCode
//        HashMap<String, List<LinkedLocation>> driverCellCodes = new HashMap<>();//<cell code of location, index>
//        List<Long> driverRouteLocationList = database.getRouteIdRFLinkedLocations().get(route.getId());
//        for (long locationId : driverRouteLocationList) {
//            LinkedLocation location = database.getLinkedLocationHashMap().get(locationId);
//            if (location != null) {
//                String cellCode = geoCellPassenger.getCellCode(location.getLat(), location.getLng());
//                List<LinkedLocation> locationList = driverCellCodes.get(cellCode);
//                if (locationList == null)
//                    locationList = new ArrayList<>();
//                locationList.add(location);
//                driverCellCodes.put(cellCode, locationList);
//            }
//        }
//        //getListRouteOfPassenger
//        List<Long> passengerRoutes = new ArrayList<>();
//        try {
//            passengerRoutes = new ArrayList<>(database.getRoleRFRoutes().get(User.PASSENGER));
//        } catch (Exception ignored) {
//        }
//        for (long routeId : passengerRoutes) {
//            Route passengerRoute = database.getRouteHashMap().get(routeId);
//            if (passengerRoute != null && !route.getCreatorId().equals(passengerRoute.getCreatorId())) {
//                // get list Linked Location
//                List<Long> passengerRouteLocationList = database.getRouteIdRFLinkedLocations().get(passengerRoute.getId());
//                if (passengerRouteLocationList != null && passengerRouteLocationList.size() >= 2) {
//                    LinkedLocation srcPassengerLocation = null;
//                    LinkedLocation desPassengerLocation = null;
//                    //get src and des
//                    int i = 0;
//                    while (srcPassengerLocation == null && i < passengerRouteLocationList.size()) {
//                        long locationId = passengerRouteLocationList.get(i);
//                        srcPassengerLocation = database.getLinkedLocationHashMap().get(locationId);
//                        i++;
//                    }
//                    int j = passengerRouteLocationList.size() - 1;
//                    while (desPassengerLocation == null && j > 0) {
//                        long locationId = passengerRouteLocationList.get(j);
//                        desPassengerLocation = database.getLinkedLocationHashMap().get(locationId);
//                        j--;
//                    }
//                    // paring
//                    if (i < j && desPassengerLocation != null && srcPassengerLocation != null) {
//                        List<String> srcCellCodes = geoCellPassenger.getCellCodesNeighbor(srcPassengerLocation.getLat(), srcPassengerLocation.getLng());
//                        List<String> desCellCodes = geoCellPassenger.getCellCodesNeighbor(desPassengerLocation.getLat(), desPassengerLocation.getLng());
//                        //****** check direction and time ******
//                        for (String srcCellCode : srcCellCodes) {
//                            for (String desCellCode : desCellCodes) {
//                                // srcCellCode and desCellCode contained in driver cell code ,
//                                // that mean driver cell code through over 2 cell,
//                                if (driverCellCodes.keySet().contains(srcCellCode) && driverCellCodes.keySet().contains(desCellCode)) {
//                                    //-> check direction , if srcIndex < desIndex in driver Route -> same direction
//                                    boolean isSameDirection = false;
//                                    LinkedLocation srcDriverLocation = null;
//                                    List<LinkedLocation> driverSrcList = driverCellCodes.get(srcCellCode);
//                                    List<LinkedLocation> driverDesList = driverCellCodes.get(desCellCode);
//                                    // check index
//                                    for (LinkedLocation src : driverSrcList) {
//                                        for (LinkedLocation des : driverDesList) {
//                                            if (src.getIndex() < des.getIndex()) { // same direction
//                                                isSameDirection = true;
//                                                // save src location
//                                                srcDriverLocation = src;
//                                                break;
//                                            }
//                                        }
//                                        if (isSameDirection)
//                                            break;
//                                    }
//                                    // if same direction => check time
//                                    if (isSameDirection) {
//                                        long timeDistanceBetween2GoTime = DateTimeUtil.timeDistance(route.getGoTime(), srcPassengerLocation.getEstimatedTime());
//                                        if (timeDistanceBetween2GoTime <= ACCEPTABLE_TIME_DRIVER) {// feature trip
//                                            passengerList.put(passengerRoute.getCreatorId(),passengerRoute.getId());
//                                        } else { // past trip
//                                            long timeDistanceBetween2Src = DateTimeUtil.timeDistance(srcDriverLocation.getEstimatedTime(), srcPassengerLocation.getEstimatedTime());
//                                            // check user still between start point and nearest point passenger src
//                                            if (timeDistanceBetween2Src <= ACCEPTABLE_TIME_PASSENGER) {
//                                                // my route is single feature
//                                                passengerList.put(passengerRoute.getCreatorId(),passengerRoute.getId());
//                                            }
//                                        }
//
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        return passengerList;
//    }
//
//}
