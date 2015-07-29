package com.bikiegang.ridesharing.controller;

import com.bikiegang.ridesharing.dao.PlannedTripDao;
import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.database.IdGenerator;
import com.bikiegang.ridesharing.geocoding.FetchingDataFromGoogleRouting;
import com.bikiegang.ridesharing.geocoding.GoogleRoutingObject.GoogleRoute;
import com.bikiegang.ridesharing.geocoding.Pairing;
import com.bikiegang.ridesharing.parsing.Parser;
import com.bikiegang.ridesharing.pojo.LatLng;
import com.bikiegang.ridesharing.pojo.LinkedLocation;
import com.bikiegang.ridesharing.pojo.PlannedTrip;
import com.bikiegang.ridesharing.pojo.User;
import com.bikiegang.ridesharing.pojo.request.AutoSearchParingRequest;
import com.bikiegang.ridesharing.pojo.request.CreatePlannedTripRequest;
import com.bikiegang.ridesharing.pojo.request.GetPlannedTripDetailRequest;
import com.bikiegang.ridesharing.pojo.request.GetUsersAroundFromMeRequest;
import com.bikiegang.ridesharing.pojo.response.*;
import com.bikiegang.ridesharing.utilities.DateTimeUtil;
import com.bikiegang.ridesharing.utilities.FakeGroup.FakePlannedTrip;
import org.json.JSONObject;

import java.io.IOException;
import java.util.*;

/**
 * Created by hpduy17 on 6/29/15.
 */
public class PlannedTripController {

    private PlannedTripDao dao = new PlannedTripDao();
    private Database database = Database.getInstance();
    public static final double DEFAULT_PRICE = 3; //3000 vnd/km -> 3 vnd/m
    public static boolean isCreatingFake = false;

    /**
     * MAIN FUNCTION
     */

    public String getPlannedTripDetail(GetPlannedTripDetailRequest request) throws IOException {
        if (request.getPlannedTripId() <= 0) {
            return Parser.ObjectToJSon(false, "'plannedTripId' is invalid");
        }
        PlannedTrip plannedTrip = database.getPlannedTripHashMap().get(request.getPlannedTripId());
        if (plannedTrip == null) {
            return Parser.ObjectToJSon(false, "Planned Trip is not found");
        }
        PlannedTripDetailResponse plannedTripDetailResponse = new PlannedTripDetailResponse(getPlannedTripSortDetail(plannedTrip), plannedTrip.getRawRoutingResult().toString());
        return Parser.ObjectToJSon(true, "Get Planned Trip detail successfully", plannedTripDetailResponse);
    }


    public String autoSearchParing(AutoSearchParingRequest request) throws Exception {

        AutoSearchParingResponse response = new AutoSearchParingResponse();
        PlannedTripShortDetailResponse[] responses = null;
        if (null == request.getCreatorId() || request.getCreatorId().equals("")) {
            return Parser.ObjectToJSon(false, "'creatorId' is not found");
        }
        if (null == request.getGoogleRoutingResult() || request.getGoogleRoutingResult().equals("")) {
            return Parser.ObjectToJSon(false, "'googleRoutingResult' is invalid");
        }
        // create fake planned trip
        PlannedTrip fakePlannedTrip = new PlannedTrip();
        fakePlannedTrip.setRawRoutingResult(new JSONObject(request.getGoogleRoutingResult()));
        fakePlannedTrip.setRole(0);
        fakePlannedTrip.setCreatorId(request.getCreatorId());
        if (request.getGoTime() > DateTimeUtil.now()) {
            fakePlannedTrip.setGoTime(request.getGoTime());
            fakePlannedTrip.setType(PlannedTrip.SINGLE_FUTURE);
        } else {
            fakePlannedTrip.setGoTime(DateTimeUtil.now());
            fakePlannedTrip.setType(PlannedTrip.INSTANT);
        }
        fakePlannedTrip.setHasHelmet(request.isHasHelmet());
        List<LinkedLocation> fakeLocation = new FetchingDataFromGoogleRouting().fetch(fakePlannedTrip);
        //TODO fake trip
        if (Database.databaseStatus == Database.TESTING && !isCreatingFake) {
            isCreatingFake = true;
            new FakePlannedTrip().fakePlannedTrip(fakePlannedTrip);
        }
        //TODO end fake
        if (fakeLocation != null) {
            HashMap<Integer, List<PlannedTrip>> plannedTrips = new Pairing().pair(fakePlannedTrip, fakeLocation);
            List<PlannedTrip> plannedTripList = new ArrayList<>();
            for (int key : plannedTrips.keySet()) {
                plannedTripList.addAll(plannedTrips.get(key));
            }
            sortPlannedTripByDistance(fakePlannedTrip.getStartLocation(), plannedTripList);
            List<UserDetailWithPlannedTripDetailResponse> details = new ArrayList<>();
            for (PlannedTrip r : plannedTripList) {
                User creator = database.getUserHashMap().get(r.getCreatorId());
                if (null != creator) {
                    PlannedTripDetailResponse plannedTripDetailResponse = new PlannedTripDetailResponse(getPlannedTripSortDetail(r), r.getRawRoutingResult().toString());
                    UserDetailWithPlannedTripDetailResponse userPlannedTripDetail = new UserDetailWithPlannedTripDetailResponse(creator, plannedTripDetailResponse);
                    details.add(userPlannedTripDetail);
                }
            }
            response.setUsers(details.toArray(new UserDetailWithPlannedTripDetailResponse[details.size()]));
        }
        return Parser.ObjectToJSon(true, "Paring successfully", response);
    }

