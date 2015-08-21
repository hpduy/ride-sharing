package com.bikiegang.ridesharing.pojo.response.angel;

import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.pojo.RequestVerify;
import com.bikiegang.ridesharing.pojo.User;
import com.bikiegang.ridesharing.pojo.response.UserShortDetailResponse;

/**
 * Created by hpduy17 on 7/31/15.
 */
public class RequestVerifySortDetailResponse {
    private long id;
    private long createdTime;
    private int numberOfCertificate;
    private UserShortDetailResponse userDetail = new UserShortDetailResponse();
    public RequestVerifySortDetailResponse(RequestVerify that) {
        this.id = that.getId();
        this.createdTime = that.getCreatedTime();
        this.numberOfCertificate = that.getNumberOfCertificate();
        User user = Database.getInstance().getUserHashMap().get(that.getUserId());
        if (user != null)
            this.userDetail = new UserShortDetailResponse(user);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumberOfCertificate() {
        return numberOfCertificate;
    }

    public void setNumberOfCertificate(int numberOfCertificate) {
        this.numberOfCertificate = numberOfCertificate;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public UserShortDetailResponse getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserShortDetailResponse userDetail) {
        this.userDetail = userDetail;
    }
}
