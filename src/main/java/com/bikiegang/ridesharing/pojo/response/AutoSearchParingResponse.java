package com.bikiegang.ridesharing.pojo.response;

/**
 * Created by hpduy17 on 7/8/15.
 */
public class AutoSearchParingResponse {
    UserDetailWithPlannedTripDetailResponse[] users;

    public UserDetailWithPlannedTripDetailResponse[] getUsers() {
        return users;
    }

    public void setUsers(UserDetailWithPlannedTripDetailResponse[] users) {
        this.users = users;
    }
}
