/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bikiegang.ridesharing.utilities;

import com.bikiegang.ridesharing.pojo.request.AddPopularLocationRequest;
import com.bikiegang.ridesharing.pojo.request.AutoSearchParingRequest;
import com.bikiegang.ridesharing.pojo.request.CreatePlannedTripRequest;
import com.bikiegang.ridesharing.pojo.request.CreateSocialTripRequest;
import com.bikiegang.ridesharing.pojo.request.EndTripRequest;
import com.bikiegang.ridesharing.pojo.request.GetAngelActiveCodesRequest;
import com.bikiegang.ridesharing.pojo.request.GetFeedsRequest;
import com.bikiegang.ridesharing.pojo.request.GetListRequestMakeTripRequest;
import com.bikiegang.ridesharing.pojo.request.GetPartnerLocationRequest;
import com.bikiegang.ridesharing.pojo.request.GetPlannedTripDetailRequest;
import com.bikiegang.ridesharing.pojo.request.GetTripByCalendarRequest;
import com.bikiegang.ridesharing.pojo.request.GetUsersAroundFromMeRequest;
import com.bikiegang.ridesharing.pojo.request.IncreasePopularityRequest;
import com.bikiegang.ridesharing.pojo.request.MergeGroupRequest;
import com.bikiegang.ridesharing.pojo.request.RatingRequest;
import com.bikiegang.ridesharing.pojo.request.RegisterRequest;
import com.bikiegang.ridesharing.pojo.request.RemoveRequestMakeTripRequest;
import com.bikiegang.ridesharing.pojo.request.ReplyMakeTripRequest;
import com.bikiegang.ridesharing.pojo.request.RequestMakeTripRequest;
import com.bikiegang.ridesharing.pojo.request.UpdateBroadcastRequest;
import com.bikiegang.ridesharing.pojo.request.UpdateCurrentLocationRequest;
import com.bikiegang.ridesharing.pojo.request.UpdateCurrentLocationWithPlannedTripRequest;
import com.bikiegang.ridesharing.pojo.request.UpdateProfileRequest;
import com.bikiegang.ridesharing.pojo.request.UpdateSocialNetworkAccountRequest;
import com.bikiegang.ridesharing.pojo.static_object.TripPattern;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;

/**
 *
 * @author root
 */
public class TestAPIUtils {

    public static AddPopularLocationRequest CreateAddPopularLocationRequest() {
        AddPopularLocationRequest result = new AddPopularLocationRequest(
                RandomStringUtils.randomAlphanumeric(30),
                RandomUtils.nextDouble(),
                RandomUtils.nextDouble(),
                RandomStringUtils.randomAlphanumeric(30),
                RandomStringUtils.randomAlphanumeric(30),
                RandomStringUtils.randomAlphanumeric(30));
        return result;
    }

    public static GetFeedsRequest CreateGetFeedsRequest() {
        GetFeedsRequest result = new GetFeedsRequest(
                RandomUtils.nextInt(),
                RandomUtils.nextInt(),
                RandomUtils.nextInt());
        return result;
    }

    public static IncreasePopularityRequest CreateIncreasePopularityRequest() {
        IncreasePopularityRequest result = new IncreasePopularityRequest(RandomStringUtils.randomAlphanumeric(30), RandomUtils.nextLong());

        return result;
    }

    public static RequestMakeTripRequest CreateRequestMakeTripRequest() {
        RequestMakeTripRequest result = new RequestMakeTripRequest(RandomStringUtils.randomAlphanumeric(30),
                RandomStringUtils.randomAlphanumeric(30),
                RandomUtils.nextLong(),
                RandomUtils.nextLong(),
                RandomUtils.nextDouble(),
                RandomStringUtils.randomAlphanumeric(30));

        return result;
    }

    public static AutoSearchParingRequest CreateAutoSearchParingRequest() {
        AutoSearchParingRequest result = new AutoSearchParingRequest(RandomStringUtils.randomAlphanumeric(30),
                RandomUtils.nextLong(),
                RandomStringUtils.randomAlphanumeric(30),
                true);
        return result;
    }

    public static GetListRequestMakeTripRequest CreateGetListRequestMakeTripRequest() {
        GetListRequestMakeTripRequest result = new GetListRequestMakeTripRequest(RandomStringUtils.randomAlphanumeric(30));
        return result;
    }

    public static MergeGroupRequest CreateMergeGroupRequest() {
        MergeGroupRequest result = new MergeGroupRequest(RandomUtils.nextLong(), RandomUtils.nextLong());

        return result;
    }

    public static UpdateBroadcastRequest CreateUpdateBroadcastRequest() {
        UpdateBroadcastRequest result = new UpdateBroadcastRequest(
                RandomStringUtils.randomAlphanumeric(30),
                RandomStringUtils.randomAlphanumeric(30),
                RandomStringUtils.randomAlphanumeric(30),
                RandomUtils.nextInt(),
                RandomUtils.nextInt());
        return result;
    }

    public static CreatePlannedTripRequest CreateCreatePlannedTripRequest() {

        TripPattern item1 = new TripPattern(RandomUtils.nextLong(),
                RandomUtils.nextLong(),
                RandomUtils.nextLong());
        TripPattern item2 = new TripPattern(RandomUtils.nextLong(),
                RandomUtils.nextLong(),
                RandomUtils.nextLong());
        TripPattern item3 = new TripPattern(RandomUtils.nextLong(),
                RandomUtils.nextLong(),
                RandomUtils.nextLong());
        TripPattern[] arr = new TripPattern[]{item1, item2, item3};
        CreatePlannedTripRequest result = new CreatePlannedTripRequest(
                RandomStringUtils.randomAlphanumeric(30),
                RandomUtils.nextInt(), RandomUtils.nextLong(),
                RandomStringUtils.randomAlphanumeric(30),
                true,
                RandomUtils.nextInt(),
                RandomStringUtils.randomAlphanumeric(30),
                arr);
        return result;
    }

    public static GetPartnerLocationRequest CreateGetPartnerLocationRequest() {
        return null;
    }

    public static RatingRequest CreateRatingRequest() {
        return null;
    }

    public static UpdateCurrentLocationRequest CreateUpdateCurrentLocationRequest() {
        return null;
    }

    public static CreateSocialTripRequest CreateCreateSocialTripRequest() {
        return null;
    }

    public static GetPlannedTripDetailRequest CreateGetPlannedTripDetailRequest() {
        return null;
    }

    public static RegisterRequest CreateRegisterRequest() {
        return null;
    }

    public static UpdateCurrentLocationWithPlannedTripRequest CreateUpdateCurrentLocationWithPlannedTripRequest() {
        return null;
    }

    public static EndTripRequest CreateEndTripRequest() {
        return null;
    }

    public static GetTripByCalendarRequest CreateGetTripByCalendarRequest() {
        return null;
    }

    public static RemoveRequestMakeTripRequest CreateRemoveRequestMakeTripRequest() {
        return null;
    }

    public static UpdateProfileRequest CreateUpdateProfileRequest() {
        return null;
    }

    public static GetAngelActiveCodesRequest CreateGetAngelActiveCodesRequest() {
        return null;
    }

    public static GetUsersAroundFromMeRequest CreateGetUsersAroundFromMeRequest() {
        return null;
    }

    public static ReplyMakeTripRequest CreateReplyMakeTripRequest() {
        return null;
    }

    public static UpdateSocialNetworkAccountRequest CreateUpdateSocialNetworkAccountRequest() {
        return null;
    }
}
