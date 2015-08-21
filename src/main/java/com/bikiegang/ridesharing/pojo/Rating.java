package com.bikiegang.ridesharing.pojo;

/**
 * Created by hpduy17 on 8/17/15.
 */
public class Rating implements PojoBase{

    private long id;
    private String ratedUserId;
    private String ratingUserId;
    private int numberOfStart;
    private long createdTime;
    private String comment;
    private long tripId;

    public Rating(long id, String ratedUserId, String ratingUserId, int numberOfStart, long createdTime, String comment, long tripId) {
        this.id = id;
        this.ratedUserId = ratedUserId;
        this.ratingUserId = ratingUserId;
        this.numberOfStart = numberOfStart;
        this.createdTime = createdTime;
        this.comment = comment;
        this.tripId = tripId;
    }

    public Rating() {
    }

    // private String[] hashTag;
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public int getNumberOfStart() {
        return numberOfStart;
    }

    public void setNumberOfStart(int numberOfStart) {
        this.numberOfStart = numberOfStart;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
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
}
