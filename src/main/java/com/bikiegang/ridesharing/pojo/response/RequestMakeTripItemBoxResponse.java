package com.bikiegang.ridesharing.pojo.response;

/**
 * Created by hpduy17 on 7/23/15.
 */

public class RequestMakeTripItemBoxResponse {
    private long requestId;
    private String otherUserId;

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public String getOtherUserId() {
        return otherUserId;
    }

    public void setOtherUserId(String otherUserId) {
        this.otherUserId = otherUserId;
    }
}
