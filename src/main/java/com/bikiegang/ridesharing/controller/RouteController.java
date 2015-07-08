package com.bikiegang.ridesharing.controller;

import com.bikiegang.ridesharing.dao.RouteDao;
import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.database.IdGenerator;
import com.bikiegang.ridesharing.geocoding.Pairing;
import com.bikiegang.ridesharing.geocoding.TimezoneMapper;
import com.bikiegang.ridesharing.parsing.Parser;
import com.bikiegang.ridesharing.pojo.*;
import com.bikiegang.ridesharing.pojo.request.CreateRouteRequest;
import com.bikiegang.ridesharing.pojo.request.GetListRouteRequest;
import com.bikiegang.ridesharing.pojo.request.ParingRequest;
import com.bikiegang.ridesharing.pojo.response.ParingResponse;
import com.bikiegang.ridesharing.pojo.response.RouteInfoResponse;
import com.bikiegang.ridesharing.utilities.DateTimeUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.*;

/**
 * Created by hpduy17 on 6/29/15.
 */
public class RouteController {
    private RouteDao dao = new RouteDao();
    private Database database = Database.getInstance();

    public String getListRoute(GetListRouteRequest request) throws JsonProcessingException {
        if (null == request.getUserId() || request.getUserId().equals("")) {
            return Parser.ObjectToJSon(false, "'userId' is not found");
        }
        HashSet<Long> routeIds = database.getUserIdRFRoutes().get(request.getUserId());
        if (null == routeIds || routeIds.size() == 0) {
            return Parser.ObjectToJSon(true, "You do not have route", new ArrayList<>());
        }
        List<RouteInfoResponse> responses = new ArrayList<>();
        for (long rId : routeIds) {
            Route route = database.getRouteHashMap().get(rId);
            if (null != route) {
                RouteInfoResponse response = new RouteInfoResponse(route);
                responses.add(response);
            }
        }
        return Parser.ObjectToJSon(true, "Get list route success", responses);
    }


    public String createRoute(CreateRouteRequest request) throws Exception {
        if (null == request.getCreatorId() || request.getCreatorId().equals("")) {
            return Parser.ObjectToJSon(false, "'creatorId' is not found");
        }
        if (request.getLat() == 0 && request.getLng() == 0) {
            return Parser.ObjectToJSon(false, "Latitude and Longitude is invalid (0,0)");
        }
        if (request.getGoTime() <= 0 && request.getArriveTime() <= 0) {
            return Parser.ObjectToJSon(false, "Time is invalid (<= 0)");
        }
        if (request.getGoTime() > 0 && request.getArriveTime() > 0 && request.getArriveTime() < request.getGoTime()) {
            return Parser.ObjectToJSon(false, "Arrive time cannot less than go time");
        }
        if (request.getType() != Route.SINGLE_FUTURE && request.getType() != Route.INSTANT && request.getType() != Route.MULTIPLE_FUTURE) {
            return Parser.ObjectToJSon(false, "Type is invalid (type: INSTANT-0, SINGLE_FUTURE-1, MULTIPLE_FUTURE-2)");
        }
        if (null == request.getLocations() || request.getLocations().length < 2) {
            return Parser.ObjectToJSon(false, "Number of locations in route must more than 2");
        }

        //get user
        User user = database.getUserHashMap().get(request.getCreatorId());
        if (user == null) {
            return Parser.ObjectToJSon(false, "User is not exits");
        }
        if (user.getCurrentRole() != User.DRIVER && user.getCurrentRole() != User.PASSENGER) {
            return Parser.ObjectToJSon(false, "Role is invalid");
        }
        //TODO: check need to use TZ or Not
        String tzId = TimezoneMapper.latLngToTimezoneString(request.getLat(), request.getLng());
        TimeZone timeZone = TimeZone.getTimeZone(tzId);
        long goTime = DateTimeUtil.now();
        long arriveTime = 0;
        if (null != timeZone && request.getGoTime() != 0) {
            goTime = request.getGoTime();
            goTime += timeZone.getOffset(goTime);
            arriveTime = request.getArriveTime();
            arriveTime += timeZone.getOffset(arriveTime);
        }
        if (request.getType() != Route.MULTIPLE_FUTURE) {
            return insertRoute(request, goTime, arriveTime, user, request.getType());
        }
        // type = multiple future -> multiple insert
        if (request.getExpiredTime() < request.getGoTime()) {
            return Parser.ObjectToJSon(false, "Expired Time is invalid");
        }
        boolean success = false;
        String message = "";
        Object result = null;
        int firstTime = 1;
        while (goTime <= request.getExpiredTime()) {
            Parser parser = (Parser) Parser.JSonToObject(insertRoute(request, goTime, arriveTime, user, Route.SINGLE_FUTURE), Parser.class);
            success = success || parser.isSuccess();
            message = "Result create route in " + goTime + " : " + parser.getMessage() + "\n";
            if (firstTime == 1) {
                result = parser.getResult();
                firstTime++;
            }
            goTime += 24 * DateTimeUtil.HOURS;
        }
        return Parser.ObjectToJSon(success, message, result);
    }

