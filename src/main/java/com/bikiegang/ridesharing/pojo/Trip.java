package com.bikiegang.ridesharing.pojo;

/**
 * Created by hpduy17 on 6/24/15.
 */
public class Trip  implements PojoBase{
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

    public Trip() {
    }

    public Trip(long id, long startTime, String driverId, String passengerId) {
        this.id = id;
        this.startTime = startTime;
        this.driverId = driverId == null ? "" : driverId;
        this.passengerId = passengerId == null ? "" : passengerId;
    }

    public Trip(Trip that) {
        this.id = that.id;
        this.startTime = that.startTime;
        this.endTime = that.endTime;
        this.pricePaid = that.pricePaid;
        this.sensitiveLocationId = that.sensitiveLocationId;
        this.breakReason = that.breakReason == null ? "" : that.breakReason;
        this.breakTrip = that.breakTrip;
        this.dangerTrip = that.dangerTrip;
        this.driverId = that.driverId == null ? "" : that.driverId;
        this.passengerId = that.passengerId == null ? "" : that.passengerId;
        this.realDistance = that.realDistance;
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
}
