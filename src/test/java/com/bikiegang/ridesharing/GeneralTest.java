package com.bikiegang.ridesharing;

import com.bikiegang.ridesharing.controller.AnnouncementController;
import com.bikiegang.ridesharing.geocoding.GeoCell;
import com.bikiegang.ridesharing.pojo.LatLng;
import com.bikiegang.ridesharing.pojo.request.PushNotificationsRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;

import java.text.ParseException;

/**
 * Created by hpduy17 on 9/18/15.
 */
public class GeneralTest {
    @Test
    public void createLatLngFromString(){
        String type1 = "10.774033, 106.703653";
        String type2 = "10.774033,106.703653";
        String type3 = "(10.774033, 106.703653)";
        String type4 = "( 10.774033,  106.703653 )";
        String type5 = "(10.774033,106.703653";
        LatLng ll = new LatLng(type1);
        assert(ll.getLat() == 10.774033 && ll.getLng() == 106.703653);
        ll = new LatLng(type2);
        assert(ll.getLat() == 10.774033 && ll.getLng() == 106.703653);
        ll = new LatLng(type3);
        assert(ll.getLat() == 10.774033 && ll.getLng() == 106.703653);
        ll = new LatLng(type4);
        assert(ll.getLat() == 10.774033 && ll.getLng() == 106.703653);
        ll = new LatLng(type5);
        assert(ll.getLat() == 10.774033 && ll.getLng() == 106.703653);
    }

    @Test
    public void isCovariated(){
        assert(new GeoCell<Long>(0.002).isCovariated(new String[]{"1#1","2#1"},new String[]{"1#1","2#2"}));

    }

    @Test
    public void createAnnouncementTest() throws JsonProcessingException, ParseException {
        PushNotificationsRequest request = new PushNotificationsRequest();
        request.setContent("This is notification content");
        request.setTitle("Create Announcement Test");
        request.setDates("17/11/2015 14:00:00,18/11/2015 07:00:00");
        new AnnouncementController().createAnnouncement(request);
    }

    @Test
    public void setNextPullTime() throws ParseException {
        assert (AnnouncementController.setNextPullTime("17/11/2015 14:00:00"));
    }

}
