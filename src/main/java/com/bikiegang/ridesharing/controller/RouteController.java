package com.bikiegang.ridesharing.controller;

import com.bikiegang.ridesharing.dao.RouteDao;
import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.database.IdGenerator;
import com.bikiegang.ridesharing.geocoding.FetchingDataFromGoogleRouting;
import com.bikiegang.ridesharing.pojo.LatLng;
import com.bikiegang.ridesharing.pojo.LinkedLocation;
import com.bikiegang.ridesharing.pojo.Route;
import com.bikiegang.ridesharing.utilities.daytime.DateTimeUtil;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by hpduy17 on 8/26/15.
 */
public class RouteController {
    private RouteDao dao = new RouteDao();
    private Database database = Database.getInstance();

    public Route createRoute(String googleRoutingResult, String creatorId, int role, String title, LatLng[] waypoint){
        try{
            Route route = new Route();
            route.setId(IdGenerator.getRouteId());
            route.setRawRoutingResult(new JSONObject(googleRoutingResult));
            route.setCreatedTime(DateTimeUtil.now());
            route.setCreatorId(creatorId);
            route.setTitle(title);
            route.setWaypoints(waypoint);
            route.setRole(role);
            //fetch data
            if(dao.insert(route)) {
                List<LinkedLocation> locations = new FetchingDataFromGoogleRouting().fetch(route);
                if (null != locations) {
                    LinkedLocationController controller = new LinkedLocationController();
                    for (LinkedLocation location : locations) {
                        location.setId(IdGenerator.getLinkedLocationId());
                        controller.insertLinkLocation(location, role);
                    }
                }
                return route;
            }
            return null;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

}
