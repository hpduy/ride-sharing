package com.bikiegang.ridesharing.pojo.static_object;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 8/20/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TripPattern {

    private long startDay;
    private long endDay;
    private int dayOfWeek;

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

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public TripPattern() {

    }

    public TripPattern(long startDay, long endDay, int dayOfWeek) {
        this.startDay = startDay;
        this.endDay = endDay;
        this.dayOfWeek = dayOfWeek;
    }

}
