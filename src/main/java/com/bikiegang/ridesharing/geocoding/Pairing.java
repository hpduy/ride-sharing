package com.bikiegang.ridesharing.geocoding;


import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.utilities.DateTimeUtil;

/**
 * Created by hpduy17 on 6/23/15.
 */
public class Pairing {
    private final double ACCEPTABLE_TIME_PASSENGER = 30 * DateTimeUtil.MINUTES;
    private final double ACCEPTABLE_TIME_DRIVER = 60 * DateTimeUtil.MINUTES;//second, distance between 2 goTime,
    private final double ACCEPTABLE_DISTANCE = 1.5 * 1000; // metres (1,5km)
    private final double ACCEPTABLE_DISTANCE_TIME = 10 * DateTimeUtil.MINUTES;
    private Database database = Database.getInstance();
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
//        List<String> srcNeighborList = new ArrayList<>(geoCell.getLocationInCellAndNeighbor(src.getLat(), src.getLng()));
//        List<String> desNeighborList = new ArrayList<>(geoCell.getLocationInCellAndNeighbor(des.getLat(), des.getLng()));
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
}