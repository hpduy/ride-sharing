package com.bikiegang.ridesharing.pojo.request.angel;

import com.bikiegang.ridesharing.pojo.CertificateDetail;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 7/15/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateCertificateRequest extends CertificateDetail{
    private long certificateId;

    public long getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(long certificateId) {
        this.certificateId = certificateId;
    }
}
