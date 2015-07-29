package com.bikiegang.ridesharing.utilities.FakeGroup;

import com.bikiegang.ridesharing.controller.PlannedTripController;
import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.geocoding.FetchingDataFromGoogleRouting;
import com.bikiegang.ridesharing.geocoding.GoogleDirectionAPIProcess;
import com.bikiegang.ridesharing.geocoding.GoogleRoutingObject.GoogleRoute;
import com.bikiegang.ridesharing.geocoding.PolyLineProcess;
import com.bikiegang.ridesharing.parsing.Parser;
import com.bikiegang.ridesharing.pojo.LatLng;
import com.bikiegang.ridesharing.pojo.PlannedTrip;
import com.bikiegang.ridesharing.pojo.User;
import com.bikiegang.ridesharing.pojo.request.CreatePlannedTripRequest;
import com.bikiegang.ridesharing.pojo.response.CreatePlannedTripResponse;
import com.bikiegang.ridesharing.utilities.DateTimeUtil;
import org.apache.commons.lang.math.RandomUtils;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.List;

/**
 * Created by hpduy17 on 7/17/15.
 */
public class FakePlannedTrip {

    public long fakeDriverPlannedTrip(PlannedTrip rawSrc, String creatorId, boolean hasHelmet) throws Exception {
        GoogleRoute route = new FetchingDataFromGoogleRouting().getRouteFromRoutingResult(rawSrc);
        //ramdom 2 point and connect it go via 2 start and end rawSrc
        LatLng[] latlngs = new LatLng[3];
        latlngs[1] = route.getLegs()[0].getStart_location();
        latlngs[2] = route.getLegs()[0].getEnd_location();
        long seed = 10;
        double latDis = (((Math.abs(RandomUtils.nextDouble() % seed)) % seed) - seed / 2) / 10000;
        double lngDis = ((Math.abs(RandomUtils.nextDouble() % seed)) - seed / 2) / 10000;
        latlngs[0] = new FakeUser().fakeCurrentLocation(latlngs[1], latDis, lngDis);
        // get new google routing result for fake trip
        JSONObject googleRoutingResult = new GoogleDirectionAPIProcess().direction(latlngs);
        CreatePlannedTripRequest createRequest = new CreatePlannedTripRequest();
        createRequest.setCreatorId(creatorId);
        createRequest.setGoTime(DateTimeUtil.now());
        createRequest.setRole(User.DRIVER);
        createRequest.setGoogleRoutingResult(googleRoutingResult.toString());
        createRequest.setIsParing(false);// no paring
        createRequest.setPrice(-1);// default price
        createRequest.setHasHelmet(hasHelmet);
        String response = new PlannedTripController().createPlannedTrip(createRequest);
        Parser parser = Parser.JSonToParser(response, CreatePlannedTripResponse.class);
        if (parser.isSuccess()) {
            if (parser.getResult() != null) {
                CreatePlannedTripResponse createPlannedTripResponse = (CreatePlannedTripResponse) parser.getResult();
                return createPlannedTripResponse.getYourPlannedTrip().getPlannedTrip().getId();
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }

    public long fakePassengerPlannedTrip(PlannedTrip rawSrc, String creatorId, boolean hasHelmet) throws Exception {
        GoogleRoute route = new FetchingDataFromGoogleRouting().getRouteFromRoutingResult(rawSrc);
        List<LatLng> trails = PolyLineProcess.decodePoly(route.getOverview_polyline().getPoints());
        LatLng[] latlngs = new LatLng[2];
        // random 2 point in trails
        int startIdx = (Math.abs(RandomUtils.nextInt()) % (trails.size() / 2));
        int endIdx = startIdx + trails.size() / 2 - 1;
        if (endIdx > trails.size() - 1)
            endIdx = trails.size() - 1;
        latlngs[0] = trails.get(startIdx);
        latlngs[1] = trails.get(endIdx);
        // get new google routing result for fake trip
        JSONObject googleRoutingResult = new GoogleDirectionAPIProcess().direction(latlngs);
        // create fake planned trip
        CreatePlannedTripRequest createRequest = new CreatePlannedTripRequest();
        createRequest.setCreatorId(creatorId);
        createRequest.setGoTime(DateTimeUtil.now());
        createRequest.setRole(User.PASSENGER);
        createRequest.setGoogleRoutingResult(googleRoutingResult.toString());
        createRequest.setIsParing(false);// no paring
        createRequest.setPrice(-1);// default price
        createRequest.setHasHelmet(hasHelmet);
        String response = new PlannedTripController().createPlannedTrip(createRequest);
        Parser parser = Parser.JSonToParser(response, CreatePlannedTripResponse.class);
        if (parser.isSuccess()) {
            if (parser.getResult() != null) {
                CreatePlannedTripResponse createPlannedTripResponse = (CreatePlannedTripResponse) parser.getResult();
                return createPlannedTripResponse.getYourPlannedTrip().getPlannedTrip().getId();
            }


        }
        return -1;
    }

    public void fakePlannedTrip(PlannedTrip rawSrc) throws Exception {
        int length = 4;
        for (int i = 0; i < length; i++) {
            User user = new FakeUser().fakeUser((i % 2) + 1, 1, false);
            Database.getInstance().getUserHashMap().put(user.getId(), user);
            long plannedTripId;
            if (i < length / 2) {
                plannedTripId = fakeDriverPlannedTrip(rawSrc, user.getId(), i % 2 == 0);
            } else {
                plannedTripId = fakePassengerPlannedTrip(rawSrc, user.getId(), i % 2 == 0);
            }
            if (plannedTripId > 0) {
                HashSet<Long> ids = Database.getInstance().getUserIdRFPlanedTrips().get(user.getId());
                if (ids == null)
                    ids = new HashSet<>();
                ids.add(plannedTripId);
                Database.getInstance().getUserIdRFPlanedTrips().put(user.getId(), ids);
            }
        }
    }
}
