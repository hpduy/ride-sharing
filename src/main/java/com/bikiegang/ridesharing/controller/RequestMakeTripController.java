package com.bikiegang.ridesharing.controller;

import com.bikiegang.ridesharing.dao.RequestMakeTripDao;
import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.database.IdGenerator;
import com.bikiegang.ridesharing.parsing.NotificationParser;
import com.bikiegang.ridesharing.parsing.Parser;
import com.bikiegang.ridesharing.pojo.PlannedTrip;
import com.bikiegang.ridesharing.pojo.RequestMakeTrip;
import com.bikiegang.ridesharing.pojo.User;
import com.bikiegang.ridesharing.pojo.request.*;
import com.bikiegang.ridesharing.pojo.response.CreatePlannedTripResponse;
import com.bikiegang.ridesharing.pojo.response.GetListRequestMakeTripDetailResponse;
import com.bikiegang.ridesharing.pojo.response.Notification.ObjectNoti;
import com.bikiegang.ridesharing.pojo.response.Notification.ReplyMakeTripNoti;
import com.bikiegang.ridesharing.pojo.response.Notification.RequestMakeTripNoti;
import com.bikiegang.ridesharing.pojo.response.RequestMakeTripDetailResponse;
import com.bikiegang.ridesharing.pojo.response.angel.RequestMakeTripResponse;
import com.bikiegang.ridesharing.utilities.BroadcastCenterUtil;
import com.bikiegang.ridesharing.utilities.DateTimeUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;
import java.util.HashMap;
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
        // process plannedTrip
        //case 1 : sender's planned trips is created ->
        long driverPlannedTripId;
        long passengerPlannedTripId;
        PlannedTrip receiverPlannedTrip = database.getPlannedTripHashMap().get(request.getReceiverPlannedTripId());
        int senderRole = User.PASSENGER;
        if (request.getSenderPlannedTripId() > 0) {

            if (receiverPlannedTrip.getRole() == User.PASSENGER) {
                driverPlannedTripId = request.getSenderPlannedTripId();
                passengerPlannedTripId = request.getReceiverPlannedTripId();
                senderRole = User.DRIVER;
            } else {
                driverPlannedTripId = request.getReceiverPlannedTripId();
                passengerPlannedTripId = request.getSenderPlannedTripId();
            }
        }
        //case 2 & 3 : planned trip of sender is not created
        //case 2: sender is driver -> copy from passenger's planned trip
        else if (receiverPlannedTrip.getRole() == User.PASSENGER) {
            passengerPlannedTripId = request.getReceiverPlannedTripId();
            senderRole = User.DRIVER;
            //copy from passenger's planned trip
            PlannedTrip passengerPlannedTrip = database.getPlannedTripHashMap().get(passengerPlannedTripId);
            if (passengerPlannedTrip == null) {
                return Parser.ObjectToJSon(false, "Receiver's PlannedTrip is not found");
            }
            CreatePlannedTripRequest createRequest = new CreatePlannedTripRequest();
            createRequest.setCreatorId(request.getSenderId());
            createRequest.setGoTime(DateTimeUtil.now());
            createRequest.setRole(senderRole);
            createRequest.setGoogleRoutingResult(passengerPlannedTrip.getRawRoutingResult().toString());
            createRequest.setIsParing(false);// no paring
            createRequest.setPrice(-1);// default price
            String response = new PlannedTripController().createPlannedTrip(createRequest);
            Parser parser = Parser.JSonToParser(response, CreatePlannedTripResponse.class);
            if (parser.isSuccess()) {
                CreatePlannedTripResponse createPlannedTripResponse = (CreatePlannedTripResponse) parser.getResult();
                driverPlannedTripId = createPlannedTripResponse.getYourPlannedTrip().getPlannedTrip().getId();
            } else {
                return Parser.ObjectToJSon(false, parser.getMessage());
            }

        }
        //case 3: sender is passenger -> create route from google routing result
        else {
            driverPlannedTripId = request.getReceiverPlannedTripId();
            CreatePlannedTripRequest createRequest = new CreatePlannedTripRequest();
            createRequest.setCreatorId(request.getSenderId());
            createRequest.setGoTime(DateTimeUtil.now());
            createRequest.setRole(senderRole);
            createRequest.setGoogleRoutingResult(request.getGoogleRoutingResult());
            createRequest.setIsParing(false);// no paring
            createRequest.setPrice(-1);// default price
            String response = new PlannedTripController().createPlannedTrip(createRequest);
            Parser parser = Parser.JSonToParser(response, CreatePlannedTripResponse.class);
            if (parser.isSuccess()) {
                CreatePlannedTripResponse createPlannedTripResponse = (CreatePlannedTripResponse) parser.getResult();
                passengerPlannedTripId = createPlannedTripResponse.getYourPlannedTrip().getPlannedTrip().getId();
            } else {
                return Parser.ObjectToJSon(false, parser.getMessage());
            }
        }
        // process request
        RequestMakeTrip requestMakeTrip = new RequestMakeTrip();
        requestMakeTrip.setId(IdGenerator.getRequestMakeTripId());
        requestMakeTrip.setReceiverId(request.getReceiverId());
        requestMakeTrip.setSenderId(request.getSenderId());
        requestMakeTrip.setStatus(RequestMakeTrip.WAITING);
        requestMakeTrip.setPrice(request.getPrice());
        requestMakeTrip.setDriverPlannedTripId(driverPlannedTripId);
        requestMakeTrip.setPassengerPlannedTripId(passengerPlannedTripId);
        requestMakeTrip.setCreatedTime(DateTimeUtil.now());
        requestMakeTrip.setSenderRole(senderRole);
        if (dao.insert(requestMakeTrip)) {
            //push notification
            RequestMakeTripNoti noti = new RequestMakeTripNoti(requestMakeTrip);
            new BroadcastCenterUtil().pushNotification(NotificationParser.ObjectToJSon(ObjectNoti.REQUEST_MAKE_TRIP, noti), requestMakeTrip.getReceiverId(), BroadcastCenterUtil.CLOUD_BIKE_SENDER_ID);
            // return request
            RequestMakeTripResponse requestMakeTripResponse = new RequestMakeTripResponse();
            requestMakeTripResponse.setRequestId(requestMakeTrip.getId());
            return Parser.ObjectToJSon(true, "The request have been sent successfully", requestMakeTripResponse);
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
        if (request.getStatus() == RequestMakeTrip.ACCEPT) {
            return Parser.ObjectToJSon(false, "Request has replied");
        }
        if (request.getStatus() == RequestMakeTrip.DENY) {
            return Parser.ObjectToJSon(false, "Request has denied");
        }
        // check user status
        if (request.getReplierId().equals(requestMakeTrip.getReceiverId())) {
            PlannedTrip senderPlannedTrip;
            if (requestMakeTrip.getSenderRole() == User.DRIVER)
                senderPlannedTrip = database.getPlannedTripHashMap().get(requestMakeTrip.getDriverPlannedTripId());
            else
                senderPlannedTrip = database.getPlannedTripHashMap().get(requestMakeTrip.getPassengerPlannedTripId());
            if (senderPlannedTrip.isBusy()) {
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
            ReplyMakeTripNoti noti = new ReplyMakeTripNoti(requestMakeTrip);
            new BroadcastCenterUtil().pushNotification(NotificationParser.ObjectToJSon(ObjectNoti.REPLY_MAKE_TRIP, noti), requestMakeTrip.getSenderId(), BroadcastCenterUtil.CLOUD_BIKE_SENDER_ID);
            if (requestMakeTrip.getStatus() == RequestMakeTrip.ACCEPT) {
                // Change user's status
                PlannedTrip driverPlannedTrip = database.getPlannedTripHashMap().get(requestMakeTrip.getDriverPlannedTripId());
                PlannedTrip passengerPlannedTrip = database.getPlannedTripHashMap().get(requestMakeTrip.getPassengerPlannedTripId());
                driverPlannedTrip.setIsBusy(true);
                passengerPlannedTrip.setIsBusy(true);
                // Deny all request similar of other user in receiver box
                long plannedTripId = requestMakeTrip.getSenderRole() == User.DRIVER ? requestMakeTrip.getPassengerPlannedTripId() : requestMakeTrip.getDriverPlannedTripId();
                denyRequest(request.getReplierId(), plannedTripId);
                //TODO make a trip
            }
            return Parser.ObjectToJSon(true, "Reply successfully");
        }
        return Parser.ObjectToJSon(false, "Cannot reply");
    }

    public String removeRequestMakeTrip(RemoveRequestMakeTripRequest request) throws JsonProcessingException {
        if (request.getRequestMakeTripId() <= 0) {
            return Parser.ObjectToJSon(false, "'requestMakeTripId' is not found");
        }
        RequestMakeTrip requestMakeTrip = database.getRequestMakeTripHashMap().get(request.getRequestMakeTripId());
        if (null == requestMakeTrip) {
            return Parser.ObjectToJSon(false, "Request is not exist");
        }
        // change to deny status
        requestMakeTrip.setStatus(RequestMakeTrip.DENY);
        if (dao.update(requestMakeTrip)) {
            return Parser.ObjectToJSon(true, "Deny successfully");
        }
        return Parser.ObjectToJSon(false, "Cannot deny this request");
        //TODO notification
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

    public String getListRequestMakeTrip(GetListRequestMakeTripRequest request) throws JsonProcessingException {
        if (null == request.getUserId() || request.getUserId().equals("")) {
            return Parser.ObjectToJSon(false, "'userId' is not found");
        }
        HashMap<Long, List<Long>> requestIdsByPlannedTripId = database.getReceiverRequestsBox().get(request.getUserId());
        List<RequestMakeTripDetailResponse> responses = new ArrayList<>();
        if (requestIdsByPlannedTripId != null) {
            for (long ptId : requestIdsByPlannedTripId.keySet()) {
                List<Long> ids = requestIdsByPlannedTripId.get(ptId);
                if (ids != null) {
                    // insert sort
                    for (long id : ids) {
                        RequestMakeTrip requestMakeTrip = database.getRequestMakeTripHashMap().get(id);
                        if (requestMakeTrip != null && requestMakeTrip.getStatus() != RequestMakeTrip.DENY) {
                            int i;
                            for (i = 0; i < responses.size(); i++) {
                                if (requestMakeTrip.getCreatedTime() > responses.get(i).getCreatedTime()) {
                                    break;
                                }
                            }
                            RequestMakeTripDetailResponse response = new RequestMakeTripDetailResponse(requestMakeTrip);
                            responses.add(i, response);
                        }

                    }
                }
            }
        }

        return Parser.ObjectToJSon(true, "Get list requests successfully", new GetListRequestMakeTripDetailResponse(responses.toArray(new RequestMakeTripDetailResponse[responses.size()])));

    }

}
