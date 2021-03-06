package com.bikiegang.ridesharing.pojo;


/**
 * Created by hpduy17 on 6/24/15.
 */
public class VerifiedCertificate implements PojoBase {
    protected long id;
    protected String image = "";
    protected String note = "";
    protected long createdTime;
    protected int type;
    protected String ownerId = "";
    protected String endorserId = "";
    protected int status;
    // final field
   
    public VerifiedCertificate() {
    }

    public VerifiedCertificate(long id, String image, String note, long createdTime, int type, String ownerId, String endorserId, int status) {
        this.id = id;
        this.image = image == null ? "" : image;
        this.note = note == null ? "" : note;
        this.createdTime = createdTime;
        this.type = type;
        this.ownerId = ownerId == null ? "" : ownerId;
        this.endorserId = endorserId == null ? "" : endorserId;
        this.status = status;
    }

    public VerifiedCertificate(VerifiedCertificate that) {
        this.id = that.id;
        this.image = that.image == null ? "" : that.image;
        this.note = that.note == null ? "" : that.note;
        this.createdTime = that.createdTime;
        this.type = that.type;
        this.ownerId = that.ownerId == null ? "" : that.ownerId;
        this.endorserId = that.endorserId == null ? "" : that.endorserId;
        this.status = that.status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getEndorserId() {
        return endorserId;
    }

    public void setEndorserId(String endorserId) {
        this.endorserId = endorserId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
