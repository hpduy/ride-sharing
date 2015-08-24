package com.bikiegang.ridesharing.database;

import org.apache.commons.lang.math.RandomUtils;

import java.util.Set;

/**
 * Created by hpduy17 on 3/20/15.
 */
public class IdGenerator {
    private static Database database = Database.getInstance();


    public static synchronized long getLinkedLocationId() {
        Set<Long> idSet = database.getLinkedLocationHashMap().keySet();
        long linkedLocationId;
        do {
            linkedLocationId = RandomUtils.nextLong();
        }
        while (idSet.contains(linkedLocationId));
        return linkedLocationId;
    }

    public static synchronized long getPlannedTripId() {
        Set<Long> idSet = database.getPlannedTripHashMap().keySet();
        long plannedTripId;
        do {
            plannedTripId = RandomUtils.nextLong();
        }
        while (idSet.contains(plannedTripId));
        return plannedTripId;
    }

    public static synchronized long getTripId() {
        Set<Long> idSet = database.getTripHashMap().keySet();
        long tripId;
        do {
            tripId = RandomUtils.nextLong();
        }
        while (idSet.contains(tripId));
        return tripId;
    }

    public static synchronized long getRequestMakeTripId() {
        Set<Long> idSet = database.getRequestMakeTripHashMap().keySet();
        long requestMakeTripId;
        do {
            requestMakeTripId = RandomUtils.nextLong();
        }
        while (idSet.contains(requestMakeTripId));
        return requestMakeTripId;
    }

    public static synchronized long getGroupPlannedTripId() {
        Set<Long> idSet = database.getGroupIdRFPlannedTrips().keySet();
        long groupPlannedTripId;
        do {
            groupPlannedTripId = RandomUtils.nextLong();
        }
        while (idSet.contains(groupPlannedTripId));
        return groupPlannedTripId;
    }

    public static synchronized long getRequestVerifyId() {
        Set<Long> idSet = database.getRequestVerifyHashMap().keySet();
        long requestVerifyId;
        do {
            requestVerifyId = RandomUtils.nextLong();
        }
        while (idSet.contains(requestVerifyId));
        return requestVerifyId;
    }

    public static synchronized long getVerifiedCertificatedId() {
        Set<Long> idSet = database.getVerifiedCertificateHashMap().keySet();
        long verifiedCertificatedId;
        do {
            verifiedCertificatedId = RandomUtils.nextLong();
        }
        while (idSet.contains(verifiedCertificatedId));
        return verifiedCertificatedId;
    }

    public static synchronized long getAngelGroupId() {
        Set<Long> idSet = database.getAngelGroupHashMap().keySet();
        long angelGroupId;
        do {
            angelGroupId = RandomUtils.nextLong();
        }
        while (idSet.contains(angelGroupId));
        return angelGroupId;
    }

    public static synchronized long getAngelGroupMemberId() {
        Set<Long> idSet = database.getAngelGroupMemberHashMap().keySet();
        long angelGroupMemberId;
        do {
            angelGroupMemberId = RandomUtils.nextLong();
        }
        while (idSet.contains(angelGroupMemberId));
        return angelGroupMemberId;
    }

    public static synchronized long getPopularLocationId() {
        Set<Long> idSet = database.getPopularLocationHashMap().keySet();
        long popularLocationId;
        do {
            popularLocationId = RandomUtils.nextLong();
        }
        while (idSet.contains(popularLocationId));
        return popularLocationId;
    }

    public static synchronized long getFeedId() {
        Set<Long> idSet = database.getFeedHashMap().keySet();
        long feedId;
        do {
            feedId = RandomUtils.nextLong();
        }
        while (idSet.contains(feedId));
        return feedId;
    }

    public static synchronized long getRatingId() {
        Set<Long> idSet = database.getRatingHashMap().keySet();
        long ratingId;
        do {
            ratingId = RandomUtils.nextLong();
        }
        while (idSet.contains(ratingId));
        return ratingId;
    }

    public static synchronized long getSocialTripId() {
        Set<Long> idSet = database.getSocialTripHashMap().keySet();
        long socialTripId;
        do {
            socialTripId = RandomUtils.nextLong();
        }
        while (idSet.contains(socialTripId));
        return socialTripId;

    }

    public static synchronized long getSocialTripAttendanceId() {
        Set<Long> idSet = database.getSocialTripAttendanceHashMap().keySet();
        long socialTripAttendance;
        do {
            socialTripAttendance = RandomUtils.nextLong();
        }
        while (idSet.contains(socialTripAttendance));
        return socialTripAttendance;
    }

    public static synchronized long getGroupAngelGroupId() {
        Set<Long> idSet = database.getGroupIdRFAngelGroups().keySet();
        long groupAngelGroupId;
        do {
            groupAngelGroupId = RandomUtils.nextLong();
        }
        while (idSet.contains(groupAngelGroupId));
        return groupAngelGroupId;
    }
}
