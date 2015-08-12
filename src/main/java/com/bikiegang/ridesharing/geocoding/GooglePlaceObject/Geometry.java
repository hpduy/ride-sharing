package com.bikiegang.ridesharing.geocoding.GooglePlaceObject;

import com.bikiegang.ridesharing.pojo.LatLng;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 8/11/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Geometry {
    private LatLng location;

    public LatLng getLocation() {
        return location;
    }

    public void setLocation(LatLng location) {
        this.location = location;
    }
}
