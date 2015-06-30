package com.bikiegang.ridesharing.controller;

import com.bikiegang.ridesharing.dao.BroadcastDao;
import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.parsing.Parser;
import com.bikiegang.ridesharing.pojo.Broadcast;
import com.bikiegang.ridesharing.utilities.ObjectCheckerUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Created by hpduy17 on 6/28/15.
 */
public class BroadcastController {
    private BroadcastDao dao = new BroadcastDao();
    private Database database = Database.getInstance();

    public String insertBroadcast(Broadcast broadcast) throws NoSuchFieldException, IllegalAccessException, JsonProcessingException {
        String message = ObjectCheckerUtil.checkNull(broadcast);
        if(message.equals("")) {
            broadcast.setId(broadcast.getDeviceId()+"#"+broadcast.getUserId());
            if(dao.insert(broadcast)){
                return Parser.ObjectToJSon(true,"Insert broadcast successfully");
            }
            return Parser.ObjectToJSon(false,"Cannot insert broadcast, please try again later");
        }
        return Parser.ObjectToJSon(false,message);
    }
    public String updateBroadcast(Broadcast broadcast) throws NoSuchFieldException, IllegalAccessException, JsonProcessingException {
        String message = ObjectCheckerUtil.checkNullAndId(broadcast);
        if(message.equals("")) {
            if(dao.update(broadcast)){
                return Parser.ObjectToJSon(true,"Update broadcast successfully");
            }
            return Parser.ObjectToJSon(false,"Cannot update broadcast, please try again later");
        }
        return Parser.ObjectToJSon(false,message);
    }
    public String deleteBroadcast(Broadcast broadcast) throws NoSuchFieldException, IllegalAccessException, JsonProcessingException {
        String message = ObjectCheckerUtil.checkNullAndId(broadcast);
        if(message.equals("")) {
            if(dao.delete(broadcast)){
                return Parser.ObjectToJSon(true,"Delete broadcast successfully");
            }
            return Parser.ObjectToJSon(false,"Cannot delete broadcast, please try again later");
        }
        return Parser.ObjectToJSon(false,message);
    }

}
