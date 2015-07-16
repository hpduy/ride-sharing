package com.bikiegang.ridesharing.pojo.response;

/**
 * Created by hpduy17 on 7/8/15.
 */
public class RouteSortDetailResponse {
    private long id;
    private int numberOfRequest;
    private String startAddress;
    private String endAddress;
    private double unitPrice;
    private int role;

    public RouteSortDetailResponse() {
    }

    public RouteSortDetailResponse(RouteSortDetailResponse that) {
        this.id = that.id;
        this.numberOfRequest = that.numberOfRequest;
        this.startAddress = that.startAddress;
        this.endAddress = that.endAddress;
        this.unitPrice = that.unitPrice;
        this.role = that.role;
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

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
