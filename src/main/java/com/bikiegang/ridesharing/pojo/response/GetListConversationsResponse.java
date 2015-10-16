package com.bikiegang.ridesharing.pojo.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 8/18/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetListConversationsResponse {
    private ConversationDetail[] conversations;

    public ConversationDetail[] getConversations() {
        return conversations;
    }

    public void setConversations(ConversationDetail[] conversations) {
        this.conversations = conversations;
    }
}
