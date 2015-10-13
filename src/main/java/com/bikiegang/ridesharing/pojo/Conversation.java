package com.bikiegang.ridesharing.pojo;

/**
 * Created by hpduy17 on 10/12/15.
 */
public class Conversation {
    private long id;
    private String ownerId;
    private String[] partnerIds;
    private long createdTime; // in second

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String[] getPartnerIds() {
        return partnerIds;
    }

    public void setPartnerIds(String[] partnerIds) {
        this.partnerIds = partnerIds;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }
}
