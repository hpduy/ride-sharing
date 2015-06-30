package com.bikiegang.ridesharing.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by hpduy17 on 6/24/15.
 */
public class Circle {
    private long id;
    private String name;
    private String description;
    private long createdTime;
    private int status;

    //final field
    @JsonIgnore
    public static final int PUBLIC = 1;
    @JsonIgnore
    public static final int CLOSED = 2;
    @JsonIgnore
    public static final int STATIC = 3;

    public Circle() {
    }

    public Circle(long id, String name, String description, long createdTime, int status) {
        this.id = id;
        this.name = name ==  null ? "" : name;
        this.description = description;
        this.createdTime = createdTime;
        this.status = status;
    }
    public Circle(Circle that) {
        this.id = that.id;
        this.name = that.name;
        this.description = that.description;
        this.createdTime = that.createdTime;
        this.status = that.status;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
