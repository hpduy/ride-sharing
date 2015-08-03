package com.bikiegang.ridesharing.pojo.response.Notification;

import com.bikiegang.ridesharing.pojo.RequestVerify;

/**
 * Created by hpduy17 on 7/22/15.
 */
public class RequestVerifyNoti{
    private long requestId;
    private int numberOfCertificate;

    public RequestVerifyNoti(RequestVerify verify) {
        this.requestId = verify.getId();
        this.numberOfCertificate = verify.getNumberOfCertificate();
    }

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }


    public int getNumberOfCertificate() {
        return numberOfCertificate;
    }

    public void setNumberOfCertificate(int numberOfCertificate) {
        this.numberOfCertificate = numberOfCertificate;
    }

}
