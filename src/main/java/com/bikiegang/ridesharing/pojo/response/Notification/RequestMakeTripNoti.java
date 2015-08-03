package com.bikiegang.ridesharing.pojo.response.Notification;

import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.pojo.RequestMakeTrip;
import com.bikiegang.ridesharing.pojo.User;

/**
 * Created by hpduy17 on 7/22/15.
 */
public class RequestMakeTripNoti {
    private long requestId;
    private String userId;
    private String userFirstName;
    private String userLastName;
    private double price;
    public RequestMakeTripNoti(RequestMakeTrip requestMakeTrip) {
        this.requestId = requestMakeTrip.getId();
        User user = Database.getInstance().getUserHashMap().get(requestMakeTrip.getSenderId());
        if (user != null) {
            this.userId = user.getId();
            this.userFirstName = user.getFirstName();
            this.userLastName = user.getLastName();
        }
        this.price = requestMakeTrip.getPrice();
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
