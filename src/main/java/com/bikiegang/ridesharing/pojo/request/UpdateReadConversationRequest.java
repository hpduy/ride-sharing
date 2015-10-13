package com.bikiegang.ridesharing.pojo.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 10/12/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateReadConversationRequest {
    private long conversationId;

    public long getConversationId() {
        return conversationId;
    }

    public void setConversationId(long conversationId) {
        this.conversationId = conversationId;
    }
}
