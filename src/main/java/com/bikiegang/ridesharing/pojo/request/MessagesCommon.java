package com.bikiegang.ridesharing.pojo.request;

import com.bikiegang.ridesharing.pojo.response.MessageDetail;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 10/12/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MessagesCommon {
    private long conversationId;
    private MessageDetail[] messages;

    public long getConversationId() {
        return conversationId;
    }

    public void setConversationId(long conversationId) {
        this.conversationId = conversationId;
    }

    public MessageDetail[] getMessages() {
        return messages;
    }

    public void setMessages(MessageDetail[] messages) {
        this.messages = messages;
    }
}
