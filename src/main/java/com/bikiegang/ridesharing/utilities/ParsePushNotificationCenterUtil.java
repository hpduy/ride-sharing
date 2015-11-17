package com.bikiegang.ridesharing.utilities;

import com.bikiegang.ridesharing.database.Database;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hpduy17 on 9/14/15.
 */
public class ParsePushNotificationCenterUtil implements Runnable {
    private static final String APPLICATION_ID = "Dk5q9pcURTm3Dc8R87MhvXAbMx3Dg9yAnF9q8HIN";
    private static final String REST_API_KEY = "n86tW8icvF5Os9xT0y9bIbGdxIzVk4UJ62w9sdP9";
    private static final String URL = "https://api.parse.com/1/push";
    private Database database = Database.getInstance();
    private List<String> receiverIds = new ArrayList<>();
    private JSONObject data = new JSONObject();
    private int app_type = 0;

    private static Map<Integer, String> messageHashMap = new HashMap<Integer, String>();

    static {
        messageHashMap.put(MessageMappingUtil.Notification_AcceptPhoneNumber, "Agree to provide phone number");
        messageHashMap.put(MessageMappingUtil.Notification_AlertTripComingUp, "Your trip will start soon");
        messageHashMap.put(MessageMappingUtil.Notification_Announcement_From_Server, "Have an announcement from PinBike");
        messageHashMap.put(MessageMappingUtil.Notification_End_Trip, "Has end your paired trip");
        messageHashMap.put(MessageMappingUtil.Notification_ReplyMakeTrip_Accept, "Has accepted to go with you");
        messageHashMap.put(MessageMappingUtil.Notification_ReplyMakeTrip_Deny, "Has canceled trip");
        messageHashMap.put(MessageMappingUtil.Notification_RequestMakeTrip, "Wants to share a ride with you");
        messageHashMap.put(MessageMappingUtil.Notification_RequestPhoneNumber, "Request your phone number");
        messageHashMap.put(MessageMappingUtil.Notification_RequestVerify, "Request you verify");
        messageHashMap.put(MessageMappingUtil.Notification_Start_Trip, "Has start your paired trip");
        messageHashMap.put(MessageMappingUtil.Notification_VerifyCertificate_Success, "Verified successfully");
    }

    public ParsePushNotificationCenterUtil(List<String> receiverIds, JSONObject data, int app_type) {
        this.receiverIds = receiverIds;
        this.data = data;
        this.app_type = app_type;
    }

    @Override
    public void run() {
//        HashSet<String> regIds = new HashSet<>();
//        for (int i = 0; i < receiverIds.size(); i++) {
//            HashSet<String> broadcastIds = database.getUserIdRFBroadcasts().get(receiverIds.get(i) + "#" + app_type);
//            if (null != broadcastIds) {
//                for (String id : broadcastIds) {
//                    Broadcast broadcast = database.getBroadcastHashMap().get(id);
//                    if (null != broadcast && broadcast.getOs() == Broadcast.IOS) {
//                        regIds.add(broadcast.getRegId());
//                    }
//                }
//            }
//        }
        int messageCode = data.getInt("messageCode");
        String alertMess = messageHashMap.get(messageCode);
        if (alertMess == null)
            alertMess = "You have new notification from PinBike";
        else {
            alertMess = data.getJSONObject("result").getString("senderName") + " " + alertMess;
        }
        JSONObject content_data = new JSONObject();
        content_data.put("channels", receiverIds);
        content_data.put("type", "ios");
        content_data.put("data", new JSONObject().put("alert", alertMess).put("content", data));
        try {
            pushData(content_data.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void pushData(String content_data) throws Exception {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = null;
        HttpEntity entity = null;
        String responseString = null;
        HttpPost httpPost = new HttpPost(URL);
        httpPost.addHeader("X-Parse-Application-Id", APPLICATION_ID);
        httpPost.addHeader("X-Parse-REST-API-Key", REST_API_KEY);
        httpPost.addHeader("Content-Type", "application/json");
        StringEntity reqEntity = new StringEntity(content_data, "UTF-8");
        httpPost.setEntity(reqEntity);
        response = httpclient.execute(httpPost);
        entity = response.getEntity();
        if (entity != null) {
            responseString = EntityUtils.toString(response.getEntity());
        }
        System.out.println("PUSH NOTIFICATION: " + content_data);
        System.out.println("PUSH NOTIFICATION: " + responseString);
    }
}
