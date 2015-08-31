/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bikiegang.ridesharing.utilities;

import com.bikiegang.ridesharing.pojo.LatLng;
import com.bikiegang.ridesharing.pojo.request.AddPopularLocationRequest;
import com.bikiegang.ridesharing.pojo.request.AutoSearchParingRequest;
import com.bikiegang.ridesharing.pojo.request.CreatePlannedTripRequest;
import com.bikiegang.ridesharing.pojo.request.CreateSocialTripRequest;
import com.bikiegang.ridesharing.pojo.request.EndTripRequest;
import com.bikiegang.ridesharing.pojo.request.GetAngelActiveCodesRequest;
import com.bikiegang.ridesharing.pojo.request.GetFeedsRequest;
import com.bikiegang.ridesharing.pojo.request.GetInformationUsingUserIdRequest;
import com.bikiegang.ridesharing.pojo.request.GetListRequestMakeTripRequest;
import com.bikiegang.ridesharing.pojo.request.GetPartnerLocationRequest;
import com.bikiegang.ridesharing.pojo.request.GetPlannedTripDetailRequest;
import com.bikiegang.ridesharing.pojo.request.GetTripByCalendarRequest;
import com.bikiegang.ridesharing.pojo.request.GetUsersAroundFromMeRequest;
import com.bikiegang.ridesharing.pojo.request.IncreasePopularityRequest;
import com.bikiegang.ridesharing.pojo.request.LoginRequest;
import com.bikiegang.ridesharing.pojo.request.MergeGroupRequest;
import com.bikiegang.ridesharing.pojo.request.RatingRequest;
import com.bikiegang.ridesharing.pojo.request.RegisterRequest;
import com.bikiegang.ridesharing.pojo.request.RemoveRequestMakeTripRequest;
import com.bikiegang.ridesharing.pojo.request.ReplyMakeTripRequest;
import com.bikiegang.ridesharing.pojo.request.RequestMakeTripRequest;
import com.bikiegang.ridesharing.pojo.request.StartTripRequest;
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
        IncreasePopularityRequest result = new IncreasePopularityRequest(
                RandomStringUtils.randomAlphanumeric(30),
                RandomUtils.nextLong());

        return result;
    }

    public static RequestMakeTripRequest CreateRequestMakeTripRequest() {
        RequestMakeTripRequest result = new RequestMakeTripRequest(
                RandomStringUtils.randomAlphanumeric(30),
                RandomStringUtils.randomAlphanumeric(30),
                RandomUtils.nextLong(),
                RandomUtils.nextLong(),
                RandomUtils.nextDouble(),
                RandomStringUtils.randomAlphanumeric(30));

        return result;
    }

    public static AutoSearchParingRequest CreateAutoSearchParingRequest() {
        AutoSearchParingRequest result = new AutoSearchParingRequest(
                RandomStringUtils.randomAlphanumeric(30),
                RandomUtils.nextLong(),
                RandomStringUtils.randomAlphanumeric(30),
                true);
        return result;
    }

    public static GetListRequestMakeTripRequest CreateGetListRequestMakeTripRequest() {
        GetListRequestMakeTripRequest result = new GetListRequestMakeTripRequest(
                RandomStringUtils.randomAlphanumeric(30));
        return result;
    }

    public static MergeGroupRequest CreateMergeGroupRequest() {
        MergeGroupRequest result = new MergeGroupRequest(
                RandomUtils.nextLong(),
                RandomUtils.nextLong());

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
        TripPattern item1 = new TripPattern(
                RandomUtils.nextLong(),
                RandomUtils.nextLong(),
                RandomUtils.nextInt());
        TripPattern item2 = new TripPattern(
                RandomUtils.nextLong(),
                RandomUtils.nextLong(),
                RandomUtils.nextInt());
        TripPattern item3 = new TripPattern(
                RandomUtils.nextLong(),
                RandomUtils.nextLong(),
                RandomUtils.nextInt());
        TripPattern[] patterns = new TripPattern[]{item1, item2, item3};
        LatLng[] waypoints = new LatLng[]{
            new LatLng(RandomUtils.nextDouble(),
            RandomUtils.nextDouble()),
            new LatLng(RandomUtils.nextDouble(),
            RandomUtils.nextDouble()),
            new LatLng(RandomUtils.nextDouble(),
            RandomUtils.nextDouble())
        };
        CreatePlannedTripRequest result = new CreatePlannedTripRequest(
                RandomStringUtils.randomAlphanumeric(30),
                RandomUtils.nextInt(),
                RandomUtils.nextLong(),
                RandomStringUtils.randomAlphanumeric(30),
                true,
                RandomUtils.nextInt(),
                RandomStringUtils.randomAlphanumeric(30),
                patterns, waypoints
        );
        return result;
    }

    public static GetPlannedTripDetailRequest CreateGetPlannedTripDetailRequest() {
        GetPlannedTripDetailRequest result = new GetPlannedTripDetailRequest(
                RandomUtils.nextLong(),
                RandomStringUtils.randomAlphanumeric(30));

        return result;
    }

    public static GetPartnerLocationRequest CreateGetPartnerLocationRequest() {
        GetPartnerLocationRequest result = new GetPartnerLocationRequest(RandomStringUtils.randomAlphabetic(10));
        return result;
    }

    public static CreateSocialTripRequest CreateCreateSocialTripRequest() {
        CreateSocialTripRequest result = new CreateSocialTripRequest(
                RandomStringUtils.randomAlphanumeric(30),
                RandomUtils.nextDouble(),
                RandomUtils.nextDouble(),
                RandomStringUtils.randomAlphanumeric(30),
                RandomUtils.nextInt(),
                RandomStringUtils.randomAlphanumeric(30),
                RandomUtils.nextInt(),
                RandomUtils.nextInt());
        return result;
    }

    public static RegisterRequest CreateRegisterRequest() {
        RegisterRequest result = new RegisterRequest(
                RandomStringUtils.randomAlphanumeric(30),
                RandomStringUtils.randomAlphanumeric(30),
                RandomStringUtils.randomAlphanumeric(30),
                RandomStringUtils.randomAlphanumeric(30),
                RandomStringUtils.randomAlphanumeric(30),
                RandomStringUtils.randomAlphanumeric(30),
                RandomStringUtils.randomAlphanumeric(30),
                RandomStringUtils.randomAlphanumeric(30),
                RandomStringUtils.randomAlphanumeric(30),
                RandomStringUtils.randomAlphanumeric(30),
                RandomStringUtils.randomAlphanumeric(30),
                RandomStringUtils.randomAlphanumeric(30),
                RandomUtils.nextInt(),
                RandomUtils.nextInt());
        return result;
    }

    public static UpdateCurrentLocationWithPlannedTripRequest CreateUpdateCurrentLocationWithPlannedTripRequest() {

        UpdateCurrentLocationWithPlannedTripRequest result = new UpdateCurrentLocationWithPlannedTripRequest(
                RandomStringUtils.randomAlphanumeric(30),
                RandomStringUtils.randomAlphanumeric(30),
                RandomUtils.nextLong());
        return result;
    }

    public static EndTripRequest CreateEndTripRequest() {
        EndTripRequest result = new EndTripRequest(
                RandomUtils.nextLong(),
                new LatLng(
                        RandomUtils.nextDouble(),
                        RandomUtils.nextDouble()),
                RandomStringUtils.randomAlphanumeric(30));
        return result;
    }

    public static GetTripByCalendarRequest CreateGetTripByCalendarRequest() {
        GetTripByCalendarRequest reuslt = new GetTripByCalendarRequest(
                RandomUtils.nextLong(),
                RandomUtils.nextLong(),
                RandomStringUtils.randomAlphanumeric(30));
        return reuslt;
    }

    public static RemoveRequestMakeTripRequest CreateRemoveRequestMakeTripRequest() {
        RemoveRequestMakeTripRequest result = new RemoveRequestMakeTripRequest(
                RandomStringUtils.randomAlphanumeric(30),
                RandomUtils.nextLong());
        return result;
    }

    public static UpdateProfileRequest CreateUpdateProfileRequest() {
        UpdateProfileRequest result = new UpdateProfileRequest(
                RandomStringUtils.randomAlphanumeric(30),
                RandomStringUtils.randomAlphanumeric(30),
                RandomStringUtils.randomAlphanumeric(30),
                RandomStringUtils.randomAlphanumeric(30),
                RandomStringUtils.randomAlphanumeric(30),
                RandomStringUtils.randomAlphanumeric(30),
                RandomStringUtils.randomAlphanumeric(30),
                RandomStringUtils.randomAlphanumeric(30));
        return result;
    }

    public static GetAngelActiveCodesRequest CreateGetAngelActiveCodesRequest() {
        GetAngelActiveCodesRequest result = new GetAngelActiveCodesRequest(
                RandomUtils.nextInt());
        return result;
    }

    public static GetUsersAroundFromMeRequest CreateGetUsersAroundFromMeRequest() {
        GetUsersAroundFromMeRequest result = new GetFeedsRequest(
                RandomUtils.nextInt(),
                RandomUtils.nextInt(),
                RandomUtils.nextInt());
        return result;
    }

    public static ReplyMakeTripRequest CreateReplyMakeTripRequest() {
        ReplyMakeTripRequest result = new ReplyMakeTripRequest(
                RandomUtils.nextLong(),
                RandomUtils.nextInt(),
                RandomStringUtils.randomAlphanumeric(30));
        return result;
    }

    public static UpdateSocialNetworkAccountRequest CreateUpdateSocialNetworkAccountRequest() {
        UpdateSocialNetworkAccountRequest result = new UpdateSocialNetworkAccountRequest(
                RandomStringUtils.randomAlphanumeric(30),
                RandomStringUtils.randomAlphanumeric(30),
                RandomUtils.nextInt());
        return result;
    }

    public static GetInformationUsingUserIdRequest CreateGetInformationUsingUserIdRequest() {
        GetInformationUsingUserIdRequest result = new GetInformationUsingUserIdRequest(
                RandomStringUtils.randomAlphanumeric(30));
        return result;
    }

    public static LoginRequest CreateLoginRequest() {
        LoginRequest result = new LoginRequest(
                RandomStringUtils.randomAlphanumeric(30),
                RandomStringUtils.randomAlphanumeric(30),
                RandomUtils.nextInt());
        return result;
    }

    public static RatingRequest CreateRatingRequest() {
        RatingRequest result = new RatingRequest(
                RandomStringUtils.randomAlphanumeric(30),
                RandomStringUtils.randomAlphanumeric(30),
                RandomUtils.nextInt(),
                RandomStringUtils.randomAlphanumeric(30),
                RandomUtils.nextLong());
        return result;
    }

    public static StartTripRequest CreateStartTripRequest() {
        StartTripRequest result = new StartTripRequest(
                RandomStringUtils.randomAlphanumeric(30),
                RandomUtils.nextLong());
        return result;
    }

    public static UpdateCurrentLocationRequest CreateUpdateCurrentLocationRequest() {
        UpdateCurrentLocationRequest result = new UpdateCurrentLocationRequest(
                RandomUtils.nextDouble(),
                RandomUtils.nextDouble(),
                RandomStringUtils.randomAlphanumeric(30));
        return result;
    }

}
