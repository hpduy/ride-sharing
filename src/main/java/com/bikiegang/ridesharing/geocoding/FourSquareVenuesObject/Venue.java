package com.bikiegang.ridesharing.geocoding.FourSquareVenuesObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by hpduy17 on 9/8/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Venue {
    boolean allowMenuUrlEdit;
    List<Category> categories;
    Contact contact;
    String id;
    Location location;
    String name;
    String referralId;
    Stats stats;
    String url;
    boolean verified;

}
