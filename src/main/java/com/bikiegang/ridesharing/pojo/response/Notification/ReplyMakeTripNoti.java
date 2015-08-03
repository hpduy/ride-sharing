package com.bikiegang.ridesharing.pojo.response.Notification;

import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.pojo.RequestMakeTrip;
import com.bikiegang.ridesharing.pojo.User;

/**
 * Created by hpduy17 on 7/22/15.
 */
public class ReplyMakeTripNoti {
    private long requestId;
    private String userId;
    private String userFirstName;
    private String userLastName;
    private int status;

    public ReplyMakeTripNoti(RequestMakeTrip requestMakeTrip) {
        this.requestId = requestMakeTrip.getId();
        this.status = requestMakeTrip.getStatus();
        User user = Database.getInstance().getUserHashMap().get(requestMakeTrip.getReceiverId());
        if (user != null) {
            this.userId = user.getId();
            this.userFirstName = user.getFirstName();
            this.userLastName = user.getLastName();
        }
    }

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
