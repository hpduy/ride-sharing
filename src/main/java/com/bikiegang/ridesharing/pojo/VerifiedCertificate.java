package com.bikiegang.ridesharing.pojo;

import com.bikiegang.ridesharing.pojo.type.CertificateType;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by hpduy17 on 6/24/15.
 */
public class VerifiedCertificate {
    private long id;
    private String image;
    private String note;
    private long createdTime;
    private CertificateType type;
    private String ownerId;
    private String endorserId;
    private int status;
    // final field
    @JsonIgnore
    public static final int WAITING = 0;
    @JsonIgnore
    public static final int AVAILABLE = 1;
    @JsonIgnore
    public static final int OUT_OF_DATE = 2;

    public VerifiedCertificate() {
    }

    public VerifiedCertificate(long id, String image, String note, long createdTime, CertificateType type, String ownerId, String endorserId, int status) {
        this.id = id;
        this.image = image;
        this.note = note;
        this.createdTime = createdTime;
        this.type = type;
        this.ownerId = ownerId;
        this.endorserId = endorserId;
        this.status = status;
    }

    public VerifiedCertificate(VerifiedCertificate that) {
        this.id = that.id;
        this.image = that.image;
        this.note = that.note;
        this.createdTime = that.createdTime;
        this.type = that.type;
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

    public CertificateType getType() {
        return type;
    }

    public void setType(CertificateType type) {
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
