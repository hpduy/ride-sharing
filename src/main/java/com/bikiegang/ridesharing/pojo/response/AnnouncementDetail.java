package com.bikiegang.ridesharing.pojo.response;

/**
 * Created by hpduy17 on 10/13/15.
 */
public class AnnouncementDetail {
    private String title;
    private String content;
    private long showtime;

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
}
