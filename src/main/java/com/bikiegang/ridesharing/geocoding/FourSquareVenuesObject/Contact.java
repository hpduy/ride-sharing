package com.bikiegang.ridesharing.geocoding.FourSquareVenuesObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 9/8/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Contact {
    String formattedPhone;
    String phone;
}
