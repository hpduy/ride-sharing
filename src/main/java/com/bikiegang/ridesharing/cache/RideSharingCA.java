/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bikiegang.ridesharing.cache;

import com.bikiegang.ridesharing.annn.framework.common.Config;
import com.bikiegang.ridesharing.annn.framework.memcache.RedisClient;
import com.bikiegang.ridesharing.annn.framework.util.ConvertUtils;
import com.bikiegang.ridesharing.annn.framework.util.JSONUtil;
import com.bikiegang.ridesharing.config.ConfigInfo;
import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.pojo.Activity;
import com.bikiegang.ridesharing.pojo.Broadcast;
import com.bikiegang.ridesharing.pojo.Circle;
import com.bikiegang.ridesharing.pojo.CircleMember;
import com.bikiegang.ridesharing.pojo.CurrentLocation;
import com.bikiegang.ridesharing.pojo.Endorse;
import com.bikiegang.ridesharing.pojo.LinkedLocation;
import com.bikiegang.ridesharing.pojo.Location;
import com.bikiegang.ridesharing.pojo.Rating;
import com.bikiegang.ridesharing.pojo.RequestMakeTrip;
import com.bikiegang.ridesharing.pojo.Route;
import com.bikiegang.ridesharing.pojo.Trip;
import com.bikiegang.ridesharing.pojo.TripFeedback;
import com.bikiegang.ridesharing.pojo.User;
import com.bikiegang.ridesharing.pojo.UserProfile;
import com.bikiegang.ridesharing.pojo.VerifiedCertificate;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author root
 */
public class RideSharingCA {

    //<editor-fold defaultstate="collapsed" desc="Singleton class">
    private RideSharingCA() {
        _prefix = Config.getParam(_configSection, "prefix");
        redisClient = redisClient.getInstance(_configSection);
    }

    public static RideSharingCA getInstance(String configSection) {
        _configSection = configSection;

        return RideSharingCAHolder.INSTANCE;
    }

    private static class RideSharingCAHolder {

        private static final RideSharingCA INSTANCE = new RideSharingCA();
    }
    private static String _prefix;
    private static String _configSection;
    private static final Logger _logger = LoggerFactory.getLogger(RideSharingCA.class);
    private static RedisClient redisClient;
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Redis function">
    private String buildKeyWithPrefix(String orgKey) {
        //you should see tv.video.{orgKey}
        String keyBuild = String.format("%s%s", _prefix, orgKey);
        return keyBuild;
    }
    RedisClient client = RedisClient.getInstance(ConfigInfo.REDIS_SERVER);

    public boolean hset(String key, String field, String value) throws Exception {
        boolean result = false;
        try {
            redisClient.hset(buildKeyWithPrefix(key), field, value);
            result = true;
        } catch (Exception ex) {
            _logger.error(ex.getMessage(), ex);
        }
        return result;
    }

    public boolean hsetnx(String key, String field, String value) throws Exception {
        boolean result = false;
        try {
            redisClient.hsetnx(buildKeyWithPrefix(key), field, value);
            result = true;
        } catch (Exception ex) {
            _logger.error(ex.getMessage(), ex);
        }
        return result;
    }

    public boolean hdel(String key, String[] field) throws Exception {
        boolean result = false;
        try {
            redisClient.hdel(buildKeyWithPrefix(key), field);
            result = true;
        } catch (Exception ex) {
            _logger.error(ex.getMessage(), ex);
        }
        return result;
    }

    public String hget(String key, String field) throws Exception {
        String result = "";
        try {
            result = redisClient.hget(buildKeyWithPrefix(key), field);
        } catch (Exception ex) {
            _logger.error(ex.getMessage(), ex);
        }
        return result;
    }

    public Map<String, String> hgetAll(String key) throws Exception {
        Map<String, String> result = new HashMap<>();
        try {
            result = redisClient.hgetAll(buildKeyWithPrefix(key));
        } catch (Exception ex) {
            _logger.error(ex.getMessage(), ex);
        }
        return result;
    }

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Redis function">
    Database database = Database.getInstance();

    public boolean RestoreActivity() {
        boolean result = false;
        try {
            database.getActivityHashMap().clear();
            Map<String, String> hgetAll = hgetAll(Activity.class.getName());
            for (Map.Entry<String, String> entrySet : hgetAll.entrySet()) {
                String key = entrySet.getKey();
                String value = entrySet.getValue();
                database.getActivityHashMap().put(ConvertUtils.toLong(key), (Activity) JSONUtil.DeSerialize(value, Activity.class));
            }
            result = true;
        } catch (Exception ex) {
            _logger.error(ex.getMessage());
            ex.printStackTrace();
        }
        return result;
    }

    public boolean RestoreBroadcast() {
        boolean result = false;
        try {
            database.getBroadcastHashMap().clear();
            Map<String, String> hgetAll = hgetAll(Broadcast.class.getName());
            for (Map.Entry<String, String> entrySet : hgetAll.entrySet()) {
                String key = entrySet.getKey();
                String value = entrySet.getValue();
                database.getBroadcastHashMap().put(key, (Broadcast) JSONUtil.DeSerialize(value, Broadcast.class));
            }
            result = true;
        } catch (Exception ex) {
            _logger.error(ex.getMessage());
            ex.printStackTrace();
        }
        return result;
    }

