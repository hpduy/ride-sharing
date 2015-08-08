package com.bikiegang.ridesharing.pojo;

import com.bikiegang.ridesharing.utilities.DateTimeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hpduy17 on 8/8/15.
 */
public class AngelGroup {
    private long id;
    private LatLng location = new LatLng();
    private List<String> tagName = new ArrayList<>();
    private long createdTime = DateTimeUtil.now();

    public AngelGroup() {

    }

    public AngelGroup(long id, LatLng location, List<String> tagName, long createdTime) {
        this.id = id;
        this.location = location;
        this.tagName = tagName;
        this.createdTime = createdTime;
    }
     public AngelGroup(AngelGroup that) {
        this.id = that.id;
        this.location = that.location;
        this.tagName = that.tagName;
        this.createdTime = that.createdTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LatLng getLocation() {
        return location;
    }

    public void setLocation(LatLng location) {
        this.location = location;
    }

    public List<String> getTagName() {
        return tagName;
    }

    public void setTagName(List<String> tagName) {
        this.tagName = tagName;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }
}
