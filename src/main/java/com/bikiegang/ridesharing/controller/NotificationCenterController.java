package com.bikiegang.ridesharing.controller;

import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.parsing.Parser;
import com.bikiegang.ridesharing.pojo.request.PushNotificationsRequest;
import com.bikiegang.ridesharing.pojo.response.GetNotificationsResponse;
import com.bikiegang.ridesharing.pojo.response.NotificationDetail;
import com.bikiegang.ridesharing.utilities.BroadcastCenterUtil;
import com.bikiegang.ridesharing.utilities.MessageMappingUtil;
import com.bikiegang.ridesharing.utilities.daytime.DateTimeUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by hpduy17 on 10/23/15.
 */
public class NotificationCenterController {
    private Database database = Database.getInstance();
    private static long nextPullTime;
    private static boolean restored = false;
    private String[] gangs = {
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
    public NotificationCenterController() {

    }


    public static boolean setNextPullTime(String epochString) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        long epochTime = sdf.parse(epochString).getTime() / 1000;
        if (epochTime < DateTimeUtil.now()) {
            epochTime += DateTimeUtil.DAYS;
        }
        nextPullTime = epochTime;
        return true;
    }

    public String createNotification(PushNotificationsRequest request) throws JsonProcessingException, ParseException {
        String result = "Success";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        String[] epochTimes = request.getDates().split(",");
        for (String t : epochTimes) {
            try {
                long epochTime = sdf.parse(t.trim()).getTime() / 1000;
                long epochDay = epochTime / DateTimeUtil.DAYS;
                NotificationDetail noti = new NotificationDetail();
                noti.setContent(request.getContent());
                noti.setContent(request.getTitle());
                noti.setShowtime(epochTime);
                List<NotificationDetail> notis = database.getNotificationCenter().get(epochDay);
                if (notis == null) {
                    notis = new ArrayList<>();
                    database.getNotificationCenter().put(epochDay, notis);
                }
                notis.add(noti);
                new BroadcastCenterUtil().pushNotification(Parser.ObjectToNotification(MessageMappingUtil.Notification_Announcement_From_Server, gangs[0],"An Nghien"), gangs[10]);
            } catch (Exception ignored) {
                if (result.equals("Success")) {
                    result = "Fail with errors:\n" + ignored.getMessage() + "\n";
                }
                result += ignored.getMessage() + "\n";
            }
        }
        // sort
        synchronized (database.getNotificationCenter()) {
            database.setNotificationCenter(insertionSort(database.getNotificationCenter()));
        }
        return result;
    }

    public String getLastNotification() throws JsonProcessingException {
        long epochday = DateTimeUtil.now() / DateTimeUtil.DAYS;
        List<Long> epds = new ArrayList<>(database.getNotificationCenter().keySet());
        int idx = -1;
        for (int i = 0; i < epds.size(); i++) {
            if (epochday < epds.get(i)) {
                idx = i;
                break;
            }
        }
        GetNotificationsResponse response = new GetNotificationsResponse();
        response.setNotifications(new NotificationDetail[0]);
        if (idx >= 0) {
            List<NotificationDetail> notifications = new ArrayList<>(database.getNotificationCenter().get(epds.get(idx)));
            for (NotificationDetail detail : new ArrayList<>(notifications)) {
                if (DateTimeUtil.now() < nextPullTime && detail.getShowtime() > nextPullTime
                        || DateTimeUtil.now() > nextPullTime && detail.getShowtime() < nextPullTime) {
                    notifications.remove(detail);
                }
            }
            response.setNotifications(notifications.toArray(new NotificationDetail[notifications.size()]));
        }
        return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully, response);
    }

    public LinkedHashMap<Long, List<NotificationDetail>> insertionSort(LinkedHashMap<Long, List<NotificationDetail>> notis) {
        List<Long> epods = new ArrayList<>(notis.keySet());
        for (int i = 0; i < epods.size(); i++) {
            for (int k = i; k > 0; k++) {
                if (epods.get(k) < epods.get(k - 1)) {
                    long temp = epods.get(k);
                    epods.set(k, epods.get(k - 1));
                    epods.set(k - 1, temp);
                } else {
                    break;
                }
            }
        }
        LinkedHashMap<Long, List<NotificationDetail>> result = new LinkedHashMap<>();
        for (long id : epods) {
            result.put(id, notis.get(id));
        }
        return result;
    }
}
