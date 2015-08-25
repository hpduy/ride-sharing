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
import com.bikiegang.ridesharing.pojo.request.*;
import com.bikiegang.ridesharing.pojo.response.*;
import com.bikiegang.ridesharing.pojo.static_object.TripPattern;
import com.bikiegang.ridesharing.utilities.daytime.DateTimeUtil;
import com.bikiegang.ridesharing.utilities.FakeGroup.FakePlannedTrip;
import com.bikiegang.ridesharing.utilities.MessageMappingUtil;
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
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_invalid, "'plannedTripId'");
        }
        PlannedTrip plannedTrip = database.getPlannedTripHashMap().get(request.getPlannedTripId());
        if (plannedTrip == null) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Object_is_not_found, "Planned Trip");
        }
        PlannedTripDetailResponse plannedTripDetailResponse = new PlannedTripDetailResponse(plannedTrip, request.getUserId(), plannedTrip.getRawRoutingResult());
        return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully, plannedTripDetailResponse);
    }

    public String autoSearchParing(AutoSearchParingRequest request) throws Exception {

        AutoSearchParingResponse response = new AutoSearchParingResponse();
        if (null == request.getCreatorId() || request.getCreatorId().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'creatorId'");
        }
        if (null == request.getGoogleRoutingResult() || request.getGoogleRoutingResult().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'googleRoutingResult'");
        }
        // create fake planned trip
        PlannedTrip fakePlannedTrip = new PlannedTrip();
        fakePlannedTrip.setRawRoutingResult(new JSONObject(request.getGoogleRoutingResult()));
        fakePlannedTrip.setRole(0);
        fakePlannedTrip.setCreatorId(request.getCreatorId());
        fakePlannedTrip.setCreatedTime(DateTimeUtil.now());
        long epochDay;
        if (request.getGoTime() > DateTimeUtil.now()) {
            fakePlannedTrip.setType(PlannedTrip.SINGLE_FUTURE);
            epochDay = request.getGoTime() / DateTimeUtil.SECONDS_PER_DAY;
            fakePlannedTrip.getTimeTable().put(epochDay, request.getGoTime());
        } else {
            fakePlannedTrip.setType(PlannedTrip.INSTANT);
            epochDay = DateTimeUtil.now() / DateTimeUtil.SECONDS_PER_DAY;
            fakePlannedTrip.getTimeTable().put(epochDay, DateTimeUtil.now());
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
            HashMap<Integer, List<PlannedTrip>> plannedTrips = new Pairing().pair(fakePlannedTrip, epochDay, fakeLocation);
            List<PlannedTrip> plannedTripList = new ArrayList<>();
            for (int key : plannedTrips.keySet()) {
                plannedTripList.addAll(plannedTrips.get(key));
            }
            sortPlannedTripByDistance(fakePlannedTrip.getStartLocation(), plannedTripList);
            List<UserAndPlannedTripDetailResponse> details = new ArrayList<>();
            for (PlannedTrip r : plannedTripList) {
                User creator = database.getUserHashMap().get(r.getCreatorId());
                if (null != creator) {
                    PlannedTripDetailResponse plannedTripDetailResponse = new PlannedTripDetailResponse(r, request.getCreatorId(), r.getRawRoutingResult());
                    UserAndPlannedTripDetailResponse userPlannedTripDetail = new UserAndPlannedTripDetailResponse(creator, plannedTripDetailResponse);
                    details.add(userPlannedTripDetail);
                }
            }
            response.setUsers(details.toArray(new UserAndPlannedTripDetailResponse[details.size()]));
        }
        return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully, response);
    }

    public String createInstantPlannedTrip(CreatePlannedTripRequest request) throws Exception {
        CreateSingleFuturePlannedTripResponse response = new CreateSingleFuturePlannedTripResponse();
        UserAndPlannedTripDetailResponse[] plannedTrips = null;
        if (null == request.getCreatorId() || request.getCreatorId().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'creatorId'");
        }
        if (null == request.getGoogleRoutingResult() || request.getGoogleRoutingResult().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'googleRoutingResult'");
        }
        PlannedTrip plannedTrip = new PlannedTrip();
        plannedTrip.setId(IdGenerator.getPlannedTripId());
        plannedTrip.setRole(request.getRole());
        long epochDay;
        plannedTrip.setType(PlannedTrip.INSTANT);
        epochDay = DateTimeUtil.now() / DateTimeUtil.SECONDS_PER_DAY;
        plannedTrip.getTimeTable().put(epochDay, DateTimeUtil.now());
        plannedTrip.setCreatorId(request.getCreatorId());
        plannedTrip.setRawRoutingResult(new JSONObject(request.getGoogleRoutingResult()));
        plannedTrip.setHasHelmet(request.isHasHelmet());
        plannedTrip.setCreatedTime(DateTimeUtil.now());
        //fetch data
        List<LinkedLocation> locations = new FetchingDataFromGoogleRouting().fetch(plannedTrip);
        //TODO fake trip
        if (Database.databaseStatus == Database.TESTING && !isCreatingFake) {
            isCreatingFake = true;
            new FakePlannedTrip().fakePlannedTrip(plannedTrip);
        }
        //TODO end fake

        // check price
        if (request.getPrice() < 0) {
            plannedTrip.setOwnerPrice(DEFAULT_PRICE);
        } else {
            plannedTrip.setOwnerPrice(request.getPrice() / plannedTrip.getSumDistance() != Double.POSITIVE_INFINITY
                    ? request.getPrice() / plannedTrip.getSumDistance() : PlannedTrip.DEFAULT_PRICE_1M);
        }
        if (dao.insert(plannedTrip) || Database.databaseStatus == Database.TESTING) {
            if (null != locations) {
                LinkedLocationController controller = new LinkedLocationController();
                for (LinkedLocation location : locations) {
                    location.setId(IdGenerator.getLinkedLocationId());
                    controller.insertLinkLocation(location, plannedTrip.getRole());
                }
            }
            UserAndPlannedTripDetailResponse yourPlannedTripDetail = getUserAndPlannedTripDetailFromObject(plannedTrip);
            if (request.isParing()) {
                HashMap<Integer, List<PlannedTrip>> paringResults = new Pairing().pair(plannedTrip, epochDay);
                List<PlannedTrip> result;
                if (plannedTrip.getRole() == User.PASSENGER) {
                    result = paringResults.get(User.DRIVER);
                } else {
                    result = paringResults.get(User.PASSENGER);
                }
                //TODO: Send requestMakeTrip to your partner
                for (PlannedTrip p : result) {
                    RequestMakeTripRequest rqmt = new RequestMakeTripRequest();
                    rqmt.setSenderId(request.getCreatorId());
                    rqmt.setReceiverId(p.getCreatorId());
                    rqmt.setSenderPlannedTripId(plannedTrip.getId());
                    rqmt.setReceiverPlannedTripId(p.getId());
                    if (plannedTrip.getRole() == User.PASSENGER)
                        rqmt.setPrice(p.getOwnerPrice() * plannedTrip.getSumDistance());
                    try {
                        new RequestMakeTripController().sendRequestMakeTrip(rqmt);
                    }catch (Exception ignored){}
                }
            }
            return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully,yourPlannedTripDetail);
        }
        return Parser.ObjectToJSon(false, MessageMappingUtil.Interactive_with_database_fail);
    }

    public String createSingleFuturePlannedTrip(CreatePlannedTripRequest request) throws Exception {
        CreateSingleFuturePlannedTripResponse response = new CreateSingleFuturePlannedTripResponse();
        UserAndPlannedTripDetailResponse[] plannedTrips = null;
        if (null == request.getCreatorId() || request.getCreatorId().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'creatorId'");
        }
        if (null == request.getGoogleRoutingResult() || request.getGoogleRoutingResult().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'googleRoutingResult'");
        }
        PlannedTrip plannedTrip = new PlannedTrip();
        plannedTrip.setId(IdGenerator.getPlannedTripId());
        plannedTrip.setRole(request.getRole());
        long epochDay;
        plannedTrip.setType(PlannedTrip.SINGLE_FUTURE);
        epochDay = request.getGoTime() / DateTimeUtil.SECONDS_PER_DAY;
        plannedTrip.getTimeTable().put(epochDay, request.getGoTime());
        plannedTrip.setCreatorId(request.getCreatorId());
        plannedTrip.setRawRoutingResult(new JSONObject(request.getGoogleRoutingResult()));
        plannedTrip.setHasHelmet(request.isHasHelmet());
        plannedTrip.setCreatedTime(DateTimeUtil.now());
        //fetch data
        List<LinkedLocation> locations = new FetchingDataFromGoogleRouting().fetch(plannedTrip);
        //TODO fake trip
        if (Database.databaseStatus == Database.TESTING && !isCreatingFake) {
            isCreatingFake = true;
            new FakePlannedTrip().fakePlannedTrip(plannedTrip);
        }
        //TODO end fake

        // check price
        if (request.getPrice() < 0) {
            plannedTrip.setOwnerPrice(DEFAULT_PRICE);
        } else {
            plannedTrip.setOwnerPrice(request.getPrice() / plannedTrip.getSumDistance() != Double.POSITIVE_INFINITY
                    ? request.getPrice() / plannedTrip.getSumDistance() : PlannedTrip.DEFAULT_PRICE_1M);
        }
        if (dao.insert(plannedTrip) || Database.databaseStatus == Database.TESTING) {
            if (null != locations) {
                LinkedLocationController controller = new LinkedLocationController();
                for (LinkedLocation location : locations) {
                    location.setId(IdGenerator.getLinkedLocationId());
                    controller.insertLinkLocation(location, plannedTrip.getRole());
                }
            }
            UserAndPlannedTripDetailResponse yourPlannedTripDetail = getUserAndPlannedTripDetailFromObject(plannedTrip);
            if (request.isParing()) {
                HashMap<Integer, List<PlannedTrip>> paringResults = new Pairing().pair(plannedTrip, epochDay);
                List<PlannedTrip> result;
                if (plannedTrip.getRole() == User.PASSENGER) {
                    result = paringResults.get(User.DRIVER);
                } else {
                    result = paringResults.get(User.PASSENGER);
                }
                plannedTrips = getListUserAndPlannedTripDetailFromObject(result, request.getCreatorId());
            }
            response.setYourPlannedTrip(yourPlannedTripDetail);
            if (plannedTrips == null) {
                plannedTrips = new UserAndPlannedTripDetailResponse[0];
            }
            response.setPairedPlannedTripsResult(plannedTrips);
            return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully, response);
        }
        return Parser.ObjectToJSon(false, MessageMappingUtil.Interactive_with_database_fail);
    }

    public String createRecurrentPlannedTrip(CreatePlannedTripRequest request) throws Exception {
        CreateRecurrentPlannedTripResponse response = new CreateRecurrentPlannedTripResponse();
        List<UserAndPlannedTripDetailByDayResponse> plannedTripsByDay = new ArrayList<>();
        if (null == request.getCreatorId() || request.getCreatorId().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'creatorId'");
        }
        if (null == request.getGoogleRoutingResult() || request.getGoogleRoutingResult().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'googleRoutingResult'");
        }
        long groupId = IdGenerator.getGroupPlannedTripId();

        for (TripPattern pattern : request.getPatterns()) {
            PlannedTrip plannedTrip = new PlannedTrip();
            plannedTrip.setId(IdGenerator.getPlannedTripId());
            plannedTrip.setRole(request.getRole());
            plannedTrip.setType(request.getTypeOfTrip());
            plannedTrip.setCreatorId(request.getCreatorId());
            plannedTrip.setRawRoutingResult(new JSONObject(request.getGoogleRoutingResult()));
            plannedTrip.setHasHelmet(request.isHasHelmet());
            plannedTrip.setCreatedTime(DateTimeUtil.now());
            plannedTrip.setGroupId(groupId);
            // process time pattern
            List<Long> timePattern = new DateTimeUtil().getTimePattern(pattern.getStartDay(), pattern.getTimeDistance(), pattern.getEndDay());
            for (long tp : timePattern) {
                long epochDay = tp / DateTimeUtil.SECONDS_PER_DAY;
                plannedTrip.getTimeTable().put(epochDay, tp);
            }
            // check price
            if (request.getPrice() < 0) {
                plannedTrip.setOwnerPrice(DEFAULT_PRICE);
            } else {
                plannedTrip.setOwnerPrice(request.getPrice() / plannedTrip.getSumDistance() != Double.POSITIVE_INFINITY
                        ? request.getPrice() / plannedTrip.getSumDistance() : PlannedTrip.DEFAULT_PRICE_1M);
            }
            //fetch data
            List<LinkedLocation> locations = new FetchingDataFromGoogleRouting().fetch(plannedTrip);
            if (dao.insert(plannedTrip) || Database.databaseStatus == Database.TESTING) {
                if (null != locations) {
                    LinkedLocationController controller = new LinkedLocationController();
                    for (LinkedLocation location : locations) {
                        location.setId(IdGenerator.getLinkedLocationId());
                        controller.insertLinkLocation(location, plannedTrip.getRole());
                    }
                }
                UserAndPlannedTripDetailResponse yourPlannedTripDetail = getUserAndPlannedTripDetailFromObject(plannedTrip);
                if (request.isParing()) {
                    long endP = new DateTimeUtil().now() / DateTimeUtil.SECONDS_PER_DAY + 7;
                    for (long epd : plannedTrip.getTimeTable().keySet()) {
                        UserAndPlannedTripDetailByDayResponse plannedTripDetailByDayResponse = new UserAndPlannedTripDetailByDayResponse();
                        if (epd > endP)
                            break;
                        UserAndPlannedTripDetailResponse[] plannedTrips = null;
                        HashMap<Integer, List<PlannedTrip>> paringResults = new Pairing().pair(plannedTrip, epd);
                        List<PlannedTrip> result;
                        if (plannedTrip.getRole() == User.PASSENGER) {
                            result = paringResults.get(User.DRIVER);
                        } else {
                            result = paringResults.get(User.PASSENGER);
                        }
                        plannedTrips = getListUserAndPlannedTripDetailFromObject(result, request.getCreatorId());
                        if (plannedTrips != null) {
                            plannedTripDetailByDayResponse.setEpochDay(epd);
                            plannedTripDetailByDayResponse.setPairedPlannedTripsResult(plannedTrips);
                            plannedTripsByDay.add(plannedTripDetailByDayResponse);
                        }
                    }
                }
                response.setYourPlannedTrip(yourPlannedTripDetail);
                response.setPairedPlannedTripsByDayResult(plannedTripsByDay.toArray(new UserAndPlannedTripDetailByDayResponse[plannedTripsByDay.size()]));
            } else {
                return Parser.ObjectToJSon(false, MessageMappingUtil.Interactive_with_database_fail);
            }
        }
        return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully, response);

    }

    public String getPlannedTripsAroundFromMe(GetUsersAroundFromMeRequest request) throws IOException {
        AutoSearchParingResponse response = new AutoSearchParingResponse();
        if (request.getCenterLat() == 0 && request.getCenterLng() == 0) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_invalid, "'lat' and 'lng'");
        }
        if (request.getRadius() < 0) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'radius'");
        }
        LatLng center = new LatLng(request.getCenterLat(), request.getCenterLng());
        List<Long> plannedTripsIds = database.getGeoCellStartLocation().getIdsInFrame(center, request.getRadius());
        List<PlannedTrip> plannedTrips = getPlannedTripsFromPlannedTripIds(plannedTripsIds);
        sortPlannedTripByDistance(center, plannedTrips);
        List<UserAndPlannedTripDetailResponse> details = new ArrayList<>();
        for (PlannedTrip r : plannedTrips) {
            User creator = database.getUserHashMap().get(r.getCreatorId());
            if (null != creator) {
                PlannedTripDetailResponse plannedTripDetailResponse = new PlannedTripDetailResponse(r, request.getUserId(), r.getRawRoutingResult());
                UserAndPlannedTripDetailResponse userPlannedTripDetail = new UserAndPlannedTripDetailResponse(creator, plannedTripDetailResponse);
                details.add(userPlannedTripDetail);
            }
        }
        response.setUsers(details.toArray(new UserAndPlannedTripDetailResponse[details.size()]));
        return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully, response);
    }


    /**
     * SUPPORT FUNCTION
     */

    public PlannedTripDetailResponse[] getListPlannedTripDetailResponse(List<Long> plannedTripIds, String senderId) throws IOException {
        PlannedTripDetailResponse[] plannedTripDetailResponses = new PlannedTripDetailResponse[plannedTripIds.size()];
        for (int i = 0; i < plannedTripIds.size(); i++) {
            long id = plannedTripIds.get(i);
            PlannedTrip r = database.getPlannedTripHashMap().get(id);
            if (null != r) {
                plannedTripDetailResponses[i] = getPlannedTripDetailResponse(r, senderId);
            }
        }
        return plannedTripDetailResponses;
    }


    public UserAndPlannedTripDetailResponse[] getListUserAndPlannedTripDetailFromObject(List<PlannedTrip> plannedTrips, String senderId) throws IOException {
        UserAndPlannedTripDetailResponse[] userAndPlannedTripDetailResponses = new UserAndPlannedTripDetailResponse[plannedTrips.size()];
        for (int i = 0; i < plannedTrips.size(); i++) {
            PlannedTrip r = plannedTrips.get(i);
            User creator = database.getUserHashMap().get(r.getCreatorId());
            if (null != creator) {
                PlannedTripDetailResponse plannedTripDetailResponse = new PlannedTripDetailResponse(r, senderId, r.getRawRoutingResult());
                UserAndPlannedTripDetailResponse userPlannedTripDetail = new UserAndPlannedTripDetailResponse(creator, plannedTripDetailResponse);
                userAndPlannedTripDetailResponses[i] = userPlannedTripDetail;
            }
        }
        return userAndPlannedTripDetailResponses;
    }

    public UserAndPlannedTripDetailResponse getUserAndPlannedTripDetailFromObject(PlannedTrip plannedTrip) throws IOException {
        User creator = database.getUserHashMap().get(plannedTrip.getCreatorId());
        PlannedTripDetailResponse plannedTripDetailResponse = new PlannedTripDetailResponse(plannedTrip, "this is my trip", plannedTrip.getRawRoutingResult());
        UserAndPlannedTripDetailResponse userPlannedTripDetail = new UserAndPlannedTripDetailResponse(creator, plannedTripDetailResponse);
        return userPlannedTripDetail;
    }

    public PlannedTripShortDetailResponse getPlannedTripSortDetail(PlannedTrip plannedTrip, String senderId) throws IOException {
        GoogleRoute googleRoute = new FetchingDataFromGoogleRouting().getRouteFromRoutingResult(plannedTrip);
        PlannedTripShortDetailResponse plannedTripSortDetailResponse = new PlannedTripShortDetailResponse();
        plannedTripSortDetailResponse.setId(plannedTrip.getId());
        plannedTripSortDetailResponse.setRole(plannedTrip.getRole());
        plannedTripSortDetailResponse.setUnitPrice(plannedTrip.getOwnerPrice());
        plannedTripSortDetailResponse.setHasHelmet(plannedTrip.isHasHelmet());
        plannedTripSortDetailResponse.setCreatedTime(plannedTrip.getCreatedTime());
        try {
            plannedTripSortDetailResponse.setRequested(database.getSenderRequestsBox().get(senderId).containsKey(plannedTrip.getId()));
        } catch (Exception ignored) {
            plannedTripSortDetailResponse.setRequested(false);
        }
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

    public PlannedTripDetailResponse getPlannedTripDetailResponse(PlannedTrip plannedTrip, String senderId) throws IOException {

        PlannedTripDetailResponse plannedTripDetailResponse = new PlannedTripDetailResponse(plannedTrip, senderId, plannedTrip.getRawRoutingResult());
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

    public List<PlannedTrip> getPlannedTripsFromPlannedTripIds(List<Long> ids) {

        List<PlannedTrip> plannedTrips = new ArrayList<>();
        if (ids != null) {
            for (long id : ids) {
                PlannedTrip plannedTrip = database.getPlannedTripHashMap().get(id);
                if (null != plannedTrip) {
                    plannedTrips.add(plannedTrip);
                }
            }
        }
        return plannedTrips;
    }
}
