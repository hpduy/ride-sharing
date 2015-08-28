package com.bikiegang.ridesharing.pojo.response;

import com.bikiegang.ridesharing.pojo.User;

/**
 * Created by hpduy17 on 8/13/15.
 */
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
