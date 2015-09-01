/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bikiegang.ridesharing.unitest.api;

import com.bikiegang.ridesharing.annn.framework.queue.QueueManager;
import com.bikiegang.ridesharing.annn.framework.util.JSONUtil;
import com.bikiegang.ridesharing.utilities.TestAPIUtils;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
public class AppApiLoadTest {

    private static final QueueManager qm;

    static {
        qm = QueueManager.getInstance("default");
        qm.init(64, 100000000);
        qm.process();
    }

    public static void main(String[] args) {
        int i = 1000000;
        while (i-- >= 0) {
            APIObject input = CreateObject();

            List<APIObject.APIData> apis = input.getAPI();
            for (APIObject.APIData item : apis) {
                qm.put(new PushCommand(item.getName(), item.data));
            }
        }
        while (qm.size() != 0) {
            System.out.println("Number job pending: " + qm.size());
            try {
                Thread.sleep(1 * 1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(AppApiLoadTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
//        }
    }

    private static APIObject CreateObject() {
        //        String ReadFile = ReadFile();
//        APIObject DeSerialize = JSONUtil.DeSerialize(ReadFile, APIObject.class);
//        System.out.println(JSONUtil.Serialize(DeSerialize));

        APIObject input = new APIObject();
        input.getAPI().add(
                new APIObject.APIData("AddPopularLocationAPI",
                        JSONUtil.Serialize(TestAPIUtils.CreateAddPopularLocationRequest()),
                        ""));
        input.getAPI().add(
                new APIObject.APIData("AutoParingWhenSearchAPI",
                        JSONUtil.Serialize(TestAPIUtils.CreateAutoSearchParingRequest()),
                        ""));
        input.getAPI().add(
                new APIObject.APIData("CreateInstantPlannedTripAPI",
                        JSONUtil.Serialize(TestAPIUtils.CreateCreatePlannedTripRequest()),
                        ""));
        input.getAPI().add(
                new APIObject.APIData("CreateRecurrentPlannedTripAPI",
                        JSONUtil.Serialize(TestAPIUtils.CreateCreatePlannedTripRequest()),
                        ""));
        input.getAPI().add(
                new APIObject.APIData("CreateSingleFuturePlannedTripAPI",
                        JSONUtil.Serialize(TestAPIUtils.CreateCreatePlannedTripRequest()),
                        ""));
        input.getAPI().add(
                new APIObject.APIData("CreateSocialTripAPI",
                        JSONUtil.Serialize(TestAPIUtils.CreateCreateSocialTripRequest()),
                        ""));
        input.getAPI().add(
                new APIObject.APIData("EndTripAPI",
                        JSONUtil.Serialize(TestAPIUtils.CreateEndTripRequest()),
                        ""));
        input.getAPI().add(
                new APIObject.APIData("GetDefaultSettingAPI",
                        "",
                        ""));
        input.getAPI().add(
                new APIObject.APIData("GetListRequestMakeTripAPI",
                        JSONUtil.Serialize(TestAPIUtils.CreateGetListRequestMakeTripRequest()),
                        "")
        );
        input.getAPI().add(
                new APIObject.APIData("GetNewFeedAPI",
                        JSONUtil.Serialize(TestAPIUtils.CreateGetFeedsRequest()),
                        "")
        );
        input.getAPI().add(
                new APIObject.APIData("GetPartnerLocationAPI",
                        JSONUtil.Serialize(TestAPIUtils.CreateGetPartnerLocationRequest()),
                        "")
        );
        input.getAPI().add(
                new APIObject.APIData("GetPlannedTripDetailAPI",
                        JSONUtil.Serialize(TestAPIUtils.CreateGetPlannedTripDetailRequest()),
                        "")
        );
        input.getAPI().add(
                new APIObject.APIData("GetPlannedTripsAroundFromMeAPI",
                        JSONUtil.Serialize(TestAPIUtils.CreateGetUsersAroundFromMeRequest()),
                        "")
        );
        input.getAPI().add(
                new APIObject.APIData("GetPopularLocationAPI",
                        "",
                        "")
        );
        input.getAPI().add(
                new APIObject.APIData("GetUsersDetailAPI",
                        JSONUtil.Serialize(TestAPIUtils.CreateGetInformationUsingUserIdRequest()),
                        "")
        );
        input.getAPI().add(
                new APIObject.APIData("GetUsersDetailWithPlannedTripsAPI",
                        JSONUtil.Serialize(TestAPIUtils.CreateGetInformationUsingUserIdRequest()),
                        "")
        );
        input.getAPI().add(
                new APIObject.APIData("LoginAPI",
                        JSONUtil.Serialize(TestAPIUtils.CreateLoginRequest()),
                        "")
        );
        input.getAPI().add(
                new APIObject.APIData("PairingPlannedTripAPI",
                        JSONUtil.Serialize(TestAPIUtils.CreateGetPlannedTripDetailRequest()),
                        "")
        );
        input.getAPI().add(
                new APIObject.APIData("RatingAPI",
                        JSONUtil.Serialize(TestAPIUtils.CreateRatingRequest()),
                        "")
        );
        input.getAPI().add(
                new APIObject.APIData("RemoveRequestMakeTripAPI",
                        JSONUtil.Serialize(TestAPIUtils.CreateRemoveRequestMakeTripRequest()),
                        "")
        );
        input.getAPI().add(
                new APIObject.APIData("RegisterAPI",
                        JSONUtil.Serialize(TestAPIUtils.CreateRegisterRequest()),
                        "")
        );
        input.getAPI().add(
                new APIObject.APIData("ReplyMakeTripAPI",
                        JSONUtil.Serialize(TestAPIUtils.CreateReplyMakeTripRequest()),
                        "")
        );
        input.getAPI().add(
                new APIObject.APIData("RequestMakeTripAPI",
                        JSONUtil.Serialize(TestAPIUtils.CreateRequestMakeTripRequest()),
                        "")
        );
        input.getAPI().add(
                new APIObject.APIData("StartTripAPI",
                        JSONUtil.Serialize(TestAPIUtils.CreateStartTripRequest()),
                        "")
        );
        input.getAPI().add(
                new APIObject.APIData("UpdateBroadcastAPI",
                        JSONUtil.Serialize(TestAPIUtils.CreateUpdateBroadcastRequest()),
                        "")
        );
        input.getAPI().add(
                new APIObject.APIData("UpdateCurrentLocationAPI",
                        JSONUtil.Serialize(TestAPIUtils.CreateUpdateCurrentLocationRequest()),
                        "")
        );
        input.getAPI().add(
                new APIObject.APIData("UpdateCurrentLocationWithPlannedTripAPI",
                        JSONUtil.Serialize(TestAPIUtils.CreateUpdateCurrentLocationWithPlannedTripRequest()),
                        "")
        );
        input.getAPI().add(
                new APIObject.APIData("UpdateUserProfileAPI",
                        JSONUtil.Serialize(TestAPIUtils.CreateUpdateProfileRequest()),
                        "")
        );
        input.getAPI().add(
                new APIObject.APIData("UpdateUserSocialNetWorkAccountAPI",
                        JSONUtil.Serialize(TestAPIUtils.CreateUpdateSocialNetworkAccountRequest()),
                        "")
        );
        return input;
    }

}
