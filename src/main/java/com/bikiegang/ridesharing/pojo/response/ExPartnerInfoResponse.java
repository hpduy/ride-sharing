package com.bikiegang.ridesharing.pojo.response;

/**
 * Created by hpduy17 on 8/18/15.
 */
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
