package com.bikiegang.ridesharing.controller;

import com.bikiegang.ridesharing.dao.RequestMakeTripDao;
import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.database.IdGenerator;
import com.bikiegang.ridesharing.parsing.Parser;
import com.bikiegang.ridesharing.pojo.PlannedTrip;
import com.bikiegang.ridesharing.pojo.RequestMakeTrip;
import com.bikiegang.ridesharing.pojo.User;
import com.bikiegang.ridesharing.pojo.request.CreatePlannedTripRequest;
import com.bikiegang.ridesharing.pojo.request.ReplyMakeTripRequest;
import com.bikiegang.ridesharing.pojo.request.RequestMakeTripRequest;
import com.bikiegang.ridesharing.pojo.response.CreatePlannedTripResponse;
import com.bikiegang.ridesharing.utilities.DateTimeUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

/**
 * Created by hpduy17 on 7/1/15.
 */
public class RequestMakeTripController {
    private RequestMakeTripDao dao = new RequestMakeTripDao();
    private Database database = Database.getInstance();

    public String sendRequestMakeTrip(RequestMakeTripRequest request) throws Exception {
        if (null == request.getSenderId() || request.getSenderId().equals("")) {
            return Parser.ObjectToJSon(false, "'senderId' is not found");
        }
        if (null == request.getReceiverId() || request.getReceiverId().equals("")) {
            return Parser.ObjectToJSon(false, "'receiverId' is not found");
        }
        if (request.getReceiverPlannedTripId() <= 0) {
            return Parser.ObjectToJSon(false, "receiverPlannedTripId is invalid");
        }
        if (request.getSenderRole() != User.DRIVER && request.getSenderRole() != User.PASSENGER) {
            return Parser.ObjectToJSon(false, "'senderRole' is invalid");
        }

        // process plannedTrip
        //case 1 : sender's planned trips is created ->
        long driverPlannedTripId;
        long passengerPlannedTripId;
        if (request.getSenderPlannedTripId() > 0) {

            if (request.getSenderRole() == User.DRIVER) {
                driverPlannedTripId = request.getSenderPlannedTripId();
                passengerPlannedTripId = request.getReceiverPlannedTripId();
            } else {
                driverPlannedTripId = request.getReceiverPlannedTripId();
                passengerPlannedTripId = request.getSenderPlannedTripId();
            }
        }
        //case 2 & 3 : planned trip of sender is not created
        //case 2: sender is driver -> copy from passenger's planned trip
        else if (request.getSenderRole() == User.DRIVER) {
            passengerPlannedTripId = request.getReceiverPlannedTripId();

            //copy from passenger's planned trip
            PlannedTrip passengerPlannedTrip = database.getPlannedTripHashMap().get(passengerPlannedTripId);
            if(passengerPlannedTrip == null){
                return Parser.ObjectToJSon(false, "Receiver's PlannedTrip is not found");
            }
            CreatePlannedTripRequest createRequest = new CreatePlannedTripRequest();
            createRequest.setCreatorId(request.getSenderId());
            createRequest.setGoTime(DateTimeUtil.now());
            createRequest.setRole(request.getSenderRole());
            createRequest.setGoogleRoutingResult(passengerPlannedTrip.getRawRoutingResult().toString());
            createRequest.setIsParing(false);// no paring
            createRequest.setPrice(-1);// default price
            String response = new PlannedTripController().createPlannedTrip(createRequest);
            Parser parser = Parser.JSonToParser(response, CreatePlannedTripResponse.class);
            if(parser.isSuccess()){
                CreatePlannedTripResponse createPlannedTripResponse = (CreatePlannedTripResponse) parser.getResult();
                driverPlannedTripId = createPlannedTripResponse.getYourPlannedTrip().getId();
            }else{
                return Parser.ObjectToJSon(false, parser.getMessage());
            }

        }
        //case 3: sender is passenger -> create route from google routing result
        else {
            driverPlannedTripId = request.getReceiverPlannedTripId();
            CreatePlannedTripRequest createRequest = new CreatePlannedTripRequest();
            createRequest.setCreatorId(request.getSenderId());
            createRequest.setGoTime(DateTimeUtil.now());
            createRequest.setRole(request.getSenderRole());
            createRequest.setGoogleRoutingResult(request.getGoogleRoutingResult());
            createRequest.setIsParing(false);// no paring
            createRequest.setPrice(-1);// default price
            String response = new PlannedTripController().createPlannedTrip(createRequest);
            Parser parser = Parser.JSonToParser(response, CreatePlannedTripResponse.class);
            if(parser.isSuccess()){
                CreatePlannedTripResponse createPlannedTripResponse = (CreatePlannedTripResponse) parser.getResult();
                passengerPlannedTripId = createPlannedTripResponse.getYourPlannedTrip().getId();
            }else{
                return Parser.ObjectToJSon(false, parser.getMessage());
            }
        }
        // process request
        RequestMakeTrip requestMakeTrip = new RequestMakeTrip();
        requestMakeTrip.setId(IdGenerator.getRequestMakeTripId());
        requestMakeTrip.setReceiverId(request.getReceiverId());
        requestMakeTrip.setSenderId(request.getSenderId());
        requestMakeTrip.setStatus(RequestMakeTrip.WAITING);

        requestMakeTrip.setDriverPlannedTripId(driverPlannedTripId);
        requestMakeTrip.setPassengerPlannedTripId(passengerPlannedTripId);

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
        if (request.getStatus() != RequestMakeTrip.WAITING) {
            return Parser.ObjectToJSon(false, "Request has replied");
        }
        // check user status
        if (request.getReplierId().equals(requestMakeTrip.getReceiverId())) {
            User sender = database.getUserHashMap().get(requestMakeTrip.getSenderId());
            if (sender.isBusy()) {
                requestMakeTrip.setStatus(RequestMakeTrip.DENY);
                dao.update(requestMakeTrip);
                return Parser.ObjectToJSon(false, "Your partner is busy, please choose another");
            }
        } else if (request.getReplierId().equals(requestMakeTrip.getSenderId())) {
            User receiver = database.getUserHashMap().get(requestMakeTrip.getReceiverId());
            if (receiver.isBusy()) {
                requestMakeTrip.setStatus(RequestMakeTrip.DENY);
                dao.update(requestMakeTrip);
                return Parser.ObjectToJSon(false, "Your partner is busy, please choose another");
            }
        } else {
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
                long routeId = requestMakeTrip.getSenderRole() == User.DRIVER ? requestMakeTrip.getPassengerPlannedTripId() : requestMakeTrip.getDriverPlannedTripId();
                denyRequest(receiver.getId(), routeId);
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
            if (requestMakeTrip != null) {
                requestMakeTrip.setStatus(RequestMakeTrip.DENY);
                dao.update(requestMakeTrip);
            }
        }

    }
}
