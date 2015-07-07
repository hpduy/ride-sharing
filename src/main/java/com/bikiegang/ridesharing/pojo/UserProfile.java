package com.bikiegang.ridesharing.pojo;

import com.bikiegang.ridesharing.da.ActivityDA;

/**
 * Created by hpduy17 on 6/25/15.
 */
public class UserProfile implements PojoBase {

    protected long id;
    protected String userId = "";
    protected String name = "";
    protected String description = "";
    protected long createdTime;

    public UserProfile() {
    }

    public UserProfile(long id, String name, String description, long createdTime, String userId) {
        this.id = id;
        this.name = name == null ? "" : name;
        this.description = description == null ? "" : description;
        this.createdTime = createdTime;
        this.userId = userId == null ? "" : userId;
    }

    public UserProfile(UserProfile that) {
        this.id = that.id;
        this.name = that.name == null ? "" : that.name;
        this.description = that.description == null ? "" : that.description;
        this.createdTime = that.createdTime;
        this.userId = that.userId == null ? "" : that.userId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
