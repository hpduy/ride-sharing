package com.bikiegang.ridesharing.pojo.response.angel;

import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.pojo.AngelGroup;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashSet;

/**
 * Created by hpduy17 on 7/23/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AngelGroupDetailResponse extends AngelGroup {
    private boolean joined = false;

    public AngelGroupDetailResponse(AngelGroup that, String userId) {
        super(that);
        HashSet<String> members = Database.getInstance().getAngelGroupIdRFUsers().get(that.getId());
        if (userId!= null && !userId.equals("") && null != members && members.contains(userId))
            this.joined = true;
    }

    public boolean isJoined() {
        return joined;
    }

    public void setJoined(boolean joined) {
        this.joined = joined;
    }
}
