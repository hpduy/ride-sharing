package com.bikiegang.ridesharing.pojo.response;

import com.bikiegang.ridesharing.pojo.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 8/13/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResponse {
    UserDetailResponse user;

    public UserResponse(User user) {
        this.user = new UserDetailResponse(user);
    }

    public UserDetailResponse getUser() {
        return user;
    }

    public void setUser(UserDetailResponse user) {
        this.user = user;
    }

}
