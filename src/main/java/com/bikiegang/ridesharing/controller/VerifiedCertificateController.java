package com.bikiegang.ridesharing.controller;

import com.bikiegang.ridesharing.dao.VerifiedCertificateDao;
import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.database.IdGenerator;
import com.bikiegang.ridesharing.parsing.Parser;
import com.bikiegang.ridesharing.pojo.VerifiedCertificate;
import com.bikiegang.ridesharing.pojo.request.GetInformationUsingUserIdRequest;
import com.bikiegang.ridesharing.pojo.request.angel.CreateCertificateRequest;
import com.bikiegang.ridesharing.pojo.request.angel.VerifyCertificateRequest;
import com.bikiegang.ridesharing.utilities.MessageMappingUtil;
import com.bikiegang.ridesharing.utilities.daytime.DateTimeUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.HashSet;

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
        VerifiedCertificate certificate = new VerifiedCertificate(request.getCertificate(), IdGenerator.getVerifiedCertificatedId(),
                "", DateTimeUtil.now(), request.getUserId(), "", VerifiedCertificate.WAITING);
        if (!dao.insert(certificate)) {
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
        certificate.setEndorserId(request.getUserId());
        certificate.setCreatedTime(DateTimeUtil.now());
        if (dao.update(certificate))
            return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully);
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


}
