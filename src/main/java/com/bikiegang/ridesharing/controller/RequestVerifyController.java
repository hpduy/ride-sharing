package com.bikiegang.ridesharing.controller;

import com.bikiegang.ridesharing.dao.RequestVerifyDao;
import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.database.IdGenerator;
import com.bikiegang.ridesharing.parsing.Parser;
import com.bikiegang.ridesharing.pojo.*;
import com.bikiegang.ridesharing.pojo.request.angel.GetListRequestVerifyRequest;
import com.bikiegang.ridesharing.pojo.request.angel.GetRequestDetailRequest;
import com.bikiegang.ridesharing.pojo.request.angel.ReplyVerifyRequest;
import com.bikiegang.ridesharing.pojo.request.angel.RequestVerifyRequest;
import com.bikiegang.ridesharing.pojo.response.angel.GetListRequestVerifyResponse;
import com.bikiegang.ridesharing.pojo.response.angel.RequestVerifyDetailResponse;
import com.bikiegang.ridesharing.pojo.response.angel.RequestVerifySortDetailResponse;
import com.bikiegang.ridesharing.utilities.BroadcastCenterUtil;
import com.bikiegang.ridesharing.utilities.MessageMappingUtil;
import com.bikiegang.ridesharing.utilities.daytime.DateTimeUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by hpduy17 on 7/22/15.
 */
public class RequestVerifyController {
    private RequestVerifyDao dao = new RequestVerifyDao();
    private Database database = Database.getInstance();

    public String sendVerificationRequest(RequestVerifyRequest request) throws JsonProcessingException {
        if (null == request.getUserId() || request.getUserId().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'userId'");
        }
        if (null == request.getAngelId() || request.getAngelId().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'angelId'");
        }
        HashSet<Long> cerIds = database.getUserIdRFCertificates().get(request.getUserId());
        if (cerIds == null || cerIds.isEmpty()) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Object_is_not_found, "Certificates");
        }
        User user = database.getUserHashMap().get(request.getUserId());
        if (null == user) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Object_is_not_found, "User");
        }

        User angel = database.getUserHashMap().get(request.getAngelId());
        if (null == angel) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Object_is_not_found, "Angel");
        }
        if(database.getUserHashMap().containsKey(request.getUserId()+"#"+request.getAngelId())){
            return Parser.ObjectToJSon(false, MessageMappingUtil.Request_has_been_sent, "verification");
        }
        // create request
        RequestVerify requestVerify = new RequestVerify();
        requestVerify.setId(IdGenerator.getRequestVerifyId());
        requestVerify.setCreatedTime(DateTimeUtil.now());
        requestVerify.setAngelId(request.getAngelId());
        requestVerify.setUserId(request.getUserId());
        requestVerify.setNumberOfCertificate(cerIds.size());
        requestVerify.setStatus(RequestVerify.WAITING);
//        requestVerify.createSignature();

        if (dao.insert(requestVerify)) {
            new BroadcastCenterUtil().pushNotification(Parser.ObjectToNotification(MessageMappingUtil.Notification_RequestVerify, user), angel.getId(), Broadcast.ANGEL_APP);
            return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully);
        }
        return Parser.ObjectToJSon(false, MessageMappingUtil.Interactive_with_database_fail);
    }

    public String sendVerificationReply(ReplyVerifyRequest request) throws JsonProcessingException {
        if (request.getRequestId() <= 0) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_invalid, "'requestId'");
        }
        if (request.getStatus() != RequestVerify.ACCEPT && request.getStatus() != RequestVerify.DENY) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_invalid, "'status'");
        }
        RequestVerify requestVerify = database.getRequestVerifyHashMap().get(request.getRequestId());
        if (null == requestVerify) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Object_is_not_found, "Request");
        }
        if (requestVerify.getStatus() == RequestVerify.DENY) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Request_has_denied);
        }
        requestVerify.setStatus(request.getStatus());
        if (dao.update(requestVerify)) {
            if (requestVerify.getStatus() == RequestVerify.ACCEPT) {
                User angel = database.getUserHashMap().get(requestVerify.getAngelId());
//                if (null != angel) {
//                    ReplyVerifyNoti noti = new ReplyVerifyNoti(requestVerify, angel, ObjectNoti.REPLY_VERIFY);
//                    new BroadcastCenterUtil().pushNotification(Parser.ObjectToJSon(noti), requestVerify.getUserId(), BroadcastCenterUtil.CLOUD_BIKE_SENDER_ID);
//                }
            }
            return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully);
        }
        return Parser.ObjectToJSon(false, MessageMappingUtil.Interactive_with_database_fail);
    }

    public String getListRequestVerify(GetListRequestVerifyRequest request) throws JsonProcessingException {
        if (null == request.getAngelId() || request.getAngelId().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'angelId'");
        }
        List<RequestVerifySortDetailResponse> responses = new ArrayList<>();
        List<Long> requestIds = database.getAngelRequestsBox().get(request.getAngelId());
        if (requestIds != null) {
            for (long id : requestIds) {
                RequestVerify requestVerify = database.getRequestVerifyHashMap().get(id);
                if (requestVerify != null && requestVerify.getStatus() != RequestVerify.DENY) {
                    int i;
                    for (i = 0; i < responses.size(); i++) {
                        if (requestVerify.getCreatedTime() > responses.get(i).getCreatedTime()) {
                            break;
                        }
                    }
                    RequestVerifySortDetailResponse response = new RequestVerifySortDetailResponse(requestVerify);
                    responses.add(i, response);
                }
            }
        }
        return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully, new GetListRequestVerifyResponse(responses));
    }

    public String getRequestDetail(GetRequestDetailRequest request) throws JsonProcessingException {
        if (request.getRequestId() <= 0) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'requestId'");
        }
        RequestVerify requestVerify = database.getRequestVerifyHashMap().get(request.getRequestId());
        if (null == requestVerify) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Object_is_not_found, "Request");
        }
        // update current location for angel
        new UserController().updateCurrentLocation(request);
        if (Database.databaseStatus != Database.TESTING)
            if (!new UserController().checkSameBox(requestVerify.getAngelId(), requestVerify.getUserId()))
                return Parser.ObjectToJSon(false, MessageMappingUtil.User_and_angel_is_not_same_place_and_time);
        RequestVerifyDetailResponse response = new RequestVerifyDetailResponse(requestVerify);
        return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully, response);
    }

}
