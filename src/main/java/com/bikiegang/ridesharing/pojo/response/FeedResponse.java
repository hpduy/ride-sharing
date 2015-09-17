package com.bikiegang.ridesharing.pojo.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 8/18/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FeedResponse {
    private UserShortDetailResponse userDetail;
    private ExPartnerInfoResponse partnerInfo;
    private TripInFeed tripDetail;

    public UserShortDetailResponse getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserShortDetailResponse userDetail) {
        this.userDetail = userDetail;
    }

    public ExPartnerInfoResponse getPartnerInfo() {
        return partnerInfo;
    }

    public void setPartnerInfo(ExPartnerInfoResponse partnerInfo) {
        this.partnerInfo = partnerInfo;
    }

    public TripInFeed getTripDetail() {
        return tripDetail;
    }

    public void setTripDetail(TripInFeed tripDetail) {
        this.tripDetail = tripDetail;
    }

}
