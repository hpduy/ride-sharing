package com.bikiegang.ridesharing.pojo.response;

import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.pojo.*;

import java.util.List;

/**
 * Created by hpduy17 on 7/8/15.
 */
public class TripInfoResponse extends Trip {
    private Location driverCurrentLocation;
    private LatLng[] tripLocations;

    public TripInfoResponse() {
    }

    public TripInfoResponse(Trip that) {
        super(that);
        // get current location of the driver and trip locations base on passenger trip
        // or locations both of them go through out
        Database database = Database.getInstance();
        User driver = database.getUserHashMap().get(that.getDriverId());
        CurrentLocation location = database.getCurrentLocationHashMap().get(driver.getCurrentLocationId());
        if(location == null){
            driverCurrentLocation = new Location();
        }
        driverCurrentLocation = new Location(location);
        // get list Trip location

        List<Long> locationIds = database.getTripIdRFLinkedLocations().get(that.getId());
        if(location != null){

        }else {

        }

    }
}
