package com.bikiegang.ridesharing.controller;

import com.bikiegang.ridesharing.dao.RouteDao;
import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.database.IdGenerator;
import com.bikiegang.ridesharing.geocoding.FetchingDataFromGoogleRouting;
import com.bikiegang.ridesharing.geocoding.GoogleRoutingObject.GoogleRoute;
import com.bikiegang.ridesharing.geocoding.Pairing;
import com.bikiegang.ridesharing.parsing.Parser;
import com.bikiegang.ridesharing.pojo.LinkedLocation;
import com.bikiegang.ridesharing.pojo.Route;
import com.bikiegang.ridesharing.pojo.User;
import com.bikiegang.ridesharing.pojo.request.AutoSearchParingRequest;
import com.bikiegang.ridesharing.pojo.request.CreateRouteRequest;
import com.bikiegang.ridesharing.pojo.request.GetRouteDetailRequest;
import com.bikiegang.ridesharing.pojo.response.AutoSearchParingResponse;
import com.bikiegang.ridesharing.pojo.response.RouteDetailResponse;
import com.bikiegang.ridesharing.pojo.response.RouteSortDetailResponse;
import com.bikiegang.ridesharing.pojo.response.UserDetailWithRouteDetailResponse;
import com.bikiegang.ridesharing.utilities.DateTimeUtil;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by hpduy17 on 6/29/15.
 */
public class RouteController {
    private RouteDao dao = new RouteDao();
    private Database database = Database.getInstance();
    public static final double DEFAULT_PRICE = 3; //3000 vnd/km -> 3 vnd/m

    public RouteSortDetailResponse[] getListRouteSortDetail(List<Long> routeIds) throws IOException {
        RouteSortDetailResponse[] routeSortDetails = new RouteSortDetailResponse[routeIds.size()];
        for (int i = 0; i < routeIds.size(); i++) {
            long id = routeIds.get(i);
            Route r = database.getRouteHashMap().get(id);
            if (null != r) {
                routeSortDetails[i] = getRouteSortDetail(r);
            }
        }
        return routeSortDetails;
    }

    public RouteSortDetailResponse[] getListRouteSortDetailFromRoutes(List<Route> routes) throws IOException {
        RouteSortDetailResponse[] routeSortDetails = new RouteSortDetailResponse[routes.size()];
        for (int i = 0; i < routes.size(); i++) {
            routeSortDetails[i] = getRouteSortDetail(routes.get(i));
        }
        return routeSortDetails;
    }

    public RouteSortDetailResponse getRouteSortDetail(Route route) throws IOException {
        GoogleRoute googleRoute = new FetchingDataFromGoogleRouting().getRouteFromRoutingResult(route);
        RouteSortDetailResponse routeSortDetail = new RouteSortDetailResponse();
        routeSortDetail.setId(route.getId());
        routeSortDetail.setRole(route.getRole());
        routeSortDetail.setUnitPrice(route.getOwnerPrice());
        routeSortDetail.setStartAddress(googleRoute.getLegs()[0].getStart_address());
        routeSortDetail.setEndAddress(googleRoute.getLegs()[0].getEnd_address());
        int numberOfRequest = 0;
        try {
            numberOfRequest = database.getReceiverRequestsBox().get(route.getCreatorId()).get(route.getId()).size();
        } catch (Exception ignored) {

        }
        routeSortDetail.setNumberOfRequest(numberOfRequest);
        return routeSortDetail;
    }

    public String getRouteDetail(GetRouteDetailRequest request) throws IOException {
        if (request.getRouteId() <= 0) {
            return Parser.ObjectToJSon(false, "'routeId' is invalid");
        }
        Route route = database.getRouteHashMap().get(request.getRouteId());
        if (route == null) {
            return Parser.ObjectToJSon(false, "Route is not found");
        }
        RouteDetailResponse routeDetailResponse = new RouteDetailResponse(getRouteSortDetail(route), route.getRawRoutingResult().toString());
        return Parser.ObjectToJSon(true, "Get route detail successfully", routeDetailResponse);
    }

