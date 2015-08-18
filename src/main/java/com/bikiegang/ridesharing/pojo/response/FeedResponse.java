package com.bikiegang.ridesharing.pojo.response;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by hpduy17 on 8/18/15.
 */
public class FeedResponse {
    private UserShortDetailResponse userDetail;
    private ExPartnerInfoResponse partnerInfo;
    private TripInFeed tripDetail;
    private int typeOfTrip;
    @JsonIgnore
    private final int PLANNED_TRIP = 0;
    @JsonIgnore
    private final int SOCIAL_TRIP = 0;

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

    public int getTypeOfTrip() {
        return typeOfTrip;
    }

    public void setTypeOfTrip(int typeOfTrip) {
        this.typeOfTrip = typeOfTrip;
    }
}
