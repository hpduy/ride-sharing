package com.bikiegang.ridesharing.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by hpduy17 on 7/22/15.
 */
public class CertificateDetail {

    protected String fullname = "";
    protected String idNumber = "";
    protected String dayOfBirth = ""; //yyyyMMdd
    protected String address = "";
    protected String registerDay = "";
    protected String expiredDay = "";
    protected String[] image;
    protected int type;
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

    public CertificateDetail() {
    }

    public CertificateDetail(String fullname, String idNumber, String dayOfBirth, String address, String registerDay, String expiredDay, String[] image, int type) {
        this.fullname = fullname;
        this.idNumber = idNumber;
        this.dayOfBirth = dayOfBirth;
        this.address = address;
        this.registerDay = registerDay;
        this.expiredDay = expiredDay;
        this.image = image;
        this.type = type;
    }

    public CertificateDetail(CertificateDetail that) {
        this.fullname = that.fullname;
        this.idNumber = that.idNumber;
        this.dayOfBirth = that.dayOfBirth;
        this.address = that.address;
        this.registerDay = that.registerDay;
        this.expiredDay = that.expiredDay;
        this.image = that.image;
        this.type = that.type;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(String dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRegisterDay() {
        return registerDay;
    }

    public void setRegisterDay(String registerDay) {
        this.registerDay = registerDay;
    }

    public String getExpiredDay() {
        return expiredDay;
    }

    public void setExpiredDay(String expiredDay) {
        this.expiredDay = expiredDay;
    }

    public String[] getImage() {
        return image;
    }

    public void setImage(String[] image) {
        this.image = image;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
