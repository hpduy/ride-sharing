package com.bikiegang.ridesharing.pojo.request;

import com.bikiegang.ridesharing.annn.framework.util.JSONUtil;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 8/8/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MergeGroupRequest {

    private long firstGroupId;
    private long secondGroupId;

    public long getFirstGroupId() {
        return firstGroupId;
    }

    public void setFirstGroupId(long firstGroupId) {
        this.firstGroupId = firstGroupId;
    }

    public long getSecondGroupId() {
        return secondGroupId;
    }

    public void setSecondGroupId(long secondGroupId) {
        this.secondGroupId = secondGroupId;
    }

    public MergeGroupRequest() {
    }

    public MergeGroupRequest(long firstGroupId, long secondGroupId) {
        this.firstGroupId = firstGroupId;
        this.secondGroupId = secondGroupId;
    }

    @Override
    public String toString() {
        return JSONUtil.Serialize(this);
    }
}
