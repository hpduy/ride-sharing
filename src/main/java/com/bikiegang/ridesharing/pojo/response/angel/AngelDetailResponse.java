package com.bikiegang.ridesharing.pojo.response.angel;

import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.pojo.User;
import com.bikiegang.ridesharing.pojo.response.UserDetailResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by hpduy17 on 9/14/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AngelDetailResponse extends UserDetailResponse {
    private String facebookId = "";
    private String googleId = "";
    private String twitterId = "";
    private String linkedInId = "";
    private List<Long> groupIds;

    public AngelDetailResponse(User user) {
        super(user);
        this.facebookId = user.getFacebookId();
        this.twitterId = user.getTwitterId();
        this.googleId = user.getGoogleId();
        this.linkedInId = user.getLinkedInId();
        HashSet<Long> ids = Database.getInstance().getUserIdRFAngelGroups().get(user.getId());
        if(ids != null){
            groupIds = new ArrayList<>(ids);
        }
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    public String getTwitterId() {
        return twitterId;
    }

    public void setTwitterId(String twitterId) {
        this.twitterId = twitterId;
    }

    public String getLinkedInId() {
        return linkedInId;
    }

    public void setLinkedInId(String linkedInId) {
        this.linkedInId = linkedInId;
    }

    public List<Long> getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(List<Long> groupIds) {
        this.groupIds = groupIds;
    }
}
