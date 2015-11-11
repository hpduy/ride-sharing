package com.bikiegang.ridesharing.pojo.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by hpduy17 on 8/18/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetUsersResponse {
    private List<UserDetailResponse> users;

    public List<UserDetailResponse> getUsers() {
        return users;
    }

    public void setUsers(List<UserDetailResponse> users) {
        this.users = users;
    }
}
