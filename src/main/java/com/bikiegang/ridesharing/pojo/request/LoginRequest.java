package com.bikiegang.ridesharing.pojo.request;

import com.bikiegang.ridesharing.annn.framework.util.JSONUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 7/15/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginRequest {
    private String email;
    private String password ;
    private int type = 1;
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


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public LoginRequest() {
    }

    public LoginRequest(String userId, String password, int type) {
        this.email = userId;
        this.password = password;
        this.type = type;
    }

        @Override
    public String toString() {
        return JSONUtil.Serialize(this);
    }

    
}
