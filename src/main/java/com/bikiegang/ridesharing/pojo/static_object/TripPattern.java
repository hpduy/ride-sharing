package com.bikiegang.ridesharing.pojo.static_object;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 8/20/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TripPattern {
    private long startDay;
    private long endDay;
    private long timeDistance;

    public long getStartDay() {
        return startDay;
    }

    public void setStartDay(long startDay) {
        this.startDay = startDay;
    }

    public long getEndDay() {
        return endDay;
    }

    public void setEndDay(long endDay) {
        this.endDay = endDay;
    }

    public long getTimeDistance() {
        return timeDistance;
    }

    public void setTimeDistance(long timeDistance) {
        this.timeDistance = timeDistance;
    }
}
