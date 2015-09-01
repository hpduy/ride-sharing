package com.bikiegang.ridesharing.pojo.request;

import com.bikiegang.ridesharing.annn.framework.util.JSONUtil;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 7/15/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetPartnerLocationRequest extends UpdateCurrentLocationRequest {

    private String partnerId;

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public GetPartnerLocationRequest() {
    }

    public GetPartnerLocationRequest(String partnerId) {
        this.partnerId = partnerId;
    }

    public GetPartnerLocationRequest(String partnerId, double lat, double lng, String userId) {
        super(lat, lng, userId);
        this.partnerId = partnerId;
    }

    @Override
    public String toString() {
        return JSONUtil.Serialize(this);
    }
}
