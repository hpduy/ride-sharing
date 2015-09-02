package com.bikiegang.ridesharing.geocoding;

import com.bikiegang.ridesharing.annn.framework.common.LogUtil;
import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.database.IdGenerator;
import com.bikiegang.ridesharing.geocoding.GoogleRoutingObject.Bound;
import com.bikiegang.ridesharing.geocoding.GoogleRoutingObject.GoogleRoute;
import com.bikiegang.ridesharing.geocoding.GoogleRoutingObject.RoutingResult;
import com.bikiegang.ridesharing.geocoding.GoogleRoutingObject.Step;
import com.bikiegang.ridesharing.parsing.Parser;
import com.bikiegang.ridesharing.pojo.LatLng;
import com.bikiegang.ridesharing.pojo.LinkedLocation;
import com.bikiegang.ridesharing.pojo.Route;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hpduy17 on 7/14/15.
 */
public class FetchingDataFromGoogleRouting {

    Logger logger = LogUtil.getLogger(this.getClass());
    Database database = Database.getInstance();

    public List<LinkedLocation> fetch(Route route) throws IOException {
        if (null == route.getRawRoutingResult() || route.getRawRoutingResult().length() <= 0) {
            if (null == route) {
                logger.info("Planned Trip is null");
            } else {
                logger.info("JSONObject is null or empty");
            }
            return null;
        }
        if (route.getId() <= 0) {
            logger.info("Planned Trip missing id --> auto set plannedTripId");
            route.setId(IdGenerator.getPlannedTripId());
        }
        RoutingResult routingResult = (RoutingResult) Parser.JSonToObject(route.getRawRoutingResult().toString(), RoutingResult.class);
        //update plannedTrip info
        if (routingResult.getRoutes().length > 0) {
            GoogleRoute googleRoute = routingResult.getRoutes()[0];
            route.setSumDistance(googleRoute.getLegs()[0].getDistance().getValue());
            // create linkLocation
            List<LinkedLocation> locations = getLocations(googleRoute, route.getId());
            route.setEstimatedTime(locations.get(locations.size() - 1).getEstimatedTime());
            if (route.getEstimatedTime() <= 0) {
                route.setEstimatedTime(googleRoute.getLegs()[0].getDuration().getValue());
            }
            route.setStartLocation(googleRoute.getLegs()[0].getStart_location());
            route.setEndLocation(googleRoute.getLegs()[0].getEnd_location());
            route.setOverViewPolyLine(googleRoute.getOverview_polyline().getPoints());
            return locations;
        }
        return null;
    }

    private List<LinkedLocation> getLocations(GoogleRoute googleRoute, long routeId) {
        // decode polyline
        GeoCell<Long> geoCell = new GeoCell<>(GeoCell.CELL_LEN_OF_PLANNED_TRIP);
        List<LatLng> latLngs = PolyLineProcess.decodePoly(googleRoute.getOverview_polyline().getPoints());
        List<String> cellcodes = geoCell.getCellCodesFromLatLngs(latLngs);
        long[] timeForCellCodes = new long[cellcodes.size()];
        Step[] steps = googleRoute.getLegs()[0].getSteps();
        //set end time
        timeForCellCodes[cellcodes.size() - 1] = googleRoute.getLegs()[0].getDuration().getValue();
        // set time for other point
        loadTimeRecursive(steps, timeForCellCodes, cellcodes);
//
//        //TODO
//        System.out.println("## Steps ##");
//        long tempTime = 0;
//        for (Step step : steps) {
//            tempTime += step.getDuration().getValue();
//            System.out.println("s-" + geoCell.getCellCodeFromLatLng(step.getStart_location())
//                    + ";; e-" + geoCell.getCellCodeFromLatLng(step.getEnd_location())
//                    + ";; duration-" + tempTime);
//        }
//        System.out.println("Summary:" + googleRoute.getLegs()[0].getDuration().getValue());
//        System.out.println("## Load time result ##");
//        for (int t = 0; t < cellcodes.size(); t++) {
//            System.out.println(cellcodes.get(t) + "::" + timeForCellCodes[t]);
//        }


        //TODO
        // recalculate time: for time = 0 or weird time
        boolean flag = false;
        int i = 0;
        int j = 1;
        for (; j < timeForCellCodes.length; j++) {
            if (timeForCellCodes[j] == 0 || timeForCellCodes[j] < timeForCellCodes[i]) {
                if (!flag) {
                    i = j - 1;
                    flag = true;
                }
            } else {
                if (flag) {
                    long averageDuration = (timeForCellCodes[j] - timeForCellCodes[i]) / (j - i);
                    while (i < j - 1) {
                        timeForCellCodes[i + 1] = timeForCellCodes[i] + averageDuration;
                        i++;
                    }
                }
            }

        }
//        System.out.println("## After import time result ##");
//        for (int t = 0; t < cellcodes.size(); t++) {
//            System.out.println(cellcodes.get(t) + "::" + timeForCellCodes[t]);
//        }
        // create link locations

        List<LinkedLocation> locations = new ArrayList<>();
        for (int k = 0; k < cellcodes.size(); k++) {
            String cellcode = cellcodes.get(k);
            LatLng center = geoCell.getLatLngCenterFromCellCode(cellcode);
            LinkedLocation location = new LinkedLocation(center.getLat(), center.getLng(), center.getTime(), 0, timeForCellCodes[k], k, routeId);
            locations.add(location);
        }
        return locations;

    }

