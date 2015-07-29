package com.bikiegang.ridesharing.pojo.request;

/**
 * Created by hpduy17 on 7/15/15.
 */
public class AngelRegisterRequest {
    private String email;
    private String password;
    private String activeCode;

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

    public String getActiveCode() {
        return activeCode;
    }

    public void setActiveCode(String activeCode) {
        this.activeCode = activeCode;
    }
}
