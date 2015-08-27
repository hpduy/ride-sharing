package com.bikiegang.ridesharing.pojo.request;

import com.bikiegang.ridesharing.annn.framework.util.JSONUtil;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 7/15/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReplyMakeTripRequest {

    private long requestMakeTripId;
    private int status;
    private String replierId;

    public long getRequestMakeTripId() {
        return requestMakeTripId;
    }

    public void setRequestMakeTripId(long requestMakeTripId) {
        this.requestMakeTripId = requestMakeTripId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getReplierId() {
        return replierId;
    }

    public void setReplierId(String replierId) {
        this.replierId = replierId;
    }

    public ReplyMakeTripRequest() {
    }

    public ReplyMakeTripRequest(long requestMakeTripId, int status, String replierId) {
        this.requestMakeTripId = requestMakeTripId;
        this.status = status;
        this.replierId = replierId;
    }

    @Override
    public String toString() {
        return JSONUtil.Serialize(this);
    }
}
