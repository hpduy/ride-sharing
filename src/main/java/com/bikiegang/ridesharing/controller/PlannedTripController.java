package com.bikiegang.ridesharing.controller;

import com.bikiegang.ridesharing.dao.PlannedTripDao;
import com.bikiegang.ridesharing.dao.RequestMakeTripDao;
import com.bikiegang.ridesharing.dao.RouteDao;
import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.database.IdGenerator;
import com.bikiegang.ridesharing.geocoding.FetchingDataFromGoogleRouting;
import com.bikiegang.ridesharing.geocoding.Pairing;
import com.bikiegang.ridesharing.parsing.Parser;
import com.bikiegang.ridesharing.pojo.*;
import com.bikiegang.ridesharing.pojo.request.*;
import com.bikiegang.ridesharing.pojo.response.*;
import com.bikiegang.ridesharing.pojo.static_object.TripPattern;
import com.bikiegang.ridesharing.utilities.BroadcastCenterUtil;
import com.bikiegang.ridesharing.utilities.MessageMappingUtil;
import com.bikiegang.ridesharing.utilities.daytime.DateTimeUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by hpduy17 on 6/29/15.
 */
public class PlannedTripController {

    private PlannedTripDao dao = new PlannedTripDao();
    private Database database = Database.getInstance();
    public static final double DEFAULT_PRICE = 2; //2000 vnd/km -> 2 vnd/m
    public static boolean isCreatingFake = false;

    /**
     * MAIN FUNCTION
     */

