package com.bikiegang.ridesharing.pojo;

/**
 * Created by hpduy17 on 6/25/15.
 */
public class UserProfile {
    private long id;
    private String userId;
    private String name;
    private String description;
    private long createdTime;

    public UserProfile() {
    }

    public UserProfile(long id, String name, String description, long createdTime, String userId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdTime = createdTime;
        this.userId = userId;
    }

    public UserProfile(UserProfile that) {
        this.id = that.id;
        this.name = that.name;
        this.description = that.description;
        this.createdTime = that.createdTime;
        this.userId = that.userId;
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
