package com.bikiegang.ridesharing.pojo.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 7/15/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AutoSearchParingRequest {
    private String creatorId;
    private long goTime;
    private String googleRoutingResult;
    private boolean hasHelmet;

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getGoogleRoutingResult() {
        return googleRoutingResult;
    }

    public void setGoogleRoutingResult(String googleRoutingResult) {
        this.googleRoutingResult = googleRoutingResult;
    }

    public long getGoTime() {
        return goTime;
    }

    public void setGoTime(long goTime) {
        this.goTime = goTime;
    }

    public boolean isHasHelmet() {
        return hasHelmet;
    }

    public void setHasHelmet(boolean hasHelmet) {
        this.hasHelmet = hasHelmet;
    }
}