    public String createPlannedTrip(CreatePlannedTripRequest request) throws Exception {
        CreatePlannedTripResponse response = new CreatePlannedTripResponse();
        UserDetailWithPlannedTripDetailResponse[] plannedTrips = null;
        if (null == request.getCreatorId() || request.getCreatorId().equals("")) {
            return Parser.ObjectToJSon(false, "'creatorId' is not found");
        }
        if (null == request.getGoogleRoutingResult() || request.getGoogleRoutingResult().equals("")) {
            return Parser.ObjectToJSon(false, "'googleRoutingResult' is invalid");
        }
        PlannedTrip plannedTrip = new PlannedTrip();
        plannedTrip.setId(IdGenerator.getPlannedTripId());
        plannedTrip.setRole(request.getRole());
        if (request.getGoTime() > DateTimeUtil.now()) {
            plannedTrip.setGoTime(request.getGoTime());
            plannedTrip.setType(PlannedTrip.SINGLE_FUTURE);
        } else {
            plannedTrip.setGoTime(DateTimeUtil.now());
            plannedTrip.setType(PlannedTrip.INSTANT);
        }
        plannedTrip.setCreatorId(request.getCreatorId());
        plannedTrip.setRawRoutingResult(new JSONObject(request.getGoogleRoutingResult()));
        plannedTrip.setHasHelmet(request.isHasHelmet());
        //fetch data
        List<LinkedLocation> locations = new FetchingDataFromGoogleRouting().fetch(plannedTrip);
        //TODO fake trip
        if (Database.databaseStatus == Database.TESTING && !isCreatingFake) {
            isCreatingFake = true;
            new FakePlannedTrip().fakePlannedTrip(plannedTrip);
        }
        //TODO end fake
        if (null != locations) {
            LinkedLocationController controller = new LinkedLocationController();
            for (LinkedLocation location : locations) {
                location.setId(IdGenerator.getLinkedLocationId());
                controller.insertLinkLocation(location, plannedTrip.getRole());
            }
        }
        // check price
        if (request.getPrice() < 0) {
            plannedTrip.setOwnerPrice(DEFAULT_PRICE);
        } else {
            plannedTrip.setOwnerPrice(request.getPrice() / plannedTrip.getSumDistance() != Double.POSITIVE_INFINITY
                    ? request.getPrice() / plannedTrip.getSumDistance() : PlannedTrip.DEFAULT_PRICE_1KM / 1000);
        }
        if (dao.insert(plannedTrip) || Database.databaseStatus == Database.TESTING) {
            UserDetailWithPlannedTripDetailResponse yourPlannedTripDetail = getUserAndPlannedTripDetailFromObject(plannedTrip);
            if (request.isParing()) {
                HashMap<Integer, List<PlannedTrip>> paringResults = new Pairing().pair(plannedTrip);
                List<PlannedTrip> result;
                if (plannedTrip.getRole() == User.PASSENGER) {
                    result = paringResults.get(User.DRIVER);
                } else {
                    result = paringResults.get(User.PASSENGER);
                }
                plannedTrips = getListUserAndPlannedTripDetailFromObject(result);
            }
            response.setYourPlannedTrip(yourPlannedTripDetail);
            if (plannedTrips == null) {
                plannedTrips = new UserDetailWithPlannedTripDetailResponse[0];
            }
            response.setPairedPlannedTripsResult(plannedTrips);
            return Parser.ObjectToJSon(true, "Create planned trip successfully", response);
        }
        return Parser.ObjectToJSon(false, "Cannot create planned trip");
    }

