package com.bikiegang.ridesharing.pojo.request;

import com.bikiegang.ridesharing.pojo.Message;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 10/12/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MessagesCommon {
    private long conversationId;
    private Message [] messages;

    public long getConversationId() {
        return conversationId;
    }

    public void setConversationId(long conversationId) {
        this.conversationId = conversationId;
    }

    public Message[] getMessages() {
        return messages;
    }

    public void setMessages(Message[] messages) {
        this.messages = messages;
    }
}
