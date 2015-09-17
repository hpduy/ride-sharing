package com.bikiegang.ridesharing.pojo.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 8/18/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExPartnerInfoResponse {
    private String[] partnerPictureLinks;
    private int numberOfExPartner;

    public String[] getPartnerPictureLinks() {
        return partnerPictureLinks;
    }

    public void setPartnerPictureLinks(String[] partnerPictureLinks) {
        this.partnerPictureLinks = partnerPictureLinks;
    }

    public int getNumberOfExPartner() {
        return numberOfExPartner;
    }

    public void setNumberOfExPartner(int numberOfExPartner) {
        this.numberOfExPartner = numberOfExPartner;
    }
}
