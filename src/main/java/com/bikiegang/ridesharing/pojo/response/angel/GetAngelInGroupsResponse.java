package com.bikiegang.ridesharing.pojo.response.angel;

import com.bikiegang.ridesharing.pojo.response.UserShortDetailResponse;

import java.util.List;

/**
 * Created by hpduy17 on 7/23/15.
 */

public class GetAngelInGroupsResponse {
    UserShortDetailResponse[] angels;

    public GetAngelInGroupsResponse(UserShortDetailResponse[] angels) {
        this.angels = angels;
    }

    public GetAngelInGroupsResponse(List<UserShortDetailResponse> angels) {
        this.angels = angels.toArray(new UserShortDetailResponse[angels.size()]);
    }

    public UserShortDetailResponse[] getAngels() {
        return angels;
    }

    public void setAngels(UserShortDetailResponse[] angels) {
        this.angels = angels;
    }
}
