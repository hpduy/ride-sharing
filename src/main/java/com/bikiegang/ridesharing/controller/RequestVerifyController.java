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
            return Parser.ObjectToJSon(false, "'userId' is not found");
        }
        if (null == request.getAngelId() || request.getAngelId().equals("")) {
            return Parser.ObjectToJSon(false, "'angelId' is not found");
        }
        if (request.getCertificates().length == 0) {
            return Parser.ObjectToJSon(false, "Certificates is not found");
        }
        User user = database.getUserHashMap().get(request.getUserId());
        if (null == user) {
            return Parser.ObjectToJSon(false, "User is not found by userId");
        }

        User angel = database.getUserHashMap().get(request.getAngelId());
        if (null == angel) {
            return Parser.ObjectToJSon(false, "Angel is not found by angelId");
        }
        // create request
        RequestVerify requestVerify = new RequestVerify();
        requestVerify.setId(IdGenerator.getRequestVerifyId());
        requestVerify.setAngelId(request.getAngelId());
        requestVerify.setUserId(request.getUserId());
        requestVerify.setNumberOfCertificate(request.getCertificates().length);
        requestVerify.setStatus(RequestVerify.WAITING);
        requestVerify.createSignature();

        if (dao.insert(requestVerify)) {
            //Create certificate
            List<CertificateDetail> failCertificates = new VerifiedCertificateController().createCertificate(request.getCertificates(), request.getUserId(), request.getAngelId(), VerifiedCertificate.WAITING);
            if (!failCertificates.isEmpty()) {
                return Parser.ObjectToJSon(false, "Some Certificates cannot insert", failCertificates);
            }
            //TODO push notification
            RequestVerifySortDetailResponse noti = new RequestVerifySortDetailResponse(requestVerify);
            new BroadcastCenterUtil().pushNotification(NotificationParser.ObjectToJSon(ObjectNoti.REQUEST_VERIFY, noti), angel.getId(), BroadcastCenterUtil.ANGEL_SPECIAL_APP_SENDER_ID);
            return Parser.ObjectToJSon(true, "Request sent successfully");

        }
        return Parser.ObjectToJSon(false, "Cannot send request");
    }

    public String sendVerificationReply(ReplyVerifyRequest request) throws JsonProcessingException {
        if (request.getRequestId() <= 0) {
            return Parser.ObjectToJSon(false, "'requestId' is invalid");
        }
        if (request.getStatus() != RequestVerify.ACCEPT && request.getStatus() != RequestVerify.DENY) {
            return Parser.ObjectToJSon(false, "'status' is invalid");
        }
        RequestVerify requestVerify = database.getRequestVerifyHashMap().get(request.getRequestId());
        if (null == requestVerify) {
            return Parser.ObjectToJSon(false, "Request does not exist");
        }
        if (requestVerify.getStatus() == RequestVerify.DENY) {
            return Parser.ObjectToJSon(false, "Request was canceled or denied");
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
            return Parser.ObjectToJSon(true, "Request sent successfully");
        }
        return Parser.ObjectToJSon(false, "Cannot send request");
    }

    public String getListRequestVerify(GetListRequestVerifyRequest request) throws JsonProcessingException {
        if (null == request.getAngelId() || request.getAngelId().equals("")) {
            return Parser.ObjectToJSon(false, "'angelId' is not found");
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
        return Parser.ObjectToJSon(true, "Get list requests successfully", responses);
    }

    public String getRequestDetail(GetRequestDetailRequest request) throws JsonProcessingException {
        if (request.getRequestId() <= 0) {
            return Parser.ObjectToJSon(false, "'requestId' is invalid");
        }
        RequestVerify requestVerify = database.getRequestVerifyHashMap().get(request.getRequestId());
        if (null == requestVerify) {
            return Parser.ObjectToJSon(false, "Request does not exist");
        }
        // update current location for angel
        new UserController().updateCurrentLocation(request);
        if (Database.databaseStatus != Database.TESTING)
            if (!new UserController().checkSameBox(requestVerify.getAngelId(), requestVerify.getUserId()))
                return Parser.ObjectToJSon(false, "You and requester is not in a same place at a same time");
        RequestVerifyDetailResponse response = new RequestVerifyDetailResponse(requestVerify);
        return Parser.ObjectToJSon(true, "Get request detail successfully", response);
    }

}
