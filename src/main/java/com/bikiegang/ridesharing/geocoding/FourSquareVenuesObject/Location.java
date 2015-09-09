package com.bikiegang.ridesharing.geocoding.FourSquareVenuesObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by hpduy17 on 9/8/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {
    String address;
    String cc;
    String city;
    String country;
    long distance;
    List<String>formattedAddress;
    double lat;
    double lng;
    String postalCode;
    String state;
}
