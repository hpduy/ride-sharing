package com.bikiegang.ridesharing.controller;

import com.bikiegang.ridesharing.dao.PlannedTripDao;
import com.bikiegang.ridesharing.dao.RequestMakeTripDao;
import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.database.IdGenerator;
import com.bikiegang.ridesharing.parsing.Parser;
import com.bikiegang.ridesharing.pojo.PlannedTrip;
import com.bikiegang.ridesharing.pojo.RequestMakeTrip;
import com.bikiegang.ridesharing.pojo.User;
import com.bikiegang.ridesharing.pojo.request.*;
import com.bikiegang.ridesharing.pojo.response.*;
import com.bikiegang.ridesharing.utilities.BroadcastCenterUtil;
import com.bikiegang.ridesharing.utilities.MessageMappingUtil;
import com.bikiegang.ridesharing.utilities.daytime.DateTimeUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.util.*;

/**
 * Created by hpduy17 on 7/1/15.
 */
public class RequestMakeTripController {
    private RequestMakeTripDao dao = new RequestMakeTripDao();
    private Database database = Database.getInstance();

    public String sendRequestMakeTrip(RequestMakeTripRequest request) throws Exception {
        if (null == request.getSenderId() || request.getSenderId().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'senderId'");
        }
        if (null == request.getReceiverId() || request.getReceiverId().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'receiverId'");
        }
        if (request.getReceiverPlannedTripId() <= 0) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_invalid, "receiverPlannedTripId");
        }
        User sender = database.getUserHashMap().get(request.getSenderId());
        if (null == sender) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Object_is_not_found, "Sender");
        }
        User receiver = database.getUserHashMap().get(request.getReceiverId());
        if (null == receiver) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Object_is_not_found, "Receiver");
        }
        if (database.getSenderRequestsBox().containsKey(request.getSenderId()) && database.getSenderRequestsBox().get(request.getSenderId()).containsKey(request.getReceiverPlannedTripId())) {
            long requestId = database.getSenderRequestsBox().get(request.getSenderId()).get(request.getReceiverPlannedTripId());
            if (database.getRequestMakeTripHashMap().containsKey(requestId)) {
                RequestMakeTrip requestMakeTrip = database.getRequestMakeTripHashMap().get(requestId);
                if (database.getRequestMakeTripHashMap().get(requestId).getStatus() != RequestMakeTrip.DENY)
                    return Parser.ObjectToJSon(false, MessageMappingUtil.Request_has_been_sent, "receiverPlannedTrip");
                else {
                    requestMakeTrip.setStatus(RequestMakeTrip.WAITING);
                    if (dao.update(requestMakeTrip)) {
                        RequestMakeTripResponse requestMakeTripResponse = new RequestMakeTripResponse();
                        requestMakeTripResponse.setRequestId(requestMakeTrip.getId());

                        if (requestMakeTrip.getSenderRole() == User.DRIVER) {
                            new BroadcastCenterUtil().pushNotification(Parser.ObjectToNotification(MessageMappingUtil.Notification_RequestMakeTrip, sender), requestMakeTrip.getReceiverId());
                            return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully, requestMakeTripResponse);
                        } else {
                            long plannedTripId = requestMakeTrip.getPassengerPlannedTripId();
                            if (new PlannedTripController().updateRouteOfPlannedTrip(plannedTripId, request.getGoogleRoutingResult(), null, null)) {
                                new BroadcastCenterUtil().pushNotification(Parser.ObjectToNotification(MessageMappingUtil.Notification_RequestMakeTrip, sender), requestMakeTrip.getReceiverId());
                                return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully, requestMakeTripResponse);
                            }
                        }
                    }
                    return Parser.ObjectToJSon(false, MessageMappingUtil.Interactive_with_database_fail);

                }
            }

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
                return Parser.ObjectToJSon(false, MessageMappingUtil.Object_is_not_found, "Receiver's PlannedTrip");
            }
            PlannedTripInfoRequest ptR = new PlannedTripInfoRequest();
            ptR.setCreatorId(request.getSenderId());
            ptR.setGoTime(passengerPlannedTrip.getDepartureTime());
            ptR.setRole(senderRole);

            try {
                ptR.setGoogleRoutingResult(database.getRouteHashMap().get(passengerPlannedTrip.getRouteId()).getRawRoutingResult().toString());
            } catch (Exception ignored) {
            }
            ptR.setIsParing(false);// no paring
            ptR.setPrice(request.getPrice());
            CreatePlannedTripRequest createRequest = new CreatePlannedTripRequest(ptR);
            String response = new PlannedTripController().createSingleFuturePlannedTrip(createRequest);
            Parser parser = Parser.JSonToParser(response, CreateSingleFuturePlannedTripResponse.class);
            if (parser.isSuccess()) {
                CreateSingleFuturePlannedTripResponse createPlannedTripResponse = (CreateSingleFuturePlannedTripResponse) parser.getResult();
                driverPlannedTripId = createPlannedTripResponse.getYourPlannedTrip().getTripDetail().getId();
            } else {
                return Parser.ObjectToJSon(false, parser.getMessageCode(), parser.getMessage());
            }

        }
        //case 3: sender is passenger -> create route from google routing result
        else {
            driverPlannedTripId = request.getReceiverPlannedTripId();
            PlannedTrip driverPlannedTrip = database.getPlannedTripHashMap().get(driverPlannedTripId);
            PlannedTripInfoRequest ptR = new PlannedTripInfoRequest();
            ptR.setCreatorId(request.getSenderId());
            ptR.setGoTime(driverPlannedTrip.getDepartureTime());
            ptR.setRole(senderRole);
            ptR.setGoogleRoutingResult(request.getGoogleRoutingResult());
            ptR.setIsParing(false);// no paring
            ptR.setPrice(-1);// default price
            CreatePlannedTripRequest createRequest = new CreatePlannedTripRequest(ptR);
            String response = new PlannedTripController().createSingleFuturePlannedTrip(createRequest);
            Parser parser = Parser.JSonToParser(response, CreateSingleFuturePlannedTripResponse.class);
            if (parser.isSuccess()) {
                CreateSingleFuturePlannedTripResponse createPlannedTripResponse = (CreateSingleFuturePlannedTripResponse) parser.getResult();
                passengerPlannedTripId = createPlannedTripResponse.getYourPlannedTrip().getTripDetail().getId();
            } else {
                return Parser.ObjectToJSon(false, parser.getMessageCode(), parser.getMessage());
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
            new BroadcastCenterUtil().pushNotification(Parser.ObjectToNotification(MessageMappingUtil.Notification_RequestMakeTrip, sender), requestMakeTrip.getReceiverId());
            // return request
            RequestMakeTripResponse requestMakeTripResponse = new RequestMakeTripResponse();
            requestMakeTripResponse.setRequestId(requestMakeTrip.getId());
            return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully, requestMakeTripResponse);
        }
        return Parser.ObjectToJSon(false, MessageMappingUtil.Interactive_with_database_fail);
    }

    public String replyRequestMakeTrip(ReplyMakeTripRequest request) throws JsonProcessingException {
        if (request.getRequestMakeTripId() <= 0) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'requestMakeTripId'");
        }
        if (request.getStatus() != RequestMakeTrip.ACCEPT && request.getStatus() != RequestMakeTrip.DENY) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_invalid, "'status'");
        }
        RequestMakeTrip requestMakeTrip = database.getRequestMakeTripHashMap().get(request.getRequestMakeTripId());
        if (null == requestMakeTrip) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Object_is_not_found, "Request");
        }
        if (requestMakeTrip.getStatus() == RequestMakeTrip.ACCEPT) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Request_has_accepted);
        }
        if (requestMakeTrip.getStatus() == RequestMakeTrip.DENY) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Request_has_denied);
        }


        // check user status
        if (request.getReplierId().equals(requestMakeTrip.getReceiverId())) {
            PlannedTrip senderPlannedTrip;
            PlannedTrip receiverPlannedTrip;
            if (requestMakeTrip.getSenderRole() == User.DRIVER) {
                senderPlannedTrip = database.getPlannedTripHashMap().get(requestMakeTrip.getDriverPlannedTripId());
                receiverPlannedTrip = database.getPlannedTripHashMap().get(requestMakeTrip.getPassengerPlannedTripId());
            } else {
                senderPlannedTrip = database.getPlannedTripHashMap().get(requestMakeTrip.getPassengerPlannedTripId());
                receiverPlannedTrip = database.getPlannedTripHashMap().get(requestMakeTrip.getDriverPlannedTripId());
            }
            if (senderPlannedTrip.getRequestId() > 0 && database.getRequestMakeTripHashMap().containsKey(senderPlannedTrip.getRequestId())
                    || receiverPlannedTrip.getRequestId() > 0 && database.getRequestMakeTripHashMap().containsKey(receiverPlannedTrip.getRequestId())) {
                requestMakeTrip.setStatus(RequestMakeTrip.DENY);
                dao.update(requestMakeTrip);
                return Parser.ObjectToJSon(false, MessageMappingUtil.User_is_busy);
            }
        } else {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Object_is_not_belong_to_you);
        }
        //---done checker---
        // set status
        requestMakeTrip.setStatus(request.getStatus());
        if (dao.update(requestMakeTrip)) {
            //TODO notification
            // Check status of request make trip
            int action = MessageMappingUtil.Notification_ReplyMakeTrip_Deny;
            if (requestMakeTrip.getStatus() == RequestMakeTrip.ACCEPT) {
                // Change user's status
                PlannedTrip driverPlannedTrip = database.getPlannedTripHashMap().get(requestMakeTrip.getDriverPlannedTripId());
                PlannedTrip passengerPlannedTrip = database.getPlannedTripHashMap().get(requestMakeTrip.getPassengerPlannedTripId());
                driverPlannedTrip.setRequestId(requestMakeTrip.getId());
                passengerPlannedTrip.setRequestId(requestMakeTrip.getId());
                new PlannedTripDao().update(driverPlannedTrip);
                new PlannedTripDao().update(passengerPlannedTrip);
                // Deny all request similar of other user in receiver box
                long plannedTripId = requestMakeTrip.getSenderRole() == User.DRIVER ? requestMakeTrip.getPassengerPlannedTripId() : requestMakeTrip.getDriverPlannedTripId();
                denyRequest(request.getReplierId(), plannedTripId);
                action = MessageMappingUtil.Notification_ReplyMakeTrip_Accept;

            }
            User senderNoti = database.getUserHashMap().get(requestMakeTrip.getReceiverId());
            new BroadcastCenterUtil().pushNotification(Parser.ObjectToNotification(action, senderNoti), requestMakeTrip.getSenderId());
            return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully);
        }
        return Parser.ObjectToJSon(false, MessageMappingUtil.Interactive_with_database_fail);
    }

    public String removeRequestMakeTrip(RemoveRequestMakeTripRequest request) throws JsonProcessingException {
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
        // change to deny status
        requestMakeTrip.setStatus(RequestMakeTrip.DENY);
        if (dao.update(requestMakeTrip)) {
            PlannedTrip driverPlannedTrip = database.getPlannedTripHashMap().get(requestMakeTrip.getDriverPlannedTripId());
            PlannedTrip passengerPlannedTrip = database.getPlannedTripHashMap().get(requestMakeTrip.getPassengerPlannedTripId());
            if (driverPlannedTrip.getRequestId() == requestMakeTrip.getId()) {
                driverPlannedTrip.setRequestId(0);
                new PlannedTripDao().update(driverPlannedTrip);
            }
            if (passengerPlannedTrip.getRequestId() == requestMakeTrip.getId()) {
                passengerPlannedTrip.setRequestId(0);
                new PlannedTripDao().update(passengerPlannedTrip);
            }
            new BroadcastCenterUtil().pushNotification(Parser.ObjectToNotification(MessageMappingUtil.Notification_ReplyMakeTrip_Deny, database.getUserHashMap().get(request.getUserId())), requestMakeTrip.getReceiverId());
            return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully);
        }
        return Parser.ObjectToJSon(false, MessageMappingUtil.Interactive_with_database_fail);
    }

    private void denyRequest(String userId, long routeId) throws JsonProcessingException {
        List<Long> requestList = database.getReceiverRequestsBox().get(userId).get(routeId);
        for (long requestId : requestList) {
            RequestMakeTrip requestMakeTrip = database.getRequestMakeTripHashMap().get(requestId);
            if (requestMakeTrip != null && requestMakeTrip.getStatus() == RequestMakeTrip.WAITING) {
                requestMakeTrip.setStatus(RequestMakeTrip.DENY);
                if (dao.update(requestMakeTrip)) {
                    new BroadcastCenterUtil().pushNotification(Parser.ObjectToNotification(MessageMappingUtil.Notification_ReplyMakeTrip_Deny, database.getUserHashMap().get(requestMakeTrip.getSenderId())), requestMakeTrip.getReceiverId());
                }
            }
        }

    }

    public String getListRequestMakeTrip(GetListRequestMakeTripRequest request) throws IOException {
        if (null == request.getUserId() || request.getUserId().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'userId'");
        }
        return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully, new GetListRequestMakeTripDetailResponse(getListRequestMakeTripToArray(request)));

    }

    public RequestMakeTripDetailResponse[] getListRequestMakeTripToArray(GetListRequestMakeTripRequest request) throws IOException {
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
                            PlannedTrip pt = database.getPlannedTripHashMap().get(requestMakeTrip.getDriverPlannedTripId());
                            if (pt.getDepartureTime() > DateTimeUtil.now() - DateTimeUtil.HOURS) {
                                RequestMakeTripDetailResponse response = new RequestMakeTripDetailResponse(requestMakeTrip);
                                responses.add(i, response);
                            }
                        }

                    }
                }
            }
        }
        return responses.toArray(new RequestMakeTripDetailResponse[responses.size()]);
    }

    public RequestMakeTripDetailResponse[] getListRequestMakeTripToArray(String userId, Long plannedTripId) throws IOException {
        HashMap<Long, List<Long>> requestIdsByPlannedTripId = database.getReceiverRequestsBox().get(userId);
        List<RequestMakeTripDetailResponse> responses = new ArrayList<>();
        if (requestIdsByPlannedTripId != null) {
            List<Long> ids = requestIdsByPlannedTripId.get(plannedTripId);
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
                        PlannedTrip pt = database.getPlannedTripHashMap().get(requestMakeTrip.getDriverPlannedTripId());
                        if (pt.getDepartureTime() > DateTimeUtil.now() - DateTimeUtil.HOURS) {
                            RequestMakeTripDetailResponse response = new RequestMakeTripDetailResponse(requestMakeTrip);
                            responses.add(i, response);
                        }
                    }
                }
            }
        }
        return responses.toArray(new RequestMakeTripDetailResponse[responses.size()]);
    }

    public String getListRequestMakeTripOfMe(GetListRequestMakeTripRequest request) throws IOException {
        if (null == request.getUserId() || request.getUserId().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'userId'");
        }
        HashMap<Long, Long> requestIdsByPlannedTripId = database.getSenderRequestsBox().get(request.getUserId());
        List<FeedResponse> responses = new ArrayList<>();
        List<PlannedTrip> pt = new ArrayList<>();
        if (requestIdsByPlannedTripId != null) {
            for (long tId : requestIdsByPlannedTripId.keySet()) {
                RequestMakeTrip requestMakeTrip = database.getRequestMakeTripHashMap().get(requestIdsByPlannedTripId.get(tId));
                if (requestMakeTrip != null && requestMakeTrip.getStatus() == RequestMakeTrip.WAITING) {
                    PlannedTrip plannedTrip = database.getPlannedTripHashMap().get(tId);
                    if (plannedTrip != null)
                        pt.add(plannedTrip);
                }
            }
        }
        Collections.sort(pt, new Comparator<PlannedTrip>() {
            @Override
            public int compare(PlannedTrip o1, PlannedTrip o2) {
                long dis = o2.getDepartureTime() - o1.getDepartureTime();
                if (dis > 0)
                    return 1;
                return -1;
            }
        });
        for (PlannedTrip t : pt) {
            responses.add(new FeedController().convertPlannedTripToFeed(t, request.getUserId()));
        }
        GetFeedsResponse response = new GetFeedsResponse();
        response.setFeeds(responses.toArray(new FeedResponse[responses.size()]));
        return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully, response);

    }

}
