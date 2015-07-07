package com.bikiegang.ridesharing.pojo.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 7/1/15.
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
}