    public String getPlannedTripsAroundFromMe(GetUsersAroundFromMeRequest request) throws IOException {
        AutoSearchParingResponse response = new AutoSearchParingResponse();
        if (request.getCenterLat() == 0 && request.getCenterLng() == 0) {
            return Parser.ObjectToJSon(false, "Latitude and Longitude is invalid (0,0)");
        }
        if (request.getRadius() < 0) {
            return Parser.ObjectToJSon(false, "Radius is invalid (< 0)");
        }
        LatLng center = new LatLng(request.getCenterLat(), request.getCenterLng());
        List<String> plannedTripsIds = database.getGeoCellStartLocation().getIdsInFrame(center, request.getRadius());
        List<PlannedTrip> plannedTrips = getPlannedTripsFromPlannedTripIds(plannedTripsIds);
        sortPlannedTripByDistance(center, plannedTrips);
        List<UserDetailWithPlannedTripDetailResponse> details = new ArrayList<>();
        for (PlannedTrip r : plannedTrips) {
            User creator = database.getUserHashMap().get(r.getCreatorId());
            if (null != creator) {
                PlannedTripDetailResponse plannedTripDetailResponse = new PlannedTripDetailResponse(getPlannedTripSortDetail(r), r.getRawRoutingResult().toString());
                UserDetailWithPlannedTripDetailResponse userPlannedTripDetail = new UserDetailWithPlannedTripDetailResponse(creator, plannedTripDetailResponse);
                details.add(userPlannedTripDetail);
            }
        }
        response.setUsers(details.toArray(new UserDetailWithPlannedTripDetailResponse[details.size()]));
        return Parser.ObjectToJSon(true, "Paring successfully", response);
    }


    /**
     * SUPPORT FUNCTION
     */
    public PlannedTripShortDetailResponse[] getListPlannedTripSortDetailResponse(List<Long> plannedTripIds) throws IOException {
        PlannedTripShortDetailResponse[] plannedTripSortDetailResponses = new PlannedTripShortDetailResponse[plannedTripIds.size()];
        for (int i = 0; i < plannedTripIds.size(); i++) {
            long id = plannedTripIds.get(i);
            PlannedTrip r = database.getPlannedTripHashMap().get(id);
            if (null != r) {
                plannedTripSortDetailResponses[i] = getPlannedTripSortDetail(r);
            }
        }
        return plannedTripSortDetailResponses;
    }

    public PlannedTripDetailResponse[] getListPlannedTripDetailResponse(List<Long> plannedTripIds) throws IOException {
        PlannedTripDetailResponse[] plannedTripDetailResponses = new PlannedTripDetailResponse[plannedTripIds.size()];
        for (int i = 0; i < plannedTripIds.size(); i++) {
            long id = plannedTripIds.get(i);
            PlannedTrip r = database.getPlannedTripHashMap().get(id);
            if (null != r) {
                plannedTripDetailResponses[i] = getPlannedTripDetailResponse(r);
            }
        }
        return plannedTripDetailResponses;
    }

    public PlannedTripShortDetailResponse[] getListPlannedTripSortDetailFromObject(List<PlannedTrip> plannedTrips) throws IOException {
        PlannedTripShortDetailResponse[] plannedTripSortDetailResponses = new PlannedTripShortDetailResponse[plannedTrips.size()];
        for (int i = 0; i < plannedTrips.size(); i++) {
            plannedTripSortDetailResponses[i] = getPlannedTripSortDetail(plannedTrips.get(i));
        }
        return plannedTripSortDetailResponses;
    }

