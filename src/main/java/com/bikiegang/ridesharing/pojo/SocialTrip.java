package com.bikiegang.ridesharing.pojo;

/**
 * Created by hpduy17 on 8/17/15.
 */
public class SocialTrip implements PojoBase {
    protected long id;
    protected String creatorId = "";
    protected LatLng location = new LatLng();
    protected String feeling = "";
    protected int feelingIcon;
    protected String wantToGo = "";
    protected int wantToGoIcon;
    protected String content = "";
    protected long createdTime;

    public SocialTrip() {
    }

    public SocialTrip(long id, String creatorId, LatLng location, String feeling, int feelingIcon, String wantToGo, int wantToGoIcon, String content, long createdTime) {
        this.id = id;
        this.creatorId = creatorId;
        this.location = location;
        this.feeling = feeling;
        this.feelingIcon = feelingIcon;
        this.wantToGo = wantToGo;
        this.wantToGoIcon = wantToGoIcon;
        this.content = content;
        this.createdTime = createdTime;
    }

    public SocialTrip(SocialTrip that) {
        this.id = that.id;
        this.creatorId = that.creatorId;
        this.location = that.location;
        this.feeling = that.feeling;
        this.feelingIcon = that.feelingIcon;
        this.wantToGo = that.wantToGo;
        this.wantToGoIcon = that.wantToGoIcon;
        this.content = that.content;
        this.createdTime = that.createdTime;
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
    
    
}
