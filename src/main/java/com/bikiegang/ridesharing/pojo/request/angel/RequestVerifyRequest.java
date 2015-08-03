package com.bikiegang.ridesharing.pojo.request.angel;

import com.bikiegang.ridesharing.pojo.CertificateDetail;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 7/15/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestVerifyRequest {
    private String userId = "";
    private String angelId = "";
    CertificateDetail[] certificates;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAngelId() {
        return angelId;
    }

    public void setAngelId(String angelId) {
        this.angelId = angelId;
    }

    public CertificateDetail[] getCertificates() {
        return certificates;
    }

    public void setCertificates(CertificateDetail[] certificates) {
        this.certificates = certificates;
    }
}
