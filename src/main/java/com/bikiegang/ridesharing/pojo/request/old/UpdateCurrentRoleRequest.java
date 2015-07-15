package com.bikiegang.ridesharing.pojo.request.old;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 6/26/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateCurrentRoleRequest {
    private String userId;
    private int role;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
