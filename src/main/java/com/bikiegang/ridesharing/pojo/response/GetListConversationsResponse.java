package com.bikiegang.ridesharing.pojo.response;

import com.bikiegang.ridesharing.pojo.Conversation;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 8/18/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetListConversationsResponse {
    private Conversation[] conversations;

    public Conversation[] getConversations() {
        return conversations;
    }

    public void setConversations(Conversation[] conversations) {
        this.conversations = conversations;
    }
}
