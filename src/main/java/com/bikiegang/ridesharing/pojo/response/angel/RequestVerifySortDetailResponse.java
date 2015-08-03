package com.bikiegang.ridesharing.pojo.response.angel;

import com.bikiegang.ridesharing.pojo.RequestVerify;

/**
 * Created by hpduy17 on 7/31/15.
 */
public class RequestVerifySortDetailResponse {
    private long id;
    private String userId = "";
    private long requestedTime;
    private int numberOfCertificate;

    public RequestVerifySortDetailResponse(RequestVerify that) {
        this.id = that.getId();
        this.userId = that.getUserId();
        this.numberOfCertificate = that.getNumberOfCertificate();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getNumberOfCertificate() {
        return numberOfCertificate;
    }

    public void setNumberOfCertificate(int numberOfCertificate) {
        this.numberOfCertificate = numberOfCertificate;
    }

    public long getRequestedTime() {
        return requestedTime;
    }

    public void setRequestedTime(long requestedTime) {
        this.requestedTime = requestedTime;
    }
}
