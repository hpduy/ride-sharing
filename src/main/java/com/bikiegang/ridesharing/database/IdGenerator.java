package com.bikiegang.ridesharing.database;

import org.apache.commons.lang.math.RandomUtils;

import java.util.Set;

/**
 * Created by hpduy17 on 3/20/15.
 */
public class IdGenerator {
    private static long linkedLocationId = 0;
    private static long plannedTripId = 0;
    private static long tripId = 0;
    private static long requestMakeTripId = 0;
    private static long requestVerifyId = 0;
    private static long verifiedCertificatedId = 0;
    private static long groupPlannedTripId = 0;
    private static Database database = Database.getInstance();


    public static synchronized long getLinkedLocationId() {
        Set<Long> idSet = database.getLinkedLocationHashMap().keySet();
        do {
            linkedLocationId = RandomUtils.nextLong();
        }
        while (idSet.contains(linkedLocationId));
        return linkedLocationId;
    }

    public static synchronized long getPlannedTripId() {
        Set<Long> idSet = database.getPlannedTripHashMap().keySet();
        do {
            plannedTripId = RandomUtils.nextLong();
        }
        while (idSet.contains(plannedTripId));
        return plannedTripId;
    }

    public static synchronized long getTripId() {
        Set<Long> idSet = database.getTripHashMap().keySet();
        do {
            tripId = RandomUtils.nextLong();
        }
        while (idSet.contains(tripId));
        return tripId;
    }

    public static synchronized long getRequestMakeTripId() {
        Set<Long> idSet = database.getRequestMakeTripHashMap().keySet();
        do {
            requestMakeTripId = RandomUtils.nextLong();
        }
        while (idSet.contains(requestMakeTripId));
        return requestMakeTripId;
    }

    public static long getGroupPlannedTripId() {
        Set<Long> idSet = database.getGroupIdRFPlannedTrips().keySet();
        do {
            groupPlannedTripId = RandomUtils.nextLong();
        }
        while (idSet.contains(groupPlannedTripId));
        return groupPlannedTripId;
    }

    public static long getRequestVerifyId() {
        Set<Long > idSet = database.getRequestVerifyHashMap().keySet();
        do {
            requestVerifyId = RandomUtils.nextLong();
        }
        while (idSet.contains(requestVerifyId));
        return requestVerifyId;
    }

    public static long getVerifiedCertificatedId() {
        Set<Long > idSet = database.getVerifiedCertificateHashMap().keySet();
        do {
            verifiedCertificatedId = RandomUtils.nextLong();
        }
        while (idSet.contains(verifiedCertificatedId));
        return verifiedCertificatedId;
    }
}
