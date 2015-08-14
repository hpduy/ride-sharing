package com.bikiegang.ridesharing.pojo;

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
    private double pricePaid;
    private long sensitiveLocationId; // break or danger location
    private String breakReason = "";
    private boolean breakTrip;
    private boolean dangerTrip;
    private long driverPlannedTripId;
    private long passengerPlannedTripId;
    private String tripTrailPolyLine = "";

    public Trip() {
    }

    public Trip(long id, long startTime, long endTime, String driverId, String passengerId, double realDistance, double pricePaid, long sensitiveLocationId, String breakReason, boolean breakTrip, boolean dangerTrip, long driverPlannedTripId, long passengerPlannedTripId, String tripTrailPolyLine) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.driverId = driverId;
        this.passengerId = passengerId;
        this.realDistance = realDistance;
        this.pricePaid = pricePaid;
        this.sensitiveLocationId = sensitiveLocationId;
        this.breakReason = breakReason;
        this.breakTrip = breakTrip;
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
        this.pricePaid = that.pricePaid;
        this.sensitiveLocationId = that.sensitiveLocationId;
        this.breakReason = that.breakReason;
        this.breakTrip = that.breakTrip;
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

    public double getPricePaid() {
        return pricePaid;
    }

    public void setPricePaid(double pricePaid) {
        this.pricePaid = pricePaid;
    }

    public long getSensitiveLocationId() {
        return sensitiveLocationId;
    }

    public void setSensitiveLocationId(long sensitiveLocationId) {
        this.sensitiveLocationId = sensitiveLocationId;
    }

    public String getBreakReason() {
        return breakReason;
    }

    public void setBreakReason(String breakReason) {
        this.breakReason = breakReason;
    }

    public boolean isBreakTrip() {
        return breakTrip;
    }

    public void setBreakTrip(boolean breakTrip) {
        this.breakTrip = breakTrip;
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
}
