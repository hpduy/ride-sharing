package com.bikiegang.ridesharing.pojo;

import com.bikiegang.ridesharing.utilities.daytime.DateTimeUtil;

/**
 * Created by hpduy17 on 10/6/15.
 */
public class Organization {
    private long id;
    private String name;
    private String logoImageLink;
    private long createdTime = DateTimeUtil.now();

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

    public String getLogoImageLink() {
        return logoImageLink;
    }

    public void setLogoImageLink(String logoImageLink) {
        this.logoImageLink = logoImageLink;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }
}
