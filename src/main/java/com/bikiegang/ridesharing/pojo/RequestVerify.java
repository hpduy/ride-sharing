package com.bikiegang.ridesharing.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang.RandomStringUtils;


/**
 * Created by hpduy17 on 6/30/15.
 */
public class RequestVerify implements PojoBase {
    private long id;
    private String userId = "";
    private String angelId = "";
    private int numberOfCertificate;
    private String signature = RandomStringUtils.random(NUM_CHAR_SIGNATURE);
    private int status;


    @JsonIgnore
    public static final int WAITING = 0;
    @JsonIgnore
    public static final int ACCEPT = 1;
    @JsonIgnore
    public static final int DENY = 2;

    @JsonIgnore
    public static final int NUM_CHAR_SIGNATURE = 5;

    public RequestVerify() {
    }

    public RequestVerify(long id, String userId, String angelId, int numberOfCertificate, String signature, int status) {
        this.id = id;
        this.userId = userId;
        this.angelId = angelId;
        this.numberOfCertificate = numberOfCertificate;
        this.signature = signature;
        this.status = status;
    }

    public RequestVerify(RequestVerify that) {
        this.id = that.id;
        this.userId = that.userId;
        this.angelId = that.angelId;
        this.numberOfCertificate = that.numberOfCertificate;
        this.signature = that.signature;
        this.status = that.status;
    }

    public void createSignature(){
        this.signature = RandomStringUtils.random(NUM_CHAR_SIGNATURE);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAngelId() {
        return angelId;
    }

    public void setAngelId(String angelId) {
        this.angelId = angelId;
    }

    public int getNumberOfCertificate() {
        return numberOfCertificate;
    }

    public void setNumberOfCertificate(int numberOfCertificate) {
        this.numberOfCertificate = numberOfCertificate;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
