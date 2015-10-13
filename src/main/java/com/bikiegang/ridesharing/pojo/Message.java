package com.bikiegang.ridesharing.pojo;

/**
 * Created by hpduy17 on 10/12/15.
 */
public class Message {
    private String msgId;
    private String mContent;
    private String senderId;
    private String[] receiveIds;
    private long timestampInMillis;
    private long conversationId;


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

    public String[] getReceiveIds() {
        return receiveIds;
    }

    public void setReceiveIds(String[] receiveIds) {
        this.receiveIds = receiveIds;
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
