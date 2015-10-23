package com.bikiegang.ridesharing.controller;

import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.parsing.Parser;
import com.bikiegang.ridesharing.pojo.request.PushNotificationsRequest;
import com.bikiegang.ridesharing.pojo.response.GetListNotificationResponse;
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

    public String createNotification(PushNotificationsRequest request) throws JsonProcessingException, ParseException {
        String result = "Success";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String[] epochTime = request.getDates().split(",");
        for (String t : epochTime) {
            try {
                long epochDay = sdf.parse(t.replaceAll(" ", "")).getTime() / 1000 / DateTimeUtil.DAYS;
                String noti = Parser.ObjectToNotification(MessageMappingUtil.Anouncement_From_Server, "", "PinBike", request.getContent());
                List<String> notis = database.getNotificationCenter().get(epochDay);
                if (notis == null) {
                    notis = new ArrayList<>();
                    database.getNotificationCenter().put(epochDay, notis);
                }
                notis.add(noti);
            } catch (Exception ignored) {
                if(result.equals("Success")){
                    result = "Fail with errors:\n"+ ignored.getMessage()+"\n";
                }
                result += ignored.getMessage()+"\n";
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
        GetListNotificationResponse response = new GetListNotificationResponse();
        response.setNotifications(new String[0]);
        if (idx >= 0) {
             database.getNotificationCenter().get(epds.get(idx));
        }
        return Parser.ObjectToJSon(true,MessageMappingUtil.Successfully, response);
    }

    public LinkedHashMap<Long, List<String>> insertionSort(LinkedHashMap<Long, List<String>> notis) {
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
        LinkedHashMap<Long, List<String>> result = new LinkedHashMap<>();
        for (long id : epods) {
            result.put(id, notis.get(id));
        }
        return result;
    }
}
