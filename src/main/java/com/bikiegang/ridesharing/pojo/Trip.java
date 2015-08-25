package com.bikiegang.ridesharing.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by hpduy17 on 6/24/15.
 */
public class Trip implements PojoBase {

    private long id;
    private long startTime;
    private String driverId = "";
    private String passengerId = "";
    private double realDistance;
    private long endTime;
    private double farePaid;
    private LatLng sensitiveLocationId = new LatLng(); // break or danger location
    private String breakReason = "";
    private boolean smoothBreak;
    private boolean dangerTrip;
    private long driverPlannedTripId;
    private long passengerPlannedTripId;
    private String tripTrailPolyLine = "";
    private int tripStatus;
    @JsonIgnore
    public static final int UNFINISHED_TRIP = 0;
    @JsonIgnore
    public static final int FINISHED_TRIP_WITH_OUT_RATING = 1;
    @JsonIgnore
    public static final int COMPLETED_TRIP = 2;


    public Trip() {
    }

    public Trip(long id, long startTime, long endTime, String driverId, String passengerId, double realDistance, double farePaid, LatLng sensitiveLocationId, String breakReason, boolean smoothBreak, boolean dangerTrip, long driverPlannedTripId, long passengerPlannedTripId, String tripTrailPolyLine) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.driverId = driverId;
        this.passengerId = passengerId;
        this.realDistance = realDistance;
        this.farePaid = farePaid;
        this.sensitiveLocationId = sensitiveLocationId;
        this.breakReason = breakReason;
        this.smoothBreak = smoothBreak;
        this.dangerTrip = dangerTrip;
        this.driverPlannedTripId = driverPlannedTripId;
        this.passengerPlannedTripId = passengerPlannedTripId;
        this.tripTrailPolyLine = tripTrailPolyLine;
    }

    public Trip(Trip that) {
        this.id = that.id;
        this.startTime = that.startTime;
        this.endTime = that.endTime;
        this.driverId = that.driverId;
        this.passengerId = that.passengerId;
        this.realDistance = that.realDistance;
        this.farePaid = that.farePaid;
        this.sensitiveLocationId = that.sensitiveLocationId;
        this.breakReason = that.breakReason;
        this.smoothBreak = that.smoothBreak;
        this.dangerTrip = that.dangerTrip;
        this.driverPlannedTripId = that.driverPlannedTripId;
        this.passengerPlannedTripId = that.passengerPlannedTripId;
        this.tripTrailPolyLine = that.tripTrailPolyLine;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(String passengerId) {
        this.passengerId = passengerId;
    }

    public double getRealDistance() {
        return realDistance;
    }

    public void setRealDistance(double realDistance) {
        this.realDistance = realDistance;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public double getFarePaid() {
        return farePaid;
    }

    public void setFarePaid(double farePaid) {
        this.farePaid = farePaid;
    }

    public LatLng getSensitiveLocationId() {
        return sensitiveLocationId;
    }

    public void setSensitiveLocationId(LatLng sensitiveLocationId) {
        this.sensitiveLocationId = sensitiveLocationId;
    }

    public String getBreakReason() {
        return breakReason;
    }

    public void setBreakReason(String breakReason) {
        this.breakReason = breakReason;
    }

    public boolean isSmoothBreak() {
        return smoothBreak;
    }

    public void setSmoothBreak(boolean smoothBreak) {
        this.smoothBreak = smoothBreak;
    }

    public boolean isDangerTrip() {
        return dangerTrip;
    }

    public void setDangerTrip(boolean dangerTrip) {
        this.dangerTrip = dangerTrip;
    }

    public long getDriverPlannedTripId() {
        return driverPlannedTripId;
    }

    public void setDriverPlannedTripId(long driverPlannedTripId) {
        this.driverPlannedTripId = driverPlannedTripId;
    }

    public long getPassengerPlannedTripId() {
        return passengerPlannedTripId;
    }

    public void setPassengerPlannedTripId(long passengerPlannedTripId) {
        this.passengerPlannedTripId = passengerPlannedTripId;
    }

    public String getTripTrailPolyLine() {
        return tripTrailPolyLine;
    }

    public void setTripTrailPolyLine(String tripTrailPolyLine) {
        this.tripTrailPolyLine = tripTrailPolyLine;
    }

    public int getTripStatus() {
        return tripStatus;
    }

    public void setTripStatus(int tripStatus) {
        this.tripStatus = tripStatus;
    }
}
