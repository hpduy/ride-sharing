/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bikiegang.ridesharing.unitest.api;

import com.bikiegang.ridesharing.annn.framework.common.LogUtil;
import com.bikiegang.ridesharing.annn.framework.util.JSONUtil;
import com.bikiegang.ridesharing.pojo.request.CreatePlannedTripRequest;
import com.bikiegang.ridesharing.utilities.ApiUtils;
import com.bikiegang.ridesharing.utilities.TestAPIUtils;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author root
 */
public class AppApiTest {

    @Test
    public void TestAPI() {

    }

    public static void main(String[] args) {
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

//        String Serialize = JSONUtil.Serialize(input);
//        System.out.println(Serialize);
//        LOG.info(Serialize);
        List<APIObject.APIData> apis = input.getAPI();
        for (APIObject.APIData item : apis) {
            System.out.println(item.name);
            String post = ApiUtils.getInstance().getPost("http://103.20.148.111:8080/RideSharing/" + item.getName(), item.getData());
            System.out.println(item.data);
            System.out.println(post);
        }

    }

    public static final String INPUT_API_DATA = "data/api.unittest";
    private static final org.apache.log4j.Logger LOG = LogUtil.getLogger(AppApiTest.class.getName());

    public static String ReadFile() {
        String result = "";
        BufferedReader br = null;
        try {

            String sCurrentLine;
            br = new BufferedReader(new FileReader(INPUT_API_DATA));
            StringBuilder strB = new StringBuilder();
            while ((sCurrentLine = br.readLine()) != null) {
                strB.append(sCurrentLine);
            }
            result = strB.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }
}
