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
}
