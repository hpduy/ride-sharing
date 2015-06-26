package com.bikiegang.ridesharing.geocoding;


import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.pojo.LinkedLocation;
import com.bikiegang.ridesharing.pojo.Route;
import com.bikiegang.ridesharing.pojo.type.UserRole;
import com.bikiegang.ridesharing.utilities.DateTimeUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by hpduy17 on 6/23/15.
 */
public class Pairing {
    private final double ACCEPTABLE_TIME = 5 * DateTimeUtil.MINUTES;  //second
    private Database database = Database.getInstance();

    public List<Long> run(LinkedLocation src, LinkedLocation des, UserRole role) {
        GeoCell geoCell;
        if (role.equals(UserRole.DRIVER))
            geoCell = database.getGeoCellPassenger();
        else
            geoCell = database.getGeoCellDriver();
        //get all neighbor
        List<Long> srcNeighborList = new ArrayList<>(geoCell.getLocationInCellAndNeighbor(src.getLat(), src.getLng()));
        List<Long> desNeighborList = new ArrayList<>(geoCell.getLocationInCellAndNeighbor(des.getLat(), des.getLng()));
        //remove self location
        srcNeighborList.remove(src.getId());
        srcNeighborList.removeAll(desNeighborList);
        desNeighborList.remove(des.getId());
        desNeighborList.retainAll(srcNeighborList);
        // get route list around
        return getListRouteByDirectionAndTime(src,des,srcNeighborList,desNeighborList);

    }

    private List<Long> getListRouteByDirectionAndTime(LinkedLocation thisSrc, LinkedLocation thisDes, List<Long> srcNeighborList, List<Long> desNeighborList) {
        HashSet<Long> routeList = new HashSet<>();
        for (long srcId : srcNeighborList) {
            LinkedLocation src = database.getLinkedLocationHashMap().get(srcId);
            for (long desId : desNeighborList) {
                LinkedLocation des = database.getLinkedLocationHashMap().get(desId);
                // get a route though out this src and this des
                if (src.getRefId() == des.getRefId() && src.getIndex() > des.getIndex()) {
                    //check route type
                    Route route = database.getRouteHashMap().get(src.getRefId());
                    if (route != null) {
                        boolean flag = false;
                        switch (route.getType()) {
                            case FREQUENCY:
                                flag = checkTimeFrequencyRoute(thisSrc,src);
                                break;
                            case ONLY_NOW:
                                flag = checkTimeCurrentlyRoute(thisSrc,src);
                                break;
                        }
                        if (flag) {
                            routeList.add(src.getRefId());
                        }
                    }
                }
            }
        }
        return new ArrayList<>(routeList);
    }

    private boolean checkTimeFrequencyRoute(LinkedLocation thisSrc, LinkedLocation src) {
        return DateTimeUtil.timeDistanceIgnoreDate(thisSrc.getEstimatedTime(), src.getEstimatedTime()) < ACCEPTABLE_TIME;
    }

    private boolean checkTimeCurrentlyRoute(LinkedLocation thisSrc, LinkedLocation src) {
        return DateTimeUtil.timeDistance(thisSrc.getEstimatedTime(), src.getEstimatedTime()) < ACCEPTABLE_TIME;
    }

}
