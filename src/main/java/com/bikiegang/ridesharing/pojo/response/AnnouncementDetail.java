package com.bikiegang.ridesharing.pojo.response;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by hpduy17 on 10/13/15.
 */
public class AnnouncementDetail {
    private String title;
    private String content;
    private long showtime;
    private int type;
    private String link;

    @JsonIgnore
    public static final int Notification_Webview = 1;
    @JsonIgnore
    public static final int Notification_App_Main = 2;
    @JsonIgnore
    public static final int Notification_App_Chat = 3;
    @JsonIgnore
    public static final int Notification_App_Profile = 4;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getShowtime() {
        return showtime;
    }

    public void setShowtime(long showtime) {
        this.showtime = showtime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
