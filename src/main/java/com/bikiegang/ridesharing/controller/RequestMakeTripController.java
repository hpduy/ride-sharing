package com.bikiegang.ridesharing.controller;

import com.bikiegang.ridesharing.dao.RequestMakeTripDao;
import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.database.IdGenerator;
import com.bikiegang.ridesharing.parsing.Parser;
import com.bikiegang.ridesharing.pojo.RequestMakeTrip;
import com.bikiegang.ridesharing.pojo.User;
import com.bikiegang.ridesharing.pojo.request.ReplyMakeTripRequest;
import com.bikiegang.ridesharing.pojo.request.RequestMakeTripRequest;
import com.bikiegang.ridesharing.utilities.DateTimeUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

/**
 * Created by hpduy17 on 7/1/15.
 */
public class RequestMakeTripController {
    private RequestMakeTripDao dao = new RequestMakeTripDao();
    private Database database = Database.getInstance();

    public String sendRequestMakeTrip(RequestMakeTripRequest request) throws JsonProcessingException {
        if (null == request.getSenderId() || request.getSenderId().equals("")) {
            return Parser.ObjectToJSon(false, "'senderId' is not found");
        }
        if (null == request.getReceiverId() || request.getReceiverId().equals("")) {
            return Parser.ObjectToJSon(false, "'receiverId' is not found");
        }
        if (request.getReceiverPlannedTripId() <= 0) {
            return Parser.ObjectToJSon(false, "'receiverRouteId' is invalid");
        }
        if (request.getSenderPlannedTripId() <= 0) {
            return Parser.ObjectToJSon(false, "'senderRouteId' is invalid");
        }
        if (request.getSenderRole() != User.DRIVER && request.getSenderRole() != User.PASSENGER) {
            return Parser.ObjectToJSon(false, "'senderRole' is invalid");
        }
        RequestMakeTrip requestMakeTrip = new RequestMakeTrip();
        requestMakeTrip.setId(IdGenerator.getRequestMakeTripId());
        requestMakeTrip.setReceiverId(request.getReceiverId());
        requestMakeTrip.setSenderId(request.getSenderId());
        requestMakeTrip.setStatus(RequestMakeTrip.WAITING);
        if(request.getSenderRole() == User.DRIVER){
            requestMakeTrip.setDriverPlannedTripId(request.getSenderPlannedTripId());
            requestMakeTrip.setPassengerPlannedTripId(request.getReceiverPlannedTripId());
        }else {
            requestMakeTrip.setDriverPlannedTripId(request.getReceiverPlannedTripId());
            requestMakeTrip.setPassengerPlannedTripId(request.getSenderPlannedTripId());
        }
        requestMakeTrip.setSenderRole(request.getSenderRole());
        requestMakeTrip.setCreatedTime(DateTimeUtil.now());
        if (dao.insert(requestMakeTrip)) {
            return Parser.ObjectToJSon(true, "The request have been sent successfully");
        }
        return Parser.ObjectToJSon(false, "Cannot send a request");
    }

    public String replyRequestMakeTrip(ReplyMakeTripRequest request) throws JsonProcessingException {
        if (request.getRequestMakeTripId() <= 0) {
            return Parser.ObjectToJSon(false, "'requestMakeTripId' is not found");
        }
        if (request.getStatus() != RequestMakeTrip.ACCEPT && request.getStatus() != RequestMakeTrip.DENY) {
            return Parser.ObjectToJSon(false, "'status' is invalid");
        }
        RequestMakeTrip requestMakeTrip = database.getRequestMakeTripHashMap().get(request.getRequestMakeTripId());
        if (null == requestMakeTrip) {
            return Parser.ObjectToJSon(false, "Request is not exist");
        }
        if(request.getStatus() != RequestMakeTrip.WAITING){
            return Parser.ObjectToJSon(false, "Request has replied");
        }
        // check user status
        if(request.getReplierId().equals(requestMakeTrip.getReceiverId())){
            User sender = database.getUserHashMap().get(requestMakeTrip.getSenderId());
            if(sender.isBusy()){
                requestMakeTrip.setStatus(RequestMakeTrip.DENY);
                dao.update(requestMakeTrip);
                return Parser.ObjectToJSon(false, "Your partner is busy, please choose another");
            }
        }else if(request.getReplierId().equals(requestMakeTrip.getSenderId())){
            User receiver = database.getUserHashMap().get(requestMakeTrip.getReceiverId());
            if(receiver.isBusy()){
                requestMakeTrip.setStatus(RequestMakeTrip.DENY);
                dao.update(requestMakeTrip);
                return Parser.ObjectToJSon(false, "Your partner is busy, please choose another");
            }
        }else{
            return Parser.ObjectToJSon(false, "This request is not belong to you");
        }
        //---done checker---
        // set status
        requestMakeTrip.setStatus(request.getStatus());
        if (dao.update(requestMakeTrip)) {
            //TODO notification
            // Check status of request make trip
            if (requestMakeTrip.getStatus() == RequestMakeTrip.ACCEPT) {
                // Change user's status
                User sender = database.getUserHashMap().get(requestMakeTrip.getSenderId());
                User receiver = database.getUserHashMap().get(requestMakeTrip.getReceiverId());
                sender.setIsBusy(true);
                receiver.setIsBusy(true);
                // Deny all request similar of other user in receiver box
                long routeId = requestMakeTrip.getSenderRole() == User.DRIVER ? requestMakeTrip.getPassengerPlannedTripId(): requestMakeTrip.getDriverPlannedTripId();
                denyRequest(receiver.getId(), routeId );
                //TODO make a trip
            }
            return Parser.ObjectToJSon(true, "Reply successfully");
        }
        return Parser.ObjectToJSon(false, "Cannot reply");
    }

    private void denyRequest(String userId, long routeId) {
        List<Long> requestList = database.getReceiverRequestsBox().get(userId).get(routeId);
        for (long requestId : requestList) {
            RequestMakeTrip requestMakeTrip = database.getRequestMakeTripHashMap().get(requestId);
            if(requestMakeTrip != null){
                requestMakeTrip.setStatus(RequestMakeTrip.DENY);
                dao.update(requestMakeTrip);
            }
        }

    }
}