    public String autoSearchParing(AutoSearchParingRequest request) throws Exception {
        AutoSearchParingResponse response = new AutoSearchParingResponse();
        RouteSortDetailResponse[] route = null;
        if (null == request.getCreatorId() || request.getCreatorId().equals("")) {
            return Parser.ObjectToJSon(false, "'creatorId' is not found");
        }
        if (null == request.getGoogleRoutingResult() || request.getGoogleRoutingResult().equals("")) {
            return Parser.ObjectToJSon(false, "'googleRoutingResult' is invalid");
        }
        // create fake route
        Route fakeRoute = new Route();
        fakeRoute.setGoTime(DateTimeUtil.now());
        fakeRoute.setRawRoutingResult(new JSONObject(request.getGoogleRoutingResult()));
        fakeRoute.setRole(0);
        fakeRoute.setCreatorId(request.getCreatorId());
        fakeRoute.setType(Route.INSTANT);
        List<LinkedLocation> fakeLocation = new FetchingDataFromGoogleRouting().fetch(fakeRoute);
        if (fakeLocation != null) {
            HashMap<Integer, List<Route>> routes = new Pairing().pair(fakeRoute, fakeLocation);
            for (int key : routes.keySet()) {
                List<Route> routeList = routes.get(key);
                List<UserDetailWithRouteDetailResponse> details = new ArrayList<>();
                for (Route r : routeList) {
                    User creator = database.getUserHashMap().get(r.getCreatorId());
                    if (null != creator) {
                        RouteDetailResponse routeDetailResponse = new RouteDetailResponse(getRouteSortDetail(r), r.getRawRoutingResult().toString());
                        UserDetailWithRouteDetailResponse userRouteDetail = new UserDetailWithRouteDetailResponse(creator, routeDetailResponse);
                        details.add(userRouteDetail);
                    }
                }
                if (key == User.DRIVER) {
                    response.setDrivers(details.toArray(new UserDetailWithRouteDetailResponse[details.size()]));
                } else {
                    response.setPassenger(details.toArray(new UserDetailWithRouteDetailResponse[details.size()]));
                }
            }
        }
        return Parser.ObjectToJSon(true, "Paring successfully", response);
    }

    public String createRoute(CreateRouteRequest request) throws Exception {
        RouteSortDetailResponse[] routes = null;
        if (null == request.getCreatorId() || request.getCreatorId().equals("")) {
            return Parser.ObjectToJSon(false, "'creatorId' is not found");
        }
        if (null == request.getGoogleRoutingResult() || request.getGoogleRoutingResult().equals("")) {
            return Parser.ObjectToJSon(false, "'googleRoutingResult' is invalid");
        }
        Route route = new Route();
        route.setId(IdGenerator.getRouteId());
        route.setRole(request.getRole());
        if (request.getGoTime() > DateTimeUtil.now()) {
            route.setGoTime(request.getGoTime());
            route.setType(Route.SINGLE_FUTURE);
        } else {
            route.setGoTime(DateTimeUtil.now());
            route.setType(Route.INSTANT);
        }
        route.setCreatorId(request.getCreatorId());
        route.setRawRoutingResult(new JSONObject(request.getGoogleRoutingResult()));
        //fetch data
        List<LinkedLocation> locations = new FetchingDataFromGoogleRouting().fetch(route);
        if (null != locations) {
            LinkedLocationController controller = new LinkedLocationController();
            for (LinkedLocation location : locations) {
                controller.insertLinkLocation(location, route.getRole());
            }
        }
        // check price
        if (request.getPrice() < 0) {
            route.setOwnerPrice(DEFAULT_PRICE);
        } else {
            route.setOwnerPrice(request.getPrice() / route.getSumDistance());
        }
        if (dao.insert(route)) {
            HashMap<Integer, List<Route>> paringResults = new Pairing().pair(route);
            List<Route> result;
            if (route.getRole() == User.PASSENGER) {
                result = paringResults.get(User.DRIVER);
            } else {
                result = paringResults.get(User.PASSENGER);
            }
            routes = getListRouteSortDetailFromRoutes(result);
            return Parser.ObjectToJSon(true, "Create route successfully", routes);
        }
        return Parser.ObjectToJSon(true, "Cannot create route");
    }

}

