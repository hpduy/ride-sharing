package com.bikiegang.ridesharing.controller;

import com.bikiegang.ridesharing.dao.BroadcastDao;
import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.parsing.Parser;
import com.bikiegang.ridesharing.pojo.Broadcast;
import com.bikiegang.ridesharing.pojo.request.UpdateBroadcastRequest;
import com.bikiegang.ridesharing.utilities.BroadcastCenterUtil;
import com.bikiegang.ridesharing.utilities.MessageMappingUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Created by hpduy17 on 6/28/15.
 */
public class BroadcastController {
    private BroadcastDao dao = new BroadcastDao();
    private Database database = Database.getInstance();

    public String updateBroadcast(UpdateBroadcastRequest request, int broadcastType) throws Exception {
        if (request.getType() <= 0 ||
                (request.getType() != UpdateBroadcastRequest.DELETE
                        && request.getType() != UpdateBroadcastRequest.UPDATE)) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_invalid, "'type'");
        }
        if (null == request.getUserId() || request.getUserId().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_invalid, "'userId'");
        }
        if (null == request.getDeviceId() || request.getDeviceId().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_invalid, "'deviceId'");
        }
        switch (request.getType()) {
            case UpdateBroadcastRequest.DELETE:
                new BroadcastCenterUtil().pushNotification(Parser.ObjectToNotification(MessageMappingUtil.Notification_Successfully, database.getUserHashMap().get(request.getUserId())), request.getUserId(), broadcastType);
                return deleteBroadcast(request, broadcastType);
            case UpdateBroadcastRequest.UPDATE:
                new BroadcastCenterUtil().pushNotification(Parser.ObjectToNotification(MessageMappingUtil.Notification_Successfully, database.getUserHashMap().get(request.getUserId())), request.getUserId(), broadcastType);
                return insertAndUpdateBroadcast(request, broadcastType);
        }
        return Parser.ObjectToJSon(false, MessageMappingUtil.Interactive_with_database_fail);
    }

    private String insertAndUpdateBroadcast(UpdateBroadcastRequest request, int broadcastType) throws JsonProcessingException {
        if (null == request.getRegId() || request.getRegId().equals("")) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_invalid, "'regId'");
        }
        if (request.getOs() <= 0 ||
                (request.getOs() != Broadcast.ANDROID
                        && request.getOs() != Broadcast.IOS
                        && request.getOs() != Broadcast.WINDOW_PHONE)) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Element_is_invalid, "'os'");
        }
        String id = request.getDeviceId() + "#" + request.getUserId() + "#" + broadcastType;
        Broadcast broadcast = new Broadcast();
        broadcast.setId(id);
        broadcast.setDeviceId(request.getDeviceId());
        broadcast.setRegId(request.getRegId());
        broadcast.setUserId(request.getUserId());
        broadcast.setOs(request.getOs());
        if (!database.getBroadcastHashMap().containsKey(id)) {
            if (dao.insert(broadcast)) {
                return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully);
            }
            return Parser.ObjectToJSon(false, MessageMappingUtil.Interactive_with_database_fail);
        } else {
            if (dao.update(broadcast)) {
                return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully);
            }
            return Parser.ObjectToJSon(false, MessageMappingUtil.Interactive_with_database_fail);
        }
    }

    private String deleteBroadcast(UpdateBroadcastRequest request, int broadcastType) throws JsonProcessingException {
        String id = request.getDeviceId() + "#" + request.getUserId() + "#" + broadcastType;
        Broadcast broadcast = database.getBroadcastHashMap().get(id);
        if (null == broadcast) {
            return Parser.ObjectToJSon(false, MessageMappingUtil.Object_is_not_found, "Broadcast");
        }
        if (dao.delete(broadcast)) {
            return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully);
        }
        return Parser.ObjectToJSon(false, MessageMappingUtil.Interactive_with_database_fail);
    }
}
