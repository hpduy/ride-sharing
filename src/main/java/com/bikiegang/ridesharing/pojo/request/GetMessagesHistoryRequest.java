package com.bikiegang.ridesharing.pojo.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 10/12/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetMessagesHistoryRequest {
    private long conversationId;
    private String ownerId;
    private String[] partnerIds;
    private long timeInMillis;

    public long getConversationId() {
        return conversationId;
    }

    public void setConversationId(long conversationId) {
        this.conversationId = conversationId;
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

    public long getTimeInMillis() {
        return timeInMillis;
    }

    public void setTimeInMillis(long timeInMillis) {
        this.timeInMillis = timeInMillis;
    }
}
