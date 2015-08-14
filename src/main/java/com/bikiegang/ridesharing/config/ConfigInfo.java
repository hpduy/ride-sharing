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

    static {
        RIDESHARING_DB = "ridesharing_db";
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
        LINKEDLOCATION_INSERT_QUERY = "INSERT INTO `ridesharing_db`.`LinkedLocation`\n"
                + "(`id`,\n"
                + "`time`,\n"
                + "`estimatedTime`,\n"
                + "`index`,\n"
                + "`refId`,\n"
                + "`refType`,\n"
                + "`lat`,\n"
                + "`lng`)\n"
                + "VALUES\n"
                + "(?,?,?,?,?,?,?,?);";

        LINKEDLOCATION_UPDATE_QUERY = "UPDATE `ridesharing_db`.`LinkedLocation`\n"
                + "SET\n"
                + "`id` = ?,\n"
                + "`time` = ?,\n"
                + "`estimatedTime` = ?,\n"
                + "`index` = ?,\n"
                + "`refId` = ?,\n"
                + "`refType` = ?,\n"
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
                + "`goTime`,\n"
                + "`arriveTime`,\n"
                + "`sumDistance`,\n"
                + "`estimatedTime`,\n"
                + "`estimatedPrice`,\n"
                + "`ownerPrice`,\n"
                + "`estimatedFuel`,\n"
                + "`creatorId`,\n"
                + "`role`,\n"
                + "`type`,\n"
                + "`plannedTripTrailPolyLine`,\n"
                + "`rawRoutingResult`, `groupId`, `startLocation`, `endLocation`,"
                + " `polyLine`, `hasHelmet`, `createdTime`, `isBusy`)\n"
                + "VALUES\n"
                + "(?,?,?,?,"
                + "?,?,?,?,?,"
                + "?,?,?,?,?,"
                + "?,?,?,?,"
                + "?,?);";
        PLANNEDTRIP_UPDATE_QUERY = "UPDATE `ridesharing_db`.`PlannedTrip`\n"
                + "SET\n"
                + "`id` = ?,\n"
                + "`goTime` = ?,\n"
                + "`arriveTime` = ?,\n"
                + "`sumDistance` = ?,\n"
                + "`estimatedTime` = ?,\n"
                + "`estimatedPrice` = ?,\n"
                + "`ownerPrice` = ?,\n"
                + "`estimatedFuel` = ?,\n"
                + "`creatorId` = ?,\n"
                + "`role` = ?,\n"
                + "`type` = ?,\n"
                + "`plannedTripTrailPolyLine` = ?,\n"
                + "`rawRoutingResult` = ?,\n"
                + "`groupId` = ?,\n"
                + "`startLocation` = ?,\n"
                + "`endLocation` = ?,\n"
                + "`polyLine` = ?,\n"
                + "`hasHelmet` = ?,\n"
                + "`createdTime` = ?\n,"
                + "`isBusy` = ?\n"
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
                + "`pricePaid`,\n"
                + "`sensitiveLocationId`,\n"
                + "`breakReason`,\n"
                + "`breakTrip`,\n"
                + "`dangerTrip`,\n"
                + "`driverPlannedTripId`,\n"
                + "`passengerPlannedTripId`,\n"
                + "`tripTrailPolyLine`)\n"
                + "VALUES\n"
                + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
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
                + "`dangerTrip` = ?,\n"
                + "`driverPlannedTripId` = ?,\n"
                + "`passengerPlannedTripId` = ?,\n"
                + "`tripTrailPolyLine` = ?\n"
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
                + "`privacy`, `job`)\n"
                + "VALUES\n"
                + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
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
                + "`job` = ?\n"
                + "WHERE `id` = ?;";
        USER_DELETE_QUERY = "DELETE FROM `ridesharing_db`.`User`\n"
                + "WHERE `id` = ?;";
        REQUESTVERIFY_INSERT_QUERY = "INSERT INTO `ridesharing_db`.`RequestVerify`\n"
                + "(`id`,\n"
                + "`userId`,\n"
                + "`angelId`,\n"
                + "`numberOfCertificate`,\n"
                + "`signature`,\n"
                + "`status`, `createdTime`)\n"
                + "VALUES\n"
                + "(?,?,?,?,?,?,?);";
        REQUESTVERIFY_UPDATE_QUERY = "UPDATE `ridesharing_db`.`RequestVerify`\n"
                + "SET\n"
                + "`id` = ?,\n"
                + "`userId` = ?,\n"
                + "`angelId` = ?,\n"
                + "`numberOfCertificate` = ?,\n"
                + "`signature` = ?,\n"
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
                + "`image` = ?,\n"
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
                + "`createdTime`)\n"
                + "VALUES\n"
                + "(?,?,?,?,?,?);";
        ANGEL_GROUP_UPDATE_QUERY = "UPDATE `ridesharing_db`.`AngelGroup`\n"
                + "SET\n"
                + "`id` = ?,\n"
                + "`location` = ?,\n"
                + "`tagName` = ?,\n"
                + "`canonicalName` = ?,\n"
                + "`address` = ?,\n"
                + "`createdTime` = ?\n"
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

    }

}
