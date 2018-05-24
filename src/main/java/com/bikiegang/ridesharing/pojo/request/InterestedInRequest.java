package com.bikiegang.ridesharing.pojo.request;

import com.bikiegang.ridesharing.annn.framework.util.JSONUtil;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 7/15/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class InterestedInRequest {

    private String viewerId;
    private String vieweeId;

    public InterestedInRequest() {
    }

    public String getViewerId() {
        return viewerId;
    }

    public void setViewerId(String viewerId) {
        this.viewerId = viewerId;
    }

    public String getVieweeId() {
        return vieweeId;
    }

    public void setVieweeId(String vieweeId) {
        this.vieweeId = vieweeId;
    }

    @Override
    public String toString() {
        return JSONUtil.Serialize(this);
    }
}
