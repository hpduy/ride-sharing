package com.bikiegang.ridesharing.pojo.response.Notification;

import com.bikiegang.ridesharing.pojo.RequestVerify;
import com.bikiegang.ridesharing.pojo.User;
import com.bikiegang.ridesharing.pojo.response.UserDetailResponse;
import com.bikiegang.ridesharing.pojo.response.UserShortDetailResponse;

/**
 * Created by hpduy17 on 7/22/15.
 */
public class ReplyVerifyNoti extends ObjectNoti {
    private long requestId;
    private UserShortDetailResponse angelDetail;

    public ReplyVerifyNoti(RequestVerify verify, User user, int action) {
        super(action);
        this.requestId = verify.getId();
        this.angelDetail = new UserDetailResponse(user);
    }

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public UserShortDetailResponse getAngelDetail() {
        return angelDetail;
    }

    public void setAngelDetail(UserShortDetailResponse angelDetail) {
        this.angelDetail = angelDetail;
    }
}
