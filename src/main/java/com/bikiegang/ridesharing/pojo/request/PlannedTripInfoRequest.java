package com.bikiegang.ridesharing.pojo.request;

import com.bikiegang.ridesharing.pojo.LatLng;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 8/27/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlannedTripInfoRequest {
    private String creatorId;
    private int role;
    private long goTime;
    private double price = -1;
    private String googleRoutingResult;
    private boolean hasHelmet;
    @JsonIgnore
    private boolean isParing = true;
    private int typeOfTrip;
    private String title;
    private LatLng[] waypoints;
    private String note;
    private String startAddress;
    private String endAddress;

    public PlannedTripInfoRequest() {
    }

    public PlannedTripInfoRequest(String creatorId, int role, long goTime, double price, String googleRoutingResult, boolean hasHelmet, boolean isParing, int typeOfTrip, String title, LatLng[] waypoints) {
        this.creatorId = creatorId;
        this.role = role;
        this.goTime = goTime;
        this.price = price;
        this.googleRoutingResult = googleRoutingResult;
        this.hasHelmet = hasHelmet;
        this.isParing = isParing;
        this.typeOfTrip = typeOfTrip;
        this.title = title;
        this.waypoints = waypoints;
    }

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

    public boolean isHasHelmet() {
        return hasHelmet;
    }

    public void setHasHelmet(boolean hasHelmet) {
        this.hasHelmet = hasHelmet;
    }

    public boolean isParing() {
        return isParing;
    }

    public void setIsParing(boolean isParing) {
        this.isParing = isParing;
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

    public LatLng[] getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(LatLng[] waypoints) {
        this.waypoints = waypoints;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(String startAddress) {
        this.startAddress = startAddress;
    }

    public String getEndAddress() {
        return endAddress;
    }

    public void setEndAddress(String endAddress) {
        this.endAddress = endAddress;
    }
}
