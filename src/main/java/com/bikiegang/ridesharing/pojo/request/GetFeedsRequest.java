package com.bikiegang.ridesharing.pojo.request;

import com.bikiegang.ridesharing.annn.framework.util.JSONUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 8/19/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetFeedsRequest extends GetUsersAroundFromMeRequest {

    private int startIdx;
    private int numberOfFeed;
    private int getWay;
    @JsonIgnore
    public static final int NEW_FEED = 0;
    @JsonIgnore
    public static final int HISTORY_FEED = 1;

    public int getStartIdx() {
        return startIdx;
    }

    public void setStartIdx(int startIdx) {
        this.startIdx = startIdx;
    }

    public int getNumberOfFeed() {
        return numberOfFeed;
    }

    public void setNumberOfFeed(int numberOfFeed) {
        this.numberOfFeed = numberOfFeed;
    }

    public int getGetWay() {
        return getWay;
    }

    public void setGetWay(int getWay) {
        this.getWay = getWay;
    }

    public GetFeedsRequest() {
    }

    public GetFeedsRequest(int startIdx, int numberOfFeed, int getWay) {
        this.startIdx = startIdx;
        this.numberOfFeed = numberOfFeed;
        this.getWay = getWay;
    }

    @Override
    public String toString() {
        return JSONUtil.Serialize(this);
    }
}
