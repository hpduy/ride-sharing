package com.bikiegang.ridesharing.pojo.request;

import com.bikiegang.ridesharing.annn.framework.util.JSONUtil;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 7/15/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetListRequestMakeTripRequest {

    private String userId = "";

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public GetListRequestMakeTripRequest() {
    }

    public GetListRequestMakeTripRequest(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return JSONUtil.Serialize(this);
    }
}
