package com.bikiegang.ridesharing.pojo;

import com.bikiegang.ridesharing.utilities.daytime.DateTimeUtil;

/**
 * Created by hpduy17 on 10/6/15.
 */
public class Organization  implements PojoBase {
    private String name;
    private String logoImageLink;
    private String organizationId;
    private long createdTime = DateTimeUtil.now();
    private String[] tagNames = new String[0];

    public Organization() {
    }

    public Organization( String name, String logoImageLink, String organizationId, String [] tagNames) {
        this.name = name;
        this.logoImageLink = logoImageLink;
        this.organizationId = organizationId;
        this.tagNames = tagNames;
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

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String[] getTagNames() {
        return tagNames;
    }

    public void setTagNames(String[] tagNames) {
        this.tagNames = tagNames;
    }
}