    public boolean RestoreCircle() {
        boolean result = false;
        try {
            database.getCircleHashMap().clear();
            Map<String, String> hgetAll = hgetAll(Circle.class.getName());
            for (Map.Entry<String, String> entrySet : hgetAll.entrySet()) {
                String key = entrySet.getKey();
                String value = entrySet.getValue();
                database.getCircleHashMap().put(ConvertUtils.toLong(key), (Circle) JSONUtil.DeSerialize(value, Circle.class));
            }
            result = true;
        } catch (Exception ex) {
            _logger.error(ex.getMessage());
            ex.printStackTrace();
        }
        return result;
    }

    public boolean RestoreCircleMember() {
        boolean result = false;
        try {
            database.getCircleMemberHashMap().clear();
            Map<String, String> hgetAll = hgetAll(CircleMember.class.getName());
            for (Map.Entry<String, String> entrySet : hgetAll.entrySet()) {
                String key = entrySet.getKey();
                String value = entrySet.getValue();
                database.getCircleMemberHashMap().put(ConvertUtils.toLong(key), (CircleMember) JSONUtil.DeSerialize(value, CircleMember.class));
            }
            result = true;
        } catch (Exception ex) {
            _logger.error(ex.getMessage());
            ex.printStackTrace();
        }
        return result;
    }

    public boolean RestoreCurrentLocation() {
        boolean result = false;
        try {
            database.getCurrentLocationHashMap().clear();
            Map<String, String> hgetAll = hgetAll(CurrentLocation.class.getName());
            for (Map.Entry<String, String> entrySet : hgetAll.entrySet()) {
                String key = entrySet.getKey();
                String value = entrySet.getValue();
                database.getCurrentLocationHashMap().put(ConvertUtils.toLong(key), (CurrentLocation) JSONUtil.DeSerialize(value, CurrentLocation.class));
            }
            result = true;
        } catch (Exception ex) {
            _logger.error(ex.getMessage());
            ex.printStackTrace();
        }
        return result;
    }

    public boolean RestoreEndorse() {
        boolean result = false;
        try {
            database.getEndorseHashMap().clear();
            Map<String, String> hgetAll = hgetAll(Endorse.class.getName());
            for (Map.Entry<String, String> entrySet : hgetAll.entrySet()) {
                String key = entrySet.getKey();
                String value = entrySet.getValue();
                database.getEndorseHashMap().put(ConvertUtils.toLong(key), (Endorse) JSONUtil.DeSerialize(value, Endorse.class));
            }
            result = true;
        } catch (Exception ex) {
            _logger.error(ex.getMessage());
            ex.printStackTrace();
        }
        return result;
    }

    public boolean RestoreLinkedLocation() {
        boolean result = false;
        try {
            database.getLinkedLocationHashMap().clear();
            Map<String, String> hgetAll = hgetAll(LinkedLocation.class.getName());
            for (Map.Entry<String, String> entrySet : hgetAll.entrySet()) {
                String key = entrySet.getKey();
                String value = entrySet.getValue();
                database.getLinkedLocationHashMap().put(ConvertUtils.toLong(key), (LinkedLocation) JSONUtil.DeSerialize(value, LinkedLocation.class));
            }
            result = true;
        } catch (Exception ex) {
            _logger.error(ex.getMessage());
            ex.printStackTrace();
        }
        return result;
    }

    public boolean RestoreLocation() {
        boolean result = false;
        try {
            database.getLocationHashMap().clear();
            Map<String, String> hgetAll = hgetAll(Location.class.getName());
            for (Map.Entry<String, String> entrySet : hgetAll.entrySet()) {
                String key = entrySet.getKey();
                String value = entrySet.getValue();
                database.getLocationHashMap().put(ConvertUtils.toLong(key), (Location) JSONUtil.DeSerialize(value, Location.class));
            }
            result = true;
        } catch (Exception ex) {
            _logger.error(ex.getMessage());
            ex.printStackTrace();
        }
        return result;
    }

    public boolean RestoreRating() {
        boolean result = false;
        try {
            database.getRatingHashMap().clear();
            Map<String, String> hgetAll = hgetAll(Rating.class.getName());
            for (Map.Entry<String, String> entrySet : hgetAll.entrySet()) {
                String key = entrySet.getKey();
                String value = entrySet.getValue();
                database.getRatingHashMap().put(ConvertUtils.toLong(key), (Rating) JSONUtil.DeSerialize(value, Rating.class));
            }
            result = true;
        } catch (Exception ex) {
            _logger.error(ex.getMessage());
            ex.printStackTrace();
        }
        return result;
    }

