/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bikiegang.ridesharing.utilities;

import com.bikiegang.ridesharing.pojo.Broadcast;
import com.bikiegang.ridesharing.pojo.LinkedLocation;
import com.bikiegang.ridesharing.pojo.RequestMakeTrip;
import com.bikiegang.ridesharing.pojo.PlannedTrip;
import com.bikiegang.ridesharing.pojo.Trip;
import com.bikiegang.ridesharing.pojo.User;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.json.JSONObject;

/**
 *
 * @author root
 */
public class TestUtils {

    private TestUtils() {
    }

    public static TestUtils getInstance() {
        return TestUtilsHolder.INSTANCE;
    }

    private static class TestUtilsHolder {

        private static final TestUtils INSTANCE = new TestUtils();
    }

    public static Broadcast CreateBroadcast() {
        Broadcast result = new Broadcast(RandomStringUtils.randomAlphabetic(20),
                RandomStringUtils.randomAlphabetic(20),
                RandomStringUtils.randomAlphabetic(20),
                RandomStringUtils.randomAlphabetic(20), RandomUtils.nextInt());
        return result;
    }

    public static LinkedLocation CreateLinkedLocation() {
        LinkedLocation result = new LinkedLocation(RandomUtils.nextDouble(), RandomUtils.nextDouble(),
                RandomUtils.nextLong(), RandomUtils.nextLong(), RandomUtils.nextLong(),
                RandomUtils.nextInt(), RandomUtils.nextLong(), RandomUtils.nextInt());

        return result;
    }

    public static RequestMakeTrip CreateRequestMakeTrip() {
        RequestMakeTrip result = new RequestMakeTrip(RandomUtils.nextLong(),
                RandomStringUtils.randomAlphabetic(20), RandomStringUtils.randomAlphabetic(20), RandomUtils.nextLong(),
                RandomUtils.nextLong(),
                RandomUtils.nextLong(), RandomUtils.nextLong(), RandomUtils.nextInt(), RandomUtils.nextInt());

        return result;
    }

    public static PlannedTrip CreateRoute() {
        PlannedTrip result = new PlannedTrip(RandomUtils.nextLong(), RandomUtils.nextLong(), RandomUtils.nextLong(),
                RandomUtils.nextDouble(), RandomUtils.nextInt(), RandomStringUtils.randomAlphabetic(20),
                RandomUtils.nextInt(), RandomUtils.nextDouble(), RandomStringUtils.randomAlphabetic(20), new JSONObject());

        return result;
    }

    public static Trip CreateTrip() {
        Trip result = new Trip(RandomUtils.nextLong(),
                RandomUtils.nextLong(),
                RandomUtils.nextLong(),
                RandomStringUtils.randomAlphabetic(20),
                RandomStringUtils.randomAlphabetic(20),
                RandomUtils.nextDouble(),
                RandomUtils.nextDouble(),
                RandomUtils.nextLong(),
                RandomStringUtils.randomAlphabetic(20),
                true, true,
                RandomUtils.nextLong(),
                RandomUtils.nextLong(),
                RandomStringUtils.randomAlphabetic(20));

        return result;
    }

    public static User CreateUser() {
        User result = new User(RandomStringUtils.randomAlphabetic(20),
                RandomStringUtils.randomAlphabetic(20), RandomStringUtils.randomAlphabetic(20), RandomStringUtils.randomAlphabetic(20),
                RandomStringUtils.randomAlphabetic(20), RandomStringUtils.randomAlphabetic(20), RandomStringUtils.randomAlphabetic(20),
                RandomStringUtils.randomAlphabetic(20), RandomStringUtils.randomAlphabetic(20),
                RandomStringUtils.randomAlphabetic(20),
                RandomStringUtils.randomAlphabetic(20),RandomStringUtils.randomAlphabetic(20),
                RandomStringUtils.randomAlphabetic(10), RandomUtils.nextInt(),
                RandomUtils.nextInt(), true);
        return result;
    }

}
