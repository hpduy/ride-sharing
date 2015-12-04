package com.bikiegang.ridesharing.controller;

import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.database.IdGenerator;
import com.bikiegang.ridesharing.pojo.Event;
import com.bikiegang.ridesharing.pojo.PopularLocation;
import com.bikiegang.ridesharing.utilities.UploadImageUtil;

import java.text.SimpleDateFormat;

/**
 * Created by hpduy17 on 6/29/15.
 */
public class EventController {
    private Database database = Database.getInstance();
    private static boolean static_data_created = false;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
    // image processing
    private final String[] static_data = ("UNIGAMES#Nhà Thi đấu Tân Bình, 488 Hoàng Văn Thụ, quận Tân Bình, tp.HCM#http://pinride.me:8080/RideSharing/cloudbike/images/UNIGAMESxxx.jpg#10.795059, 106.653886#UniGames, Nhà thi đấu Tân Bình, Nhà thi đấu Xuân Hồng, Nhảy cổ động, Cheer Dance#1#15/11/2015 14:00:00;22/11/2015 14:00:00 #13/11/2015 00:00:00#22/11/2015 23:00:00#https://www.facebook.com/UniGamesVN#http://pinride.me:8080/RideSharing/cloudbike/images/bg_card_header_unigame.jpg#aidituicho;pinbike;unigame#Ai đi tui cho quá giang nè;Ôm miễn phí nhưng cấm sờ nha\n" +
            "Chung Kết UNIGAMES#Nhà Thi đấu Tân Bình, 488 Hoàng Văn Thụ, quận Tân Bình, tp.HCM#http://pinride.me:8080/RideSharing/cloudbike/images/UNIGAMESxxx.jpg#10.795059, 106.653886#Chung Kết UniGames, Nhà thi đấu Tân Bình, Nhà thi đấu Xuân Hồng, Nhảy cổ động, Cheer Dance#2#06/12/2015 14:00:00#02/12/2015 00:00:00#06/12/2015 23:00:00#https://www.facebook.com/UniGamesVN#http://pinride.me:8080/RideSharing/cloudbike/images/bg_card_header_unigame.jpg#aidituicho;pinbike;unigame#Ai đi tui cho quá giang nè;Ôm miễn phí nhưng cấm sờ nha" ).split("\\n");

    public EventController() {
        try {
            if (!static_data_created) {
                static_data_created = true;
                for (String line : static_data) {
                    String[] element = line.split("#");
                    PopularLocation popularLocation = new PopularLocation(element[3]);
                    popularLocation.setId(IdGenerator.getPopularLocationId());
                    popularLocation.setName(element[0]);
                    popularLocation.setAddress(element[1]);
                    popularLocation.setBackgroundImageLink(new UploadImageUtil().downloadPopularLocationIMGWithManySize(element[2], popularLocation.getName()));
                    popularLocation.setType(PopularLocation.EVENT_LOCATION);
                    // event processing
                    Event event = new Event();
                    event.setEventId(Long.parseLong(element[5]));
                    String[] eventTime = element[6].split(";");
                    long[] times = new long[eventTime.length];
                    for(int i = 0; i < times.length; i ++){
                        times[i] = sdf.parse(eventTime[i]).getTime()/1000;
                    }
                    event.setIsFixedTime(false);
                    event.setEventTimes(times);
                    event.setStartTime(sdf.parse(element[7]).getTime()/1000);
                    event.setEndTime(sdf.parse(element[8]).getTime()/1000);
                    event.setSuggestMessage("");
                    event.setInfoLink(element[9]);
                    event.setFeedBannerLink(element[10]);
                    event.setHashTags(element[11].split(";"));
                    event.setSharedContents(element[12].split(";"));
                    event.setIsFixedUnitPrice(false);
                    event.setUnitPrice(0);
                    // end
                    popularLocation.setEventId(event.getEventId());
                    database.getEventHashMap().put(event.getEventId(),event);
                    database.getPopularLocationHashMap().put(popularLocation.getId(), popularLocation);
                    database.getGeoCellPopularLocation().putToCell(popularLocation.getLat(), popularLocation.getLng(), popularLocation.getId());
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
