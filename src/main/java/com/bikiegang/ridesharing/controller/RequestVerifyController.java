package com.bikiegang.ridesharing.controller;

import com.bikiegang.ridesharing.dao.RequestVerifyDao;
import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.database.IdGenerator;
import com.bikiegang.ridesharing.parsing.NotificationParser;
import com.bikiegang.ridesharing.parsing.Parser;
import com.bikiegang.ridesharing.pojo.*;
import com.bikiegang.ridesharing.pojo.request.angel.GetListRequestVerifyRequest;
import com.bikiegang.ridesharing.pojo.request.angel.GetRequestDetailRequest;
import com.bikiegang.ridesharing.pojo.request.angel.ReplyVerifyRequest;
import com.bikiegang.ridesharing.pojo.request.angel.RequestVerifyRequest;
import com.bikiegang.ridesharing.pojo.response.Notification.ObjectNoti;
import com.bikiegang.ridesharing.pojo.response.Notification.ReplyVerifyNoti;
import com.bikiegang.ridesharing.pojo.response.angel.RequestVerifyDetailResponse;
import com.bikiegang.ridesharing.pojo.response.angel.RequestVerifySortDetailResponse;
import com.bikiegang.ridesharing.utilities.BroadcastCenterUtil;
import com.bikiegang.ridesharing.utilities.DateTimeUtil;
import com.bikiegang.ridesharing.utilities.MessageMappingUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;
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
        if (request.getCertificates().length == 0) {
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
        // create request
        RequestVerify requestVerify = new RequestVerify();
        requestVerify.setId(IdGenerator.getRequestVerifyId());
        requestVerify.setCreatedTime(DateTimeUtil.now());
        requestVerify.setAngelId(request.getAngelId());
        requestVerify.setUserId(request.getUserId());
        requestVerify.setNumberOfCertificate(request.getCertificates().length);
        requestVerify.setStatus(RequestVerify.WAITING);
        requestVerify.createSignature();

        if (dao.insert(requestVerify)) {
            //Create certificate
            System.out.println(request.getUserId());
            List<CertificateDetail> failCertificates = new VerifiedCertificateController().createCertificate(request.getCertificates(), request.getUserId(), request.getAngelId(), VerifiedCertificate.WAITING);
            if (!failCertificates.isEmpty()) {
                return Parser.ObjectToJSon(false, MessageMappingUtil.Interactive_with_database_fail, failCertificates);
            }
            //TODO push notification
            RequestVerifySortDetailResponse noti = new RequestVerifySortDetailResponse(requestVerify);
            new BroadcastCenterUtil().pushNotification(NotificationParser.ObjectToJSon(ObjectNoti.REQUEST_VERIFY, noti), angel.getId(), BroadcastCenterUtil.ANGEL_SPECIAL_APP_SENDER_ID);
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
                if (null != angel) {
                    ReplyVerifyNoti noti = new ReplyVerifyNoti(requestVerify, angel, ObjectNoti.REPLY_VERIFY);
                    new BroadcastCenterUtil().pushNotification(Parser.ObjectToJSon(noti), requestVerify.getUserId(), BroadcastCenterUtil.CLOUD_BIKE_SENDER_ID);
                }
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
        return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully, responses);
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
