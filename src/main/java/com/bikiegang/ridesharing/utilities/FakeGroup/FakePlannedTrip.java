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
import org.json.JSONObject;

import java.util.HashSet;
import java.util.List;
import java.util.Random;

/**
 * Created by hpduy17 on 7/17/15.
 */
public class FakePlannedTrip {

    public long fakeDriverPlannedTrip(PlannedTrip rawSrc, String creatorId) throws Exception {
        GoogleRoute route = new FetchingDataFromGoogleRouting().getRouteFromRoutingResult(rawSrc);
        //ramdom 2 point and connect it go via 2 start and end rawSrc
        LatLng[] latlngs = new LatLng[4];
        latlngs[1] = route.getLegs()[0].getStart_location();
        latlngs[2] = route.getLegs()[0].getEnd_location();
        long seed = 10;
        double latDis = ((new Random(System.currentTimeMillis()).nextDouble() % seed) - seed / 2) / 100;
        double lngDis = ((new Random(System.currentTimeMillis()).nextDouble() % seed) - seed / 2) / 100;
        latlngs[0] = new FakeUser().fakeCurrentLocation(latlngs[1], latDis, lngDis);
        latDis = ((new Random(System.currentTimeMillis()).nextDouble() % seed) - seed / 2) / 100;
        lngDis = ((new Random(System.currentTimeMillis()).nextDouble() % seed) - seed / 2) / 100;
        latlngs[3] = new FakeUser().fakeCurrentLocation(latlngs[2], latDis, lngDis);
        // get new google routing result for fake trip
        JSONObject googleRoutingResult = new GoogleDirectionAPIProcess().direction(latlngs);
        CreatePlannedTripRequest createRequest = new CreatePlannedTripRequest();
        createRequest.setCreatorId(creatorId);
        createRequest.setGoTime(DateTimeUtil.now());
        createRequest.setRole(User.DRIVER);
        createRequest.setGoogleRoutingResult(googleRoutingResult.toString());
        createRequest.setIsParing(false);// no paring
        createRequest.setPrice(-1);// default price
        new PlannedTripController().createPlannedTrip(createRequest);
        String response = new PlannedTripController().createPlannedTrip(createRequest);
        Parser parser = (Parser) Parser.JSonToObject(response, Parser.class);
        if(parser.isSuccess()){
            CreatePlannedTripResponse createPlannedTripResponse = (CreatePlannedTripResponse) parser.getResult();
            return createPlannedTripResponse.getYourPlannedTrip().getId();
        }else{
            return -1;
        }
    }

    public long fakePassengerPlannedTrip(PlannedTrip rawSrc, String creatorId) throws Exception {
        GoogleRoute route = new FetchingDataFromGoogleRouting().getRouteFromRoutingResult(rawSrc);
        List<LatLng> trails = PolyLineProcess.decodePoly(route.getOverview_polyline().getPoints());
        LatLng[] latlngs = new LatLng[2];
        // random 2 point in trails
        int startIdx = new Random(System.currentTimeMillis()).nextInt() % (trails.size() / 2);
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
        createRequest.setRole(User.DRIVER);
        createRequest.setGoogleRoutingResult(googleRoutingResult.toString());
        createRequest.setIsParing(false);// no paring
        createRequest.setPrice(-1);// default price
        new PlannedTripController().createPlannedTrip(createRequest);
        String response = new PlannedTripController().createPlannedTrip(createRequest);
        Parser parser = (Parser) Parser.JSonToObject(response, Parser.class);
        if(parser.isSuccess()){
            CreatePlannedTripResponse createPlannedTripResponse = (CreatePlannedTripResponse) parser.getResult();
            return createPlannedTripResponse.getYourPlannedTrip().getId();
        }else{
            return -1;
        }
    }

    public void fakePlannedTrip(PlannedTrip rawSrc) throws Exception {
        for(int i = 0 ; i < 10 ; i++) {
            User user = new FakeUser().fakeUser((i % 2) + 1, 1, false);
            Database.getInstance().getUserHashMap().put(user.getId(),user);
            long plannedTripId;
            if(i < 5){
                plannedTripId = fakeDriverPlannedTrip(rawSrc,user.getId());
            }else {
                plannedTripId = fakePassengerPlannedTrip(rawSrc,user.getId());
            }
            if(plannedTripId > 0){
                HashSet<Long> ids = Database.getInstance().getUserIdRFPlanedTrips().get(user.getId());
                if(ids == null)
                    ids = new HashSet<>();
                ids.add(plannedTripId);
                Database.getInstance().getUserIdRFPlanedTrips().put(user.getId(),ids);
            }
        }
    }
}