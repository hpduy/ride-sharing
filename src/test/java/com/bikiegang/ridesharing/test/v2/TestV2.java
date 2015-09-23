/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bikiegang.ridesharing.test.v2;

import com.bikiegang.ridesharing.annn.framework.util.StringUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author root
 */
public class TestV2 {

    String strAPIApp = "AcceptViewPhoneNumberAPI,AddPopularLocationAPI,AutoParingWhenSearchAPI,"
            + "CheckEnoughCertificatesAPI,CreateCertificateAPI,CreateInstantPlannedTripAPI,"
            + "CreateRecurrentPlannedTripAPI,CreateSingleFuturePlannedTripAPI,"
            + "CreateSocialTripAPI,EndTripAPI,ForNewAndroidGuyAPI,GetComingSoonTripAPI,"
            + "GetDefaultSettingAPI,GetGoogleRoutingResult,GetListRequestMakeTripAPI,"
            + "GetListRequestMakeTripOfMeAPI,GetListTripByCalendarAPI,GetNewFeedAPI,"
            + "GetPartnerLocationAPI,GetPlannedTripDetailAPI,GetPlannedTripsAroundFromMeAPI,"
            + "GetPopularLocationAPI,GetUsersDetailAPI,GetUsersDetailWithPlannedTripsAPI,"
            + "IncreasePopularityOfLocationAPI,LoginAPI,PairingPlannedTripAPI,RatingAPI,"
            + "RegisterAPI,RemoveRequestMakeTripAPI,ReplyMakeTripAPI,RequestMakeTripAPI,"
            + "RequestVerifyAPI,RequestViewPhoneNumberAPI,StartTripAPI,UpdateBroadcastAPI,"
            + "UpdateCurrentLocationAPI,UpdateCurrentLocationWithPlannedTripAPI,UpdateUserProfileAPI,"
            + "UpdateUserSocialNetWorkAccountAPI,UploadImageAPI,UploadPopularImageAPI";
    String strAPIAngelApp = "AngelChangePasswordAPI,AngelForgetPasswordAPI,AngelLoginAPI,"
            + "AngelRegisterAPI,AutocompleteGroupSearchAPI,CheckEmailExistAPI,ExitGroupAPI,"
            + "GetAlphabetAngelGroupsAPI,GetAngelActiveCodesAPI,GetAngelActiveCodesForWebAPI,"
            + "GetAngelGroupsAroundAPI,GetAngelsAroundFromMeAPI,GetAngelsInGroupAPI,GetListVerifyRequestAPI,"
            + "GetVerifyRequestDetailAPI,JoinGroupAPI,ReplyVerifyAPI,UpdateAngelBroadcastAPI,VerifyCertificateAPI";
    String host = "http://103.20.148.111:8080/RideSharing/";
    List<String> listAPIApp = new ArrayList<>();
    List<String> listAPIAngelApp = new ArrayList<>();
    HashMap<String, List<RequestData>> hashMapCase = new HashMap<>();

    public static TestV2 getInstance() {
        return TestV2Holder.INSTANCE;
    }

    private static class TestV2Holder {

        private static final TestV2 INSTANCE = new TestV2();
    }

    private TestV2() {
        listAPIApp = StringUtils.toList(strAPIApp, ",");
        listAPIAngelApp = StringUtils.toList(strAPIAngelApp, ",");

        //Ad case
        for (String api : listAPIApp) {
            hashMapCase.put(api, new ArrayList<RequestData>());
        }
        for (String api : listAPIAngelApp) {
            hashMapCase.put(api, new ArrayList<RequestData>());
        }
        
        //AcceptViewPhoneNumberAPI
        AddCase("AcceptViewPhoneNumberAPI", null);
    }

    void AddCase(String nameAPI, RequestData requestData) {
        if (hashMapCase.containsKey(nameAPI)) {
            hashMapCase.get(nameAPI).add(requestData);
        }
    }

    public static void main(String[] args) {

        System.out.println(TestV2.getInstance().listAPIAngelApp.size());
        System.out.println(TestV2.getInstance().listAPIApp.size());

    }

    public class RequestData {

        String json;
        int errorCode;
        boolean isSuccess;

        public RequestData() {
        }

        public RequestData(String json, int errorCode, boolean isSuccess) {
            this.json = json;
            this.errorCode = errorCode;
            this.isSuccess = isSuccess;
        }

        public String getJson() {
            return json;
        }

        public void setJson(String json) {
            this.json = json;
        }

        public int getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(int errorCode) {
            this.errorCode = errorCode;
        }

        public boolean isIsSuccess() {
            return isSuccess;
        }

        public void setIsSuccess(boolean isSuccess) {
            this.isSuccess = isSuccess;
        }

    }

}
