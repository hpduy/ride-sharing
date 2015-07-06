package com.bikiegang.ridesharing.database;

import java.util.Set;

/**
 * Created by hpduy17 on 3/20/15.
 */
public class IdGenerator {
    private static long activityId = 0;
    private static long circleId = 0;
    private static long circleMemberId = 0;
    private static long currentLocationId = 0;
    private static long endorseId = 0;
    private static long linkedLocationId = 0;
    private static long locationId = 0;
    private static long ratingId = 0;
    private static long routeId = 0;
    private static long tripId = 0;
    private static long tripFeedbackId = 0;
    private static long userProfileId = 0;
    private static long verifiedCertificateId = 0;
    private static long requestMakeTripId = 0;
    private static Database database = Database.getInstance();

    public static synchronized long getActivityId() {
        Set<Long> idSet = database.getActivityHashMap().keySet();
        do {
            activityId++;
        }
        while (idSet.contains(activityId));
        return activityId;
    }

    public static synchronized long getCircleId() {
        Set<Long> idSet = database.getCircleHashMap().keySet();
        do {
            circleId++;
        }
        while (idSet.contains(circleId));
        return circleId;
    }

    public static synchronized long getCircleMemberId() {
        Set<Long> idSet = database.getCircleMemberHashMap().keySet();
        do {
            circleMemberId++;
        }
        while (idSet.contains(circleMemberId));
        return circleMemberId;
    }

    public static synchronized long getCurrentLocationId() {
        Set<Long> idSet = database.getCurrentLocationHashMap().keySet();
        do {
            currentLocationId++;
        }
        while (idSet.contains(currentLocationId));
        return currentLocationId;
    }

    public static synchronized long getEndorseId() {
        Set<Long> idSet = database.getEndorseHashMap().keySet();
        do {
            endorseId++;
        }
        while (idSet.contains(endorseId));
        return endorseId;
    }

    public static synchronized long getLinkedLocationId() {
        Set<Long> idSet = database.getLinkedLocationHashMap().keySet();
        do {
            linkedLocationId++;
        }
        while (idSet.contains(linkedLocationId));
        return linkedLocationId;
    }

    public static synchronized long getLocationId() {
        Set<Long> idSet = database.getLocationHashMap().keySet();
        do {
            locationId++;
        }
        while (idSet.contains(locationId));
        return locationId;
    }

    public static synchronized long getRatingId() {
        Set<Long> idSet = database.getRatingHashMap().keySet();
        do {
            ratingId++;
        }
        while (idSet.contains(ratingId));
        return ratingId;
    }

    public static synchronized long getRouteId() {
        Set<Long> idSet = database.getRouteHashMap().keySet();
        do {
            routeId++;
        }
        while (idSet.contains(routeId));
        return routeId;
    }

    public static synchronized long getTripId() {
        Set<Long> idSet = database.getTripHashMap().keySet();
        do {
            tripId++;
        }
        while (idSet.contains(tripId));
        return tripId;
    }

    public static synchronized long getTripFeedbackId() {
        Set<Long> idSet = database.getTripFeedbackHashMap().keySet();
        do {
            tripFeedbackId++;
        }
        while (idSet.contains(tripFeedbackId));
        return tripFeedbackId;
    }

    public static synchronized long getUserProfileId() {
        Set<Long> idSet = database.getUserProfileHashMap().keySet();
        do {
            userProfileId++;
        }
        while (idSet.contains(userProfileId));
        return userProfileId;
    }

    public static synchronized long getVerifiedCertificateId() {
        Set<Long> idSet = database.getVerifiedCertificateHashMap().keySet();
        do {
            verifiedCertificateId++;
        }
        while (idSet.contains(verifiedCertificateId));
        return verifiedCertificateId;
    }

    public static synchronized long getRequestMakeTripId() {
        Set<Long> idSet = database.getRequestMakeTripHashMap().keySet();
        do {
            requestMakeTripId++;
        }
        while (idSet.contains(requestMakeTripId));
        return requestMakeTripId;
    }
}
