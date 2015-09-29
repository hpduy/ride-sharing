package com.bikiegang.ridesharing.pojo;

import com.bikiegang.ridesharing.utilities.daytime.DateTimeUtil;

/**
 * Created by hpduy17 on 9/29/15.
 */
public class Feedback {
    private String userId = "";
    private String email = "";
    private String content = "";
    private long createdTime = DateTimeUtil.now();

    public Feedback() {
    }

    public Feedback(String userId, String email, String content, long createdTime) {
        this.userId = userId;
        this.email = email;
        this.content = content;
        this.createdTime = createdTime;
    }
    public Feedback(Feedback that) {
        this.userId = that.userId;
        this.email = that.email;
        this.content = that.content;
        this.createdTime = that.createdTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }
}
