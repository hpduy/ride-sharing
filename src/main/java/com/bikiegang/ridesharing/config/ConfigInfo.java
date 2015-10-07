/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bikiegang.ridesharing.config;

/**
 *
 * @author root
 */
public class ConfigInfo {

    /*
     Config db, redis, gearman
     */
    public static String RIDESHARING_DB = "ridesharing_db";
    public static String REDIS_SERVER = "redis_server";
    public static String RIDESHARING_WORKER_GEARMAN = "ridesharing_gearman";

    /*
     Query database
     */
    public static String BROADCAST_INSERT_QUERY;
    public static String BROADCAST_UPDATE_QUERY;
    public static String BROADCAST_DELETE_QUERY;
    public static String LINKEDLOCATION_DELETE_QUERY;
    public static String LINKEDLOCATION_UPDATE_QUERY;
    public static String LINKEDLOCATION_INSERT_QUERY;
    public static String REQUESTMAKETRIP_DELETE_QUERY;
    public static String REQUESTMAKETRIP_UPDATE_QUERY;
    public static String REQUESTMAKETRIP_INSERT_QUERY;
    public static String PLANNEDTRIP_DELETE_QUERY;
    public static String PLANNEDTRIP_UPDATE_QUERY;
    public static String PLANNEDTRIP_INSERT_QUERY;
    public static String TRIP_DELETE_QUERY;
    public static String TRIP_UPDATE_QUERY;
    public static String TRIP_INSERT_QUERY;
    public static String USER_INSERT_QUERY;
    public static String USER_UPDATE_QUERY;
    public static String USER_DELETE_QUERY;
    public static String REQUESTVERIFY_DELETE_QUERY;
    public static String REQUESTVERIFY_UPDATE_QUERY;
    public static String REQUESTVERIFY_INSERT_QUERY;
    public static String VERIFIEDCERTIFICATE_INSERT_QUERY;
    public static String VERIFIEDCERTIFICATE_UPDATE_QUERY;
    public static String VERIFIEDCERTIFICATE_DELETE_QUERY;
    public static String ANGEL_GROUP_INSERT_QUERY;
    public static String ANGEL_GROUP_UPDATE_QUERY;
    public static String ANGEL_GROUP_DELETE_QUERY;
    public static String ANGEL_GROUP_MEMBER_INSERT_QUERY;
    public static String ANGEL_GROUP_MEMBER_UPDATE_QUERY;
    public static String ANGEL_GROUP_MEMBER_DELETE_QUERY;
    public static String FEED_GROUP_INSERT_QUERY;
    public static String FEED_GROUP_UPDATE_QUERY;
    public static String FEED_GROUP_DELETE_QUERY;
    public static String POPULARLOCATION_GROUP_INSERT_QUERY;
    public static String POPULARLOCATION_GROUP_UPDATE_QUERY;
    public static String POPULARLOCATION_GROUP_DELETE_QUERY;
    public static String RATING_GROUP_INSERT_QUERY;
    public static String RATING_GROUP_UPDATE_QUERY;
    public static String RATING_GROUP_DELETE_QUERY;
    public static String SOCIALTRIP_ATTENDANCEGROUP_INSERT_QUERY;
    public static String SOCIALTRIP_ATTENDANCEGROUP_UPDATE_QUERY;
    public static String SOCIALTRIP_ATTENDANCEGROUP_DELETE_QUERY;
    public static String SOCIALTRIP_GROUP_INSERT_QUERY;
    public static String SOCIALTRIP_GROUP_UPDATE_QUERY;
    public static String SOCIALTRIP_GROUP_DELETE_QUERY;
    public static String TRIPCALENDAR_INSERT_QUERY;
    public static String TRIPCALENDAR_UPDATE_QUERY;
    public static String TRIPCALENDAR_DELETE_QUERY;
    public static String ROUTE_DELETE_QUERY;
    public static String ROUTE_UPDATE_QUERY;
    public static String ROUTE_INSERT_QUERY;
    public static String FEEDBACK_GROUP_INSERT_QUERY;
    public static String FEEDBACK_GROUP_UPDATE_QUERY;
    public static String FEEDBACK_GROUP_DELETE_QUERY;
    public static String EVENT_DELETE_QUERY;
    public static String EVENT_UPDATE_QUERY;
    public static String EVENT_INSERT_QUERY;
    public static String ORGANIZATION_DELETE_QUERY;
    public static String ORGANIZATION_INSERT_QUERY;
    public static String ORGANIZATION_UPDATE_QUERY;

