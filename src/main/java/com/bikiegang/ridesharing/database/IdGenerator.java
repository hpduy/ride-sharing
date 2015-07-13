package com.bikiegang.ridesharing.database;

import java.util.Set;

/**
 * Created by hpduy17 on 3/20/15.
 */
public class IdGenerator {
    private static long linkedLocationId = 0;
    private static long routeId = 0;
    private static long tripId = 0;
    private static long requestMakeTripId = 0;
    private static Database database = Database.getInstance();


    public static synchronized long getLinkedLocationId() {
        Set<Long> idSet = database.getLinkedLocationHashMap().keySet();
        do {
            linkedLocationId++;
        }
        while (idSet.contains(linkedLocationId));
        return linkedLocationId;
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

    public static synchronized long getRequestMakeTripId() {
        Set<Long> idSet = database.getRequestMakeTripHashMap().keySet();
        do {
            requestMakeTripId++;
        }
        while (idSet.contains(requestMakeTripId));
        return requestMakeTripId;
    }
}
