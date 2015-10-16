package com.bikiegang.ridesharing.pojo.response;

import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.pojo.Message;
import com.bikiegang.ridesharing.pojo.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hpduy17 on 10/13/15.
 */
public class MessageDetail extends Message {
    private UserDetailResponse senderDetail;
    private UserDetailResponse[] receiversDetail;

    public MessageDetail() {

    }

    public MessageDetail(Message that) {
        super(that);
        Database database = Database.getInstance();
        User sender = database.getUserHashMap().get(that.getSenderId());
        if(sender != null){
            senderDetail = new UserDetailResponse(sender);
        }
        List<UserDetailResponse> receivers = new ArrayList<>();
        for(String id : that.getReceiverIds()){
            User r = database.getUserHashMap().get(id);
            if(r != null){
               receivers.add(new UserDetailResponse(r));
            }
        }
            receiversDetail = receivers.toArray(new UserDetailResponse[receivers.size()]);
    }

    public UserDetailResponse getSenderDetail() {
        return senderDetail;
    }

    public void setSenderDetail(UserDetailResponse senderDetail) {
        this.senderDetail = senderDetail;
    }

    public UserDetailResponse[] getReceiversDetail() {
        return receiversDetail;
    }

    public void setReceiversDetail(UserDetailResponse[] receiversDetail) {
        this.receiversDetail = receiversDetail;
    }
}
