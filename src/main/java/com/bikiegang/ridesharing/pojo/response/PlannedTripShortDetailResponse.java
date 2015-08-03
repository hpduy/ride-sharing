package com.bikiegang.ridesharing.pojo.response;

/**
 * Created by hpduy17 on 7/8/15.
 */
public class PlannedTripShortDetailResponse {
    private long id;
    private int numberOfRequest;
    private String startAddress;
    private String endAddress;
    private double unitPrice;
    private double ownerDistance;
    private int role;
    private boolean hasHelmet;
    private long createdTime;
    private boolean requested;

    public PlannedTripShortDetailResponse() {
    }

    public PlannedTripShortDetailResponse(PlannedTripShortDetailResponse that) {
        this.id = that.id;
        this.numberOfRequest = that.numberOfRequest;
        this.startAddress = that.startAddress;
        this.endAddress = that.endAddress;
        this.unitPrice = that.unitPrice;
        this.role = that.role;
        this.hasHelmet = that.hasHelmet;
        this.createdTime = that.createdTime;
        this.requested = that.requested;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumberOfRequest() {
        return numberOfRequest;
    }

    public void setNumberOfRequest(int numberOfRequest) {
        this.numberOfRequest = numberOfRequest;
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

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getOwnerDistance() {
        return ownerDistance;
    }

    public void setOwnerDistance(double ownerDistance) {
        this.ownerDistance = ownerDistance;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public boolean isHasHelmet() {
        return hasHelmet;
    }

    public void setHasHelmet(boolean hasHelmet) {
        this.hasHelmet = hasHelmet;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public boolean isRequested() {
        return requested;
    }

    public void setRequested(boolean requested) {
        this.requested = requested;
    }
}