    public String getPlannedTripDetail(GetPlannedTripDetailRequest request) throws IOException {
        if (null == request.getUserId() || request.getUserId().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'userId'");
        }
        if (request.getPlannedTripId() <= 0) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_invalid, "'plannedTripId'");
        }
        PlannedTrip plannedTrip = database.getPlannedTripHashMap().get(request.getPlannedTripId());
        if (plannedTrip == null) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Object_is_not_found, "Planned Trip");
        }
        return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully, new FeedController().convertPlannedTripToFeed(plannedTrip, request.getUserId()));
    }

    public String autoSearchParing(AutoSearchParingRequest request) throws Exception {

        GetFeedsResponse response = new GetFeedsResponse();
        if (null == request.getCreatorId() || request.getCreatorId().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'creatorId'");
        }
        if (null == request.getGoogleRoutingResult() || request.getGoogleRoutingResult().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'googleRoutingResult'");
        }

        // create fake planned trip
        PlannedTrip fakePlannedTrip = new PlannedTrip();
        fakePlannedTrip.setRole(0);
        fakePlannedTrip.setCreatorId(request.getCreatorId());
        Route fakeRoute = new Route();
        fakeRoute.setRawRoutingResult(request.getGoogleRoutingResult());
        fakeRoute.setCreatedTime(DateTimeUtil.now());
        if (request.getGoTime() > DateTimeUtil.now()) {
            fakePlannedTrip.setType(PlannedTrip.SINGLE_FUTURE);
            fakePlannedTrip.setDepartureTime(request.getGoTime());
        } else {
            fakePlannedTrip.setType(PlannedTrip.INSTANT);
            fakePlannedTrip.setDepartureTime(request.getGoTime());
        }
        fakePlannedTrip.setHasHelmet(request.isHasHelmet());
        List<LinkedLocation> fakeLocation = new FetchingDataFromGoogleRouting().fetch(fakeRoute);
        if (fakeLocation != null) {
            HashMap<Integer, List<PlannedTrip>> plannedTrips = new Pairing().pair(fakePlannedTrip, fakeRoute, fakeLocation);
            List<PlannedTrip> plannedTripList = new ArrayList<>();
            for (int key : plannedTrips.keySet()) {
                plannedTripList.addAll(plannedTrips.get(key));
            }
            sortPlannedTripByDepartureTime(fakeRoute.getStartLocation(), plannedTripList);
            sortPlannedTripByDepartureTime(fakeRoute.getStartLocation(), plannedTripList);
            if (plannedTripList.size() > 100) {
                plannedTripList = plannedTripList.subList(0, 100);
            }
            response.setFeeds(new FeedController().convertPlannedTripsToFeeds(plannedTripList, fakePlannedTrip.getCreatorId()));
        }
        return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully, response);
    }

    public String createInstantPlannedTrip(CreatePlannedTripRequest request) throws Exception {
        if (null == request.getPlannedTrip().getCreatorId() || request.getPlannedTrip().getCreatorId().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'creatorId'");
        }
        if (null == request.getPlannedTrip().getGoogleRoutingResult() || request.getPlannedTrip().getGoogleRoutingResult().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'googleRoutingResult'");
        }
        if (!database.getUserHashMap().containsKey(request.getPlannedTrip().getCreatorId())) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Object_is_not_found, "'User'");
        }
        //create route
        Route route = new RouteController().createRoute(request.getPlannedTrip().getGoogleRoutingResult(),
                request.getPlannedTrip().getCreatorId(), request.getPlannedTrip().getRole(),
                request.getPlannedTrip().getTitle(), request.getPlannedTrip().getWaypoints(),
                request.getPlannedTrip().getStartAddress(), request.getPlannedTrip().getEndAddress());
        if (route != null) {
            PlannedTrip plannedTrip = new PlannedTrip();
            plannedTrip.setId(IdGenerator.getPlannedTripId());
            plannedTrip.setRole(request.getPlannedTrip().getRole());
            plannedTrip.setType(PlannedTrip.INSTANT);
            plannedTrip.setDepartureTime(DateTimeUtil.now());
            plannedTrip.setCreatorId(request.getPlannedTrip().getCreatorId());
            plannedTrip.setHasHelmet(request.getPlannedTrip().isHasHelmet());
            plannedTrip.setRouteId(route.getId());
            plannedTrip.setOwnerPrice(request.getPlannedTrip().getPrice() < 0 ? DEFAULT_PRICE : request.getPlannedTrip().getPrice());
            plannedTrip.setNote(request.getPlannedTrip().getNote());
            if (dao.insert(plannedTrip) || Database.databaseStatus == Database.TESTING) {
                UserAndPlannedTripDetailResponse yourPlannedTripDetail = getUserAndPlannedTripDetailFromObject(plannedTrip);
                // insert calendar
                new TripCalendarController().putToCalendar(plannedTrip.getDepartureTime(), plannedTrip.getId(), plannedTrip.getCreatorId());
                if (request.getPlannedTrip().isParing()) {
                    new FeedController().postNewFeed(plannedTrip.getId(), Feed.PLANNED_TRIP, plannedTrip.getDepartureTime());
                    HashMap<Integer, List<PlannedTrip>> paringResults = new Pairing().pair(plannedTrip);
                    List<PlannedTrip> result;
                    if (plannedTrip.getRole() == User.PASSENGER) {
                        result = paringResults.get(User.DRIVER);
                    } else {
                        result = paringResults.get(User.PASSENGER);
                    }
                    //TODO: Send requestMakeTrip to your partner
                    for (PlannedTrip p : result) {
                        RequestMakeTripRequest rqmt = new RequestMakeTripRequest();
                        rqmt.setSenderId(request.getPlannedTrip().getCreatorId());
                        rqmt.setReceiverId(p.getCreatorId());
                        rqmt.setSenderPlannedTripId(plannedTrip.getId());
                        rqmt.setReceiverPlannedTripId(p.getId());
                        if (plannedTrip.getRole() == User.PASSENGER)
                            rqmt.setPrice(p.getOwnerPrice() * route.getSumDistance());
                        try {
                            new RequestMakeTripController().sendRequestMakeTrip(rqmt);
                        } catch (Exception ignored) {
                        }
                    }
                }
                return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully, yourPlannedTripDetail);
            }
        }
        return Parser.ObjectToJSon(false, MessageMappingUtil.Interactive_with_database_fail);
    }

    public String createSingleFuturePlannedTrip(CreatePlannedTripRequest request) throws Exception {
        CreateSingleFuturePlannedTripResponse response = new CreateSingleFuturePlannedTripResponse();
        FeedResponse[] plannedTrips = null;
        if (null == request.getPlannedTrip().getCreatorId() || request.getPlannedTrip().getCreatorId().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'creatorId'");
        }
        if (null == request.getPlannedTrip().getGoogleRoutingResult() || request.getPlannedTrip().getGoogleRoutingResult().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'googleRoutingResult'");
        }
        if (!database.getUserHashMap().containsKey(request.getPlannedTrip().getCreatorId())) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Object_is_not_found, "'User'");
        }
        //create route
        Route route = new RouteController().createRoute(request.getPlannedTrip().getGoogleRoutingResult(),
                request.getPlannedTrip().getCreatorId(), request.getPlannedTrip().getRole(),
                request.getPlannedTrip().getTitle(), request.getPlannedTrip().getWaypoints(),
                request.getPlannedTrip().getStartAddress(), request.getPlannedTrip().getEndAddress());
        if (route != null) {
            PlannedTrip plannedTrip = new PlannedTrip();
            plannedTrip.setId(IdGenerator.getPlannedTripId());
            plannedTrip.setRole(request.getPlannedTrip().getRole());
            plannedTrip.setType(request.getPlannedTrip().isParing() ? PlannedTrip.SINGLE_FUTURE : PlannedTrip.REQUESTED_PLANNED_TRIP);
            plannedTrip.setDepartureTime(request.getPlannedTrip().getGoTime());
            plannedTrip.setCreatorId(request.getPlannedTrip().getCreatorId());
            plannedTrip.setHasHelmet(request.getPlannedTrip().isHasHelmet());
            plannedTrip.setRouteId(route.getId());
            plannedTrip.setNote(request.getPlannedTrip().getNote());
            plannedTrip.setOwnerPrice(request.getPlannedTrip().getPrice() < 0 ? DEFAULT_PRICE : request.getPlannedTrip().getPrice());
            if(database.getEventHashMap().containsKey(request.getPlannedTrip().getEventId())){
                plannedTrip.setEventId(request.getPlannedTrip().getEventId());
            }
            if (dao.insert(plannedTrip) || Database.databaseStatus == Database.TESTING) {
                // insert calendar
                new TripCalendarController().putToCalendar(plannedTrip.getDepartureTime(), plannedTrip.getId(), plannedTrip.getCreatorId());
                FeedResponse yourPlannedTripDetail = new FeedController().convertPlannedTripToFeed(plannedTrip, plannedTrip.getCreatorId());
                if (request.getPlannedTrip().isParing()) {
                    new FeedController().postNewFeed(plannedTrip.getId(), Feed.PLANNED_TRIP, plannedTrip.getDepartureTime());
                    HashMap<Integer, List<PlannedTrip>> paringResults = new Pairing().pair(plannedTrip);
                    List<PlannedTrip> result;
                    if (plannedTrip.getRole() == User.PASSENGER) {
                        result = paringResults.get(User.DRIVER);
                    } else {
                        result = paringResults.get(User.PASSENGER);
                    }
                    plannedTrips = new FeedController().convertPlannedTripsToFeeds(result, request.getPlannedTrip().getCreatorId());
                }
                response.setYourPlannedTrip(yourPlannedTripDetail);
                if (plannedTrips == null) {
                    plannedTrips = new FeedResponse[0];
                }
                response.setPairedPlannedTripsResult(plannedTrips);
                return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully, response);
            }
        }
        return Parser.ObjectToJSon(false, MessageMappingUtil.Interactive_with_database_fail);
    }

    public String createRecurrentPlannedTrip(CreatePlannedTripRequest request) throws Exception {
        CreateRecurrentPlannedTripResponse response = new CreateRecurrentPlannedTripResponse();
        List<UserAndPlannedTripDetailByDayResponse> plannedTripsByDay = new ArrayList<>();
        if (null == request.getPlannedTrip().getCreatorId() || request.getPlannedTrip().getCreatorId().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'creatorId'");
        }
        if (null == request.getPlannedTrip().getGoogleRoutingResult() || request.getPlannedTrip().getGoogleRoutingResult().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'googleRoutingResult'");
        }
        if (!database.getUserHashMap().containsKey(request.getPlannedTrip().getCreatorId())) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Object_is_not_found, "'User'");
        }
        long groupId = IdGenerator.getGroupPlannedTripId();
        Route route = new RouteController().createRoute(request.getPlannedTrip().getGoogleRoutingResult(),
                request.getPlannedTrip().getCreatorId(), request.getPlannedTrip().getRole(),
                request.getPlannedTrip().getTitle(), request.getPlannedTrip().getWaypoints(),
                request.getPlannedTrip().getStartAddress(), request.getPlannedTrip().getEndAddress());
        if (route != null) {
            for (TripPattern pattern : request.getPatterns()) {
                List<Long> timePattern = new DateTimeUtil().getTimePattern(pattern.getStartDay(), pattern.getDayOfWeek(), pattern.getEndDay());
                long endP = new DateTimeUtil().now() / DateTimeUtil.SECONDS_PER_DAY + 7; // just pair 7 days nearest
                for (long tp : timePattern) {
                    PlannedTrip plannedTrip = new PlannedTrip();
                    plannedTrip.setId(IdGenerator.getPlannedTripId());
                    plannedTrip.setRole(request.getPlannedTrip().getRole());
                    plannedTrip.setType(PlannedTrip.MULTIPLE_FUTURE);
                    plannedTrip.setCreatorId(request.getPlannedTrip().getCreatorId());
                    plannedTrip.setHasHelmet(request.getPlannedTrip().isHasHelmet());
                    plannedTrip.setGroupId(groupId);
                    plannedTrip.setDepartureTime(tp);
                    plannedTrip.setOwnerPrice(request.getPlannedTrip().getPrice() < 0 ? DEFAULT_PRICE : request.getPlannedTrip().getPrice());
                    plannedTrip.setRouteId(route.getId());
                    plannedTrip.setNote(request.getPlannedTrip().getNote());
                    if (dao.insert(plannedTrip)) {
                        // insert calendar
                        new TripCalendarController().putToCalendar(plannedTrip.getDepartureTime(), plannedTrip.getId(), plannedTrip.getCreatorId());
                        FeedResponse yourPlannedTripDetail = new FeedController().convertPlannedTripToFeed(plannedTrip, plannedTrip.getCreatorId());
                        if (request.getPlannedTrip().isParing()) {
                            new FeedController().postNewFeed(plannedTrip.getId(), Feed.PLANNED_TRIP, plannedTrip.getDepartureTime());
                            UserAndPlannedTripDetailByDayResponse plannedTripDetailByDayResponse = new UserAndPlannedTripDetailByDayResponse();
                            long epd = plannedTrip.getDepartureTime() / DateTimeUtil.SECONDS_PER_DAY;
                            if (epd < endP) {
                                HashMap<Integer, List<PlannedTrip>> paringResults = new Pairing().pair(plannedTrip);
                                List<PlannedTrip> result;
                                if (plannedTrip.getRole() == User.PASSENGER) {
                                    result = paringResults.get(User.DRIVER);
                                } else {
                                    result = paringResults.get(User.PASSENGER);
                                }
                                FeedResponse[] plannedTrips = new FeedController().convertPlannedTripsToFeeds(result, request.getPlannedTrip().getCreatorId());
                                if (plannedTrips != null) {
                                    plannedTripDetailByDayResponse.setEpochDay(epd);
                                    plannedTripDetailByDayResponse.setPairedPlannedTripsResult(plannedTrips);
                                    plannedTripDetailByDayResponse.setYourPlannedTrip(yourPlannedTripDetail);
                                    plannedTripsByDay.add(plannedTripDetailByDayResponse);
                                }
                            } else {//(epd - 1) * DateTimeUtil.SECONDS_PER_DAY
                                new FeedController().postNewFeed(plannedTrip.getId(), Feed.PLANNED_TRIP, plannedTrip.getDepartureTime()); // (epd - 1) * DateTimeUtil.SECONDS_PER_DAY : post feed truoc ngay di 1 ngay
                            }
                        }

                    } else {
                        return Parser.ObjectToJSon(false, MessageMappingUtil.Interactive_with_database_fail);
                    }
                }
            }
            response.setPairedPlannedTripsByDayResult(plannedTripsByDay.toArray(new UserAndPlannedTripDetailByDayResponse[plannedTripsByDay.size()]));
        }
        return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully, response);

    }

    public String getPlannedTripsAroundFromMe(GetUsersAroundFromMeRequest request) throws IOException {
        GetFeedsResponse response = new GetFeedsResponse();
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
        FeedResponse[] details = new FeedController().convertPlannedTripsToFeeds(plannedTrips, request.getUserId());
        response.setFeeds(details);
        return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully, response);
    }

    public String removePlannedTrip(GetPlannedTripDetailRequest request) throws JsonProcessingException {
        if (null == request.getUserId() || request.getUserId().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'userId'");
        }
        if (request.getPlannedTripId() <= 0) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_invalid, "'plannedTripId'");
        }
        PlannedTrip plannedTrip = database.getPlannedTripHashMap().get(request.getPlannedTripId());
        if (plannedTrip == null) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Object_is_not_found, "Planned Trip");
        }
        if (!plannedTrip.getCreatorId().equals(request.getUserId())) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Object_is_not_belong_to_you, "Planned Trip");
        }
        // remove planned trip
        long routeId = plannedTrip.getRouteId();
        long requestId = plannedTrip.getRequestId();
        if (dao.delete(plannedTrip)) {
            if (null == database.getRouteRFPlannedTripsByDay().get(routeId) || database.getRouteRFPlannedTripsByDay().get(routeId).isEmpty()) {
                Route route = database.getRouteHashMap().get(routeId);
                new RouteDao().delete(route);
            }
            // remove Request Make Trip
            if (database.getRequestMakeTripHashMap().containsKey(requestId)) {
                RequestMakeTrip requestMakeTrip = database.getRequestMakeTripHashMap().get(requestId);
                User user = database.getUserHashMap().get(request.getUserId());
                if (user != null) {
                    new BroadcastCenterUtil().pushNotification(Parser.ObjectToNotification(MessageMappingUtil.Notification_ReplyMakeTrip_Deny, user), requestMakeTrip.getSenderId());
                }
                long partnerPlannedTripId = requestMakeTrip.getSenderRole() == User.DRIVER ? requestMakeTrip.getDriverPlannedTripId() : requestMakeTrip.getPassengerPlannedTripId();
                if (new RequestMakeTripDao().delete(requestMakeTrip)) {
                    // update partner
                    PlannedTrip partnerPlannedTrip = database.getPlannedTripHashMap().get(partnerPlannedTripId);
                    if (partnerPlannedTrip != null) {
                        partnerPlannedTrip.setRequestId(0);
                        dao.update(partnerPlannedTrip);
                    }
                }

            }
            return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully);
        }
        return Parser.ObjectToJSon(false, MessageMappingUtil.Interactive_with_database_fail);
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
                PlannedTripDetailResponse plannedTripDetailResponse = new PlannedTripDetailResponse(r, senderId);
                UserAndPlannedTripDetailResponse userPlannedTripDetail = new UserAndPlannedTripDetailResponse(creator, plannedTripDetailResponse);
                userAndPlannedTripDetailResponses[i] = userPlannedTripDetail;
            }
        }
        return userAndPlannedTripDetailResponses;
    }

    public UserAndPlannedTripDetailResponse getUserAndPlannedTripDetailFromObject(PlannedTrip plannedTrip) throws IOException {
        User creator = database.getUserHashMap().get(plannedTrip.getCreatorId());
        PlannedTripDetailResponse plannedTripDetailResponse = new PlannedTripDetailResponse(plannedTrip, "this is my trip");
        UserAndPlannedTripDetailResponse userPlannedTripDetail = new UserAndPlannedTripDetailResponse(creator, plannedTripDetailResponse);
        return userPlannedTripDetail;
    }


    public PlannedTripDetailResponse getPlannedTripDetailResponse(PlannedTrip plannedTrip, String senderId) throws IOException {
        PlannedTripDetailResponse plannedTripDetailResponse = new PlannedTripDetailResponse(plannedTrip, senderId);
        return plannedTripDetailResponse;
    }

    private void sortPlannedTripByDistance(final LatLng origin, List<PlannedTrip> plannedTrips) {
        Collections.sort(plannedTrips, new Comparator<PlannedTrip>() {
            @Override
            public int compare(PlannedTrip o1, PlannedTrip o2) {
                Route r1 = database.getRouteHashMap().get(o1.getRouteId());
                Route r2 = database.getRouteHashMap().get(o2.getRouteId());
                double dis1 = origin.distanceInMetres(r1.getStartLocation());
                double dis2 = origin.distanceInMetres(r2.getStartLocation());
                if (dis1 > dis2)
                    return -1;
                return 0;
            }
        });
    }

    private void sortPlannedTripByDepartureTime(final LatLng origin, List<PlannedTrip> plannedTrips) {
        Collections.sort(plannedTrips, new Comparator<PlannedTrip>() {
            @Override
            public int compare(PlannedTrip o1, PlannedTrip o2) {
                if (o1.getDepartureTime() > o2.getDepartureTime())
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

    public String paringPlannedTrip(GetPlannedTripDetailRequest request) throws Exception {
        CreateSingleFuturePlannedTripResponse response = new CreateSingleFuturePlannedTripResponse();
        FeedResponse[] plannedTrips = null;
        PlannedTrip plannedTrip = database.getPlannedTripHashMap().get(request.getPlannedTripId());
        if (null != plannedTrip) {
            FeedResponse yourPlannedTripDetail = new FeedController().convertPlannedTripToFeed(plannedTrip, request.getUserId());
            HashMap<Integer, List<PlannedTrip>> paringResults = new Pairing().pair(plannedTrip);
            List<PlannedTrip> result;
            if (plannedTrip.getRole() == User.PASSENGER) {
                result = paringResults.get(User.DRIVER);
            } else {
                result = paringResults.get(User.PASSENGER);
            }
            plannedTrips = new FeedController().convertPlannedTripsToFeeds(result, request.getUserId());

            response.setYourPlannedTrip(yourPlannedTripDetail);
        }
        if (plannedTrips == null) {
            plannedTrips = new FeedResponse[0];
        }
        response.setPairedPlannedTripsResult(plannedTrips);
        return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully, response);
    }

    public boolean updateRouteOfPlannedTrip(long plannedTripId, String googleRoutingResult, String startAddress, String endAddress) {
        try {
            PlannedTrip plannedTrip = database.getPlannedTripHashMap().get(plannedTripId);
            if (plannedTrip == null)
                return false;
            Route oldRoute = database.getRouteHashMap().get(plannedTrip.getRouteId());
            if (oldRoute == null)
                return false;
            Route route = new RouteController().createRoute(googleRoutingResult,
                    oldRoute.getCreatorId(), oldRoute.getRole(),
                    oldRoute.getTitle(), oldRoute.getWaypoints(),
                    startAddress, endAddress);
            plannedTrip.setRouteId(route.getId());
            if (dao.update(plannedTrip)) {
                return new RouteDao().delete(oldRoute);
            }
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public String repeatPlannedTrip(RepeatPlannedTripRequest request) throws JsonProcessingException {
        if (null == request.getUserId() || request.getUserId().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'userId'");
        }
        if (request.getPlannedTripId() <= 0) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_invalid, "'plannedTripId'");
        }
        PlannedTrip plannedTrip = database.getPlannedTripHashMap().get(request.getPlannedTripId());
        if (plannedTrip == null) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Object_is_not_found, "Planned Trip");
        }
        if (!plannedTrip.getCreatorId().equals(request.getUserId())) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Object_is_not_belong_to_you, "Planned Trip");
        }
        // repeat planned trip
        String mess = "";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
        for (long ept : request.getRepeatTimes()) {
            PlannedTrip pt = new PlannedTrip(plannedTrip);
            pt.setId(IdGenerator.getPlannedTripId());
            pt.setDepartureTime(ept);
            if (dao.insert(pt)) {
                new TripCalendarController().putToCalendar(pt.getDepartureTime(), pt.getId(), pt.getCreatorId());
                new FeedController().postNewFeed(pt.getId(), Feed.PLANNED_TRIP, pt.getDepartureTime());
            } else {
                mess += "Cannot repeat planned trip at" + simpleDateFormat.format(new Date(ept)) + "\n";
            }
        }
        if (mess.equals(""))
            return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully);
        return Parser.ObjectToJSon(true, MessageMappingUtil.Fail, mess);

    }
}
