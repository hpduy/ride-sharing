package com.bikiegang.ridesharing.pojo.request;

import com.bikiegang.ridesharing.pojo.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 6/26/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterRequest extends User {
    private int type;
    //final field
    @JsonIgnore
    public static final int EMAIL = 0;
    @JsonIgnore
    public static final int FACEBOOK = 1;
    @JsonIgnore
    public static final int GOOGLE = 2;


}
