package com.bikiegang.ridesharing.pojo.response;

import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.pojo.Conversation;
import com.bikiegang.ridesharing.pojo.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hpduy17 on 10/13/15.
 */
public class ConversationDetail {
    private long conversationId;
    private MessageDetail lastMessage;
    private int numberOfUnread;
    private UserDetailResponse[] chatUsers;

    public ConversationDetail() {
    }

    public ConversationDetail(Conversation that) {
        Database database = Database.getInstance();
        this.conversationId = that.getId();
        List<String> messIds = database.getConversationIdRFMessages().get(this.conversationId);
        if (messIds != null && !messIds.isEmpty()) {
            this.numberOfUnread = messIds.size() - that.getLastMessageIndex();
            this.lastMessage = new MessageDetail(database.getMessageLinkedHashMap().get(messIds.get(messIds.size() - 1)));
        }
        List<UserDetailResponse> receivers = new ArrayList<>();
        for (String id : that.getPartnerIds()) {
            User r = database.getUserHashMap().get(id);
            if (r != null) {
                receivers.add(new UserDetailResponse(r));
            }
        }
        chatUsers = receivers.toArray(new UserDetailResponse[receivers.size()]);

    }

    public long getConversationId() {
        return conversationId;
    }

    public void setConversationId(long conversationId) {
        this.conversationId = conversationId;
    }

    public MessageDetail getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(MessageDetail lastMessage) {
        this.lastMessage = lastMessage;
    }

    public UserDetailResponse[] getChatUsers() {
        return chatUsers;
    }

    public void setChatUsers(UserDetailResponse[] chatUsers) {
        this.chatUsers = chatUsers;
    }

    public int getNumberOfUnread() {
        return numberOfUnread;
    }

    public void setNumberOfUnread(int numberOfUnread) {
        this.numberOfUnread = numberOfUnread;
    }
}
