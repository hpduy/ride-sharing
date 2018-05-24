package com.bikiegang.ridesharing.controller;

import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.parsing.Parser;
import com.bikiegang.ridesharing.pojo.request.PushNotificationsRequest;
import com.bikiegang.ridesharing.pojo.response.AnnouncementDetail;
import com.bikiegang.ridesharing.pojo.response.GetAnnouncementsResponse;
import com.bikiegang.ridesharing.utilities.MessageMappingUtil;
import com.bikiegang.ridesharing.utilities.daytime.DateTimeUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by hpduy17 on 10/23/15.
 */
public class AnnouncementController {
    private Database database = Database.getInstance();
    private static long nextPullTime;
    public static boolean restored = false;
    private final long[] goldenTimes = {4 * DateTimeUtil.HOURS + 30 * DateTimeUtil.MINUTES,
            10 * DateTimeUtil.HOURS + 30 * DateTimeUtil.MINUTES,
            16 * DateTimeUtil.HOURS + 30 * DateTimeUtil.MINUTES,
            22 * DateTimeUtil.HOURS + 30 * DateTimeUtil.MINUTES};

    private final int offset = 7 * DateTimeUtil.SECONDS_PER_HOUR;
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

    public AnnouncementController() {

    }


    public static boolean setNextPullTime(String epochString) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
        long epochTime = sdf.parse(epochString).getTime() / 1000;
        if (epochTime < DateTimeUtil.now()) {
            epochTime += DateTimeUtil.DAYS;
        }
        nextPullTime = epochTime;
        return true;
    }

    public String createAnnouncement(PushNotificationsRequest request) throws JsonProcessingException, ParseException {
        String result = "Success";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
        String[] epochTimes = request.getDates().split(",");
        for (String t : epochTimes) {
            try {
                long epochTime = sdf.parse(t.trim()).getTime() / 1000;
                long epochDay = epochTime / DateTimeUtil.DAYS;
                AnnouncementDetail noti = new AnnouncementDetail();
                noti.setContent(request.getContent());
                noti.setTitle(request.getTitle());
                noti.setShowtime(epochTime);
                noti.setType(request.getType());
                noti.setLink(request.getLink());
                List<AnnouncementDetail> notis = database.getAnnouncementHashMap().get(epochDay);
                if (notis == null) {
                    notis = new ArrayList<>();
                    database.getAnnouncementHashMap().put(epochDay, notis);
                }
                notis.add(noti);
                //new BroadcastCenterUtil().pushNotification(Parser.ObjectToNotification(MessageMappingUtil.Notification_Announcement_From_Server, gangs[0],"An Nghien"), gangs[10]);
            } catch (Exception ignored) {
                if (result.equals("Success")) {
                    result = "Fail with errors:\n" + ignored.getMessage() + "\n";
                }
                result += ignored.getMessage() + "\n";
            }
        }
        // sort
        synchronized (database.getAnnouncementHashMap()) {
            database.setAnnouncementHashMap(insertionSort(database.getAnnouncementHashMap()));
        }
        return result;
    }

    public String getLastNotification() throws JsonProcessingException {
        long now = (DateTimeUtil.now()+ offset) % DateTimeUtil.SECONDS_PER_DAY; // + 7 hours offset
        int flagGoldenTime;
        if (now > goldenTimes[0] && now < goldenTimes[1]) {
            flagGoldenTime = 2;
        } else if (now > goldenTimes[1] && now < goldenTimes[2]) {
            flagGoldenTime = 3;
        } else if (now > goldenTimes[2] && now < goldenTimes[3]) {
            flagGoldenTime = 4;
        } else {
            flagGoldenTime = 1;
        }
        long epochday = DateTimeUtil.now() / DateTimeUtil.DAYS;
        List<Long> epds = new ArrayList<>(database.getAnnouncementHashMap().keySet());
        int idx = -1;
        for (int i = 0; i < epds.size(); i++) {
            if (epochday <= epds.get(i)) {
                idx = i;
                break;
            }
        }
        GetAnnouncementsResponse response = new GetAnnouncementsResponse();
        response.setAnnouncements(new AnnouncementDetail[0]);
        if (idx >= 0) {
            List<AnnouncementDetail> notifications = new ArrayList<>();
            for (AnnouncementDetail detail : database.getAnnouncementHashMap().get(epds.get(idx))) {
                long timeInDate = (detail.getShowtime()+ offset) % DateTimeUtil.SECONDS_PER_DAY;
                switch (flagGoldenTime) {
                    case 1:
                        if (timeInDate >= goldenTimes[0] && timeInDate < goldenTimes[1]) {
                            notifications.add(detail);
                        }
                        break;
                    case 2:
                        if (timeInDate >= goldenTimes[1] && timeInDate < goldenTimes[2]) {
                            notifications.add(detail);
                        }
                        break;
                    case 3:
                        if (timeInDate >= goldenTimes[2] && timeInDate < goldenTimes[3]) {
                            notifications.add(detail);
                        }
                        break;
                    case 4:
                        if (timeInDate < goldenTimes[0] || timeInDate >= goldenTimes[3]) {
                            notifications.add(detail);
                        }
                        break;
                }
            }
            response.setAnnouncements(notifications.toArray(new AnnouncementDetail[notifications.size()]));
        }
        response.setNextPullTime(nextPullTime);
        return Parser.ObjectToJSon(true, MessageMappingUtil.Successfully, response);
    }

    public LinkedHashMap<Long, List<AnnouncementDetail>> insertionSort(LinkedHashMap<Long, List<AnnouncementDetail>> notis) {
        List<Long> epods = new ArrayList<>(notis.keySet());
        for (int i = 0; i < epods.size(); i++) {
            for (int k = i; k > 0; k--) {
                if (epods.get(k) < epods.get(k - 1)) {
                    long temp = epods.get(k);
                    epods.set(k, epods.get(k - 1));
                    epods.set(k - 1, temp);
                } else {
                    break;
                }
            }
        }
        LinkedHashMap<Long, List<AnnouncementDetail>> result = new LinkedHashMap<>();
        for (long id : epods) {
            result.put(id, notis.get(id));
        }
        return result;
    }
}
