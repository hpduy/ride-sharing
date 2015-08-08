package com.bikiegang.ridesharing.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by hpduy17 on 7/21/15.
 */
public class VerifiedCertificate extends CertificateDetail implements PojoBase {
    private long id;
    private String note = "";
    private long createdTime;
    private String ownerId = "";
    private String endorserId = "";
    private int status;

    // final field
    /**
     * STATUS
     */
    @JsonIgnore
    public static final int WAITING = 0;
    @JsonIgnore
    public static final int AVAILABLE = 1;
    @JsonIgnore
    public static final int IS_EXPIRED = 2;
    public VerifiedCertificate() {
        super();
    }


    public VerifiedCertificate(CertificateDetail that, long id, String note, long createdTime, String ownerId, String endorserId, int status) {
        super(that);
        this.id = id;
        this.note = note;
        this.createdTime = createdTime;
        this.ownerId = ownerId;
        this.endorserId = endorserId;
        this.status = status;
    }
     public VerifiedCertificate(VerifiedCertificate that) {
        super(that);
        this.id = that.id;
        this.note = that.note;
        this.createdTime = that.createdTime;
        this.ownerId = that.ownerId;
        this.endorserId = that.endorserId;
        this.status = that.status;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
