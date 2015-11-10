package com.bikiegang.ridesharing.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 10/6/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Event implements PojoBase {
    private long eventId;
    private boolean isFixedTime;
    private long[] eventTimes;
    private boolean isFixedUnitPrice;
    private int unitPrice;
    private String suggestMessage;
    private String infoLink;
    private String feedBannerLink;
    @JsonIgnore
    private long startTime;
    @JsonIgnore
    private long endTime;
    public boolean isFixedTime() {
        return isFixedTime;
    }

    public void setIsFixedTime(boolean isFixedTime) {
        this.isFixedTime = isFixedTime;
    }

    public long[] getEventTimes() {
        return eventTimes;
    }

    public void setEventTimes(long[] eventTimes) {
        this.eventTimes = eventTimes;
    }

    public boolean isFixedUnitPrice() {
        return isFixedUnitPrice;
    }

    public void setIsFixedUnitPrice(boolean isFixedUnitPrice) {
        this.isFixedUnitPrice = isFixedUnitPrice;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getSuggestMessage() {
        return suggestMessage;
    }

    public void setSuggestMessage(String suggestMessage) {
        this.suggestMessage = suggestMessage;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getInfoLink() {
        return infoLink;
    }

    public void setInfoLink(String infoLink) {
        this.infoLink = infoLink;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public String getFeedBannerLink() {
        return feedBannerLink;
    }

    public void setFeedBannerLink(String feedBannerLink) {
        this.feedBannerLink = feedBannerLink;
    }
}
