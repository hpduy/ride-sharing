package com.bikiegang.ridesharing.pojo.response.Notification;

import com.bikiegang.ridesharing.pojo.RequestVerify;
import com.bikiegang.ridesharing.pojo.User;
import com.bikiegang.ridesharing.pojo.response.UserDetailResponse;

/**
 * Created by hpduy17 on 7/22/15.
 */
public class RequestVerifyNoti {
    private long requestId;
    private String userId = "";
    private int numberOfCertificate;
    private String signature = "";
    private UserDetailResponse userDetail;

    public RequestVerifyNoti(RequestVerify verify, User user) {
        this.requestId = verify.getId();
        this.userId = verify.getUserId();
        this.numberOfCertificate = verify.getNumberOfCertificate();
        this.signature = verify.getSignature();
        this.userDetail = new UserDetailResponse(user);
    }

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getNumberOfCertificate() {
        return numberOfCertificate;
    }

    public void setNumberOfCertificate(int numberOfCertificate) {
        this.numberOfCertificate = numberOfCertificate;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public UserDetailResponse getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetailResponse userDetail) {
        this.userDetail = userDetail;
    }
}
