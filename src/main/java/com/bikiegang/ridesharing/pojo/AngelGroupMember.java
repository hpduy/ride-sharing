package com.bikiegang.ridesharing.pojo;

/**
 * Created by hpduy17 on 8/8/15.
 */
public class AngelGroupMember implements PojoBase {

    private long id;
    private long groupId;
    private String angelId;
    private long createdTime;

    public AngelGroupMember() {
    }

    public AngelGroupMember(long id, long groupId, String angelId, long createdTime) {
        this.id = id;
        this.groupId = groupId;
        this.angelId = angelId;
        this.createdTime = createdTime;
    }

    public AngelGroupMember(AngelGroupMember that) {
        this.id = that.id;
        this.groupId = that.groupId;
        this.angelId = that.angelId;
        this.createdTime = that.createdTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public String getAngelId() {
        return angelId;
    }

    public void setAngelId(String angelId) {
        this.angelId = angelId;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }
}
