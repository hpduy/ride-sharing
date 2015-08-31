package com.bikiegang.ridesharing.pojo.request;

import com.bikiegang.ridesharing.annn.framework.util.JSONUtil;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 8/20/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RatingRequest {

    private String ratedUserId;
    private String ratingUserId;
    private int numberOfStar;
    private String comment;
    private long tripId;

    public String getRatedUserId() {
        return ratedUserId;
    }

    public void setRatedUserId(String ratedUserId) {
        this.ratedUserId = ratedUserId;
    }

    public String getRatingUserId() {
        return ratingUserId;
    }

    public void setRatingUserId(String ratingUserId) {
        this.ratingUserId = ratingUserId;
    }

    public int getNumberOfStar() {
        return numberOfStar;
    }

    public void setNumberOfStar(int numberOfStar) {
        this.numberOfStar = numberOfStar;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getTripId() {
        return tripId;
    }

    public void setTripId(long tripId) {
        this.tripId = tripId;
    }

    public RatingRequest() {
    }

    public RatingRequest(String ratedUserId, String ratingUserId, int numberOfStar, String comment, long tripId) {
        this.ratedUserId = ratedUserId;
        this.ratingUserId = ratingUserId;
        this.numberOfStar = numberOfStar;
        this.comment = comment;
        this.tripId = tripId;
    }

  
    @Override
    public String toString() {
        return JSONUtil.Serialize(this);
    }
}
