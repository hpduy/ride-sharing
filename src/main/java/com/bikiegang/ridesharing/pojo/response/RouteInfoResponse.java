package com.bikiegang.ridesharing.pojo.response;

import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.pojo.LatLng;
import com.bikiegang.ridesharing.pojo.Route;

import java.util.List;

/**
 * Created by hpduy17 on 6/30/15.
 */
public class RouteInfoResponse extends Route {
    private LatLng[] locations;

    public RouteInfoResponse() {
    }

    public RouteInfoResponse(Route that) {
        super(that);
        // get list location
        List<Long> locationId = Database.getInstance().getRouteIdRFLinkedLocations().get(that.getId());
        if(locationId != null){

        }
    }
}
