package com.bikiegang.ridesharing.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 7/22/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CertificateDetail {

    protected String idNumber = "";
    protected String address = "";
    protected String regoDay = "";
    protected String expiryDay = "";
    protected String[] imageLinks;
    protected int type;
    protected String organization = "";
    @JsonIgnore
    public static final int VISA = 1;
    @JsonIgnore
    public static final int PASSPORT = 2;
    @JsonIgnore
    public static final int IDENTIFY_CARD = 3;
    @JsonIgnore
    public static final int STUDENT_CARD = 4;
    @JsonIgnore
    public static final int DRIVER_LICENSE = 5;
    @JsonIgnore
    public static final int OTHER = 6;

    public CertificateDetail() {

    }

    public CertificateDetail(String idNumber, String address, String regoDay, String expiryDay, String[] image, int type) {
        this.idNumber = idNumber;
        this.address = address;
        this.regoDay = regoDay;
        this.expiryDay = expiryDay;
        this.imageLinks = image;
        this.type = type;
    }

    public CertificateDetail(CertificateDetail that) {
        this.idNumber = that.idNumber;
        this.address = that.address;
        this.regoDay = that.regoDay;
        this.expiryDay = that.expiryDay;
        this.imageLinks = that.imageLinks;
        this.type = that.type;
        this.organization = that.organization;
    }


    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRegoDay() {
        return regoDay;
    }

    public void setRegoDay(String regoDay) {
        this.regoDay = regoDay;
    }

    public String getExpiryDay() {
        return expiryDay;
    }

    public void setExpiryDay(String expiryDay) {
        this.expiryDay = expiryDay;
    }

    public String[] getImageLinks() {
        return imageLinks;
    }

    public void setImageLinks(String[] imageLinks) {
        this.imageLinks = imageLinks;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }
}
