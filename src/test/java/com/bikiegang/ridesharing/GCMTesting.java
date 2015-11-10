package com.bikiegang.ridesharing;

import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.parsing.Parser;
import com.bikiegang.ridesharing.pojo.Broadcast;
import com.bikiegang.ridesharing.pojo.User;
import com.bikiegang.ridesharing.utilities.BroadcastCenterUtil;
import com.bikiegang.ridesharing.utilities.MessageMappingUtil;
import com.bikiegang.ridesharing.utilities.ParsePushNotificationCenterUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by hpduy17 on 7/30/15.
 */
public class GCMTesting extends com.bikiegang.ridesharing.Test {

    String[] gangs = {
            "fb_1119948731350574",//duy big
            "gg_104511978063048563987", // an nguyen
            "fb_796315037151408",// an nguyen
            "e_pumie.hoan2010@gmail.com", // thi
            "fb_1036274976385529", // thi
            "fb_968496846533933",// duyCT
            "gg_100887638771064551141",// duyCT
            "fb_120364848303139",// cloudbike
            "fb_929818660424600",// TaiL
            "fb_1149718978377938",// TaiNG
            "fb_10152850663931856",// TungLe

    };
    String [] temp = {"fb_929818660424600"};
    @Before
    public void setup(){
        Database.getInstance().restore();
    }
    @Test
    public void sendGCM() throws JsonProcessingException, InterruptedException {
        String senderId = "gg_104511978063048563987";
        String receiverId = "fb_796315037151408";
        Database database = Database.getInstance();
        Broadcast br = new Broadcast();
        br.setOs(Broadcast.ANDROID);
        br.setUserId(receiverId);
        br.setRegId("fNVv1rWn4hk:APA91bGhvCkNM4BMTiGENFTfjiv7-zlOXOM-9bseYQXHfxLhXnOFhsEpvp6Cz0wvGEZxKb8HDrSLjUqhzAiE5MdUi1JxV6mhRZkDtv3EHNbAXc7Ny7zgH9c43OzusYpWdWsReTCg3bSe");
        br.setDeviceId("abc");
        br.setType(Broadcast.MAIN_APP);
        br.setId(br.getDeviceId() + "#" + br.getUserId());
        database.getBroadcastHashMap().put(br.getId(), br);
        HashSet<String> broadcastIds = database.getUserIdRFBroadcasts().get(br.getUserId() + "#" + br.getType());
        if (broadcastIds == null) {
            broadcastIds = new HashSet<>();
            database.getUserIdRFBroadcasts().put(br.getUserId() + "#" + br.getType(), broadcastIds);
        }
        broadcastIds.add(br.getId());
        new BroadcastCenterUtil().pushNotification(Parser.ObjectToNotification(MessageMappingUtil.Notification_RequestMakeTrip, senderId,"An Nghien"), receiverId);
        Thread.sleep(1000 * 60);
    }


    @Test
    public void sendParse() throws JsonProcessingException, InterruptedException {
        String senderId = "gg_104511978063048563987";
        User sender = Database.getInstance().getUserHashMap().get(senderId);
        Thread t = new Thread(new ParsePushNotificationCenterUtil(Arrays.asList(temp),new JSONObject(Parser.ObjectToNotification(MessageMappingUtil.Notification_RequestMakeTrip, sender)),0));
        t.start();
        Thread.sleep(1000 * 30);
    }
}