    private String insertRoute(CreateRouteRequest request, long goTime, long arriveTime, User user, int type) throws Exception {
        // time process
        Route route = new Route();
        route.setId(IdGenerator.getRouteId());
        route.setCreatorId(request.getCreatorId());
        route.setGoTime(goTime);
        route.setArriveTime(arriveTime);
        route.setType(type);
        route.setRole(user.getCurrentRole());
        route.setOwnerPrice(request.getPrice());
        //insert route
        if (dao.insert(route)) {
            // estimate & location mapping
//            long estTime = route.getGoTime();
            long refId = route.getId();
            int refType = LinkedLocation.IN_ROUTE;
            int role = route.getRole();
            int index = 0;
            int prev = 0;
            LinkedLocationController controller = new LinkedLocationController();
            double sumDistance = 0;
            for (int i = 0; i < request.getLocations().length; i++) {
                Location location = request.getLocations()[i];
                //get distance from prev location
                double distance = location.distanceInMetres(request.getLocations()[prev]);
                //get time distance from the first stop ( t = S/v)
                long timeDistanceFromFirstStop = (long) ((sumDistance + distance) / Route.DEFAULT_VELOCITY);
                location.setCreatedTime(DateTimeUtil.now());
                location.setEstimatedTime(route.getGoTime() + timeDistanceFromFirstStop);
                Parser parser = (Parser) Parser.JSonToObject(controller.insertLinkLocation(location, i, refId, refType, role), Parser.class);
                if (parser.isSuccess()) {
                    index++;
                    sumDistance += distance;
                    prev = i;
                }
            }
            //estimate some fields
            route.setSumDistance(sumDistance);
            route.setEstimatedFuel(sumDistance * Route.DEFAULT_FUEL_1KM);
            route.setEstimatedTime((long) (sumDistance / Route.DEFAULT_VELOCITY));
            route.setEstimatedPrice(sumDistance * Route.DEFAULT_PRICE_1KM);
            //update route
            try {
                dao.update(route);
            } catch (Exception ignored) {
            }
            return paring(route);
        }
        return Parser.ObjectToJSon(false, "Creating route cannot complete");
    }

    public String paring(ParingRequest request) throws Exception {
        if (request.getRouteId() <= 0) {
            return Parser.ObjectToJSon(false, "'routeId' is invalid");
        }
        return paring(request.getRouteId());
    }

    public String paring(long routeId) throws Exception {
        Route route = database.getRouteHashMap().get(routeId);
        if (null == route)
            Parser.ObjectToJSon(false, "Route cannot found");
        return paring(route);
    }

    public String paring(Route route) throws Exception {
        List<ParingResponse> results = new ArrayList<>();
        HashMap<String, Long> paringResult = new HashMap<>();
        switch (route.getRole()) {
            case User.DRIVER:
                paringResult = new Pairing().getListPassengerCompatible(route);
                break;
            case User.PASSENGER:
                paringResult = new Pairing().getListDriverCompatible(route);
                break;
        }
        for (String userId : paringResult.keySet()) {
            User user = database.getUserHashMap().get(userId);
            Route userRoute = database.getRouteHashMap().get(paringResult.get(userId));
            if (user != null && userRoute != null) {
                CurrentLocation currentLocation = database.getCurrentLocationHashMap().get(user.getCurrentLocationId());
                List<Long> locationIds = database.getRouteIdRFLinkedLocations().get(userRoute.getId());
                if (currentLocation != null && locationIds != null) {
                    List<LatLng> locations = new ArrayList<>();
                    for (long id : locationIds) {
                        LinkedLocation location = database.getLinkedLocationHashMap().get(id);
                        if (location != null) {
                            locations.add(new LatLng(location));
                        }
                    }
                    results.add(new ParingResponse(user, userRoute, locations.toArray(new LatLng[locations.size()]), currentLocation));
                }
            }
        }
        return Parser.ObjectToJSon(true, "Paring successfully", results);
    }

}