    static {
        RIDESHARING_DB = "ridesharing_db";
        EVENT_INSERT_QUERY = "INSERT INTO `ridesharing_db`.`Event`\n"
                + "(`id`,\n"
                + "`name`,\n"
                + "`address`,\n"
                + "`backgroundImageLink`,\n"
                + "`searcher`,\n"
                + "`type`,\n"
                + "`eventSocialId`,\n"
                + "`showTime`,\n"
                + "`referenceLink`)\n"
                + "VALUES\n"
                + "(?,?,?,?,?,?,?,?,?);";
        EVENT_UPDATE_QUERY = "UPDATE `ridesharing_db`.`Event`\n"
                + "SET\n"
                + "`id` = ?,\n"
                + "`name` = ?,\n"
                + "`address` = ?,\n"
                + "`backgroundImageLink` = ?,\n"
                + "`searcher` = ?,\n"
                + "`type` = ?,\n"
                + "`eventSocialId` = ?,\n"
                + "`showTime` = ?,\n"
                + "`referenceLink` = ?\n"
                + "WHERE `id` = ?;";
        EVENT_DELETE_QUERY = "DELETE FROM `ridesharing_db`.`Event`\n"
                + "WHERE <{where_expression}>;";
        ORGANIZATION_INSERT_QUERY = "INSERT INTO `ridesharing_db`.`Organization`\n"
                + "(`id`,\n"
                + "`name`,\n"
                + "`logoImageLink`,\n"
                + "`code`,\n"
                + "`createdTime`)\n"
                + "VALUES\n"
                + "(?,?,?,?,?);";
        ORGANIZATION_UPDATE_QUERY = "UPDATE `ridesharing_db`.`Organization`\n"
                + "SET\n"
                + "`id` = ?,\n"
                + "`name` = ?,\n"
                + "`logoImageLink` = ?,\n"
                + "`code` = ?,\n"
                + "`createdTime` = ?\n"
                + "WHERE `id` = ?;";
        ORGANIZATION_DELETE_QUERY = "DELETE FROM `ridesharing_db`.`Organization`\n"
                + "WHERE `id` = ?;";
        BROADCAST_INSERT_QUERY = "INSERT INTO `ridesharing_db`.`Broadcast`\n"
                + "(`id`,\n"
                + "`userId`,\n"
                + "`deviceId`,\n"
                + "`regId`,\n"
                + "`os`, `type`)\n"
                + "VALUES\n"
                + "(?,?,?,?,?,?);";
        BROADCAST_UPDATE_QUERY = "UPDATE `ridesharing_db`.`Broadcast`\n"
                + "SET\n"
                + "`id` = ?,\n"
                + "`userId` = ?,\n"
                + "`deviceId` = ?,\n"
                + "`regId` = ?,\n"
                + "`os` = ?, `type` = ?\n"
                + "WHERE `id` = ?;";
        BROADCAST_DELETE_QUERY = "DELETE FROM `ridesharing_db`.`Broadcast`\n"
                + "WHERE `id` = ?;";
        LINKEDLOCATION_INSERT_QUERY = "INSERT INTO `ridesharing_db`.`LinkedLocation`\n"
                + "(`id`,\n"
                + "`time`,\n"
                + "`estimatedTime`,\n"
                + "`index`,\n"
                + "`refId`,\n"
                + "`lat`,\n"
                + "`lng`)\n"
                + "VALUES\n"
                + "(?,?,?,?,?,?,?);";

        LINKEDLOCATION_UPDATE_QUERY = "UPDATE `ridesharing_db`.`LinkedLocation`\n"
                + "SET\n"
                + "`id` = ?,\n"
                + "`time` = ?,\n"
                + "`estimatedTime` = ?,\n"
                + "`index` = ?,\n"
                + "`refId` = ?,\n"
                + "`lat` = ?,\n"
                + "`lng` = ?\n"
                + "WHERE `id` = ?;";

        LINKEDLOCATION_DELETE_QUERY = "DELETE FROM `ridesharing_db`.`LinkedLocation`\n"
                + "WHERE `id` = ?;";

        REQUESTMAKETRIP_INSERT_QUERY = "INSERT INTO `ridesharing_db`.`RequestMakeTrip`\n"
                + "(`id`,\n"
                + "`senderId`,\n"
                + "`receiverId`,\n"
                + "`senderRole`,\n"
                + "`createdTime`,\n"
                + "`driverPlannedTripId`,\n"
                + "`passengerPlannedTripId`,\n"
                + "`status`, `price`)\n"
                + "VALUES\n"
                + "(?,?,?,?,?,?,?,?,?);";
        REQUESTMAKETRIP_UPDATE_QUERY = "UPDATE `ridesharing_db`.`RequestMakeTrip`\n"
                + "SET\n"
                + "`id` = ?,\n"
                + "`senderId` = ?,\n"
                + "`receiverId` = ?,\n"
                + "`senderRole` = ?,\n"
                + "`createdTime` = ?,\n"
                + "`driverPlannedTripId` = ?,\n"
                + "`passengerPlannedTripId` = ?,\n"
                + "`status` = ?,\n"
                + "`price` = ?\n"
                + "WHERE `id` = ?;";
        REQUESTMAKETRIP_DELETE_QUERY = "DELETE FROM `ridesharing_db`.`RequestMakeTrip`\n"
                + "WHERE `id` = ?;";
        PLANNEDTRIP_INSERT_QUERY = "INSERT INTO `ridesharing_db`.`PlannedTrip`\n"
                + "(`id`,\n"
                + "`arriveTime`,\n"
                + "`departureTime`,\n"
                + "`ownerPrice`,\n"
                + "`estimatedPrice`,\n"
                + "`creatorId`,\n"
                + "`role`,\n"
                + "`type`,\n"
                + "`plannedTripTrailPolyLine`,\n"
                + "`timePatternId`,\n"
                + "`hasHelmet`,\n"
                + "`requestId`,\n"
                + "`routeId`, `note`)\n"
                + "VALUES\n"
                + "(?,?,?,?,?,"
                + "?,?,?,?,?,"
                + "?,?,?,?);";
        PLANNEDTRIP_UPDATE_QUERY = "UPDATE `ridesharing_db`.`PlannedTrip`\n"
                + "SET\n"
                + "`id` = ?,\n"
                + "`arriveTime` = ?,\n"
                + "`departureTime` = ?,\n"
                + "`ownerPrice` = ?,\n"
                + "`estimatedPrice` = ?,\n"
                + "`creatorId` = ?,\n"
                + "`role` = ?,\n"
                + "`type` = ?,\n"
                + "`plannedTripTrailPolyLine` = ?,\n"
                + "`timePatternId` = ?,\n"
                + "`hasHelmet` = ?,\n"
                + "`requestId` = ?,\n"
                + "`routeId` = ?,\n"
                + "`note` = ?\n"
                + "WHERE `id` = ?;";
        PLANNEDTRIP_DELETE_QUERY = "DELETE FROM `ridesharing_db`.`PlannedTrip`\n"
                + "WHERE `id` = ?;";
        TRIP_INSERT_QUERY = "INSERT INTO `ridesharing_db`.`Trip`\n"
                + "(`id`,\n"
                + "`startTime`,\n"
                + "`driverId`,\n"
                + "`passengerId`,\n"
                + "`realDistance`,\n"
                + "`endTime`,\n"
                + "`farePaid`,\n"
                + "`sensitiveLocationId`,\n"
                + "`breakReason`,\n"
                + "`breakTrip`,\n"
                + "`dangerTrip`,\n"
                + "`driverPlannedTripId`,\n"
                + "`passengerPlannedTripId`,\n"
                + "`tripTrailPolyLine`, `tripStatus`, `ratedUser`)\n"
                + "VALUES\n"
                + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        TRIP_UPDATE_QUERY = "UPDATE `ridesharing_db`.`Trip`\n"
                + "SET\n"
                + "`id` = ?,\n"
                + "`startTime` = ?,\n"
                + "`driverId` = ?,\n"
                + "`passengerId` = ?,\n"
                + "`realDistance` = ?,\n"
                + "`endTime` = ?,\n"
                + "`farePaid` = ?,\n"
                + "`sensitiveLocationId` = ?,\n"
                + "`breakReason` = ?,\n"
                + "`breakTrip` = ?,\n"
                + "`dangerTrip` = ?,\n"
                + "`driverPlannedTripId` = ?,\n"
                + "`passengerPlannedTripId` = ?,\n"
                + "`tripTrailPolyLine` = ?, `tripStatus` = ?, `ratedUser` = ?\n"
                + "WHERE `id` = ?;";
        TRIP_DELETE_QUERY = "DELETE FROM `ridesharing_db`.`Trip`\n"
                + "WHERE `id` = ?;";
        USER_INSERT_QUERY = "INSERT INTO `ridesharing_db`.`User`\n"
                + "(`id`,\n"
                + "`email`,\n"
                + "`password`,\n"
                + "`facebookId`,\n"
                + "`googleId`,\n"
                + "`twitterId`,\n"
                + "`firstName`,\n"
                + "`lastName`,\n"
                + "`profilePicture`,\n"
                + "`phone`,\n"
                + "`currentLocation`,\n"
                + "`birthDay`,\n"
                + "`gender`,\n"
                + "`status`,\n"
                + "`isBusy`,\n"
                + "`currentRole`,\n"
                + "`linkedInId`,\n"
                + "`selfIntro`, \n"
                + "`privacy`, `job`, `phoneViewer`, `organizationId`)\n"
                + "VALUES\n"
                + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        USER_UPDATE_QUERY = "UPDATE `ridesharing_db`.`User`\n"
                + "SET\n"
                + "`id` = ?,\n"
                + "`email` = ?,\n"
                + "`password` = ?,\n"
                + "`facebookId` = ?,\n"
                + "`googleId` = ?,\n"
                + "`twitterId` = ?,\n"
                + "`firstName` = ?,\n"
                + "`lastName` = ?,\n"
                + "`profilePicture` = ?,\n"
                + "`phone` = ?,\n"
                + "`currentLocation` = ?,\n"
                + "`birthDay` = ?,\n"
                + "`gender` = ?,\n"
                + "`status` = ?,\n"
                + "`isBusy` = ?,\n"
                + "`currentRole` = ?,\n"
                + "`linkedInId` = ?,\n"
                + "`selfIntro` = ?,\n"
                + "`privacy` = ?,\n"
                + "`job` = ?, `phoneViewer` = ?, `organizationId` = ?\n"
                + "WHERE `id` = ?;";
        USER_DELETE_QUERY = "DELETE FROM `ridesharing_db`.`User`\n"
                + "WHERE `id` = ?;";
        REQUESTVERIFY_INSERT_QUERY = "INSERT INTO `ridesharing_db`.`RequestVerify`\n"
                + "(`id`,\n"
                + "`userId`,\n"
                + "`angelId`,\n"
                + "`numberOfCertificate`,\n"
                + "`status`, `createdTime`)\n"
                + "VALUES\n"
                + "(?,?,?,?,?,?);";
        REQUESTVERIFY_UPDATE_QUERY = "UPDATE `ridesharing_db`.`RequestVerify`\n"
                + "SET\n"
                + "`id` = ?,\n"
                + "`userId` = ?,\n"
                + "`angelId` = ?,\n"
                + "`numberOfCertificate` = ?,\n"
                + "`status` = ?,\n"
                + "`createdTime` = ?\n"
                + "WHERE `id` = ?;";
        REQUESTVERIFY_DELETE_QUERY = "DELETE FROM `ridesharing_db`.`RequestVerify`\n"
                + "WHERE `id` = ?;";
        VERIFIEDCERTIFICATE_INSERT_QUERY = "INSERT INTO `ridesharing_db`.`VerifiedCertificate`\n"
                + "(`id`,\n"
                + "`note`,\n"
                + "`createdTime`,\n"
                + "`ownerId`,\n"
                + "`endorserId`,\n"
                + "`status`,\n"
                + "`idNumber`,\n"
                + "`address`,\n"
                + "`regoDay`,\n"
                + "`expiryDay`,\n"
                + "`imageLinks`,\n"
                + "`type`,`organization`)\n"
                + "VALUES\n"
                + "(?,?,?,?,?,?,?,?,?,?,?,?,?);";
        VERIFIEDCERTIFICATE_UPDATE_QUERY = "UPDATE `ridesharing_db`.`VerifiedCertificate`\n"
                + "SET\n"
                + "`id` = ?,\n"
                + "`note` = ?,\n"
                + "`createdTime` = ?,\n"
                + "`ownerId` = ?,\n"
                + "`endorserId` = ?,\n"
                + "`status` = ?,\n"
                + "`idNumber` = ?,\n"
                + "`address` = ?,\n"
                + "`regoDay` = ?,\n"
                + "`expiryDay` = ?,\n"
                + "`imageLinks` = ?,\n"
                + "`type` = ?,\n"
                + "`organization` = ?\n"
                + "WHERE `id` = ?;";
        VERIFIEDCERTIFICATE_DELETE_QUERY = "DELETE FROM `ridesharing_db`.`VerifiedCertificate`\n"
                + "WHERE `id` = ?;";
        ANGEL_GROUP_INSERT_QUERY = "INSERT INTO `ridesharing_db`.`AngelGroup`\n"
                + "(`id`,\n"
                + "`location`,\n"
                + "`tagName`,\n"
                + "`canonicalName`,\n"
                + "`address`,\n"
                + "`createdTime`, `organizationId`)\n"
                + "VALUES\n"
                + "(?,?,?,?,?,?,?);";
        ANGEL_GROUP_UPDATE_QUERY = "UPDATE `ridesharing_db`.`AngelGroup`\n"
                + "SET\n"
                + "`id` = ?,\n"
                + "`location` = ?,\n"
                + "`tagName` = ?,\n"
                + "`canonicalName` = ?,\n"
                + "`address` = ?,\n"
                + "`createdTime` = ?, `organizationId` = ?\n"
                + "WHERE `id` = ?;";
        ANGEL_GROUP_DELETE_QUERY = "DELETE FROM `ridesharing_db`.`AngelGroup`\n"
                + "WHERE `id` = ?;";
        ANGEL_GROUP_MEMBER_INSERT_QUERY = "INSERT INTO `ridesharing_db`.`AngelGroupMember`\n"
                + "(`id`,\n"
                + "`groupId`,\n"
                + "`angelId`,\n"
                + "`createdTime`)\n"
                + "VALUES\n"
                + "(?,?,?,?);";
        ANGEL_GROUP_MEMBER_UPDATE_QUERY = "UPDATE `ridesharing_db`.`AngelGroupMember`\n"
                + "SET\n"
                + "`id` = ?,\n"
                + "`groupId` = ?,\n"
                + "`angelId` = ?,\n"
                + "`createdTime` = ?\n"
                + "WHERE `id` = ?;";
        ANGEL_GROUP_MEMBER_DELETE_QUERY = "DELETE FROM `ridesharing_db`.`AngelGroupMember`\n"
                + "WHERE `id` = ?;";

        FEED_GROUP_INSERT_QUERY = "INSERT INTO `ridesharing_db`.`Feed`\n"
                + "(`id`,\n"
                + "`type`,\n"
                + "`refId`, `createdTime`)\n"
                + "VALUES\n"
                + "(?,?,?,?);";
        FEED_GROUP_UPDATE_QUERY = "UPDATE `ridesharing_db`.`Feed`\n"
                + "SET\n"
                + "`id` = ?,\n"
                + "`type` = ?,\n"
                + "`refId` = ?, `createdTime` = ?\n"
                + "WHERE `id` = ?;";
        FEED_GROUP_DELETE_QUERY = "DELETE FROM `ridesharing_db`.`Feed`\n"
                + "WHERE `id` = ?;";

        POPULARLOCATION_GROUP_INSERT_QUERY = "INSERT INTO `ridesharing_db`.`PopularLocation`\n"
                + "(`id`,\n"
                + "`name`,\n"
                + "`address`,\n"
                + "`backgroundImageLink`,\n"
                + "`searcher`,\n"
                + "`lat`,\n"
                + "`lng`,\n"
                + "`time`, `type`)\n"
                + "VALUES\n"
                + "(?,?,?,?,?,?,?,?,?);";
        POPULARLOCATION_GROUP_UPDATE_QUERY = "UPDATE `ridesharing_db`.`PopularLocation`\n"
                + "SET\n"
                + "`id` = ?,\n"
                + "`name` = ?,\n"
                + "`address` = ?,\n"
                + "`backgroundImageLink` = ?,\n"
                + "`searcher` = ?,\n"
                + "`lat` = ?,\n"
                + "`lng` = ?,\n"
                + "`time` = ?, `type` = ?\n"
                + "WHERE `id` = ?;";
        POPULARLOCATION_GROUP_DELETE_QUERY = "DELETE FROM `ridesharing_db`.`PopularLocation`\n"
                + "WHERE `id` = ?;";

        RATING_GROUP_INSERT_QUERY = "INSERT INTO `ridesharing_db`.`Rating`\n"
                + "(`id`,\n"
                + "`ratedUserId`,\n"
                + "`ratingUserId`,\n"
                + "`numberOfStart`,\n"
                + "`createdTime`,\n"
                + "`comment`,\n"
                + "`tripId`)\n"
                + "VALUES\n"
                + "(?,?,?,?,?,?,?);";
        RATING_GROUP_UPDATE_QUERY = "UPDATE `ridesharing_db`.`Rating`\n"
                + "SET\n"
                + "`id` = ?,\n"
                + "`ratedUserId` = ?,\n"
                + "`ratingUserId` = ?,\n"
                + "`numberOfStart` = ?,\n"
                + "`createdTime` = ?,\n"
                + "`comment` = ?,\n"
                + "`tripId` = ?\n"
                + "WHERE `id` = ?;";
        RATING_GROUP_DELETE_QUERY = "DELETE FROM `ridesharing_db`.`Rating`\n"
                + "WHERE `id` = ?;";
        SOCIALTRIP_ATTENDANCEGROUP_INSERT_QUERY = "INSERT INTO `ridesharing_db`.`SocialTripAttendance`\n"
                + "(`id`,\n"
                + "`userId`,\n"
                + "`socialTripId`,\n"
                + "`role`)\n"
                + "VALUES\n"
                + "(?,?,?,?);";
        SOCIALTRIP_ATTENDANCEGROUP_UPDATE_QUERY = "UPDATE `ridesharing_db`.`SocialTripAttendance`\n"
                + "SET\n"
                + "`id` = ?,\n"
                + "`userId` = ?,\n"
                + "`socialTripId` = ?,\n"
                + "`role` = ?\n"
                + "WHERE `id` = ?;";
        SOCIALTRIP_ATTENDANCEGROUP_DELETE_QUERY = "DELETE FROM `ridesharing_db`.`SocialTripAttendance`\n"
                + "WHERE `id` = ?;";
        SOCIALTRIP_GROUP_INSERT_QUERY = "INSERT INTO `ridesharing_db`.`SocialTrip`\n"
                + "(`id`,\n"
                + "`creatorId`,\n"
                + "`location`,\n"
                + "`feeling`,\n"
                + "`feelingIcon`,\n"
                + "`wantToGo`,\n"
                + "`wantToGoIcon`,\n"
                + "`content`,\n"
                + "`createdTime`)\n"
                + "VALUES\n"
                + "(?,?,?,?,?,?,?,?,?);";
        SOCIALTRIP_GROUP_UPDATE_QUERY = "UPDATE `ridesharing_db`.`SocialTrip`\n"
                + "SET\n"
                + "`id` = ?,\n"
                + "`creatorId` = ?,\n"
                + "`location` = ?,\n"
                + "`feeling` = ?,\n"
                + "`feelingIcon` = ?,\n"
                + "`wantToGo` = ?,\n"
                + "`wantToGoIcon` = ?,\n"
                + "`content` = ?,\n"
                + "`createdTime` = ?\n"
                + "WHERE `id` = ?;";
        SOCIALTRIP_GROUP_DELETE_QUERY = "DELETE FROM `ridesharing_db`.`SocialTrip`\n"
                + "WHERE `id` = ?;";

        TRIPCALENDAR_INSERT_QUERY = "INSERT INTO `ridesharing_db`.`TripCalendar`\n"
                + "(`id`,\n"
                + "`creatorId`,\n"
                + "`createdTime`,\n"
                + "`geoCellGrid`)\n"
                + "VALUES\n"
                + "(?,?,?,?);";
        TRIPCALENDAR_UPDATE_QUERY = "UPDATE `ridesharing_db`.`TripCalendar`\n"
                + "SET\n"
                + "`id` = ?,\n"
                + "`creatorId` = ?,\n"
                + "`createdTime` = ?,\n"
                + "`geoCellGrid` = ?\n"
                + "WHERE `id` = ?;";
        TRIPCALENDAR_DELETE_QUERY = "DELETE FROM `ridesharing_db`.`TripCalendar`\n"
                + "WHERE `id` = ?;";

        ROUTE_INSERT_QUERY = "INSERT INTO `ridesharing_db`.`Route`\n"
                + "(`id`,\n"
                + "`rawRoutingResult`,\n"
                + "`estimatedFuel`,\n"
                + "`estimatedTime`,\n"
                + "`startLocation`,\n"
                + "`waypoints`,\n"
                + "`endLocation`,\n"
                + "`overViewPolyLine`,\n"
                + "`createdTime`,\n"
                + "`sumDistance`,\n"
                + "`creatorId`,\n"
                + "`title`, `role`)\n"
                + "VALUES\n"
                + "(?,?,?,?,?,"
                + "?,?,?,?,?,"
                + "?,?,?);";
        ROUTE_UPDATE_QUERY = "UPDATE `ridesharing_db`.`Route`\n"
                + "SET\n"
                + "`id` = ?,\n"
                + "`rawRoutingResult` = ?,\n"
                + "`estimatedFuel` = ?,\n"
                + "`estimatedTime` = ?,\n"
                + "`startLocation` = ?,\n"
                + "`waypoints` = ?,\n"
                + "`endLocation` = ?,\n"
                + "`overViewPolyLine` = ?,\n"
                + "`createdTime` = ?,\n"
                + "`sumDistance` = ?,\n"
                + "`creatorId` = ?,\n"
                + "`title` = ?, `role` = ?\n"
                + "WHERE `id` = ?;";
        ROUTE_DELETE_QUERY = "DELETE FROM `ridesharing_db`.`Route`\n"
                + "WHERE `id` = ?;";
        FEEDBACK_GROUP_INSERT_QUERY = "INSERT INTO `ridesharing_db`.`Feedback`\n"
                + "(`id`,\n"
                + "`userId`,\n"
                + "`email`,\n"
                + "`content`,\n"
                + "`createdTime`)\n"
                + "VALUES\n"
                + "(?,?,?,?,?);";
        FEEDBACK_GROUP_UPDATE_QUERY = "UPDATE `ridesharing_db`.`Feedback`\n"
                + "SET\n"
                + "`id` = ?,\n"
                + "`userId` = ?,\n"
                + "`email` = ?,\n"
                + "`content` = ?,\n"
                + "`createdTime` = ?\n"
                + "WHERE `id` = ?;";
        FEEDBACK_GROUP_DELETE_QUERY = "DELETE FROM `ridesharing_db`.`Feedback`\n"
                + "WHERE `id` = ?;";
    }

}
