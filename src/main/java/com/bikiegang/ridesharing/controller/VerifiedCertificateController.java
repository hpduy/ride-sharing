package com.bikiegang.ridesharing.controller;

import com.bikiegang.ridesharing.dao.UserDao;
import com.bikiegang.ridesharing.dao.VerifiedCertificateDao;
import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.database.IdGenerator;
import com.bikiegang.ridesharing.parsing.Parser;
import com.bikiegang.ridesharing.pojo.Broadcast;
import com.bikiegang.ridesharing.pojo.User;
import com.bikiegang.ridesharing.pojo.VerifiedCertificate;
import com.bikiegang.ridesharing.pojo.request.GetInformationUsingUserIdRequest;
import com.bikiegang.ridesharing.pojo.request.angel.CreateCertificateRequest;
import com.bikiegang.ridesharing.pojo.request.angel.VerifyCertificateRequest;
import com.bikiegang.ridesharing.utilities.BroadcastCenterUtil;
import com.bikiegang.ridesharing.utilities.MessageMappingUtil;
import com.bikiegang.ridesharing.utilities.Path;
import com.bikiegang.ridesharing.utilities.SendMailUtil;
import com.bikiegang.ridesharing.utilities.daytime.DateTimeUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.TimeZone;

/**
 * Created by hpduy17 on 7/22/15.
 */
public class VerifiedCertificateController {
    private VerifiedCertificateDao dao = new VerifiedCertificateDao();
    private Database database = Database.getInstance();

    public String createCertificate(CreateCertificateRequest request) throws JsonProcessingException {
        if (request.getUserId() == null || request.getUserId().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'userId'");
        }
        if (request.getCertificate() == null) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'certificate'");
        }
        User user = database.getUserHashMap().get(request.getUserId());
        if (user == null) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Object_is_not_found, "'user'");
        }
        VerifiedCertificate certificate = new VerifiedCertificate(request.getCertificate(), IdGenerator.getVerifiedCertificatedId(),
                "", DateTimeUtil.now(), request.getUserId(), "", VerifiedCertificate.WAITING);
        if (dao.insert(certificate)) {
            //TODO MAIL TO CUSTOMER SERVICE
            try {
                String mailBody = new UserController().parseUser2HTML(user) + parseCertificate2HTML(certificate);
                new SendMailUtil("verify@pinride.me", user.getFirstName()+" "+ user.getLastName()+" verification request", mailBody);
            } catch (Exception ignored) {
                ignored.printStackTrace();
            }
            return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully);
        }
        return Parser.ObjectToJSon(false, MessageMappingUtil.Interactive_with_database_fail);
    }

    public String verifyCertificate(VerifyCertificateRequest request) throws JsonProcessingException {
        if (null == request.getUserId()) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'userId'");
        }
        if (request.getCertificateId() <= 0) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_invalid, "'certificateId'");
        }
        VerifiedCertificate certificate = database.getVerifiedCertificateHashMap().get(request.getCertificateId());
        if (null == certificate) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Object_is_not_found, "certificate");
        }
        if (certificate.getStatus() == VerifiedCertificate.AVAILABLE) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Certificate_verified);
        }
        if (certificate.getStatus() == VerifiedCertificate.IS_EXPIRED) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Certificate_expired);
        }
        certificate.setStatus(VerifiedCertificate.AVAILABLE);
        certificate.setEndorserId("VERIFICATION TEAM");
        certificate.setCreatedTime(DateTimeUtil.now());
        if (dao.update(certificate)) {
            User user = database.getUserHashMap().get(certificate.getOwnerId());
            if (user != null) {
                int count = 0;
                if (database.getUserIdRFCertificates().get(certificate.getOwnerId()).size() >= 2) {
                    for (long cid : database.getUserIdRFCertificates().get(certificate.getOwnerId())) {
                        VerifiedCertificate vc = database.getVerifiedCertificateHashMap().get(cid);
                        if (vc.getStatus() == VerifiedCertificate.AVAILABLE) {
                            count++;
                        }
                    }

                    if (count >= 2) {
                        user.setStatus(User.VERIFIED);
                        new UserDao().update(user);
                    }
                }
            }
            new BroadcastCenterUtil().pushNotification(Parser.ObjectToNotification(MessageMappingUtil.Notification_VerifyCertificate_Success, database.getUserHashMap().get(request.getUserId())), certificate.getOwnerId(), Broadcast.ANGEL_APP);
            return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully);
        }
        return Parser.ObjectToJSon(false, MessageMappingUtil.Interactive_with_database_fail);
    }

    public String checkEnoughCertificates(GetInformationUsingUserIdRequest request) throws JsonProcessingException {
        if (request.getUserId() == null || request.getUserId().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_not_found, "'userId'");
        }
        HashSet<Long> cerIds = database.getUserIdRFCertificates().get(request.getUserId());
        if (cerIds == null || cerIds.size() < 2) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Fail);
        }
        return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully);
    }

    public String parseCertificate2HTML(VerifiedCertificate c) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
        VerifiedCertificate certificateDetail = new VerifiedCertificate(c);
        //get url from path
        certificateDetail.setImageLinks(Path.getUrlsFromPaths(certificateDetail.getImageLinks()));
        String html = "<h3>Certificate</h3><table>";
        html += "<tr><td>Id: " + certificateDetail.getId() + "<td><tr>";
        html += "<tr><td>Note: " + certificateDetail.getNote() + "<td><tr>";
        html += "<tr><td>Upload Time: " + sdf.format(certificateDetail.getCreatedTime() * 1000) + "<td><tr>";
        for (int i = 0; i < certificateDetail.getImageLinks().length; i++) {
            html += "<tr><td>IMG" + (i + 1) + ": <img src=\"" + Path.getUrlFromPath(certificateDetail.getImageLinks()[i]) + "\"/><td><tr>";
        }
        html += "</table>";
        html += "<hr>";
        html += "<br/><p>Active link[WARNING] <a href ='http://www.pinbike.me:8080/RideSharing/VerifyUserServlet?userId=" + c.getOwnerId() +
                "&certificateId=" + c.getId() +
                "&token=zjshnDT1jU5rDQBtR98gDcmfaaaa'> Click here</a></p>";
        return html;
    }

}
