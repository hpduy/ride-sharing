package com.bikiegang.ridesharing.controller;

import com.bikiegang.ridesharing.dao.VerifiedCertificateDao;
import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.database.IdGenerator;
import com.bikiegang.ridesharing.parsing.Parser;
import com.bikiegang.ridesharing.pojo.RequestVerify;
import com.bikiegang.ridesharing.pojo.User;
import com.bikiegang.ridesharing.pojo.request.ReplyVerifyRequest;
import com.bikiegang.ridesharing.pojo.request.RequestVerifyRequest;
import com.bikiegang.ridesharing.pojo.response.Notification.RequestVerifyNoti;
import com.bikiegang.ridesharing.utilities.BroadcastCenterUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Created by hpduy17 on 7/22/15.
 */
public class VerifiedCertificateController {
    private VerifiedCertificateDao dao = new VerifiedCertificateDao();
    private Database database = Database.getInstance();

    public String sendVerificationRequest(RequestVerifyRequest request) throws JsonProcessingException {
        if (null == request.getUserId() || request.getUserId().equals("")) {
            return Parser.ObjectToJSon(false, "'userId' is not found");
        }
        if (null == request.getAngelId() || request.getAngelId().equals("")) {
            return Parser.ObjectToJSon(false, "'angelId' is not found");
        }
        if (request.getNumberOfCertificate() <= 0) {
            return Parser.ObjectToJSon(false, "'numberOfCertificate' should more than 0 (> 0)");
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
        requestVerify.setId(IdGenerator.getRequestReplyId());
        requestVerify.setAngelId(request.getAngelId());
        requestVerify.setUserId(request.getUserId());
        requestVerify.setNumberOfCertificate(request.getNumberOfCertificate());
        requestVerify.setStatus(RequestVerify.WAITING);
        requestVerify.createSignature();

        if(dao.insert(requestVerify)){
            //TODO push notification
            RequestVerifyNoti noti = new RequestVerifyNoti(requestVerify,user);
            new BroadcastCenterUtil().pushNotification(noti.toString(),angel.getId());
            return Parser.ObjectToJSon(true, "Request sent successfully");
        }
        return Parser.ObjectToJSon(false, "Cannot send request");
    }

    public String sendVerificationReply(ReplyVerifyRequest request) throws JsonProcessingException {
        if (request.getRequestId() <= 0) {
            return Parser.ObjectToJSon(false, "'requestId' is invalid");
        }
        if (request.getStatus() !=  RequestVerify.ACCEPT && request.getStatus() != RequestVerify.DENY) {
            return Parser.ObjectToJSon(false, "'status' is invalid");
        }
        RequestVerify requestVerify = database.getRequestVerifyHashMap().get(request.getRequestId());
        if (null == requestVerify) {
            return Parser.ObjectToJSon(false, "Request is not exist");
        }
        request.setStatus(request.getStatus());
        if(dao.update(requestVerify)){
            if(request.getStatus() == RequestVerify.ACCEPT) {

                new BroadcastCenterUtil().pushNotification(noti.toString(), user.getId());
            }
            return Parser.ObjectToJSon(true, "Request sent successfully");
        }
        return Parser.ObjectToJSon(false, "Cannot send request");
    }

}
