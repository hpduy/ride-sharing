package com.bikiegang.ridesharing.utilities;

import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.parsing.Parser;
import com.bikiegang.ridesharing.pojo.Broadcast;
import com.bikiegang.ridesharing.pojo.static_object.GCMTransferMessage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hpduy17 on 6/13/15.
 */
public class BroadcastCenterUtil implements Runnable {
    private List<String> contents = new ArrayList<>();
    private List<String> userIds = new ArrayList<>();
    private String collapseKey = "CloudBike";
    private String senderId = CLOUD_BIKE_SENDER_ID;
    private int type = Broadcast.MAIN_APP;
    private final String urlStringPath = Path.getServerAddress() + "/GCMBroadcast";

    //final variable
    public static String CLOUD_BIKE_SENDER_ID = "AIzaSyCLMZnnCx00PKpYkrjFCfKExCFGRhVdoqA";//"AIzaSyBbF-lPqCpcUsiJdahgt21WB00vpKRxXik";
    public static String ANGEL_SPECIAL_APP_SENDER_ID = "AIzaSyCLMZnnCx00PKpYkrjFCfKExCFGRhVdoqA";

    public BroadcastCenterUtil() {
    }

    public BroadcastCenterUtil(List<String> contents, List<String> userIds, String senderId, int type) {
        this.contents = contents;
        this.userIds = userIds;
        this.senderId = senderId;
        this.type = type;
    }

    @Override
    public void run() {
        try {
            //connect different server;
            if (Database.databaseStatus == Database.TESTING) {
                URL url = new URL(urlStringPath);
                HttpURLConnection myConn = (HttpURLConnection) url.openConnection();
                myConn.setDoOutput(true); // do output or post
                PrintWriter po = new PrintWriter(new OutputStreamWriter(myConn.getOutputStream(), "UTF-8"));
                GCMTransferMessage gcmMessage = new GCMTransferMessage();
                gcmMessage.setCollapseKey(collapseKey);
                gcmMessage.setContents(contents);
                gcmMessage.setUserIds(userIds);
                gcmMessage.setSenderId(senderId);
                gcmMessage.setType(type);
                po.println(Parser.ObjectToJSon(gcmMessage));
                po.close();
                //read data
                StringBuffer strBuffer = new StringBuffer();
                BufferedReader in = new BufferedReader(new InputStreamReader(myConn.getInputStream(), "UTF-8"));
                String inputLine = null;
                while ((inputLine = in.readLine()) != null) {
                    strBuffer.append(inputLine);
                }
                System.out.print(strBuffer.toString());
                in.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void pushNotification(String content, List<String> userIds, String senderId, int type) {
        List<String> contents = new ArrayList<>();
        for (int i = 0; i < userIds.size(); i++) {
            contents.add(content);
        }
        Thread pusher = new Thread(new BroadcastCenterUtil(contents, userIds, senderId, type));
        pusher.start();
    }

    public void pushNotification(String content, List<String> userIds, String senderId) {
       pushNotification(content,userIds,senderId,type);
    }

    public void pushNotification(String content, List<String> userIds, int type) {
        List<String> contents = new ArrayList<>();
        for (int i = 0; i < userIds.size(); i++) {
            contents.add(content);
        }
        Thread pusher = new Thread(new BroadcastCenterUtil(contents, userIds, senderId, type));
        pusher.start();
    }
    public void pushNotification(String content, List<String> userIds) {
       pushNotification(content,userIds,type);
    }

    public void pushNotification(String content, String userId, String senderId, int type) {
        List<String> contents = new ArrayList<>();
        List<String> userIds = new ArrayList<>();
        contents.add(content);
        userIds.add(userId);
        Thread pusher = new Thread(new BroadcastCenterUtil(contents, userIds, senderId, type));
        pusher.start();
    }
    public void pushNotification(String content, String userId, String senderId) {
       pushNotification(content,userId,senderId,type);
    }

    public void pushNotification(String content, String userId, int type) {
        List<String> contents = new ArrayList<>();
        List<String> userIds = new ArrayList<>();
        contents.add(content);
        userIds.add(userId);
        Thread pusher = new Thread(new BroadcastCenterUtil(contents, userIds, senderId, type));
        pusher.start();
    }
    public void pushNotification(String content, String userId) {
       pushNotification(content,userId,type);
    }



}
