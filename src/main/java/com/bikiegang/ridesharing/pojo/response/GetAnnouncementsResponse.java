package com.bikiegang.ridesharing.pojo.response;

/**
 * Created by hpduy17 on 10/13/15.
 */
public class GetAnnouncementsResponse {
    private AnnouncementDetail[] announcements;
    private long nextPullTime;

    public AnnouncementDetail[] getAnnouncements() {
        return announcements;
    }

    public void setAnnouncements(AnnouncementDetail[] announcements) {
        this.announcements = announcements;
    }

    public long getNextPullTime() {
        return nextPullTime;
    }

    public void setNextPullTime(long nextPullTime) {
        this.nextPullTime = nextPullTime;
    }
}
