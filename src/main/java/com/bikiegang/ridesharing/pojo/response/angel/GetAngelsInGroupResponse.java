package com.bikiegang.ridesharing.pojo.response.angel;

import com.bikiegang.ridesharing.pojo.response.UserShortDetailResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by hpduy17 on 7/23/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetAngelsInGroupResponse {
    UserShortDetailResponse[] angels;

    public GetAngelsInGroupResponse(UserShortDetailResponse[] angels) {
        this.angels = angels;
    }

    public GetAngelsInGroupResponse(List<UserShortDetailResponse> angels) {
        this.angels = angels.toArray(new UserShortDetailResponse[angels.size()]);
    }

    public UserShortDetailResponse[] getAngels() {
        return angels;
    }

    public void setAngels(UserShortDetailResponse[] angels) {
        this.angels = angels;
    }
}
