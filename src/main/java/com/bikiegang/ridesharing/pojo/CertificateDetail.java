package com.bikiegang.ridesharing.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by hpduy17 on 7/22/15.
 */
public class CertificateDetail {

    private String fullname = "";
    private String idNumber = "";
    private String dayOfBirth = ""; //yyyyMMdd
    private String address = "";
    private String[] image;
    private int type;
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
}
