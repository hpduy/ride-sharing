package com.bikiegang.ridesharing.controller;

import com.bikiegang.ridesharing.dao.BroadcastDao;
import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.parsing.Parser;
import com.bikiegang.ridesharing.pojo.Broadcast;
import com.bikiegang.ridesharing.pojo.request.UpdateBroadcastRequest;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Created by hpduy17 on 6/28/15.
 */
public class BroadcastController {
    private BroadcastDao dao = new BroadcastDao();
    private Database database = Database.getInstance();

    public String updateBroadcast(UpdateBroadcastRequest request) throws Exception {
        if (request.getType() <= 0 ||
                (request.getType() != UpdateBroadcastRequest.DELETE
                        && request.getType() != UpdateBroadcastRequest.UPDATE)) {
            return Parser.ObjectToJSon(false, "'type' is invalid");
        }
        if (null == request.getUserId() || request.getUserId().equals("")) {
            return Parser.ObjectToJSon(false, "'userId' is invalid");
        }
        if (null == request.getDeviceId() || request.getDeviceId().equals("")) {
            return Parser.ObjectToJSon(false, "'deviceId' is invalid");
        }
        switch (request.getType()) {
            case UpdateBroadcastRequest.DELETE:
                return deleteBroadcast(request);
            case UpdateBroadcastRequest.UPDATE:
                return insertAndUpdateBroadcast(request);
        }
        return Parser.ObjectToJSon(false, "Cannot update broadcast, please try again");
    }

    private String insertAndUpdateBroadcast(UpdateBroadcastRequest request) throws JsonProcessingException {
        if (null == request.getRegId() || request.getRegId().equals("")) {
            return Parser.ObjectToJSon(false, "'regId' is invalid");
        }
        if (request.getOs() <= 0 ||
                (request.getOs() != Broadcast.ANDROID
                        && request.getOs() != Broadcast.IOS
                        && request.getOs() != Broadcast.WINDOW_PHONE)) {
            return Parser.ObjectToJSon(false, "'os' is invalid");
        }
        String id = request.getDeviceId() + "#" + request.getUserId();
        Broadcast broadcast = new Broadcast();
        broadcast.setId(id);
        broadcast.setDeviceId(request.getDeviceId());
        broadcast.setRegId(request.getRegId());
        broadcast.setUserId(request.getUserId());
        broadcast.setOs(request.getOs());
        if (database.getBroadcastHashMap().containsKey(id)) {
            if (dao.insert(broadcast)) {
                return Parser.ObjectToJSon(true, "Broadcast has been inserted successfully");
            }
            return Parser.ObjectToJSon(false, "Cannot insert broadcast");
        } else {
            if (dao.update(broadcast)) {
                return Parser.ObjectToJSon(true, "Broadcast has been updated successfully");
            }
            return Parser.ObjectToJSon(false, "Cannot update broadcast");
        }
    }

    private String deleteBroadcast(UpdateBroadcastRequest request) throws JsonProcessingException {
        String id = request.getDeviceId() + "#" + request.getUserId();
        Broadcast broadcast = database.getBroadcastHashMap().get(id);
        if (null == broadcast) {
            return Parser.ObjectToJSon(false, "Broadcast is not found");
        }
        if (dao.delete(broadcast)) {
            return Parser.ObjectToJSon(true, "Broadcast has been deleted successfully");
        }
        return Parser.ObjectToJSon(false, "Cannot delete broadcast");
    }
}
