package com.bikiegang.ridesharing.pojo.request;

import com.bikiegang.ridesharing.annn.framework.util.JSONUtil;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 8/20/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RepeatPlannedTripRequest {

    private String userId;
    private long plannedTripId;
    private long[] repeatTimes;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getPlannedTripId() {
        return plannedTripId;
    }

    public void setPlannedTripId(long plannedTripId) {
        this.plannedTripId = plannedTripId;
    }

    public long[] getRepeatTimes() {
        return repeatTimes;
    }

    public void setRepeatTimes(long[] repeatTimes) {
        this.repeatTimes = repeatTimes;
    }

    @Override
    public String toString() {
        return JSONUtil.Serialize(this);
    }
}
