package com.bikiegang.ridesharing.pojo.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 8/2/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateSocialNetworkAccountRequest {
    private String userId;
    private String socialNetworkId;
    private int type;
    @JsonIgnore
    public static final int FACEBOOK = 1;
    @JsonIgnore
    public static final int GOOGLE = 2;
    @JsonIgnore
    public static final int TWITTER = 3;
    @JsonIgnore
    public static final int LINKEDIN = 4;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSocialNetworkId() {
        return socialNetworkId;
    }

    public void setSocialNetworkId(String socialNetworkId) {
        this.socialNetworkId = socialNetworkId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
