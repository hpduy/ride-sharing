package com.bikiegang.ridesharing.pojo.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 8/20/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateSocialTripRequest {
    private String userId;
    private double lat;
    private double lng;
    private String feeling;
    private int feelingIcon;
    private String wantToGo;
    private int wantToGoIcon;
    private String content = "";

    public String getUserId() {
        return userId;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public String getFeeling() {
        return feeling;
    }

    public int getFeelingIcon() {
        return feelingIcon;
    }

    public String getWantToGo() {
        return wantToGo;
    }

    public int getWantToGoIcon() {
        return wantToGoIcon;
    }

    public String getContent() {
        return content;
    }
}