    public boolean RestoreRequestMakeTrip() {
        boolean result = false;
        try {
            database.getRequestMakeTripHashMap().clear();
            Map<String, String> hgetAll = hgetAll(RequestMakeTrip.class.getName());
            for (Map.Entry<String, String> entrySet : hgetAll.entrySet()) {
                String key = entrySet.getKey();
                String value = entrySet.getValue();
                database.getRequestMakeTripHashMap().put(ConvertUtils.toLong(key), (RequestMakeTrip) JSONUtil.DeSerialize(value, RequestMakeTrip.class));
            }
            result = true;
        } catch (Exception ex) {
            _logger.error(ex.getMessage());
            ex.printStackTrace();
        }
        return result;
    }

    public boolean RestoreRoute() {
        boolean result = false;
        try {
            database.getRouteHashMap().clear();
            Map<String, String> hgetAll = hgetAll(Route.class.getName());
            for (Map.Entry<String, String> entrySet : hgetAll.entrySet()) {
                String key = entrySet.getKey();
                String value = entrySet.getValue();
                database.getRouteHashMap().put(ConvertUtils.toLong(key), (Route) JSONUtil.DeSerialize(value, Route.class));
            }
            result = true;
        } catch (Exception ex) {
            _logger.error(ex.getMessage());
            ex.printStackTrace();
        }
        return result;
    }

    public boolean RestoreTrip() {
        boolean result = false;
        try {
            database.getTripHashMap().clear();
            Map<String, String> hgetAll = hgetAll(Trip.class.getName());
            for (Map.Entry<String, String> entrySet : hgetAll.entrySet()) {
                String key = entrySet.getKey();
                String value = entrySet.getValue();
                database.getTripHashMap().put(ConvertUtils.toLong(key), (Trip) JSONUtil.DeSerialize(value, Trip.class));
            }
            result = true;
        } catch (Exception ex) {
            _logger.error(ex.getMessage());
            ex.printStackTrace();
        }
        return result;
    }

    public boolean RestoreTripFeedback() {
        boolean result = false;
        try {
            database.getTripFeedbackHashMap().clear();
            Map<String, String> hgetAll = hgetAll(TripFeedback.class.getName());
            for (Map.Entry<String, String> entrySet : hgetAll.entrySet()) {
                String key = entrySet.getKey();
                String value = entrySet.getValue();
                database.getTripFeedbackHashMap().put(ConvertUtils.toLong(key), (TripFeedback) JSONUtil.DeSerialize(value, TripFeedback.class));
            }
            result = true;
        } catch (Exception ex) {
            _logger.error(ex.getMessage());
            ex.printStackTrace();
        }
        return result;
    }

    public boolean RestoreUser() {
        boolean result = false;
        try {
            database.getUserHashMap().clear();
            Map<String, String> hgetAll = hgetAll(User.class.getName());
            for (Map.Entry<String, String> entrySet : hgetAll.entrySet()) {
                String key = entrySet.getKey();
                String value = entrySet.getValue();
                database.getUserHashMap().put(key, (User) JSONUtil.DeSerialize(value, User.class));
            }
            result = true;
        } catch (Exception ex) {
            _logger.error(ex.getMessage());
            ex.printStackTrace();
        }
        return result;
    }

    public boolean RestoreUserProfile() {
        boolean result = false;
        try {
            database.getUserProfileHashMap().clear();
            Map<String, String> hgetAll = hgetAll(UserProfile.class.getName());
            for (Map.Entry<String, String> entrySet : hgetAll.entrySet()) {
                String key = entrySet.getKey();
                String value = entrySet.getValue();
                database.getUserProfileHashMap().put(ConvertUtils.toLong(key), (UserProfile) JSONUtil.DeSerialize(value, UserProfile.class));
            }
            result = true;
        } catch (Exception ex) {
            _logger.error(ex.getMessage());
            ex.printStackTrace();
        }
        return result;
    }

    public boolean RestoreVerifiedCertificate() {
        boolean result = false;
        try {
            database.getVerifiedCertificateHashMap().clear();
            Map<String, String> hgetAll = hgetAll(VerifiedCertificate.class.getName());
            for (Map.Entry<String, String> entrySet : hgetAll.entrySet()) {
                String key = entrySet.getKey();
                String value = entrySet.getValue();
                database.getVerifiedCertificateHashMap().put(ConvertUtils.toLong(key), (VerifiedCertificate) JSONUtil.DeSerialize(value, VerifiedCertificate.class));
            }
            result = true;
        } catch (Exception ex) {
            _logger.error(ex.getMessage());
            ex.printStackTrace();
        }
        return result;
    }

    public boolean RestoreDatabase() {
        boolean result = false;
        result = RestoreActivity();
        result &= RestoreBroadcast();
        result &= RestoreCircle();
        result &= RestoreCircleMember();
        result &= RestoreCurrentLocation();
        result &= RestoreEndorse();
        result &= RestoreLinkedLocation();
        result &= RestoreLocation();
        result &= RestoreRating();
        result &= RestoreRequestMakeTrip();
        result &= RestoreRoute();
        result &= RestoreTrip();
        result &= RestoreTripFeedback();
        result &= RestoreUser();
        result &= RestoreUserProfile();
        result &= RestoreVerifiedCertificate();
        return result;
    }

//</editor-fold>
}
