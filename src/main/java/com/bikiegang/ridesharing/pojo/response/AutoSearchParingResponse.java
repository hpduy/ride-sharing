package com.bikiegang.ridesharing.pojo.response;

/**
 * Created by hpduy17 on 7/8/15.
 */
public class AutoSearchParingResponse {
    UserAndPlannedTripDetailResponse[] users;

    public UserAndPlannedTripDetailResponse[] getUsers() {
        return users;
    }

    public void setUsers(UserAndPlannedTripDetailResponse[] users) {
        this.users = users;
    }
}
