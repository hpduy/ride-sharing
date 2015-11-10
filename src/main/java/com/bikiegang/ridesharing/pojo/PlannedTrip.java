package com.bikiegang.ridesharing.pojo;

import com.bikiegang.ridesharing.utilities.daytime.DateTimeUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by hpduy17 on 6/24/15.
 */
public class PlannedTrip implements PojoBase {
    private long id;
    private long arriveTime;
    private long departureTime;
    private double ownerPrice;
    private double estimatedPrice; // use when role is passenger
    private String creatorId = "";
    private int role; // user role
    private int type;
    private String plannedTripTrailPolyLine = "";
    private long timePatternId; // Day of week
    private boolean hasHelmet;
    private long requestId;
    private long routeId;
    private String note;
    private long eventId;
    /**
     * PLANNED TRIP TYPE
     */
    @JsonIgnore
    public static final int INSTANT = 0;
    @JsonIgnore
    public static final int SINGLE_FUTURE = 1;
    @JsonIgnore
    public static final int MULTIPLE_FUTURE = 2;
    @JsonIgnore
    public static final int REQUESTED_PLANNED_TRIP = 3;
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
    public static final double DEFAULT_PRICE_1M = DEFAULT_PRICE_1KM / 1000;

    public PlannedTrip() {
    }

    public PlannedTrip(long id, long arriveTime, int type, String creatorId, int role, double ownerPrice, String plannedTripTrailPolyLine, long timePatternId) {
        this.id = id;
        this.arriveTime = arriveTime;
        this.type = type;
        this.role = role;
        this.creatorId = creatorId == null ? "" : creatorId;
        this.ownerPrice = ownerPrice;
        this.plannedTripTrailPolyLine = plannedTripTrailPolyLine;
        this.timePatternId = timePatternId;
    }

    public PlannedTrip(PlannedTrip that) {
        this.id = that.id;
        this.arriveTime = that.arriveTime;
        this.departureTime = that.departureTime;
        this.ownerPrice = that.ownerPrice;
        this.estimatedPrice = that.estimatedPrice;
        this.creatorId = that.creatorId;
        this.role = that.role;
        this.type = that.type;
        this.plannedTripTrailPolyLine = that.plannedTripTrailPolyLine;
        this.timePatternId = that.timePatternId;
        this.hasHelmet = that.hasHelmet;
        this.requestId = that.requestId;
        this.routeId = that.routeId;
        this.note = that.note;
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


    public double getEstimatedPrice() {
        return estimatedPrice;
    }

    public void setEstimatedPrice(double estimatedPrice) {
        this.estimatedPrice = estimatedPrice;
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

    public long getGroupId() {
        return timePatternId;
    }

    public void setGroupId(long groupId) {
        this.timePatternId = groupId;
    }

    public boolean isHasHelmet() {
        return hasHelmet;
    }

    public void setHasHelmet(boolean hasHelmet) {
        this.hasHelmet = hasHelmet;
    }

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public long getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(long departureTime) {
        this.departureTime = departureTime;
    }

    public long getTimePatternId() {
        return timePatternId;
    }

    public void setTimePatternId(long timePatternId) {
        this.timePatternId = timePatternId;
    }

    public long getRouteId() {
        return routeId;
    }

    public void setRouteId(long routeId) {
        this.routeId = routeId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }
}