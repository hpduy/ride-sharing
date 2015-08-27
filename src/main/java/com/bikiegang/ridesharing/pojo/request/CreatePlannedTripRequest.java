package com.bikiegang.ridesharing.pojo.request;

import com.bikiegang.ridesharing.pojo.LatLng;
import com.bikiegang.ridesharing.annn.framework.util.JSONUtil;
import com.bikiegang.ridesharing.pojo.static_object.TripPattern;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 7/15/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreatePlannedTripRequest {

    private String creatorId;
    private int role;
    private long goTime;
    private double price = -1;
    private String googleRoutingResult;
    private boolean hasHelmet;
    private boolean isParing = true;
    private int typeOfTrip;
    private String title;
    private TripPattern[] patterns;
    private LatLng[] waypoints;

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public long getGoTime() {
        return goTime;
    }

    public void setGoTime(long goTime) {
        this.goTime = goTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getGoogleRoutingResult() {
        return googleRoutingResult;
    }

    public void setGoogleRoutingResult(String googleRoutingResult) {
        this.googleRoutingResult = googleRoutingResult;
    }

    public boolean isParing() {
        return isParing;
    }

    public void setIsParing(boolean isParing) {
        this.isParing = isParing;
    }

    public boolean isHasHelmet() {
        return hasHelmet;
    }

    public void setHasHelmet(boolean hasHelmet) {
        this.hasHelmet = hasHelmet;
    }

    public int getTypeOfTrip() {
        return typeOfTrip;
    }

    public void setTypeOfTrip(int typeOfTrip) {
        this.typeOfTrip = typeOfTrip;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TripPattern[] getPatterns() {
        return patterns;
    }

    public void setPatterns(TripPattern[] patterns) {
        this.patterns = patterns;
    }

    public LatLng[] getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(LatLng[] waypoints) {
        this.waypoints = waypoints;
    }

    public CreatePlannedTripRequest() {
    }

    public CreatePlannedTripRequest(String creatorId, int role, long goTime, String googleRoutingResult, boolean hasHelmet, int typeOfTrip, String title, TripPattern[] patterns, LatLng[] waypoints) {
        this.creatorId = creatorId;
        this.role = role;
        this.goTime = goTime;
        this.googleRoutingResult = googleRoutingResult;
        this.hasHelmet = hasHelmet;
        this.typeOfTrip = typeOfTrip;
        this.title = title;
        this.patterns = patterns;
        this.waypoints = waypoints;
    }

}
