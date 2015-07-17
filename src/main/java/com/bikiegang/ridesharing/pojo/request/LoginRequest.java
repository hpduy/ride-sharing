package com.bikiegang.ridesharing.pojo.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 7/15/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginRequest {
    private String userId = "";
    private String password = "";
    private int type;
    //final field
    @JsonIgnore
    public static final int EMAIL = 1;
    @JsonIgnore
    public static final int FACEBOOK = 2;
    @JsonIgnore
    public static final int GOOGLE = 3;
    @JsonIgnore
    public static final int TWITTER = 4;
    @JsonIgnore
    public static final int LINKEDIN = 5;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
