package com.bikiegang.ridesharing.controller;

import com.bikiegang.ridesharing.dao.TripDao;
import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.database.IdGenerator;
import com.bikiegang.ridesharing.geocoding.PolyLineProcess;
import com.bikiegang.ridesharing.parsing.Parser;
import com.bikiegang.ridesharing.pojo.RequestMakeTrip;
import com.bikiegang.ridesharing.pojo.Trip;
import com.bikiegang.ridesharing.pojo.User;
import com.bikiegang.ridesharing.pojo.request.EndTripRequest;
import com.bikiegang.ridesharing.pojo.request.GetInformationUsingUserIdRequest;
import com.bikiegang.ridesharing.pojo.request.StartTripRequest;
import com.bikiegang.ridesharing.utilities.BroadcastCenterUtil;
import com.bikiegang.ridesharing.utilities.daytime.DateTimeUtil;
import com.bikiegang.ridesharing.utilities.MessageMappingUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Created by hpduy17 on 8/18/15.
 */
public class TripController {
    private TripDao dao = new TripDao();
    private Database database = Database.getInstance();

    public String startTrip(StartTripRequest request) throws JsonProcessingException {
        if (request.getPlannedTripId() <= 0) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_invalid, "'plannedTripId'");
        }
        if (null == request.getUserId() || request.getUserId().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'userId'");
        }
        if (null == database.getSenderRequestsBox().get(request.getUserId()).get(request.getPlannedTripId())) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Object_is_not_found, "Request");
        }
        long requestId = database.getSenderRequestsBox().get(request.getUserId()).get(request.getPlannedTripId());
        RequestMakeTrip requestMakeTrip = database.getRequestMakeTripHashMap().get(requestId);
        if (null == requestMakeTrip) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Object_is_not_found, "Request");
        }
        Trip trip = new Trip();
        trip.setId(IdGenerator.getTripId());
        trip.setPassengerId(requestMakeTrip.getSenderRole() == User.PASSENGER ? requestMakeTrip.getSenderId() : requestMakeTrip.getReceiverId());
        trip.setDriverId(requestMakeTrip.getSenderRole() == User.DRIVER ? requestMakeTrip.getSenderId() : requestMakeTrip.getReceiverId());
        trip.setDriverPlannedTripId(requestMakeTrip.getDriverPlannedTripId());
        trip.setPassengerPlannedTripId(requestMakeTrip.getPassengerPlannedTripId());
        trip.setStartTime(DateTimeUtil.now());
        if (dao.insert(trip)) {
            return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully);
        }
        return Parser.ObjectToJSon(false, MessageMappingUtil.Interactive_with_database_fail);
    }

    public String endTrip(EndTripRequest request) throws JsonProcessingException {
        if (request.getTripId() <= 0) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_invalid, "'tripId'");
        }
        if (null == request.getEndLocation()) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'location'");
        }
        if (null == request.getTripTrailPolyLine() || request.getTripTrailPolyLine().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'tripTrailPolyLine'");
        }
        Trip trip = database.getTripHashMap().get(request.getTripId());
        if (null == trip) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Object_is_not_found, "trip");
        }
        trip.setTripTrailPolyLine(request.getTripTrailPolyLine());
        trip.setRealDistance(PolyLineProcess.getDistanceFromPolyLine(request.getTripTrailPolyLine()));
        trip.setEndTime(DateTimeUtil.now());
        trip.setSensitiveLocationId(request.getEndLocation());
        trip.setTripStatus(Trip.FINISHED_TRIP_WITH_OUT_RATING);

        if (dao.update(trip)) {
            try {
                User driver = database.getUserHashMap().get(trip.getDriverId());
                new BroadcastCenterUtil().pushNotification(Parser.ObjectToNotification(MessageMappingUtil.Notification_End_Trip, driver), trip.getPassengerId());
            } catch (Exception ignored) {
            }
            return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully);
        }
        return Parser.ObjectToJSon(false, MessageMappingUtil.Interactive_with_database_fail);
    }

    public String getListTripNonRating(GetInformationUsingUserIdRequest request) throws JsonProcessingException {
        if (null == request.getUserId() || request.getUserId().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'userId'");
        }
        //TODO waiting designer
        return "";
    }
}
