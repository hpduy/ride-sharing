package com.bikiegang.ridesharing.pojo;

import com.bikiegang.ridesharing.utilities.DateTimeUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.json.JSONObject;

/**
 * Created by hpduy17 on 6/24/15.
 */
public class PlannedTrip implements PojoBase {

    private long id;
    private long goTime;
    private long arriveTime;
    private double sumDistance;
    private long estimatedTime; // duration from first location
    private double estimatedPrice; // use when role is passenger
    private double ownerPrice;
    private double estimatedFuel;
    private String creatorId = "";
    private int role; // user role
    private int type;
    private String plannedTripTrailPolyLine = "";
    private JSONObject rawRoutingResult = new JSONObject();
    private long groupId;
    /**
     * ROUTE TYPE
     */
    @JsonIgnore
    public static final int INSTANT = 0;
    @JsonIgnore
    public static final int SINGLE_FUTURE = 1;
    @JsonIgnore
    public static final int MULTIPLE_FUTURE = 2;
    /**
     * ESTIMATE PARAMETER
     */
    @JsonIgnore
    public static final double DEFAULT_FUEL_1KM = 1.0 / 50; // 1l -> 50km => 1km = 1/50l
    @JsonIgnore
    public static final long DEFAULT_VELOCITY = 30 * 1000 / DateTimeUtil.HOURS; // m/s ( default velocity in HCMC 30km/h)
    @JsonIgnore
    public static final double DEFAULT_PRICE_1KM = 3000; // 3000vnd / 1km
    @JsonIgnore
    public static final double DEFAULT_PRICE_1M = DEFAULT_PRICE_1KM/1000;

    public PlannedTrip() {
    }

    public PlannedTrip(long id, long goTime, long arriveTime, double sumDistance, int type, String creatorId, int role, double ownerPrice, String plannedTripTrailPolyLine, JSONObject rawRoutingResult, long groupId) {
        this.id = id;
        this.goTime = goTime;
        this.arriveTime = arriveTime;
        this.sumDistance = sumDistance;
        this.type = type;
        this.role = role;
        this.creatorId = creatorId == null ? "" : creatorId;
        this.ownerPrice = ownerPrice;
        this.plannedTripTrailPolyLine = plannedTripTrailPolyLine;
        this.rawRoutingResult = rawRoutingResult;
        this.groupId = groupId;
    }

    public PlannedTrip(PlannedTrip that) {
        this.id = that.id;
        this.goTime = that.goTime;
        this.arriveTime = that.arriveTime;
        this.sumDistance = that.sumDistance;
        this.estimatedTime = that.estimatedTime;
        this.estimatedPrice = that.estimatedPrice;
        this.estimatedFuel = that.estimatedFuel;
        this.creatorId = that.creatorId == null ? "" : that.creatorId;
        this.role = that.role;
        this.type = that.type;
        this.ownerPrice = that.ownerPrice;
        this.plannedTripTrailPolyLine = that.plannedTripTrailPolyLine;
        this.rawRoutingResult = that.rawRoutingResult;
        this.groupId = that.groupId;
    }


    public long getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(long arriveTime) {
        this.arriveTime = arriveTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getGoTime() {
        return goTime;
    }

    public void setGoTime(long goTime) {
        this.goTime = goTime;
    }

    public double getSumDistance() {
        return sumDistance;
    }

    public void setSumDistance(double sumDistance) {
        this.sumDistance = sumDistance;
    }

    public long getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(long estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public double getEstimatedPrice() {
        return estimatedPrice;
    }

    public void setEstimatedPrice(double estimatedPrice) {
        this.estimatedPrice = estimatedPrice;
    }

    public double getEstimatedFuel() {
        return estimatedFuel;
    }

    public void setEstimatedFuel(double estimatedFuel) {
        this.estimatedFuel = estimatedFuel;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public double getOwnerPrice() {
        return ownerPrice;
    }

    public void setOwnerPrice(double ownerPrice) {
        this.ownerPrice = ownerPrice;
    }

    public String getPlannedTripTrailPolyLine() {
        return plannedTripTrailPolyLine;
    }

    public void setPlannedTripTrailPolyLine(String plannedTripTrailPolyLine) {
        this.plannedTripTrailPolyLine = plannedTripTrailPolyLine;
    }

    public JSONObject getRawRoutingResult() {
        return rawRoutingResult;
    }

    public void setRawRoutingResult(JSONObject rawRoutingResult) {
        this.rawRoutingResult = rawRoutingResult;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }
}
