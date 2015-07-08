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

    public static String RIDESHARING_DB = "ridesharing_db";
    public static String REDIS_SERVER = "redis_server";
    public static String RIDESHARING_WORKER_GEARMAN = "ridesharing_gearman";

    public static String ACTIVITY_INSERT_QUERY;
    public static String ACTIVITY_UPDATE_QUERY;
    public static String ACTIVITY_DELETE_QUERY;
    public static String BROADCAST_INSERT_QUERY;
    public static String BROADCAST_UPDATE_QUERY;
    public static String BROADCAST_DELETE_QUERY;
    public static String CIRCLE_DELETE_QUERY;
    public static String CIRCLE_UPDATE_QUERY;
    public static String CIRCLE_INSERT_QUERY;
    public static String CIRCLEMEMBER_INSERT_QUERY;
    public static String CIRCLEMEMBER_UPDATE_QUERY;
    public static String CIRCLEMEMBER_DELETE_QUERY;
    public static String CURRENTLOCATION_INSERT_QUERY;
    public static String CURRENTLOCATION_UPDATE_QUERY;
    public static String CURRENTLOCATION_DELETE_QUERY;
    public static String ENDORSE_INSERT_QUERY;
    public static String ENDORSE_UPDATE_QUERY;
    public static String ENDORSE_DELETE_QUERY;
    public static String LINKEDLOCATION_DELETE_QUERY;
    public static String LINKEDLOCATION_UPDATE_QUERY;
    public static String LINKEDLOCATION_INSERT_QUERY;
    public static String LOCATION_DELETE_QUERY;
    public static String LOCATION_UPDATE_QUERY;
    public static String LOCATION_INSERT_QUERY;
    public static String RATING_DELETE_QUERY;
    public static String RATING_UPDATE_QUERY;
    public static String RATING_INSERT_QUERY;
    public static String REQUESTMAKETRIP_DELETE_QUERY;
    public static String REQUESTMAKETRIP_UPDATE_QUERY;
    public static String REQUESTMAKETRIP_INSERT_QUERY;
    public static String ROUTE_DELETE_QUERY;
    public static String ROUTE_UPDATE_QUERY;
    public static String ROUTE_INSERT_QUERY;
    public static String TRIP_DELETE_QUERY;
    public static String TRIP_UPDATE_QUERY;
    public static String TRIP_INSERT_QUERY;
    public static String TRIPFEEDBACK_INSERT_QUERY;
    public static String TRIPFEEDBACK_UPDATE_QUERY;
    public static String TRIPFEEDBACK_DELETE_QUERY;
    public static String USER_INSERT_QUERY;
    public static String USER_UPDATE_QUERY;
    public static String USER_DELETE_QUERY;
    public static String USERPROFILE_INSERT_QUERY;
    public static String USERPROFILE_UPDATE_QUERY;
    public static String USERPROFILE_DELETE_QUERY;
    public static String VERIFIEDCERTIFICATE_DELETE_QUERY;
    public static String VERIFIEDCERTIFICATE_UPDATE_QUERY;
    public static String VERIFIEDCERTIFICATE_INSERT_QUERY;

    static {
        RIDESHARING_DB = "ridesharing_db";
        ACTIVITY_INSERT_QUERY = "INSERT INTO `ridesharing_db`.`Activity`\n"
                + "(`id`,\n"
                + "`ownerId`,\n"
                + "`posUserId`,\n"
                + "`negUserId`,\n"
                + "`createdTime`,\n"
                + "`action`,\n"
                + "`ref`,\n"
                + "`isRead`)\n"
                + "VALUES\n"
                + "(?,?,?,?,?,?,?,?);";
        ACTIVITY_UPDATE_QUERY = "UPDATE `ridesharing_db`.`Activity`\n"
                + "SET\n"
                + "`id` = ?,\n"
                + "`ownerId` = ?,\n"
                + "`posUserId` = ?,\n"
                + "`negUserId` = ?,\n"
                + "`createdTime` = ?,\n"
                + "`action` = ?,\n"
                + "`ref` = ?,\n"
                + "`isRead` = ?\n"
                + "WHERE `id` = ?;";

        ACTIVITY_DELETE_QUERY = "DELETE FROM `ridesharing_db`.`Activity`\n"
                + "WHERE `id` = ?;";
        BROADCAST_INSERT_QUERY = "INSERT INTO `ridesharing_db`.`Broadcast`\n"
                + "(`id`,\n"
                + "`userId`,\n"
                + "`deviceId`,\n"
                + "`regId`,\n"
                + "`os`)\n"
                + "VALUES\n"
                + "(?,?,?,?,?);";
        BROADCAST_UPDATE_QUERY = "UPDATE `ridesharing_db`.`Broadcast`\n"
                + "SET\n"
                + "`id` = ?,\n"
                + "`userId` = ?,\n"
                + "`deviceId` = ?,\n"
                + "`regId` = ?,\n"
                + "`os` = ?\n"
                + "WHERE `id` = ?;";
        BROADCAST_DELETE_QUERY = "DELETE FROM `ridesharing_db`.`Broadcast`\n"
                + "WHERE `id` = ?;";

        CIRCLE_INSERT_QUERY = "INSERT INTO `ridesharing_db`.`Circle`\n"
                + "(`id`,\n"
                + "`name`,\n"
                + "`description`,\n"
                + "`createdTime`,\n"
                + "`status`)\n"
                + "VALUES\n"
                + "(?,?,?,?,?);";

        CIRCLE_UPDATE_QUERY = "UPDATE `ridesharing_db`.`Circle`\n"
                + "SET\n"
                + "`id` = ?,\n"
                + "`name` = ?,\n"
                + "`description` = ?,\n"
                + "`createdTime` = ?,\n"
                + "`status` = ?\n"
                + "WHERE `id` = ?;";

        CIRCLE_DELETE_QUERY = "DELETE FROM `ridesharing_db`.`Circle`\n"
                + "WHERE `id` = ?;";

        CIRCLEMEMBER_INSERT_QUERY = "INSERT INTO `ridesharing_db`.`CircleMember`\n"
                + "(`id`,\n"
                + "`circleId`,\n"
                + "`memberId`,\n"
                + "`createdTime`)\n"
                + "VALUES\n"
                + "(?,?,?,?);";
        CIRCLEMEMBER_UPDATE_QUERY = "UPDATE `ridesharing_db`.`CircleMember`\n"
                + "SET\n"
                + "`id` = ?,\n"
                + "`circleId` = ?,\n"
                + "`memberId` = ?,\n"
                + "`createdTime` = ?\n"
                + "WHERE `id` = ?;";
        CIRCLEMEMBER_DELETE_QUERY = "DELETE FROM `ridesharing_db`.`CircleMember`\n"
                + "WHERE `id` = ?;";

        CURRENTLOCATION_INSERT_QUERY = "INSERT INTO `ridesharing_db`.`CurrentLocation`\n"
                + "(`id`,\n"
                + "`createdTime`,\n"
                + "`estimatedTime`,\n"
                + "`address`,\n"
                + "`userId`,\n"
                + "`previousLocationId`,\n"
                + "`lat`,\n"
                + "`lng`)\n"
                + "VALUES\n"
                + "(?,?,?,?,?,?,?,?);";
        CURRENTLOCATION_UPDATE_QUERY = "UPDATE `ridesharing_db`.`CurrentLocation`\n"
                + "SET\n"
                + "`id` = ?,\n"
                + "`createdTime` = ?,\n"
                + "`estimatedTime` = ?,\n"
                + "`address` = ?,\n"
                + "`userId` = ?,\n"
                + "`previousLocationId` = ?,\n"
                + "`lat` = ?,\n"
                + "`lng` = ?\n"
                + "WHERE `id` = ?;";
        CURRENTLOCATION_DELETE_QUERY = "DELETE FROM `ridesharing_db`.`CurrentLocation`\n"
                + "WHERE `id` = ?;";

        ENDORSE_INSERT_QUERY = "INSERT INTO `ridesharing_db`.`Endorse`\n"
                + "(`id`,\n"
                + "`endorserId`,\n"
                + "`endorsedUserId`,\n"
                + "`circleId`,\n"
                + "`comment`,\n"
                + "`createdTime`)\n"
                + "VALUES\n"
                + "(?,?,?,?,?,?);";
        ENDORSE_UPDATE_QUERY = "UPDATE `ridesharing_db`.`Endorse`\n"
                + "SET\n"
                + "`id` = ?,\n"
                + "`endorserId` = ?,\n"
                + "`endorsedUserId` = ?,\n"
                + "`circleId` = ?,\n"
                + "`comment` = ?,\n"
                + "`createdTime` = ?\n"
                + "WHERE `id` = ?;";

        ENDORSE_DELETE_QUERY = "DELETE FROM `ridesharing_db`.`Endorse`\n"
                + "WHERE `id` = ?;";

        LINKEDLOCATION_INSERT_QUERY = "INSERT INTO `ridesharing_db`.`LinkedLocation`\n"
                + "(`id`,\n"
                + "`createdTime`,\n"
                + "`estimatedTime`,\n"
                + "`address`,\n"
                + "`index`,\n"
                + "`refId`,\n"
                + "`refType`,\n"
                + "`lat`,\n"
                + "`lng`)\n"
                + "VALUES\n"
                + "(?,?,?,?,?,?,?,?,?);";

        LINKEDLOCATION_UPDATE_QUERY = "UPDATE `ridesharing_db`.`LinkedLocation`\n"
                + "SET\n"
                + "`id` = ?,\n"
                + "`createdTime` = ?,\n"
                + "`estimatedTime` = ?,\n"
                + "`address` = ?,\n"
                + "`index` = ?,\n"
                + "`refId` = ?,\n"
                + "`refType` = ?,\n"
                + "`lat` = ?,\n"
                + "`lng` = ?\n"
                + "WHERE `id` = ?;";

        LINKEDLOCATION_DELETE_QUERY = "DELETE FROM `ridesharing_db`.`LinkedLocation`\n"
                + "WHERE `id` = ?;";

        LOCATION_INSERT_QUERY = "INSERT INTO `ridesharing_db`.`Location`\n"
                + "(`id`,\n"
                + "`createdTime`,\n"
                + "`estimatedTime`,\n"
                + "`address`,\n"
                + "`lat`,\n"
                + "`lng`)\n"
                + "VALUES\n"
                + "(?,?,?,?,?,?);";
        LOCATION_UPDATE_QUERY = "UPDATE `ridesharing_db`.`Location`\n"
                + "SET\n"
                + "`id` = ?,\n"
                + "`createdTime` = ?,\n"
                + "`estimatedTime` = ?,\n"
                + "`address` = ?,\n"
                + "`lat` = ?,\n"
                + "`lng` = ?\n"
                + "WHERE `id` = ?;";
        LOCATION_DELETE_QUERY = "DELETE FROM `ridesharing_db`.`Location`\n"
                + "WHERE `id` = ?;";

        RATING_INSERT_QUERY = "INSERT INTO `ridesharing_db`.`Rating`\n"
                + "(`id`,\n"
                + "`assessorId`,\n"
                + "`profileId`,\n"
                + "`value`,\n"
                + "`createdTime`,\n"
                + "`comment`)\n"
                + "VALUES\n"
                + "(?,?,?,?,?,?);";

        RATING_UPDATE_QUERY = "UPDATE `ridesharing_db`.`Rating`\n"
                + "SET\n"
                + "`id` = ?,\n"
                + "`assessorId` = ?,\n"
                + "`profileId` = ?,\n"
                + "`value` = ?,\n"
                + "`createdTime` = ?,\n"
                + "`comment` = ?\n"
                + "WHERE `id` = ?;";

        RATING_DELETE_QUERY = "DELETE FROM `ridesharing_db`.`Rating`\n"
                + "WHERE `id` = ?;";

        REQUESTMAKETRIP_INSERT_QUERY = "INSERT INTO `ridesharing_db`.`RequestMakeTrip`\n"
                + "(`id`,\n"
                + "`senderId`,\n"
                + "`receiverId`,\n"
                + "`createdTime`,\n"
                + "`senderRouteId`,\n"
                + "`status`)\n"
                + "VALUES\n"
                + "(?,?,?,?,?,?);";
        REQUESTMAKETRIP_UPDATE_QUERY = "UPDATE `ridesharing_db`.`RequestMakeTrip`\n"
                + "SET\n"
                + "`id` = ?,\n"
                + "`senderId` = ?,\n"
                + "`receiverId` = ?,\n"
                + "`createdTime` = ?,\n"
                + "`senderRouteId` = ?,\n"
                + "`status` = ?\n"
                + "WHERE `id` = ?;";
        REQUESTMAKETRIP_DELETE_QUERY = "DELETE FROM `ridesharing_db`.`RequestMakeTrip`\n"
                + "WHERE `id` = ?;";
        ROUTE_INSERT_QUERY = "INSERT INTO `ridesharing_db`.`Route`\n"
                + "(`id`,\n"
                + "`goTime`,\n"
                + "`sumDistance`,\n"
                + "`estimatedTime`,\n"
                + "`estimatedPrice`,\n"
                + "`ownerPrice`,\n"
                + "`estimatedFuel`,\n"
                + "`creatorId`,\n"
                + "`role`,\n"
                + "`type`,\n"
                + "`arriveTime`)\n"
                + "VALUES\n"
                + "(?,?,?,?,?,?,?,?,?,?,?);";
        ROUTE_UPDATE_QUERY = "UPDATE `ridesharing_db`.`Route`\n"
                + "SET\n"
                + "`id` = ?,\n"
                + "`goTime` = ?,\n"
                + "`sumDistance` = ?,\n"
                + "`estimatedTime` = ?,\n"
                + "`estimatedPrice` = ?,\n"
                + "`ownerPrice` = ?,\n"
                + "`estimatedFuel` = ?,\n"
                + "`creatorId` = ?,\n"
                + "`role` = ?,\n"
                + "`type` = ?,\n"
                + "`arriveTime` = ?\n"
                + "WHERE `id` = ?;";
        ROUTE_DELETE_QUERY = "DELETE FROM `ridesharing_db`.`Route`\n"
                + "WHERE `id` = ?;";
        TRIP_INSERT_QUERY = "INSERT INTO `ridesharing_db`.`Trip`\n"
                + "(`id`,\n"
                + "`startTime`,\n"
                + "`driverId`,\n"
                + "`passengerId`,\n"
                + "`realDistance`,\n"
                + "`endTime`,\n"
                + "`pricePaid`,\n"
                + "`sensitiveLocationId`,\n"
                + "`breakReason`,\n"
                + "`breakTrip`,\n"
                + "`dangerTrip`)\n"
                + "VALUES\n"
                + "(?,?,?,?,?,?,?,?,?,?,?);";
        TRIP_UPDATE_QUERY = "UPDATE `ridesharing_db`.`Trip`\n"
                + "SET\n"
                + "`id` = ?,\n"
                + "`startTime` = ?,\n"
                + "`driverId` = ?,\n"
                + "`passengerId` = ?,\n"
                + "`realDistance` = ?,\n"
                + "`endTime` = ?,\n"
                + "`pricePaid` = ?,\n"
                + "`sensitiveLocationId` = ?,\n"
                + "`breakReason` = ?,\n"
                + "`breakTrip` = ?,\n"
                + "`dangerTrip` = ?\n"
                + "WHERE `id` = ?;";
        TRIP_DELETE_QUERY = "DELETE FROM `ridesharing_db`.`Trip`\n"
                + "WHERE `id` = ?;";
        TRIPFEEDBACK_INSERT_QUERY = "INSERT INTO `ridesharing_db`.`TripFeedback`\n"
                + "(`id`,\n"
                + "`tripId`,\n"
                + "`userFeedBackId`,\n"
                + "`userBeFeedbackId`,\n"
                + "`value`,\n"
                + "`createdTime`,\n"
                + "`comment`)\n"
                + "VALUES\n"
                + "(?,?,?,?,?,?,?);";
        TRIPFEEDBACK_UPDATE_QUERY = "UPDATE `ridesharing_db`.`TripFeedback`\n"
                + "SET\n"
                + "`id` = ?,\n"
                + "`tripId` = ?,\n"
                + "`userFeedBackId` = ?,\n"
                + "`userBeFeedbackId` = ?,\n"
                + "`value` = ?,\n"
                + "`createdTime` = ?,\n"
                + "`comment` = ?\n"
                + "WHERE `id` = ?;";
        TRIPFEEDBACK_DELETE_QUERY = "DELETE FROM `ridesharing_db`.`TripFeedback`\n"
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
                + "`currentLocationId`,\n"
                + "`birthDay`,\n"
                + "`gender`,\n"
                + "`status`,\n"
                + "`isBusy`,\n"
                + "`currentRole`)\n"
                + "VALUES\n"
                + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
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
                + "`currentLocationId` = ?,\n"
                + "`birthDay` = ?,\n"
                + "`gender` = ?,\n"
                + "`status` = ?,\n"
                + "`isBusy` = ?,\n"
                + "`currentRole` = ?\n"
                + "WHERE `id` = ?;";
        USER_DELETE_QUERY = "DELETE FROM `ridesharing_db`.`User`\n"
                + "WHERE `id` = ?;";

        USERPROFILE_INSERT_QUERY = "INSERT INTO `ridesharing_db`.`UserProfile`\n"
                + "(`id`,\n"
                + "`userId`,\n"
                + "`name`,\n"
                + "`description`,\n"
                + "`createdTime`)\n"
                + "VALUES\n"
                + "(?,?,?,?,?);";
        USERPROFILE_UPDATE_QUERY = "UPDATE `ridesharing_db`.`UserProfile`\n"
                + "SET\n"
                + "`id` = ?,\n"
                + "`userId` = ?,\n"
                + "`name` = ?,\n"
                + "`description` = ?,\n"
                + "`createdTime` = ?\n"
                + "WHERE `id` = ?;";
        USERPROFILE_DELETE_QUERY = "DELETE FROM `ridesharing_db`.`UserProfile`\n"
                + "WHERE `id` = ?;";
        VERIFIEDCERTIFICATE_INSERT_QUERY = "INSERT INTO `ridesharing_db`.`VerifiedCertificate`\n"
                + "(`id`,\n"
                + "`image`,\n"
                + "`note`,\n"
                + "`createdTime`,\n"
                + "`type`,\n"
                + "`ownerId`,\n"
                + "`endorserId`,\n"
                + "`status`)\n"
                + "VALUES\n"
                + "(?,?,?,?,?,?,?,?);";
        VERIFIEDCERTIFICATE_UPDATE_QUERY = "UPDATE `ridesharing_db`.`VerifiedCertificate`\n"
                + "SET\n"
                + "`id` = ?,\n"
                + "`image` = ?,\n"
                + "`note` = ?,\n"
                + "`createdTime` = ?,\n"
                + "`type` = ?,\n"
                + "`ownerId` = ?,\n"
                + "`endorserId` = ?,\n"
                + "`status` = ?\n"
                + "WHERE `id` = ?;";
        VERIFIEDCERTIFICATE_DELETE_QUERY = "DELETE FROM `ridesharing_db`.`VerifiedCertificate`\n"
                + "WHERE `id` = ?;";
    }

}
