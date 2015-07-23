package com.bikiegang.ridesharing.pojo.response.Notification;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by hpduy17 on 7/22/15.
 */

public class ObjectNoti {
    protected int action ;

    //final variable
    @JsonIgnore
    public static final int REQUEST_MAKE_TRIP = 1;
    @JsonIgnore
    public static final int REPLY_MAKE_TRIP = 2;
    @JsonIgnore
    public static final int REQUEST_VERIFY = 3;
    @JsonIgnore
    public static final int REPLY_VERIFY = 4;

    public ObjectNoti(int action) {
        this.action = action;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }
}
