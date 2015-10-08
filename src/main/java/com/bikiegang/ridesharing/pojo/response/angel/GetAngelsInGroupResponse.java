package com.bikiegang.ridesharing.pojo.response.angel;

import com.bikiegang.ridesharing.pojo.response.UserDetailResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by hpduy17 on 7/23/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetAngelsInGroupResponse {
    UserDetailResponse[] angels;

    public GetAngelsInGroupResponse(UserDetailResponse[] angels) {
        this.angels = angels;
    }

    public GetAngelsInGroupResponse(List<UserDetailResponse> angels) {
        this.angels = angels.toArray(new UserDetailResponse[angels.size()]);
    }

    public UserDetailResponse[] getAngels() {
        return angels;
    }

    public void setAngels(UserDetailResponse[] angels) {
        this.angels = angels;
    }
}