    private void loadTimeRecursive(Step[] steps, long[] timeForCellCodes, List<String> cellcodes) {
        try {
            GeoCell<Long> geoCell = new GeoCell<>(GeoCell.CELL_LEN_OF_PLANNED_TRIP);
            long savedTime = 0;
            for (Step step : steps) {
                // get time
                String cellCodeStart = geoCell.getCellCodeFromLatLng(step.getStart_location());
                String cellCodeEnd = geoCell.getCellCodeFromLatLng(step.getEnd_location());
                int startIdx = cellcodes.indexOf(cellCodeStart);
                int endIdx = cellcodes.indexOf(cellCodeEnd);
                savedTime += step.getDuration().getValue();
                if (endIdx > 0 && startIdx >= 0 && timeForCellCodes[endIdx] == 0) {
                    timeForCellCodes[endIdx] = timeForCellCodes[startIdx] + savedTime;
                    savedTime = 0;
                }
                // recursive
                if (step.getSteps() != null && step.getSteps().length > 0) {
                    loadTimeRecursive(step.getSteps(), timeForCellCodes, cellcodes);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // GET BOUND
    public Bound getBoundFromRoutingResult(Route route) throws IOException {
        if (null == route.getRawRoutingResult() || route.getRawRoutingResult().length() <= 0) {
            if (null == route) {
                logger.info("Planned Trip is null");
            } else {
                logger.info("JSONObject is null or empty");
            }
            return null;
        }
        if (route.getId() <= 0) {
            logger.info("Planned Trip missing id --> auto set plannedTripId");
            route.setId(IdGenerator.getPlannedTripId());
        }
        RoutingResult routingResult = (RoutingResult) Parser.JSonToObject(route.getRawRoutingResult().toString(), RoutingResult.class);
        GoogleRoute googleRoute = routingResult.getRoutes()[0];
        return googleRoute.getBounds();
    }

    public GoogleRoute getRouteFromRoutingResult(Route route) throws IOException {
        if (null == route.getRawRoutingResult() || route.getRawRoutingResult().length() <= 0) {
            if (null == route) {
                logger.info("plannedTrip is null");
            } else {
                logger.info("JSONObject is null or empty");
            }
            return null;
        }
        if (route.getId() <= 0) {
            logger.info("Planned Trip missing id --> auto set plannedTripId");
            route.setId(IdGenerator.getPlannedTripId());
        }
        RoutingResult routingResult = (RoutingResult) Parser.JSonToObject(route.getRawRoutingResult().toString(), RoutingResult.class);
        GoogleRoute googleRoute = routingResult.getRoutes()[0];
        return googleRoute;
    }

}
