package com.bikiegang.ridesharing.pojo.request;

import com.bikiegang.ridesharing.annn.framework.util.JSONUtil;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 7/15/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetUserDetailRequest extends GetInformationUsingUserIdRequest {

    private String viewerId;

    public GetUserDetailRequest() {
    }

    public GetUserDetailRequest(String userId, String viewerId) {
        super(userId);
        this.viewerId = viewerId;
    }

    public GetUserDetailRequest(String viewerId) {
        this.viewerId = viewerId;
    }

    public String getViewerId() {
        return viewerId;
    }

    public void setViewerId(String viewerId) {
        this.viewerId = viewerId;
    }

    @Override
    public String toString() {
        return JSONUtil.Serialize(this);
    }
}
