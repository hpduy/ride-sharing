package com.bikiegang.ridesharing.pojo;

import org.json.JSONObject;

/**
 * Created by hpduy17 on 8/26/15.
 */
public class Route implements PojoBase {

    private long id;
    private String rawRoutingResult = "";
    private double estimatedFuel;
    private long estimatedTime; // duration from first location
    private LatLng startLocation = new LatLng();
    private LatLng[] waypoints;
    private LatLng endLocation = new LatLng();
    private String overViewPolyLine = "";
    private long createdTime;
    private double sumDistance;
    private String creatorId = "";
    private String title = "";
    private int role;

    public Route() {
    }

    public Route(long id, String rawRoutingResult, double estimatedFuel, long estimatedTime, LatLng startLocation, LatLng[] waypoints, LatLng endLocation, String overViewPolyLine, long createdTime, double sumDistance, String creatorId, String title) {
        this.id = id;
        this.rawRoutingResult = rawRoutingResult;
        this.estimatedFuel = estimatedFuel;
        this.estimatedTime = estimatedTime;
        this.startLocation = startLocation;
        this.waypoints = waypoints;
        this.endLocation = endLocation;
        this.overViewPolyLine = overViewPolyLine;
        this.createdTime = createdTime;
        this.sumDistance = sumDistance;
        this.creatorId = creatorId;
        this.title = title;
    }

    public Route(Route that) {
        this.id = that.id;
        this.rawRoutingResult = that.rawRoutingResult;
        this.estimatedFuel = that.estimatedFuel;
        this.estimatedTime = that.estimatedTime;
        this.startLocation = that.startLocation;
        this.waypoints = that.waypoints;
        this.endLocation = that.endLocation;
        this.overViewPolyLine = that.overViewPolyLine;
        this.createdTime = that.createdTime;
        this.sumDistance = that.sumDistance;
        this.creatorId = that.creatorId;
        this.title = that.title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public JSONObject getRawRoutingResult() {
        return new JSONObject(this.rawRoutingResult);
    }

    public void setRawRoutingResult(String rawRoutingResult) {
        this.rawRoutingResult = rawRoutingResult;
    }

    public double getEstimatedFuel() {
        return estimatedFuel;
    }

    public void setEstimatedFuel(double estimatedFuel) {
        this.estimatedFuel = estimatedFuel;
    }

    public long getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(long estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public LatLng getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(LatLng startLocation) {
        this.startLocation = startLocation;
    }

    public LatLng[] getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(LatLng[] waypoints) {
        this.waypoints = waypoints;
    }

    public LatLng getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(LatLng endLocation) {
        this.endLocation = endLocation;
    }

    public String getOverViewPolyLine() {
        return overViewPolyLine;
    }

    public void setOverViewPolyLine(String overViewPolyLine) {
        this.overViewPolyLine = overViewPolyLine;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public double getSumDistance() {
        return sumDistance;
    }

    public void setSumDistance(double sumDistance) {
        this.sumDistance = sumDistance;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

}
