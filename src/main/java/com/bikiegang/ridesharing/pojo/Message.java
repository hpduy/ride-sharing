package com.bikiegang.ridesharing.pojo;

/**
 * Created by hpduy17 on 10/12/15.
 */
public class Message implements PojoBase {

    private String msgId;
    private String mContent;
    private String senderId;
    private String[] receiverIds;
    private long timestampInMillis;
    private long conversationId;

    public Message() {
    }

    public Message(String msgId, String mContent, String senderId, String[] receiverIds, long timestampInMillis, long conversationId) {
        this.msgId = msgId;
        this.mContent = mContent;
        this.senderId = senderId;
        this.receiverIds = receiverIds;
        this.timestampInMillis = timestampInMillis;
        this.conversationId = conversationId;
    }

    public Message(Message that) {
        this.msgId = that.msgId;
        this.mContent = that.mContent;
        this.senderId = that.senderId;
        this.receiverIds = that.receiverIds;
        this.timestampInMillis = that.timestampInMillis;
        this.conversationId = that.conversationId;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getmContent() {
        return mContent;
    }

    public void setmContent(String mContent) {
        this.mContent = mContent;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String[] getReceiverIds() {
        return receiverIds;
    }

    public void setReceiverIds(String[] receiverIds) {
        this.receiverIds = receiverIds;
    }

    public long getTimestampInMillis() {
        return timestampInMillis;
    }

    public void setTimestampInMillis(long timestampInMillis) {
        this.timestampInMillis = timestampInMillis;
    }

    public long getConversationId() {
        return conversationId;
    }

    public void setConversationId(long conversationId) {
        this.conversationId = conversationId;
    }
}
