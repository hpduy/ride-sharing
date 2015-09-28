package com.bikiegang.ridesharing.pojo.response;

import com.bikiegang.ridesharing.controller.FeedController;
import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.pojo.PlannedTrip;
import com.bikiegang.ridesharing.pojo.RequestMakeTrip;
import com.bikiegang.ridesharing.pojo.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.IOException;

/**
 * Created by hpduy17 on 7/31/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestMakeTripDetailResponse {
    private long requestId;
    private long createdTime;
    private boolean accepted = false;
    private UserDetailResponse userDetail = new UserDetailResponse(); // delete later
    private FeedResponse feed = new FeedResponse();

    public RequestMakeTripDetailResponse(RequestMakeTrip that) throws IOException {
        Database database = Database.getInstance();
        this.requestId = that.getId();
        this.createdTime = that.getCreatedTime();
        User user = database.getUserHashMap().get(that.getSenderId());
        if (user != null)
            this.userDetail = new UserDetailResponse(user);
        RequestMakeTrip requestMakeTrip = database.getRequestMakeTripHashMap().get(requestId);
        if(requestMakeTrip != null){
            PlannedTrip pt = database.getPlannedTripHashMap()
                    .get( requestMakeTrip.getSenderRole() == User.DRIVER ? requestMakeTrip.getDriverPlannedTripId() : requestMakeTrip.getPassengerPlannedTripId());
            if(pt != null){
                this.feed = new FeedController().convertPlannedTripToFeed(pt, requestMakeTrip.getReceiverId());
            }
            if(requestMakeTrip.getStatus() == RequestMakeTrip.ACCEPT)
                this.accepted = true;
        }
    }

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public UserDetailResponse getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetailResponse userDetail) {
        this.userDetail = userDetail;
    }

    public FeedResponse getFeed() {
        return feed;
    }

    public void setFeed(FeedResponse feed) {
        this.feed = feed;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
}
