package com.bikiegang.ridesharing.pojo.response;

/**
 * Created by hpduy17 on 10/13/15.
 */
public class GetNotificationsResponse {
    private NotificationDetail[] notifications;
    private long nextPullTime;

    public NotificationDetail[] getNotifications() {
        return notifications;
    }

    public void setNotifications(NotificationDetail[] notifications) {
        this.notifications = notifications;
    }

    public long getNextPullTime() {
        return nextPullTime;
    }

    public void setNextPullTime(long nextPullTime) {
        this.nextPullTime = nextPullTime;
    }
}
