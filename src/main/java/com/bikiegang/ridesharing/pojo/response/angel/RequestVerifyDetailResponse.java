package com.bikiegang.ridesharing.pojo.response.angel;

import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.pojo.RequestVerify;
import com.bikiegang.ridesharing.pojo.User;
import com.bikiegang.ridesharing.pojo.response.UserDetailWithCertificatesResponse;

/**
 * Created by hpduy17 on 7/31/15.
 */
public class RequestVerifyDetailResponse {
    private long id;
    private long createdTime;
    private int numberOfCertificate;
    private UserDetailWithCertificatesResponse userDetail = new UserDetailWithCertificatesResponse();

    public RequestVerifyDetailResponse(RequestVerify that) {
        this.id = that.getId();
        this.numberOfCertificate = that.getNumberOfCertificate();
        this.createdTime = that.getCreatedTime();
        User user = Database.getInstance().getUserHashMap().get(that.getUserId());
        if (user != null)
            this.userDetail = new UserDetailWithCertificatesResponse(user);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public int getNumberOfCertificate() {
        return numberOfCertificate;
    }

    public void setNumberOfCertificate(int numberOfCertificate) {
        this.numberOfCertificate = numberOfCertificate;
    }

    public UserDetailWithCertificatesResponse getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetailWithCertificatesResponse userDetail) {
        this.userDetail = userDetail;
    }
}
