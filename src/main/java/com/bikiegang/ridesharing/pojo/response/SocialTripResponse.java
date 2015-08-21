package com.bikiegang.ridesharing.pojo.response;

import com.bikiegang.ridesharing.pojo.Feed;
import com.bikiegang.ridesharing.pojo.LatLng;
import com.bikiegang.ridesharing.pojo.SocialTrip;

/**
 * Created by hpduy17 on 7/8/15.
 */
public class SocialTripResponse extends TripInFeed {

    protected LatLng location = new LatLng();
    protected String feeling = "";
    protected int feelingIcon;
    protected String wantToGo = "";
    protected int wantToGoIcon;
    protected String content = "";

    public SocialTripResponse(SocialTrip that) {
        this.id = that.getId();
        this.creatorId = that.getCreatorId();
        this.location = that.getLocation();
        this.feeling = that.getFeeling();
        this.feelingIcon = that.getFeelingIcon();
        this.wantToGo = that.getWantToGo();
        this.wantToGoIcon = that.getWantToGoIcon();
        this.content = that.getContent();
        this.createdTime = that.getCreatedTime();
        this.typeOfTrip = Feed.SOCIAL_TRIP;
    }

    public SocialTripResponse() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public LatLng getLocation() {
        return location;
    }

    public void setLocation(LatLng location) {
        this.location = location;
    }

    public String getFeeling() {
        return feeling;
    }

    public void setFeeling(String feeling) {
        this.feeling = feeling;
    }

    public int getFeelingIcon() {
        return feelingIcon;
    }

    public void setFeelingIcon(int feelingIcon) {
        this.feelingIcon = feelingIcon;
    }

    public String getWantToGo() {
        return wantToGo;
    }

    public void setWantToGo(String wantToGo) {
        this.wantToGo = wantToGo;
    }

    public int getWantToGoIcon() {
        return wantToGoIcon;
    }

    public void setWantToGoIcon(int wantToGoIcon) {
        this.wantToGoIcon = wantToGoIcon;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public int getTypeOfTrip() {
        return typeOfTrip;
    }

    public void setTypeOfTrip(int typeOfTrip) {
        this.typeOfTrip = typeOfTrip;
    }
}
