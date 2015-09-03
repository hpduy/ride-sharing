package com.bikiegang.ridesharing.pojo.request.angel;

import com.bikiegang.ridesharing.pojo.CertificateDetail;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 7/15/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateCertificateRequest {
    private String userId;
    CertificateDetail certificate;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public CertificateDetail getCertificate() {
        return certificate;
    }

    public void setCertificate(CertificateDetail certificate) {
        this.certificate = certificate;
    }
}
