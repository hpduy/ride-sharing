package com.bikiegang.ridesharing.controller;

import com.bikiegang.ridesharing.dao.VerifiedCertificateDao;
import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.database.IdGenerator;
import com.bikiegang.ridesharing.parsing.Parser;
import com.bikiegang.ridesharing.pojo.CertificateDetail;
import com.bikiegang.ridesharing.pojo.RequestVerify;
import com.bikiegang.ridesharing.pojo.VerifiedCertificate;
import com.bikiegang.ridesharing.pojo.request.CreateCertificateRequest;
import com.bikiegang.ridesharing.utilities.DateTimeUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hpduy17 on 7/22/15.
 */
public class VerifiedCertificateController {
    private VerifiedCertificateDao dao = new VerifiedCertificateDao();
    private Database database = Database.getInstance();

    public String createCertificate(CreateCertificateRequest request) throws JsonProcessingException {
        if (request.getRequestId() <= 0) {
            return Parser.ObjectToJSon(false, "'requestId' is invalid");
        }
        RequestVerify requestVerify = database.getRequestVerifyHashMap().get(request.getRequestId());
        if (null == requestVerify) {
            return Parser.ObjectToJSon(false, "Request is not exist");
        }
        if (request.getCertificates() == null) {
            return Parser.ObjectToJSon(false, "Certificates is not exist");
        }
        if (request.getCertificates().length == 0) {
            return Parser.ObjectToJSon(false, "Certificates is empty");
        }
        if(!request.getSignature().equals(requestVerify.getSignature())){
            return Parser.ObjectToJSon(false, "Certificates is empty");
        }
        List<CertificateDetail> failCertificates = new ArrayList<>();
        for(CertificateDetail certificateDetail : request.getCertificates()){
            VerifiedCertificate certificate = new VerifiedCertificate(certificateDetail,IdGenerator.getVerifiedCertificatedId(),
                    request.getNote(), DateTimeUtil.now(),requestVerify.getUserId(),requestVerify.getAngelId(),VerifiedCertificate.AVAILABLE);
            if(!dao.insert(certificate)){
                failCertificates.add(certificateDetail);
            }
        }
        if(!failCertificates.isEmpty()){
            return Parser.ObjectToJSon(false, "Some Certificates cannot insert", failCertificates);
        }
        return Parser.ObjectToJSon(true, "Certificates is created successfully");

    }


}
