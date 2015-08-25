package com.bikiegang.ridesharing.pojo.request.angel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 8/8/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class JoinGroupRequest {
    private String userId;
    private long[] groupIds;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long[] getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(long[] groupIds) {
        this.groupIds = groupIds;
    }
}
