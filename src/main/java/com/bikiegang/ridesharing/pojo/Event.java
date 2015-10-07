package com.bikiegang.ridesharing.pojo;

/**
 * Created by hpduy17 on 10/6/15.
 */
public class Event extends PopularLocation  implements PojoBase {
    private String eventSocialId;
    private String showTime;
    private String referenceLink;

    public Event() {
        super.setType(EVENT_LOCATION);
    }

    public String getEventSocialId() {
        return eventSocialId;
    }

    public void setEventSocialId(String eventSocialId) {
        this.eventSocialId = eventSocialId;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public String getReferenceLink() {
        return referenceLink;
    }

    public void setReferenceLink(String referenceLink) {
        this.referenceLink = referenceLink;
    }
}
