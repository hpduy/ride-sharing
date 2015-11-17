package com.bikiegang.ridesharing.pojo.response;

/**
 * Created by hpduy17 on 10/13/15.
 */
public class GetAnnouncementsResponse {
    private AnnouncementDetail[] announcement;
    private long nextPullTime;

    public AnnouncementDetail[] getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(AnnouncementDetail[] announcement) {
        this.announcement = announcement;
    }

    public long getNextPullTime() {
        return nextPullTime;
    }

    public void setNextPullTime(long nextPullTime) {
        this.nextPullTime = nextPullTime;
    }
}
