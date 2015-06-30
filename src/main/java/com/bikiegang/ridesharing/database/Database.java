package com.bikiegang.ridesharing.database;


import com.bikiegang.ridesharing.geocoding.GeoCell;
import com.bikiegang.ridesharing.pojo.*;
import kyotocabinet.DB;

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

    /*DATABASE-TABLE*/
    //CORE
    private HashMap<Long, Activity> activityHashMap = new HashMap<>();
    private HashMap<String, Broadcast> broadcastHashMap = new HashMap<>();
    private HashMap<Long, Circle> circleHashMap = new HashMap<>();
    private HashMap<Long, CircleMember> circleMemberHashMap = new HashMap<>();
    private HashMap<Long, CurrentLocation> currentLocationHashMap = new HashMap<>();
    private HashMap<Long, Endorse> endorseHashMap = new HashMap<>();
    private HashMap<Long, LinkedLocation> linkedLocationHashMap = new HashMap<>();
    private HashMap<Long, Location> locationHashMap = new HashMap<>();
    private HashMap<Long, Rating> ratingHashMap = new HashMap<>();
    private HashMap<Long, Route> routeHashMap = new HashMap<>();
    private HashMap<Long, Trip> tripHashMap = new HashMap<>();
    private HashMap<Long, TripFeedback> tripFeedbackHashMap = new HashMap<>();
    private HashMap<String, User> userHashMap = new HashMap<>();
    private HashMap<Long, UserProfile> userProfileHashMap = new HashMap<>();
    private HashMap<Long, VerifiedCertificate> verifiedCertificateHashMap = new HashMap<>();
    private HashMap<Long, RequestMakeTrip> requestMakeTripHashMap = new HashMap<>();
    //REFERENCE
    /**
     * USER
     */
    private HashMap<String, String> facebookRFUserId = new HashMap<>();//<fbId, userId>
    private HashMap<String, String> googleRFUserId = new HashMap<>();//<ggId, userId>
    private HashMap<String, String> twitterRFUserId = new HashMap<>();//<twId, userId>
    private HashMap<String, String> emailRFUserId = new HashMap<>();//<email, userId>
    /**
     * USER PROFILE
     */
    private HashMap<String, HashSet<Long>> userIdRFUserProfiles = new HashMap<>(); //<userId,<userProfileId>>
    /**
     * BROADCAST
     */
    private HashMap<String, HashSet<String>> userIdRFBroadcastes = new HashMap<>(); //<userId,<broadcastId>>

    /**
     * CIRCLE MEMBER
     */
    private HashMap<String, HashSet<Long>> userIdRFCircles = new HashMap<>(); //<userId,<circleId>>
    private HashMap<String, HashSet<Long>> circleIdRFUsers = new HashMap<>(); //<circleId,<userId>>
    private HashMap<String, Long> userAndCircleRFCircleMemberId = new HashMap<>(); //<userId#circleId,circleMemberId>

    /**
     * VERIFIED CERTIFICATE
     */
    private HashMap<String, HashSet<Long>> userIdRFVerifiedCertificates = new HashMap<>(); // <userId,<VCId>>

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
     * TRIP FEEDBACK
     */
    private HashMap<String, HashSet<Long>> userFeedbackIdRFTrips = new HashMap<>(); // <userId,<tripId>>
    private HashMap<String, HashSet<Long>> userBeFeedbackIdRFTrips = new HashMap<>(); // <userId,<tripId>>
    /**
     * ENDORSE
     */
    private HashMap<String, String> endorsedUserAndCircleIdRFEndorser = new HashMap<>(); //<circleId,<userId>>

    /**
     * LINKED LOCATION
     */
    private HashMap<Long, List<Long>> routeIdRFLinkedLocations = new HashMap<>(); //<routeId,<LinkedLocationId>>
    private HashMap<Long, List<Long>> tripIdRFLinkedLocations = new HashMap<>(); //<tripId,<LinkedLocationId>>

    /**
     * RATING
     */
    private HashMap<Long, Long> userProfileIdRFRatings = new HashMap<>();//<userProfileId,RatingId>
    /**
     * ACTIVITY
     */
    private HashMap<String, HashSet<Long>> userIdRFActivities = new HashMap<>();//<userId,<activityId>>
    //GEOCELL
    private GeoCell geoCellDriver = new GeoCell(); // for route
    private GeoCell geoCellPassenger = new GeoCell();// for route
    private GeoCell geoCellCurrentLocation = new GeoCell();

    /*PERSONAL FUNCTION*/
    public static void restore() {

    }

    public static void backup() {
        DB kyotoDB = new DB();


    }
     /*GET-SET Function*/
    /*CORE GET-SET*/

    public HashMap<Long, Activity> getActivityHashMap() {
        return activityHashMap;
    }

    public HashMap<String, Broadcast> getBroadcastHashMap() {
        return broadcastHashMap;
    }

    public HashMap<Long, Circle> getCircleHashMap() {
        return circleHashMap;
    }

    public HashMap<Long, CircleMember> getCircleMemberHashMap() {
        return circleMemberHashMap;
    }

    public HashMap<Long, CurrentLocation> getCurrentLocationHashMap() {
        return currentLocationHashMap;
    }

    public HashMap<Long, Endorse> getEndorseHashMap() {
        return endorseHashMap;
    }

    public HashMap<Long, LinkedLocation> getLinkedLocationHashMap() {
        return linkedLocationHashMap;
    }

    public HashMap<Long, Location> getLocationHashMap() {
        return locationHashMap;
    }

    public HashMap<Long, Rating> getRatingHashMap() {
        return ratingHashMap;
    }

    public HashMap<Long, Route> getRouteHashMap() {
        return routeHashMap;
    }

    public HashMap<Long, Trip> getTripHashMap() {
        return tripHashMap;
    }

    public HashMap<Long, TripFeedback> getTripFeedbackHashMap() {
        return tripFeedbackHashMap;
    }

    public HashMap<String, User> getUserHashMap() {
        return userHashMap;
    }

    public HashMap<Long, UserProfile> getUserProfileHashMap() {
        return userProfileHashMap;
    }

    public HashMap<Long, VerifiedCertificate> getVerifiedCertificateHashMap() {
        return verifiedCertificateHashMap;
    }

    public HashMap<Long, RequestMakeTrip> getRequestMakeTripHashMap() {g
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

    public HashMap<String, HashSet<Long>> getUserIdRFUserProfiles() {
        return userIdRFUserProfiles;
    }

    public HashMap<String, HashSet<String>> getUserIdRFBroadcastes() {
        return userIdRFBroadcastes;
    }

    public HashMap<String, HashSet<Long>> getUserIdRFCircles() {
        return userIdRFCircles;
    }

    public HashMap<String, HashSet<Long>> getCircleIdRFUsers() {
        return circleIdRFUsers;
    }

    public HashMap<String, Long> getUserAndCircleRFCircleMemberId() {
        return userAndCircleRFCircleMemberId;
    }

    public HashMap<String, HashSet<Long>> getUserIdRFVerifiedCertificates() {
        return userIdRFVerifiedCertificates;
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

    public HashMap<String, HashSet<Long>> getUserFeedbackIdRFTrips() {
        return userFeedbackIdRFTrips;
    }

    public HashMap<String, HashSet<Long>> getUserBeFeedbackIdRFTrips() {
        return userBeFeedbackIdRFTrips;
    }

    public HashMap<String, String> getEndorsedUserAndCircleIdRFEndorser() {
        return endorsedUserAndCircleIdRFEndorser;
    }

    public HashMap<Long, List<Long>> getRouteIdRFLinkedLocations() {
        return routeIdRFLinkedLocations;
    }

    public HashMap<Long, List<Long>> getTripIdRFLinkedLocations() {
        return tripIdRFLinkedLocations;
    }

    public HashMap<Long, Long> getUserProfileIdRFRatings() {
        return userProfileIdRFRatings;
    }

    public HashMap<String, HashSet<Long>> getUserIdRFActivities() {
        return userIdRFActivities;
    }

    public HashMap<Integer, HashSet<Long>> getRoleRFRoutes() {
        return roleRFRoutes;
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
