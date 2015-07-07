/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bikiegang.ridesharing.utilities;

import com.bikiegang.ridesharing.pojo.Activity;
import com.bikiegang.ridesharing.pojo.Broadcast;
import com.bikiegang.ridesharing.pojo.Circle;
import com.bikiegang.ridesharing.pojo.CircleMember;
import com.bikiegang.ridesharing.pojo.CurrentLocation;
import com.bikiegang.ridesharing.pojo.Endorse;
import com.bikiegang.ridesharing.pojo.LinkedLocation;
import com.bikiegang.ridesharing.pojo.Location;
import com.bikiegang.ridesharing.pojo.Rating;
import com.bikiegang.ridesharing.pojo.RequestMakeTrip;
import com.bikiegang.ridesharing.pojo.Route;
import com.bikiegang.ridesharing.pojo.Trip;
import com.bikiegang.ridesharing.pojo.TripFeedback;
import com.bikiegang.ridesharing.pojo.User;
import com.bikiegang.ridesharing.pojo.UserProfile;
import com.bikiegang.ridesharing.pojo.VerifiedCertificate;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;

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

    public static Activity CreateActivity() {
        Activity result = new Activity(RandomUtils.nextLong(),
                RandomStringUtils.randomAlphabetic(20),
                RandomStringUtils.randomAlphabetic(20),
                RandomStringUtils.randomAlphabetic(20),
                RandomUtils.nextLong(),
                RandomUtils.nextInt(),
                RandomUtils.nextLong(),
                true);
        return result;
    }

    public static Broadcast CreateBroadcast() {
        Broadcast result = new Broadcast(RandomStringUtils.randomAlphabetic(20),
                RandomStringUtils.randomAlphabetic(20),
                RandomStringUtils.randomAlphabetic(20),
                RandomStringUtils.randomAlphabetic(20), RandomUtils.nextInt());
        return result;
    }

    public static Circle CreateCircle() {
        Circle result = new Circle(RandomUtils.nextLong(), RandomStringUtils.randomAlphabetic(20),
                RandomStringUtils.randomAlphabetic(20), RandomUtils.nextLong(), RandomUtils.nextInt());

        return result;
    }

    public static CircleMember CreateCircleMember() {
        CircleMember result = new CircleMember(RandomUtils.nextLong(),
                RandomUtils.nextLong(),
                RandomStringUtils.randomAlphabetic(20),
                RandomUtils.nextLong());

        return result;
    }

    public static CurrentLocation CreateCurrentLocation() {
        CurrentLocation result = new CurrentLocation(RandomUtils.nextDouble(), RandomUtils.nextDouble(),
                RandomUtils.nextLong(),
                RandomUtils.nextLong(), RandomStringUtils.randomAlphabetic(20), RandomStringUtils.randomAlphabetic(20),
                RandomUtils.nextLong());

        return result;
    }

    public static Endorse CreateEndorse() {
        Endorse result = new Endorse(RandomUtils.nextLong(),
                RandomStringUtils.randomAlphabetic(20),
                RandomStringUtils.randomAlphabetic(20),
                RandomUtils.nextLong(),
                RandomStringUtils.randomAlphabetic(20),
                RandomUtils.nextLong());

        return result;
    }

    public static LinkedLocation CreateLinkedLocation() {
        LinkedLocation result = new LinkedLocation(new Location(RandomUtils.nextDouble(), RandomUtils.nextDouble(),
                RandomUtils.nextLong(), RandomUtils.nextLong(), RandomStringUtils.randomAlphabetic(20)),
                RandomUtils.nextInt(), RandomUtils.nextLong(), RandomUtils.nextInt());

        return result;
    }

    public static Location CreateLocation() {
        Location result = new Location(RandomUtils.nextDouble(), RandomUtils.nextDouble(),
                RandomUtils.nextLong(), RandomUtils.nextLong(), RandomStringUtils.randomAlphabetic(20));

        return result;
    }

    public static Rating CreateRating() {
        Rating result = new Rating(RandomUtils.nextLong(), RandomStringUtils.randomAlphabetic(20),
                RandomUtils.nextLong(), RandomUtils.nextInt(), RandomUtils.nextLong(), RandomStringUtils.randomAlphabetic(20));

        return result;
    }

    public static RequestMakeTrip CreateRequestMakeTrip() {
        RequestMakeTrip result = new RequestMakeTrip(RandomUtils.nextLong(), RandomStringUtils.randomAlphabetic(20),
                RandomStringUtils.randomAlphabetic(20), RandomUtils.nextLong(),
                RandomUtils.nextLong(), RandomUtils.nextInt());

        return result;
    }

    public static Route CreateRoute() {
        Route result = new Route(RandomUtils.nextLong(), RandomUtils.nextLong(), RandomUtils.nextLong(),
                RandomUtils.nextDouble(), RandomUtils.nextInt(), RandomStringUtils.randomAlphabetic(20),
                RandomUtils.nextInt(), RandomUtils.nextDouble());

        return result;
    }

    public static Trip CreateTrip() {
        Trip result = new Trip(RandomUtils.nextLong(), RandomUtils.nextLong(),
                RandomStringUtils.randomAlphabetic(20), RandomStringUtils.randomAlphabetic(20));

        return result;
    }

    public static TripFeedback CreateTripFeedback() {
        TripFeedback result = new TripFeedback(RandomUtils.nextLong(), RandomUtils.nextLong(),
                RandomUtils.nextInt(), RandomUtils.nextLong(), RandomStringUtils.randomAlphabetic(20), RandomStringUtils.randomAlphabetic(20),
                RandomStringUtils.randomAlphabetic(20));

        return result;
    }

    public static User CreateUser() {
        User result = new User(RandomStringUtils.randomAlphabetic(20),
                RandomStringUtils.randomAlphabetic(20), RandomStringUtils.randomAlphabetic(20), RandomStringUtils.randomAlphabetic(20),
                RandomStringUtils.randomAlphabetic(20), RandomStringUtils.randomAlphabetic(20), RandomStringUtils.randomAlphabetic(20),
                RandomStringUtils.randomAlphabetic(20), RandomStringUtils.randomAlphabetic(20), RandomStringUtils.randomAlphabetic(20),
                RandomUtils.nextLong(), RandomUtils.nextInt(), RandomUtils.nextInt(), true);

        return result;
    }

    public static UserProfile CreateUserProfile() {
        UserProfile result = new UserProfile(RandomUtils.nextLong(), RandomStringUtils.randomAlphabetic(20),
                RandomStringUtils.randomAlphabetic(20), RandomUtils.nextLong(), RandomStringUtils.randomAlphabetic(20));
        return result;
    }

    public static VerifiedCertificate CreateVerifiedCertificate() {
        VerifiedCertificate result = new VerifiedCertificate(RandomUtils.nextLong(),
                RandomStringUtils.randomAlphabetic(20), RandomStringUtils.randomAlphabetic(20),
                RandomUtils.nextLong(), RandomUtils.nextInt(), RandomStringUtils.randomAlphabetic(20),
                RandomStringUtils.randomAlphabetic(20), RandomUtils.nextInt());
        return result;
    }
}
