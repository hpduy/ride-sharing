package com.bikiegang.ridesharing.database;

import com.bikiegang.ridesharing.cache.RideSharingCA;
import com.bikiegang.ridesharing.config.ConfigInfo;
import com.bikiegang.ridesharing.geocoding.GeoCell;
import com.bikiegang.ridesharing.pojo.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Created by hpduy17 on 6/16/15.
 */
public class Database {

    private static Database ourInstance = new Database();

    public static Database getInstance() {
        return ourInstance;
    }

    private Database() {
    }

    // STATIC DATABASE STATUS
    public static int databaseStatus = Database.DEVELOPMENT;
    //FINAL STATE
    public static final int DEVELOPMENT = 0;
    public static final int PRODUCTION = 1;
    public static final int TESTING = 2;

    /*DATABASE-TABLE*/
    //CORE
    private HashMap<String, Broadcast> broadcastHashMap = new HashMap<>();
    private HashMap<Long, LinkedLocation> linkedLocationHashMap = new HashMap<>();
    private HashMap<Long, Route> routeHashMap = new HashMap<>();
    private HashMap<Long, Trip> tripHashMap = new HashMap<>();
    private HashMap<String, User> userHashMap = new HashMap<>();
    private HashMap<Long, RequestMakeTrip> requestMakeTripHashMap = new HashMap<>();
    //REFERENCE
    /**
     * USER
     */
    private HashMap<String, String> facebookRFUserId = new HashMap<>();//<fbId, userId>
    private HashMap<String, String> googleRFUserId = new HashMap<>();//<ggId, userId>
    private HashMap<String, String> twitterRFUserId = new HashMap<>();//<twId, userId>
    private HashMap<String, String> emailRFUserId = new HashMap<>();//<email, userId>
    private HashMap<String, String> linkedInRFUserId = new HashMap<>();//<lkId, userId>
    /**
     * BROADCAST
     */
    private HashMap<String, HashSet<String>> userIdRFBroadcasts = new HashMap<>(); //<userId,<broadcastId>>

    /**
     * ROUTE
     */
    private HashMap<String, HashSet<Long>> userIdRFRoutes = new HashMap<>(); // <userId,<routeId>>
    private HashMap<Integer, HashSet<Long>> roleRFRoutes = new HashMap<>(); // <role,<routeId>>
    /**
     * TRIP
     */
    private HashMap<String, HashSet<Long>> driverIdRFTrips = new HashMap<>(); // <userId,<tripId>>
    private HashMap<String, HashSet<Long>> passengerIdRFTrips = new HashMap<>(); // <userId,<tripId>>
    /**
     * REQUEST MAKE TRIP
     */
    private HashMap<String, HashMap<Long, Long>> senderRequestsBox = new HashMap<>(); //<senderId,<receiverRouteId,requestIds>>
    private HashMap<String, HashMap<Long, List<Long>>> receiverRequestsBox = new HashMap<>(); //<receiverId,<receiverRouteId,<requestIds>>>
    /**
     * LINKED LOCATION
     */
    private HashMap<Long, List<Long>> routeIdRFLinkedLocations = new HashMap<>(); //<routeId,<LinkedLocationId>>

    /**
     * GEOCELL
     */
    private GeoCell geoCellDriver = new GeoCell(); // for route
    private GeoCell geoCellPassenger = new GeoCell();// for route
    private GeoCell geoCellCurrentLocation = new GeoCell();

//    /*PERSONAL FUNCTION*/
    public void restore() {
        RideSharingCA rideSharingCA = RideSharingCA.getInstance(ConfigInfo.REDIS_SERVER);
        //restore
        assert (rideSharingCA.RestoreDatabase());
    }

    public void backup() {
    }
    /*GET-SET Function*/
    /*CORE GET-SET*/

    public HashMap<String, Broadcast> getBroadcastHashMap() {
        return broadcastHashMap;
    }

    public HashMap<Long, LinkedLocation> getLinkedLocationHashMap() {
        return linkedLocationHashMap;
    }

    public HashMap<Long, Route> getRouteHashMap() {
        return routeHashMap;
    }

    public HashMap<Long, Trip> getTripHashMap() {
        return tripHashMap;
    }

    public HashMap<String, User> getUserHashMap() {
        return userHashMap;
    }

    public HashMap<Long, RequestMakeTrip> getRequestMakeTripHashMap() {
        return requestMakeTripHashMap;
    }

    /*REFERENCE GET-SET*/
    public HashMap<String, String> getFacebookRFUserId() {
        return facebookRFUserId;
    }

    public HashMap<String, String> getGoogleRFUserId() {
        return googleRFUserId;
    }

    public HashMap<String, String> getTwitterRFUserId() {
        return twitterRFUserId;
    }

    public HashMap<String, String> getEmailRFUserId() {
        return emailRFUserId;
    }

    public HashMap<String, HashSet<String>> getUserIdRFBroadcasts() {
        return userIdRFBroadcasts;
    }

    public HashMap<String, HashSet<Long>> getUserIdRFRoutes() {
        return userIdRFRoutes;
    }

    public HashMap<String, HashSet<Long>> getDriverIdRFTrips() {
        return driverIdRFTrips;
    }

    public HashMap<String, HashSet<Long>> getPassengerIdRFTrips() {
        return passengerIdRFTrips;
    }

    public HashMap<Long, List<Long>> getRouteIdRFLinkedLocations() {
        return routeIdRFLinkedLocations;
    }

    public HashMap<Integer, HashSet<Long>> getRoleRFRoutes() {
        return roleRFRoutes;
    }

    public HashMap<String, HashMap<Long, Long>> getSenderRequestsBox() {
        return senderRequestsBox;
    }

    public HashMap<String, HashMap<Long, List<Long>>> getReceiverRequestsBox() {
        return receiverRequestsBox;
    }

    public HashMap<String, String> getLinkedInRFUserId() {
        return linkedInRFUserId;
    }

    /*GEOCELL GET-SET*/
    public GeoCell getGeoCellDriver() {
        return geoCellDriver;
    }

    public void setGeoCellDriver(GeoCell geoCellDriver) {
        this.geoCellDriver = geoCellDriver;
    }

    public GeoCell getGeoCellPassenger() {
        return geoCellPassenger;
    }

    public void setGeoCellPassenger(GeoCell geoCellPassenger) {
        this.geoCellPassenger = geoCellPassenger;

    }

    public GeoCell getGeoCellCurrentLocation() {
        return geoCellCurrentLocation;
    }
}
