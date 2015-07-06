package com.bikiegang.ridesharing.controller;

import com.bikiegang.ridesharing.dao.CurrentLocationDao;
import com.bikiegang.ridesharing.dao.UserDao;
import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.database.IdGenerator;
import com.bikiegang.ridesharing.geocoding.TimezoneMapper;
import com.bikiegang.ridesharing.parsing.Parser;
import com.bikiegang.ridesharing.pojo.CurrentLocation;
import com.bikiegang.ridesharing.pojo.User;
import com.bikiegang.ridesharing.pojo.request.UpdateCurrentLocationRequest;
import com.bikiegang.ridesharing.utilities.DateTimeUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.TimeZone;

/**
 * Created by hpduy17 on 6/29/15.
 */
public class CurrentLocationController {
    private CurrentLocationDao dao = new CurrentLocationDao();
    private Database database = Database.getInstance();

    public String updateUserCurrentLocation(UpdateCurrentLocationRequest request) throws JsonProcessingException {
        if (null == request.getUserId() || request.getUserId().equals("")) {
            return Parser.ObjectToJSon(false, "'userId' is not found (empty or null)");
        }
        if (request.getLat() == 0 && request.getLng() == 0) {
            return Parser.ObjectToJSon(false, "Latitude and Longitude is invalid (0,0)");
        }
        if (request.getTime() < 0) {
            return Parser.ObjectToJSon(false, "Epoch time is invalid (< 0)");
        }
        //get user
        User user = database.getUserHashMap().get(request.getUserId());
        if (user == null) {
            return Parser.ObjectToJSon(false, "User is not exits");
        }
        // time process
        String tzId = TimezoneMapper.latLngToTimezoneString(request.getLat(), request.getLng());
        TimeZone timeZone = TimeZone.getTimeZone(tzId);
        long offset = 0;
        long time = request.getTime() == 0 ? DateTimeUtil.now() : request.getTime();
        if (null != timeZone) {
            offset = timeZone.getOffset(time);
        }
        // create current location
        CurrentLocation currentLocation = new CurrentLocation();
        currentLocation.setId(IdGenerator.getCurrentLocationId());
        currentLocation.setLat(request.getLat());
        currentLocation.setLng(request.getLng());
        currentLocation.setCreatedTime(time + offset);
        currentLocation.setEstimatedTime(time + offset);
        currentLocation.setAddress(request.getAddress());
        currentLocation.setUserId(request.getUserId());
        currentLocation.setPreviousLocationId(user.getCurrentLocationId());
        //insert to geo cell
        database.getGeoCellCurrentLocation().putToCell(currentLocation);
        if (dao.insert(currentLocation)) {
            //update
            user.setCurrentLocationId(currentLocation.getId());
            if (new UserDao().update(user)) {
                Parser.ObjectToJSon(true, "Update current location successfully");
            }
            return Parser.ObjectToJSon(false, "Cannot update user's current location");
        }
        return Parser.ObjectToJSon(false, "Cannot insert current location to database");
    }

}