    public UserDetailWithPlannedTripDetailResponse[] getListUserAndPlannedTripDetailFromObject(List<PlannedTrip> plannedTrips) throws IOException {
        UserDetailWithPlannedTripDetailResponse[] userDetailWithPlannedTripDetailResponses = new UserDetailWithPlannedTripDetailResponse[plannedTrips.size()];
        for (int i = 0; i < plannedTrips.size(); i++) {
            PlannedTrip r = plannedTrips.get(i);
            User creator = database.getUserHashMap().get(r.getCreatorId());
            if (null != creator) {
                PlannedTripDetailResponse plannedTripDetailResponse = new PlannedTripDetailResponse(getPlannedTripSortDetail(r), r.getRawRoutingResult().toString());
                UserDetailWithPlannedTripDetailResponse userPlannedTripDetail = new UserDetailWithPlannedTripDetailResponse(creator, plannedTripDetailResponse);
                userDetailWithPlannedTripDetailResponses[i] = userPlannedTripDetail;
            }
        }
        return userDetailWithPlannedTripDetailResponses;
    }

    public UserDetailWithPlannedTripDetailResponse getUserAndPlannedTripDetailFromObject(PlannedTrip plannedTrip) throws IOException {
        User creator = database.getUserHashMap().get(plannedTrip.getCreatorId());
        PlannedTripDetailResponse plannedTripDetailResponse = new PlannedTripDetailResponse(getPlannedTripSortDetail(plannedTrip), plannedTrip.getRawRoutingResult().toString());
        UserDetailWithPlannedTripDetailResponse userPlannedTripDetail = new UserDetailWithPlannedTripDetailResponse(creator, plannedTripDetailResponse);
        return userPlannedTripDetail;
    }

    public PlannedTripShortDetailResponse getPlannedTripSortDetail(PlannedTrip plannedTrip) throws IOException {
        GoogleRoute googleRoute = new FetchingDataFromGoogleRouting().getRouteFromRoutingResult(plannedTrip);
        PlannedTripShortDetailResponse plannedTripSortDetailResponse = new PlannedTripShortDetailResponse();
        plannedTripSortDetailResponse.setId(plannedTrip.getId());
        plannedTripSortDetailResponse.setRole(plannedTrip.getRole());
        plannedTripSortDetailResponse.setUnitPrice(plannedTrip.getOwnerPrice());
        plannedTripSortDetailResponse.setHasHelmet(plannedTrip.isHasHelmet());
        if (googleRoute != null) {
            plannedTripSortDetailResponse.setStartAddress(googleRoute.getLegs()[0].getStart_address());
            plannedTripSortDetailResponse.setEndAddress(googleRoute.getLegs()[0].getEnd_address());
            plannedTripSortDetailResponse.setOwnerDistance(plannedTrip.getSumDistance());
        }
        int numberOfRequest = 0;
        try {
            numberOfRequest = database.getReceiverRequestsBox().get(plannedTrip.getCreatorId()).get(plannedTrip.getId()).size();
        } catch (Exception ignored) {

        }
        plannedTripSortDetailResponse.setNumberOfRequest(numberOfRequest);
        return plannedTripSortDetailResponse;
    }

    public PlannedTripDetailResponse getPlannedTripDetailResponse(PlannedTrip plannedTrip) throws IOException {

        PlannedTripDetailResponse plannedTripDetailResponse = new PlannedTripDetailResponse(getPlannedTripSortDetail(plannedTrip), plannedTrip.getRawRoutingResult().toString());
        return plannedTripDetailResponse;
    }

    private void sortPlannedTripByDistance(final LatLng origin, List<PlannedTrip> plannedTrips) {
        Collections.sort(plannedTrips, new Comparator<PlannedTrip>() {
            @Override
            public int compare(PlannedTrip o1, PlannedTrip o2) {
                double dis1 = origin.distanceInMetres(o1.getStartLocation());
                double dis2 = origin.distanceInMetres(o2.getStartLocation());
                if (dis1 > dis2)
                    return -1;
                return 0;
            }
        });
    }

    public List<PlannedTrip> getPlannedTripsFromPlannedTripIds(List<String> ids) {

        List<PlannedTrip> plannedTrips = new ArrayList<>();
        if (ids != null) {
            for (String id : ids) {
                PlannedTrip plannedTrip = database.getPlannedTripHashMap().get(Long.parseLong(id));
                if (null != plannedTrip) {
                    plannedTrips.add(plannedTrip);
                }
            }
        }
        return plannedTrips;
    }
}
