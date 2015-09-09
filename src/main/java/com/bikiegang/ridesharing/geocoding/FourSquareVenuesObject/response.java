package com.bikiegang.ridesharing.geocoding.FourSquareVenuesObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by hpduy17 on 9/8/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response {
     List<Venue> venues;
}
