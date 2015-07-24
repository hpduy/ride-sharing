package com.bikiegang.ridesharing.pojo.request;

import com.bikiegang.ridesharing.pojo.CertificateDetail;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 7/15/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateCertificateRequest {
    private String signature;
    private long requestId;
    private String note;
    CertificateDetail[] certificates;
    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public CertificateDetail[] getCertificates() {
        return certificates;
    }

    public void setCertificates(CertificateDetail[] certificates) {
        this.certificates = certificates;
    }

    public String getNote() {
        return note;
    }
}
