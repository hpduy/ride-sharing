package com.bikiegang.ridesharing.pojo.static_object;

import java.util.HashMap;

/**
 * Created by hpduy17 on 8/16/15.
 */
public class Message {
    private int code;
    private HashMap<String,String> messages;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public HashMap<String, String> getMessages() {
        return messages;
    }

    public void setMessages(HashMap<String, String> messages) {
        this.messages = messages;
    }
}
