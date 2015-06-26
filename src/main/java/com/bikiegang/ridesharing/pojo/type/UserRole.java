package com.bikiegang.ridesharing.pojo.type;

/**
 * Created by hpduy17 on 6/24/15.
 */
public enum UserRole {
    DRIVER,
    PASSENGER;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
