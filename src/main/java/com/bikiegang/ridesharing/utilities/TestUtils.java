/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bikiegang.ridesharing.utilities;

import com.bikiegang.ridesharing.pojo.AngelGroup;
import com.bikiegang.ridesharing.pojo.AngelGroupMember;
import com.bikiegang.ridesharing.pojo.Broadcast;
import com.bikiegang.ridesharing.pojo.CertificateDetail;
import com.bikiegang.ridesharing.pojo.LatLng;
import com.bikiegang.ridesharing.pojo.LinkedLocation;
import com.bikiegang.ridesharing.pojo.RequestMakeTrip;
import com.bikiegang.ridesharing.pojo.PlannedTrip;
import com.bikiegang.ridesharing.pojo.RequestVerify;
import com.bikiegang.ridesharing.pojo.Trip;
import com.bikiegang.ridesharing.pojo.User;
import com.bikiegang.ridesharing.pojo.VerifiedCertificate;
import java.util.ArrayList;
import java.util.List;
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
                RandomStringUtils.randomAlphabetic(20),
                RandomUtils.nextInt());
        return result;
    }

    public static LinkedLocation CreateLinkedLocation() {
        LinkedLocation result = new LinkedLocation(RandomUtils.nextDouble(),
                RandomUtils.nextDouble(),
                RandomUtils.nextLong(),
                RandomUtils.nextLong(),
                RandomUtils.nextLong(),
                RandomUtils.nextInt(),
                RandomUtils.nextLong(),
                RandomUtils.nextInt());

        return result;
    }

    public static RequestMakeTrip CreateRequestMakeTrip() {
        RequestMakeTrip result = new RequestMakeTrip(RandomUtils.nextLong(),
                RandomStringUtils.randomAlphabetic(20),
                RandomStringUtils.randomAlphabetic(20),
                RandomUtils.nextLong(),
                RandomUtils.nextLong(),
                RandomUtils.nextLong(),
                RandomUtils.nextLong(),
                2,
                RandomUtils.nextInt());

        return result;
    }

    public static PlannedTrip CreatePlannedTrip() {
        PlannedTrip result = new PlannedTrip(RandomUtils.nextLong(),
                RandomUtils.nextLong(),
                RandomUtils.nextLong(),
                RandomUtils.nextDouble(),
                RandomUtils.nextInt(),
                RandomStringUtils.randomAlphabetic(20),
                RandomUtils.nextInt(),
                RandomUtils.nextDouble(),
                RandomStringUtils.randomAlphabetic(20),
                new JSONObject(),
                RandomUtils.nextLong());
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
                true,
                true,
                RandomUtils.nextLong(),
                RandomUtils.nextLong(),
                RandomStringUtils.randomAlphabetic(20));

        return result;
    }

    public static User CreateUser() {
        User result = new User(RandomStringUtils.randomAlphabetic(20),
                RandomStringUtils.randomAlphabetic(20),
                RandomStringUtils.randomAlphabetic(20),
                RandomStringUtils.randomAlphabetic(20),
                RandomStringUtils.randomAlphabetic(20),
                RandomStringUtils.randomAlphabetic(20),
                RandomStringUtils.randomAlphabetic(20),
                RandomStringUtils.randomAlphabetic(20),
                RandomStringUtils.randomAlphabetic(20),
                RandomStringUtils.randomAlphabetic(20),
                RandomStringUtils.randomAlphabetic(20),
                RandomStringUtils.randomAlphabetic(20),
                RandomStringUtils.randomAlphabetic(10),
                RandomUtils.nextInt(),
                RandomUtils.nextInt(), true);
        return result;
    }

    public static AngelGroup CreateAngelGroup() {

        List<String> tags = new ArrayList<>();
        tags.add(RandomStringUtils.randomAlphanumeric(10));
        tags.add(RandomStringUtils.randomAlphanumeric(10));
        tags.add(RandomStringUtils.randomAlphanumeric(10));
        tags.add(RandomStringUtils.randomAlphanumeric(10));

        AngelGroup value = new AngelGroup(RandomUtils.nextLong(),
                new LatLng(RandomUtils.nextDouble(),
                        RandomUtils.nextDouble()),
                tags,
                RandomUtils.nextLong(),
                RandomStringUtils.randomAlphanumeric(30)
        );

        return value;
    }

    public static AngelGroupMember CreateAngelGroupMember() {
        AngelGroupMember value = new AngelGroupMember(RandomUtils.nextLong(),
                RandomUtils.nextLong(),
                RandomStringUtils.randomAlphanumeric(20),
                RandomUtils.nextLong());
        return value;
    }

    public static RequestVerify CreateRequestVerify() {
        RequestVerify value = new RequestVerify(RandomUtils.nextLong(),
                RandomStringUtils.randomAlphanumeric(20),
                RandomStringUtils.randomAlphanumeric(20),
                RandomUtils.nextInt(),
                RandomStringUtils.randomAlphanumeric(20),
                RandomUtils.nextInt());
        return value;
    }

    public static VerifiedCertificate CreateVerifiedCertificate() {
        VerifiedCertificate value = new VerifiedCertificate(new CertificateDetail(RandomStringUtils.randomAlphanumeric(20),
                RandomStringUtils.randomAlphanumeric(25),
                RandomStringUtils.randomAlphanumeric(20),
                RandomStringUtils.randomAlphabetic(20),
                new String[]{
                    RandomStringUtils.randomAlphabetic(20),
                    RandomStringUtils.randomAlphanumeric(20)
                },
                RandomUtils.nextInt()),
                RandomUtils.nextLong(),
                RandomStringUtils.randomAlphabetic(20),
                RandomUtils.nextLong(),
                RandomStringUtils.randomAlphanumeric(20),
                RandomStringUtils.randomAlphanumeric(20),
                RandomUtils.nextInt());

        return value;
    }
}
