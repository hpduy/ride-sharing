package com.bikiegang.ridesharing.pojo;

/**
 * Created by hpduy17 on 10/12/15.
 */
public class Conversation implements PojoBase {

    private long id;
    private String ownerId;
    private String[] partnerIds;
    private long createdTime; // in second
    private int lastMessageIndex = -1;

    public Conversation() {
    }

    public Conversation(long id, String ownerId, String[] partnerIds, long createdTime, int lastMessageIndex) {
        this.id = id;
        this.ownerId = ownerId;
        this.partnerIds = partnerIds;
        this.createdTime = createdTime;
        this.lastMessageIndex = lastMessageIndex;
    }

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

    public int getLastMessageIndex() {
        return lastMessageIndex;
    }

    public void setLastMessageIndex(int lastMessageIndex) {
        this.lastMessageIndex = lastMessageIndex;
    }
}
