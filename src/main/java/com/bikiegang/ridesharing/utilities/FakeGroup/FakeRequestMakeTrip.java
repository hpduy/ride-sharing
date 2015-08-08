package com.bikiegang.ridesharing.utilities.FakeGroup;

import com.bikiegang.ridesharing.controller.RequestMakeTripController;
import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.pojo.User;
import com.bikiegang.ridesharing.pojo.request.RequestMakeTripRequest;

/**
 * Created by hpduy17 on 8/5/15.
 */
public class FakeRequestMakeTrip {
    public void FakeRequestVerify(User tester, int numberOfRequest) throws Exception {
        Database database = Database.getInstance();
        for (int i = 0; i < numberOfRequest; i++) {
            RequestMakeTripRequest requestMakeTripRequest = new RequestMakeTripRequest();
            User user = new FakeUser().fakeUser(User.FEMALE, User.UNVERIFIED, false);
            database.getUserHashMap().put(user.getId(), user);
            requestMakeTripRequest.setSenderId(user.getId());
            requestMakeTripRequest.setReceiverId(tester.getId());
            requestMakeTripRequest.setPrice(20000);
            requestMakeTripRequest.setSenderPlannedTripId(new FakePlannedTrip().fakePlannedTrip(tester.getId(),true, User.PASSENGER));
            requestMakeTripRequest.setReceiverPlannedTripId(new FakePlannedTrip().fakePlannedTrip(user.getId(),true, User.DRIVER));
            String result = new RequestMakeTripController().sendRequestMakeTrip(requestMakeTripRequest);
            System.out.print("Fake make trip request :::: " + result);
        }
    }
}
