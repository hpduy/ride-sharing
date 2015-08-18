package com.bikiegang.ridesharing.pojo.response;

import com.bikiegang.ridesharing.pojo.LatLng;
import com.bikiegang.ridesharing.pojo.SocialTrip;

/**
 * Created by hpduy17 on 7/8/15.
 */
public class SocialTripResponse extends SocialTrip implements TripInFeed {

    public SocialTripResponse() {
    }

    public SocialTripResponse(long id, String creatorId, LatLng location, String feeling, int feelingIcon, String wantToGo, int wantToGoIcon, String content, long createdTime) {
        super(id, creatorId, location, feeling, feelingIcon, wantToGo, wantToGoIcon, content, createdTime);
    }

    public SocialTripResponse(SocialTrip that) {
        super(that);
    }
}
