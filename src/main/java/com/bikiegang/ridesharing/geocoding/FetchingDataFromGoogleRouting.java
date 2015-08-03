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
import com.bikiegang.ridesharing.pojo.PlannedTrip;
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

    public List<LinkedLocation> fetch(PlannedTrip plannedTrip) throws IOException {
        if (null == plannedTrip.getRawRoutingResult() || plannedTrip.getRawRoutingResult().length() <= 0) {
            if (null == plannedTrip)
                logger.info("Planned Trip is null");
            else
                logger.info("JSONObject is null or empty");
            return null;
        }
        if (plannedTrip.getId() <= 0) {
            logger.info("Planned Trip missing id --> auto set plannedTripId");
            plannedTrip.setId(IdGenerator.getPlannedTripId());
        }
        RoutingResult routingResult = (RoutingResult) Parser.JSonToObject(plannedTrip.getRawRoutingResult().toString(), RoutingResult.class);
        //update plannedTrip info
        if(routingResult.getRoutes().length > 0) {
            GoogleRoute googleRoute = routingResult.getRoutes()[0];
            plannedTrip.setSumDistance(googleRoute.getLegs()[0].getDistance().getValue());
            // create linkLocation
            List<LinkedLocation> locations = getLocations(googleRoute, plannedTrip.getId());
            plannedTrip.setEstimatedTime(locations.get(locations.size() - 1).getEstimatedTime());
            plannedTrip.setStartLocation(googleRoute.getLegs()[0].getStart_location());
            plannedTrip.setEndLocation(googleRoute.getLegs()[0].getEnd_location());
            plannedTrip.setPolyLine(googleRoute.getOverview_polyline().getPoints());
            return locations;
        }
        return null;
    }


    private List<LinkedLocation> getLocations(GoogleRoute googleRoute, long plannedTripId) {
        // decode polyline
        GeoCell geoCell = new GeoCell();
        List<LatLng> latLngs = PolyLineProcess.decodePoly(googleRoute.getOverview_polyline().getPoints());
        List<String> cellcodes = geoCell.getCellCodesFromLatLngs(latLngs);
        long[] timeForCellCodes = new long[cellcodes.size()];
        Step[] steps = googleRoute.getLegs()[0].getSteps();
        //set end time
        timeForCellCodes[cellcodes.size()-1] = googleRoute.getLegs()[0].getDuration().getValue();
        // set time for other point
        loadTimeRecursive(steps, timeForCellCodes, cellcodes);
        // recalculate time: for time = 0 or weird time
        for (int i = 0; i < timeForCellCodes.length - 1; ) {
            for (int j = i + 1; j < timeForCellCodes.length; ) {
                if (timeForCellCodes[j] <= timeForCellCodes[i]) {
                    if (j == timeForCellCodes.length -1) { // case time of the last < time member
                        timeForCellCodes[j] = timeForCellCodes[i] + (j-i)* ((timeForCellCodes[i] - timeForCellCodes[0])/(i+1));
                    }
                    j++;

                } else {
                    if (j - i > 1) {
                        long averageDuration = (timeForCellCodes[j] - timeForCellCodes[i]) / (j - i);
                        while (i < j - 1) {
                            timeForCellCodes[i + 1] = timeForCellCodes[i] + averageDuration;
                            i++;
                        }
                    } else {
                        i++;
                        j++;
                    }
                }
                i++;
            }
        }
        // create link locations
        List<LinkedLocation> locations = new ArrayList<>();
        for (int i = 0; i < cellcodes.size(); i++) {
            String cellcode = cellcodes.get(i);
            LatLng center = geoCell.getLatLngCenterFromCellCode(cellcode);
            LinkedLocation location = new LinkedLocation(center.getLat(), center.getLng(), center.getTime(), 0, timeForCellCodes[i], i, plannedTripId, LinkedLocation.IN_PLANNED_TRIP);
            locations.add(location);
        }
        return locations;

    }

    private void loadTimeRecursive(Step[] steps, long[] timeForCellCodes, List<String> cellcodes) {
        try {
            GeoCell geoCell = new GeoCell();
            for (Step step : steps) {
                // get time
                String cellCodeStart = geoCell.getCellCodeFromLatLng(step.getStart_location());
                String cellCodeEnd = geoCell.getCellCodeFromLatLng(step.getEnd_location());
                int startIdx = cellcodes.indexOf(cellCodeStart);
                int endIdx = cellcodes.indexOf(cellCodeEnd);
                if (endIdx > 0 && startIdx >= 0 && timeForCellCodes[endIdx] == 0) {
                    timeForCellCodes[endIdx] = timeForCellCodes[startIdx] + step.getDuration().getValue();
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

    public Bound getBoundFromRoutingResult(PlannedTrip plannedTrip) throws IOException {
        if (null == plannedTrip.getRawRoutingResult() || plannedTrip.getRawRoutingResult().length() <= 0) {
            if (null == plannedTrip)
                logger.info("Planned Trip is null");
            else
                logger.info("JSONObject is null or empty");
            return null;
        }
        if (plannedTrip.getId() <= 0) {
            logger.info("Planned Trip missing id --> auto set plannedTripId");
            plannedTrip.setId(IdGenerator.getPlannedTripId());
        }
        RoutingResult routingResult = (RoutingResult) Parser.JSonToObject(plannedTrip.getRawRoutingResult().toString(), RoutingResult.class);
        GoogleRoute googleRoute = routingResult.getRoutes()[0];
        return googleRoute.getBounds();
    }

    public GoogleRoute getRouteFromRoutingResult(PlannedTrip plannedTrip) throws IOException {
        if (null == plannedTrip.getRawRoutingResult() || plannedTrip.getRawRoutingResult().length() <= 0) {
            if (null == plannedTrip)
                logger.info("plannedTrip is null");
            else
                logger.info("JSONObject is null or empty");
            return null;
        }
        if (plannedTrip.getId() <= 0) {
            logger.info("Planned Trip missing id --> auto set plannedTripId");
            plannedTrip.setId(IdGenerator.getPlannedTripId());
        }
        RoutingResult routingResult = (RoutingResult) Parser.JSonToObject(plannedTrip.getRawRoutingResult().toString(), RoutingResult.class);
        GoogleRoute googleRoute = routingResult.getRoutes()[0];
        return googleRoute;
    }

}
