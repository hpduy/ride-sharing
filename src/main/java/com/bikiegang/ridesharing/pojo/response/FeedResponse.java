package com.bikiegang.ridesharing.pojo.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 8/18/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FeedResponse {
    private UserDetailResponse userDetail;
    private ExPartnerInfoResponse partnerInfo;
    private TripInFeed tripDetail;
    private UserDetailResponse partnerDetail;
    private TripInFeed partnerTripDetail;


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


    public TripInFeed getPartnerTripDetail() {
        return partnerTripDetail;
    }

    public void setPartnerTripDetail(TripInFeed partnerTripDetail) {
        this.partnerTripDetail = partnerTripDetail;
    }

    public UserDetailResponse getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetailResponse userDetail) {
        this.userDetail = userDetail;
    }

    public UserDetailResponse getPartnerDetail() {
        return partnerDetail;
    }

    public void setPartnerDetail(UserDetailResponse partnerDetail) {
        this.partnerDetail = partnerDetail;
    }
}
