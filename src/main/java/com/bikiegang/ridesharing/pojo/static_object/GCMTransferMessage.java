package com.bikiegang.ridesharing.pojo.static_object;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hpduy17 on 7/8/15.
 */
public class GCMTransferMessage {
    private List<String> contents = new ArrayList<>();
    private List<String> userIds = new ArrayList<>();
    private String senderId = "";
    private String collapseKey = "";
    private int type;

    public List<String> getContents() {
        return contents;
    }

    public void setContents(List<String> contents) {
        this.contents = contents;
    }

    public List<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }

    public String getCollapseKey() {
        return collapseKey;
    }

    public void setCollapseKey(String collapseKey) {
        this.collapseKey = collapseKey;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
