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
    public static String BROADCAST_INSERT_QUERY;
    public static String BROADCAST_UPDATE_QUERY;
    public static String BROADCAST_DELETE_QUERY;
    public static String LINKEDLOCATION_DELETE_QUERY;
    public static String LINKEDLOCATION_UPDATE_QUERY;
    public static String LINKEDLOCATION_INSERT_QUERY;
    public static String REQUESTMAKETRIP_DELETE_QUERY;
    public static String REQUESTMAKETRIP_UPDATE_QUERY;
    public static String REQUESTMAKETRIP_INSERT_QUERY;
    public static String ROUTE_DELETE_QUERY;
    public static String ROUTE_UPDATE_QUERY;
    public static String ROUTE_INSERT_QUERY;
    public static String TRIP_DELETE_QUERY;
    public static String TRIP_UPDATE_QUERY;
    public static String TRIP_INSERT_QUERY;
    public static String USER_INSERT_QUERY;
    public static String USER_UPDATE_QUERY;
    public static String USER_DELETE_QUERY;

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
                + "`driverRouteId`,\n"
                + "`passengerRouteId`,\n"
                + "`status`)\n"
                + "VALUES\n"
                + "(?,?,?,?,?,?,?,?);";
        REQUESTMAKETRIP_UPDATE_QUERY = "UPDATE `ridesharing_db`.`RequestMakeTrip`\n"
                + "SET\n"
                + "`id` = ?,\n"
                + "`senderId` = ?,\n"
                + "`receiverId` = ?,\n"
                + "`senderRole` = ?,\n"
                + "`createdTime` = ?,\n"
                + "`driverRouteId` = ?,\n"
                + "`passengerRouteId` = ?,\n"
                + "`status` = ?\n"
                + "WHERE `id` = ?;";
        REQUESTMAKETRIP_DELETE_QUERY = "DELETE FROM `ridesharing_db`.`RequestMakeTrip`\n"
                + "WHERE `id` = ?;";
        ROUTE_INSERT_QUERY = "INSERT INTO `ridesharing_db`.`Route`\n"
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
                + "`routeTrailPolyLine`,\n"
                + "`rawRoutingResult`)\n"
                + "VALUES\n"
                + "(?,?,?,?,?,?,?,?,?,?,?,?,?);";
        ROUTE_UPDATE_QUERY = "UPDATE `ridesharing_db`.`Route`\n"
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
                + "`routeTrailPolyLine` = ?,\n"
                + "`rawRoutingResult` = ?\n"
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
                + "`dangerTrip`,\n"
                + "`driverRouteId`,\n"
                + "`passengerRouteId`,\n"
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
                + "`driverRouteId` = ?,\n"
                + "`passengerRouteId` = ?,\n"
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
                + "`selfIntro`)\n"
                + "VALUES\n"
                + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
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
                + "`selfIntro` = ?\n"
                + "WHERE `id` = ?;";
        USER_DELETE_QUERY = "DELETE FROM `ridesharing_db`.`User`\n"
                + "WHERE `id` = ?;";
    }

}
