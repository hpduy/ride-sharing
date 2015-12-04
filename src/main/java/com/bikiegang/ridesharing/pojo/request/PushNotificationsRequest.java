package com.bikiegang.ridesharing.pojo.request;

import com.bikiegang.ridesharing.pojo.response.AnnouncementDetail;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 7/15/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PushNotificationsRequest {
    private String content = "";
    private String title = "";
    private String dates = ""; //dd/MM/yyyy hh:mm:ss
    private String link = "";
    private int type = AnnouncementDetail.Notification_App_Main;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
