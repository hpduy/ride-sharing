package com.bikiegang.ridesharing.pojo.response;

import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.pojo.RequestMakeTrip;
import com.bikiegang.ridesharing.pojo.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 7/31/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestMakeTripDetailResponse {
    private long requestId;
    private long createdTime;
    private UserDetailResponse userDetail = new UserDetailResponse();

    public RequestMakeTripDetailResponse(RequestMakeTrip that) {
        this.requestId = that.getId();
        this.createdTime = that.getCreatedTime();
        User user = Database.getInstance().getUserHashMap().get(that.getSenderId());
        if (user != null)
            this.userDetail = new UserDetailResponse(user);
    }

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public UserDetailResponse getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetailResponse userDetail) {
        this.userDetail = userDetail;
    }
}
