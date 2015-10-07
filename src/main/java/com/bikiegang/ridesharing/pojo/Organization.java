package com.bikiegang.ridesharing.pojo;

import com.bikiegang.ridesharing.utilities.daytime.DateTimeUtil;

/**
 * Created by hpduy17 on 10/6/15.
 */
public class Organization {
    private long id;
    private String name;
    private String logoImageLink;
    private String code;
    private long createdTime = DateTimeUtil.now();

    public Organization() {
    }

    public Organization(long id, String name, String logoImageLink, String code) {
        this.id = id;
        this.name = name;
        this.logoImageLink = logoImageLink;
        this.code = code;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
