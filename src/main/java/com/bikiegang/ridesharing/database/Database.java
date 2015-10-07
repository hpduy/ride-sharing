package com.bikiegang.ridesharing.database;

import com.bikiegang.ridesharing.cache.RideSharingCA;
import com.bikiegang.ridesharing.config.ConfigInfo;
import com.bikiegang.ridesharing.geocoding.GeoCell;
import com.bikiegang.ridesharing.pojo.*;
import com.bikiegang.ridesharing.utilities.MapUtil;

import java.util.*;

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
    private HashMap<Long, PlannedTrip> plannedTripHashMap = new HashMap<>();
    private HashMap<Long, Trip> tripHashMap = new HashMap<>();
    private HashMap<String, User> userHashMap = new HashMap<>();
    private HashMap<Long, RequestMakeTrip> requestMakeTripHashMap = new HashMap<>();
    private HashMap<Long, RequestVerify> requestVerifyHashMap = new HashMap<>();
    private HashMap<Long, VerifiedCertificate> verifiedCertificateHashMap = new HashMap<>();
    private HashMap<Long, AngelGroup> angelGroupHashMap = new HashMap<>();
    private HashMap<Long, AngelGroupMember> angelGroupMemberHashMap = new HashMap<>();
    private LinkedHashMap<Long, PopularLocation> popularLocationHashMap = new LinkedHashMap<>();
    private HashMap<Long, SocialTrip> socialTripHashMap = new HashMap<>();
    private LinkedHashMap<Long, Feed> feedHashMap = new LinkedHashMap<>();
    private HashMap<Long, Rating> ratingHashMap = new LinkedHashMap<>();
    private HashMap<Long, SocialTripAttendance> socialTripAttendanceHashMap = new LinkedHashMap<>();
    private HashMap<Long, TripCalendar> tripCalendarHashMap = new LinkedHashMap<>();
    private HashMap<Long, Route> routeHashMap = new HashMap<>();
    private HashMap<Long, Feedback> feedbackHashMap = new HashMap<>();
    private HashMap<Long, Event> eventHashMap = new HashMap<>();
    private HashMap<Long, Organization> organizationHashMap = new HashMap<>();

    //REFERENCE
    /**
     * USER
     */
    private HashMap<String, String> facebookRFUserId = new HashMap<>();//<fbId, userId>
    private HashMap<String, String> googleRFUserId = new HashMap<>();//<ggId, userId>
    private HashMap<String, String> twitterRFUserId = new HashMap<>();//<twId, userId>
    private HashMap<String, String> emailRFUserId = new HashMap<>();//<email, userId>
    private HashMap<String, String> linkedInRFUserId = new HashMap<>();//<lkId, userId>
    private HashMap<Long, String> organizationRFUserId = new HashMap<>(); //<organizationId, userId>
    /**
     * BROADCAST
     */
    private HashMap<String, HashSet<String>> userIdRFBroadcasts = new HashMap<>(); //<userId,<broadcastId>>

    /**
     * PLANNED TRIP
     */
    private HashMap<String, HashSet<Long>> userIdRFPlannedTrips = new HashMap<>(); // <userId,<plannedTripId>>
    private HashMap<Integer, HashSet<Long>> roleRFPlannedTrips = new HashMap<>(); // <role,<plannedTripId>>
    private HashMap<Long, HashSet<Long>> groupIdRFPlannedTrips = new HashMap<>(); // <groupId,<plannedTripId>>
    private HashMap<Long, HashMap<Long, Long>> routeRFPlannedTripsByDay = new HashMap<>();// <routeId,<epochday, plannedTrip>

    /**
     * TRIP
     */
    private HashMap<String, HashSet<Long>> driverIdRFTrips = new HashMap<>(); // <userId,<tripId>>
    private HashMap<String, HashSet<Long>> passengerIdRFTrips = new HashMap<>(); // <userId,<tripId>>
    private HashMap<Long, Long> plannedTripIdRFTrips = new HashMap<>(); // <plannedTripId,<tripId>>
    /**
     * REQUEST MAKE TRIP
     */
    private HashMap<String, HashMap<Long, Long>> senderRequestsBox = new HashMap<>(); //<senderId,<receiverPlannedTripId,requestIds>>
    private HashMap<String, HashMap<Long, List<Long>>> receiverRequestsBox = new HashMap<>(); //<receiverId,<receiverPlannedTripId,<requestIds>>>
    /**
     * REQUEST VERIFY
     */
    private HashMap<String, Long> userRequestBox = new HashMap<>(); //<userId#angelId,requestIds>
    private HashMap<String, List<Long>> angelRequestsBox = new HashMap<>(); //<angelId,<requestIds>>
    /**
     * VERIFY CERTIFICATE
     */
    private HashMap<String, HashSet<Long>> userIdRFCertificates = new HashMap<>(); //<userId,<verifiedCertificateId>>

    /**
     * LINKED LOCATION
     */
    private HashMap<Long, List<Long>> routeIdRFLinkedLocations = new HashMap<>(); //<routeId,<LinkedLocationId>>
    /**
     * ANGEL GROUP
     */
    private HashMap<Long, HashSet<Long>> organizationIdRFAngelGroups = new HashMap<>(); // <groupId,<AngelGroupId>>
    /**
     * ANGEL GROUP MEMBER
     */
    private HashMap<String, HashSet<Long>> userIdRFAngelGroups = new HashMap<>(); // <userId,<angelGroupId>>
    private HashMap<Long, HashSet<String>> angelGroupIdRFUsers = new HashMap<>(); // <angelGroupId,<userId>>
    private HashMap<String, Long> userAndGroupRFAngelGroupMember = new HashMap<>(); // <userId#angelGroupId, angelGroupMemberId>
    /**
     * POPULAR LOCATION
     */
    private List<Long> orderedPopularLocation = new ArrayList<>(); // < popularLocationId> which sorted
    /**
     * SOCIAL TRIP
     */
    private HashMap<String, HashSet<Long>> userIdRFSocialTrips = new HashMap<>(); // <userId,<socialTripId>>
    /**
     * SOCIAL ATTENDANCE
     */
    //TODO: do later
    /**
     * TRIP CALENDAR
     */
    private HashMap<String, Long> userIdRFTripCalendar = new HashMap<>();// <userId,tripCalendarId>
    /**
     * RATING
     */
    private HashMap<String, HashSet<Long>> UserIdRFRatings = new HashMap<>();// <userId(ratedUserId),<ratingIds>>

    /**
     * GEOCELL
     */
    private GeoCell<Long> geoCellDriver = new GeoCell<>(GeoCell.CELL_LEN_OF_PLANNED_TRIP); // for route
    private GeoCell<Long> geoCellPassenger = new GeoCell<>(GeoCell.CELL_LEN_OF_PLANNED_TRIP);// for route
    private GeoCell<String> geoCellCurrentLocation = new GeoCell<>(GeoCell.CELL_LEN_OF_PT_START_LOCATION);
    private GeoCell<Long> geoCellStartLocation = new GeoCell<>(GeoCell.CELL_LEN_OF_PT_START_LOCATION);
    private GeoCell<Long> geoCellAngelGroup = new GeoCell<>(GeoCell.CELL_LEN_OF_ANGEL_GROUP);
    private GeoCell<Long> geoCellSocialTrip = new GeoCell<>(GeoCell.CELL_LEN_OF_PT_START_LOCATION);

    //    /*PERSONAL FUNCTION*/
    public void restore() {
        RideSharingCA rideSharingCA = RideSharingCA.getInstance(ConfigInfo.REDIS_SERVER);
        //restore
        rideSharingCA.RestoreDatabase();
        setFeedHashMap((LinkedHashMap<Long, Feed>) MapUtil.sortByValue(getFeedHashMap()));
    }

    public void backup() {
    }
    /*GET-SET Function*/
    /*CORE GET-SET*/

    public HashMap<String, HashSet<Long>> getUserIdRFCertificates() {
        return userIdRFCertificates;
    }

    public void setUserIdRFCertificates(HashMap<String, HashSet<Long>> userIdRFCertificates) {
        this.userIdRFCertificates = userIdRFCertificates;
    }

    public HashMap<Long, Feedback> getFeedbackHashMap() {
        return feedbackHashMap;
    }

    public void setFeedbackHashMap(HashMap<Long, Feedback> feedbackHashMap) {
        this.feedbackHashMap = feedbackHashMap;
    }

    public HashMap<String, Broadcast> getBroadcastHashMap() {
        return broadcastHashMap;
    }

    public HashMap<Long, LinkedLocation> getLinkedLocationHashMap() {
        return linkedLocationHashMap;
    }

    public HashMap<Long, PlannedTrip> getPlannedTripHashMap() {
        return plannedTripHashMap;
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

    public HashMap<Long, RequestVerify> getRequestVerifyHashMap() {
        return requestVerifyHashMap;
    }

    public HashMap<Long, VerifiedCertificate> getVerifiedCertificateHashMap() {
        return verifiedCertificateHashMap;
    }

    public HashMap<Long, AngelGroup> getAngelGroupHashMap() {
        return angelGroupHashMap;
    }

    public LinkedHashMap<Long, PopularLocation> getPopularLocationHashMap() {
        return popularLocationHashMap;
    }

    public HashMap<Long, AngelGroupMember> getAngelGroupMemberHashMap() {
        return angelGroupMemberHashMap;
    }

    public HashMap<Long, SocialTrip> getSocialTripHashMap() {
        return socialTripHashMap;
    }

    public LinkedHashMap<Long, Feed> getFeedHashMap() {
        return feedHashMap;
    }

    public HashMap<Long, Rating> getRatingHashMap() {
        return ratingHashMap;
    }

    public HashMap<Long, SocialTripAttendance> getSocialTripAttendanceHashMap() {
        return socialTripAttendanceHashMap;
    }

    public HashMap<Long, TripCalendar> getTripCalendarHashMap() {
        return tripCalendarHashMap;
    }

    public HashMap<Long, Route> getRouteHashMap() {
        return routeHashMap;
    }

    public HashMap<Long, Event> getEventHashMap() {
        return eventHashMap;
    }

    public HashMap<Long, Organization> getOrganizationHashMap() {
        return organizationHashMap;
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

    public void setFacebookRFUserId(HashMap<String, String> facebookRFUserId) {
        this.facebookRFUserId = facebookRFUserId;
    }

    public void setGoogleRFUserId(HashMap<String, String> googleRFUserId) {
        this.googleRFUserId = googleRFUserId;
    }

    public void setTwitterRFUserId(HashMap<String, String> twitterRFUserId) {
        this.twitterRFUserId = twitterRFUserId;
    }

    public void setEmailRFUserId(HashMap<String, String> emailRFUserId) {
        this.emailRFUserId = emailRFUserId;
    }

    public void setLinkedInRFUserId(HashMap<String, String> linkedInRFUserId) {
        this.linkedInRFUserId = linkedInRFUserId;
    }

    public HashMap<String, HashSet<String>> getUserIdRFBroadcasts() {
        return userIdRFBroadcasts;
    }

    public HashMap<String, HashSet<Long>> getUserIdRFPlannedTrips() {
        return userIdRFPlannedTrips;
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

    public HashMap<Integer, HashSet<Long>> getRoleRFPlannedTrips() {
        return roleRFPlannedTrips;
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

    public HashMap<Long, HashSet<Long>> getGroupIdRFPlannedTrips() {
        return groupIdRFPlannedTrips;
    }

    public HashMap<String, Long> getUserRequestBox() {
        return userRequestBox;
    }

    public HashMap<String, List<Long>> getAngelRequestsBox() {
        return angelRequestsBox;
    }

    public HashMap<String, HashSet<Long>> getUserIdRFAngelGroups() {
        return userIdRFAngelGroups;
    }

    public HashMap<Long, HashSet<String>> getAngelGroupIdRFUsers() {
        return angelGroupIdRFUsers;
    }

    public HashMap<String, Long> getUserAndGroupRFAngelGroupMember() {
        return userAndGroupRFAngelGroupMember;
    }

    public List<Long> getOrderedPopularLocation() {
        return orderedPopularLocation;
    }

    public HashMap<String, HashSet<Long>> getUserIdRFSocialTrips() {
        return userIdRFSocialTrips;
    }

    public HashMap<String, Long> getUserIdRFTripCalendar() {
        return userIdRFTripCalendar;
    }

    public void setOrderedPopularLocation(List<Long> orderedPopularLocation) {
        this.orderedPopularLocation = orderedPopularLocation;
    }

    public HashMap<Long, HashSet<Long>> getOrganizationIdRFAngelGroups() {
        return organizationIdRFAngelGroups;
    }

    public HashMap<Long, HashMap<Long, Long>> getRouteRFPlannedTripsByDay() {
        return routeRFPlannedTripsByDay;
    }

    public HashMap<String, HashSet<Long>> getUserIdRFRatings() {
        return UserIdRFRatings;
    }

    public HashMap<Long, Long> getPlannedTripIdRFTrips() {
        return plannedTripIdRFTrips;
    }

    public HashMap<Long, String> getOrganizationRFUserId() {
        return organizationRFUserId;
    }

    public void setOrganizationHashMap(HashMap<Long, Organization> organizationHashMap) {
        this.organizationHashMap = organizationHashMap;
    }

    public void setOrganizationRFUserId(HashMap<Long, String> organizationRFUserId) {
        this.organizationRFUserId = organizationRFUserId;
    }

    /*GEOCELL GET-SET*/
    public GeoCell<Long> getGeoCellDriver() {
        return geoCellDriver;
    }

    public void setGeoCellDriver(GeoCell<Long> geoCellDriver) {
        this.geoCellDriver = geoCellDriver;
    }

    public GeoCell<Long> getGeoCellPassenger() {
        return geoCellPassenger;
    }

    public void setGeoCellPassenger(GeoCell<Long> geoCellPassenger) {
        this.geoCellPassenger = geoCellPassenger;
    }

    public GeoCell<String> getGeoCellCurrentLocation() {
        return geoCellCurrentLocation;
    }

    public void setGeoCellCurrentLocation(GeoCell<String> geoCellCurrentLocation) {
        this.geoCellCurrentLocation = geoCellCurrentLocation;
    }

    public GeoCell<Long> getGeoCellStartLocation() {
        return geoCellStartLocation;
    }

    public void setGeoCellStartLocation(GeoCell<Long> geoCellStartLocation) {
        this.geoCellStartLocation = geoCellStartLocation;
    }

    public GeoCell<Long> getGeoCellAngelGroup() {
        return geoCellAngelGroup;
    }

    public void setGeoCellAngelGroup(GeoCell<Long> geoCellAngelGroup) {
        this.geoCellAngelGroup = geoCellAngelGroup;
    }

    public GeoCell<Long> getGeoCellSocialTrip() {
        return geoCellSocialTrip;
    }

    public void setFeedHashMap(LinkedHashMap<Long, Feed> feedHashMap) {
        this.feedHashMap = feedHashMap;
    }
}
