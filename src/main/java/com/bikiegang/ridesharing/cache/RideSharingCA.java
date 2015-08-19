/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bikiegang.ridesharing.cache;

import com.bikiegang.ridesharing.annn.framework.common.Config;
import com.bikiegang.ridesharing.annn.framework.common.LogUtil;
import com.bikiegang.ridesharing.annn.framework.memcache.RedisClient;
import com.bikiegang.ridesharing.annn.framework.util.ConvertUtils;
import com.bikiegang.ridesharing.annn.framework.util.JSONUtil;
import com.bikiegang.ridesharing.config.ConfigInfo;
import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.geocoding.GeoCell;
import com.bikiegang.ridesharing.pojo.*;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.Arrays;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
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
    private static final Logger _logger = LogUtil.getLogger(RideSharingCA.class);
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

    public boolean hdel(String key, String field) throws Exception {
        boolean result = false;
        try {
            redisClient.hdel(buildKeyWithPrefix(key), new String[]{field});
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

    public boolean lpush(String key, String value) {
        try {
            redisClient.lpush(buildKeyWithPrefix(key), new String[]{value});
        } catch (Exception ex) {
            _logger.error(ex.getMessage(), ex);
        }
        return true;
    }

    public boolean lrem(String key, String value) {
        try {
            redisClient.lrem(buildKeyWithPrefix(key), 1, value);
        } catch (Exception ex) {
            _logger.error(ex.getMessage(), ex);
        }
        return true;
    }

    public List<String> lrange(String key) {
        List<String> lrange = new ArrayList<>();
        try {
            lrange = redisClient.lrange(buildKeyWithPrefix(key), 0, redisClient.llen(buildKeyWithPrefix(key)));
        } catch (Exception ex) {
            _logger.error(ex.getMessage(), ex);
        }
        return lrange;
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Restore function">
    Database database = Database.getInstance();

    public boolean RestoreAngelGroup() {
        boolean result = false;
        try {
            database.getAngelGroupHashMap().clear();
            Map<String, String> hgetAll = hgetAll(AngelGroup.class.getName());
            for (Map.Entry<String, String> entrySet : hgetAll.entrySet()) {
                String key = entrySet.getKey();
                String value = entrySet.getValue();

                database.getAngelGroupHashMap().put(ConvertUtils.toLong(key),
                        (AngelGroup) JSONUtil.DeSerialize(value, AngelGroup.class));
            }
            result = true;
        } catch (Exception ex) {
            _logger.error(ex.getMessage());
            ex.printStackTrace();
        }
        return result;
    }

    public boolean RestoreAngelMemberGroup() {
        boolean result = false;
        try {
            database.getAngelGroupMemberHashMap().clear();
            database.getUserIdRFAngelGroups().clear();
            database.getAngelGroupIdRFUsers().clear();
            database.getUserAndGroupRFAngelGroupMember().clear();

            //cache.hset(obj.getClass().getName(), String.valueOf(obj.getId()), JSONUtil.Serialize(obj));
            Map<String, String> hgetAll = hgetAll(AngelGroupMember.class.getName());
            for (Map.Entry<String, String> entrySet : hgetAll.entrySet()) {
                String key = entrySet.getKey();
                String value = entrySet.getValue();

                database.getAngelGroupMemberHashMap().put(ConvertUtils.toLong(key),
                        (AngelGroupMember) JSONUtil.DeSerialize(value, AngelGroupMember.class));
            }

            hgetAll = hgetAll(AngelGroupMember.class.getName() + ":user");
            for (Map.Entry<String, String> entrySet : hgetAll.entrySet()) {
                String key = entrySet.getKey();
                String value = entrySet.getValue();

                database.getUserIdRFAngelGroups().put(key, (HashSet<Long>) JSONUtil.DeSerialize(value, new TypeToken<HashSet<Long>>() {
                }.getType()));
            }
            hgetAll = hgetAll(AngelGroupMember.class.getName() + ":angelgroup");
            for (Map.Entry<String, String> entrySet : hgetAll.entrySet()) {
                String key = entrySet.getKey();
                String value = entrySet.getValue();

                database.getAngelGroupIdRFUsers().put(ConvertUtils.toLong(key), (HashSet<String>) JSONUtil.DeSerialize(value, new TypeToken<HashSet<String>>() {
                }.getType()));
            }
            hgetAll = hgetAll(AngelGroupMember.class.getName() + ":userandgroup");
            for (Map.Entry<String, String> entrySet : hgetAll.entrySet()) {
                String key = entrySet.getKey();
                String value = entrySet.getValue();

                database.getUserAndGroupRFAngelGroupMember().put(key, ConvertUtils.toLong(value));
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
            database.getUserIdRFBroadcasts().clear();

            Map<String, String> hgetAll = hgetAll(Broadcast.class.getName());
            for (Map.Entry<String, String> entrySet : hgetAll.entrySet()) {
                String key = entrySet.getKey();
                String value = entrySet.getValue();
                database.getBroadcastHashMap().put(key, (Broadcast) JSONUtil.DeSerialize(value, Broadcast.class));
            }

            //User  ==> Broadcast
            Map<String, String> userRF = hgetAll(Broadcast.class.getName() + ":user");
            for (Map.Entry<String, String> entrySet : userRF.entrySet()) {
                String key = entrySet.getKey();
                String value = entrySet.getValue();

                database.getUserIdRFBroadcasts().put(key, (HashSet<String>) JSONUtil.DeSerialize(value,
                        new TypeToken<HashSet<String>>() {
                        }.getType()));
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
            database.getPlannedTripIdRFLinkedLocations().clear();
            Map<String, String> hgetAll = hgetAll(LinkedLocation.class.getName());
            for (Map.Entry<String, String> entrySet : hgetAll.entrySet()) {
                String key = entrySet.getKey();
                String value = entrySet.getValue();
                LinkedLocation obj = (LinkedLocation) JSONUtil.DeSerialize(value, LinkedLocation.class);
                database.getLinkedLocationHashMap().put(ConvertUtils.toLong(key), obj);
                // more (put to geocell)
                if (obj.getRefType() == LinkedLocation.IN_PLANNED_TRIP) {
                    PlannedTrip pt = database.getPlannedTripHashMap().get(obj.getRefId());
                    GeoCell geoCell = null;
                    if (pt.getRole() == User.DRIVER) {
                        geoCell = database.getGeoCellDriver();
                    }
                    if (pt.getRole() == User.PASSENGER) {
                        geoCell = database.getGeoCellPassenger();
                    }
                    if (null != geoCell) {
                        geoCell.putToCell(obj, obj.getId());
                    }
                }
            }

            //plannedTrip => linklocation
            Map<String, String> plannedTripRF = hgetAll(LinkedLocation.class.getName() + ":plannedtrip");
            for (Map.Entry<String, String> entrySet : plannedTripRF.entrySet()) {
                String key = entrySet.getKey();
                String value = entrySet.getValue();

                database.getPlannedTripIdRFLinkedLocations().put(ConvertUtils.toLong(key), (List<Long>) JSONUtil.DeSerialize(value, new TypeToken<List<Long>>() {
                }.getType()));
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
            database.getSenderRequestsBox().clear();
            database.getReceiverRequestsBox().clear();

            Map<String, String> hgetAll = hgetAll(RequestMakeTrip.class.getName());
            for (Map.Entry<String, String> entrySet : hgetAll.entrySet()) {
                String key = entrySet.getKey();
                String value = entrySet.getValue();
                database.getRequestMakeTripHashMap().put(ConvertUtils.toLong(key), (RequestMakeTrip) JSONUtil.DeSerialize(value, RequestMakeTrip.class));
            }

            //SenderRequestsBox
            Map<String, String> senderRF = hgetAll(RequestMakeTrip.class.getName() + ":sender");
            for (Map.Entry<String, String> entrySet : senderRF.entrySet()) {
                String key = entrySet.getKey();
                String value = entrySet.getValue();

                database.getSenderRequestsBox().put(key, (HashMap<Long, Long>) JSONUtil.DeSerialize(value, new TypeToken<HashMap<Long, Long>>() {
                }.getType()));
            }

            //ReceiverRequestsBox
            Map<String, String> receiverRF = hgetAll(RequestMakeTrip.class.getName() + ":receiver");
            for (Map.Entry<String, String> entrySet : receiverRF.entrySet()) {
                String key = entrySet.getKey();
                String value = entrySet.getValue();

                database.getReceiverRequestsBox().put(key, (HashMap<Long, List<Long>>) JSONUtil.DeSerialize(value, new TypeToken<HashMap<Long, List<Long>>>() {
                }.getType()));
            }
            result = true;
        } catch (Exception ex) {
            _logger.error(ex.getMessage());
            ex.printStackTrace();
        }
        return result;
    }

    public boolean RestorePlannedTrip() {
        boolean result = false;
        try {

            database.getPlannedTripHashMap().clear();
            database.getUserIdRFPlanedTrips().clear();
            database.getRoleRFPlannedTrips().clear();
            database.getGroupIdRFPlannedTrips().clear();

            Map<String, String> hgetAll = hgetAll(PlannedTrip.class.getName());
            for (Map.Entry<String, String> entrySet : hgetAll.entrySet()) {
                String key = entrySet.getKey();
                String value = entrySet.getValue();
                PlannedTrip obj = (PlannedTrip) JSONUtil.DeSerialize(value, PlannedTrip.class);
                database.getPlannedTripHashMap().put(ConvertUtils.toLong(key), obj);
                database.getGeoCellStartLocation().putToCell(obj.getStartLocation(), obj.getId());
            }

            //User=>plannedtrip
            Map<String, String> userRF = hgetAll(PlannedTrip.class.getName() + ":user");
            for (Map.Entry<String, String> entrySet : userRF.entrySet()) {
                String key = entrySet.getKey();
                String value = entrySet.getValue();

                database.getUserIdRFPlanedTrips().put(key, (HashSet<Long>) JSONUtil.DeSerialize(value, new TypeToken<HashSet<Long>>() {
                }.getType()));
            }
            //Role=>plannedTrip
            Map<String, String> roleRF = hgetAll(PlannedTrip.class.getName() + ":role");
            for (Map.Entry<String, String> entrySet : roleRF.entrySet()) {
                String key = entrySet.getKey();
                String value = entrySet.getValue();

                database.getRoleRFPlannedTrips().put(ConvertUtils.toInt(key), (HashSet<Long>) JSONUtil.DeSerialize(value, new TypeToken<HashSet<Long>>() {
                }.getType()));
            }
            //Group=>plannedTrip
            Map<String, String> groupRF = hgetAll(PlannedTrip.class.getName() + ":group");
            for (Map.Entry<String, String> entrySet : groupRF.entrySet()) {
                String key = entrySet.getKey();
                String value = entrySet.getValue();

                database.getGroupIdRFPlannedTrips().put(ConvertUtils.toLong(key), (HashSet<Long>) JSONUtil.DeSerialize(value, new TypeToken<HashSet<Long>>() {
                }.getType()));
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
            database.getDriverIdRFTrips().clear();
            database.getPassengerIdRFTrips().clear();

            Map<String, String> hgetAll = hgetAll(Trip.class.getName());
            for (Map.Entry<String, String> entrySet : hgetAll.entrySet()) {
                String key = entrySet.getKey();
                String value = entrySet.getValue();
                database.getTripHashMap().put(ConvertUtils.toLong(key), (Trip) JSONUtil.DeSerialize(value, Trip.class));
            }

            //Driver
            Map<String, String> driverRF = hgetAll(Trip.class.getName() + ":driver");
            for (Map.Entry<String, String> entrySet : driverRF.entrySet()) {
                String key = entrySet.getKey();
                String value = entrySet.getValue();

                database.getDriverIdRFTrips().put(key, (HashSet<Long>) JSONUtil.DeSerialize(value, new TypeToken<HashSet<Long>>() {
                }.getType()));
            }
            //Passenger
            Map<String, String> passengerRF = hgetAll(Trip.class.getName() + ":passenger");
            for (Map.Entry<String, String> entrySet : passengerRF.entrySet()) {
                String key = entrySet.getKey();
                String value = entrySet.getValue();

                database.getPassengerIdRFTrips().put(key, (HashSet<Long>) JSONUtil.DeSerialize(value, new TypeToken<HashSet<Long>>() {
                }.getType()));
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
            //Clear data
            database.getUserHashMap().clear();
            database.getFacebookRFUserId().clear();
            database.getGoogleRFUserId().clear();
            database.getTwitterRFUserId().clear();
            database.getEmailRFUserId().clear();
            database.getLinkedInRFUserId().clear();

            //Normal
            Map<String, String> hgetAll = hgetAll(User.class.getName());
            for (Map.Entry<String, String> entrySet : hgetAll.entrySet()) {
                String key = entrySet.getKey();
                String value = entrySet.getValue();
                database.getUserHashMap().put(key, (User) JSONUtil.DeSerialize(value, User.class));
            }
            //Facebook
            Map<String, String> facebookFR = hgetAll(User.class.getName() + ":facebook");
            database.setFacebookRFUserId((HashMap<String, String>) facebookFR);
            //Google
            Map<String, String> googleFR = hgetAll(User.class.getName() + ":google");
            database.setGoogleRFUserId((HashMap<String, String>) googleFR);
            //Mail
            Map<String, String> mailFR = hgetAll(User.class.getName() + ":email");
            database.setEmailRFUserId((HashMap<String, String>) mailFR);
            //Twitter
            Map<String, String> twitterFR = hgetAll(User.class.getName() + ":twitter");
            database.setTwitterRFUserId((HashMap<String, String>) twitterFR);
            //Linked
            Map<String, String> linkedFR = hgetAll(User.class.getName() + ":linked");
            database.setLinkedInRFUserId((HashMap<String, String>) linkedFR);
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
            database.getUserIdRFCertificates().clear();

            Map<String, String> hgetAll = hgetAll(VerifiedCertificate.class.getName());
            for (Map.Entry<String, String> entrySet : hgetAll.entrySet()) {
                String key = entrySet.getKey();
                String value = entrySet.getValue();
                database.getVerifiedCertificateHashMap().put(ConvertUtils.toLong(key), (VerifiedCertificate) JSONUtil.DeSerialize(value,
                        VerifiedCertificate.class));
            }

            //User=>Certificates
            Map<String, String> userRF = hgetAll(VerifiedCertificate.class.getName() + ":user");
            for (Map.Entry<String, String> entrySet : userRF.entrySet()) {
                String key = entrySet.getKey();
                String value = entrySet.getValue();

                database.getUserIdRFCertificates().put(key, (HashSet<Long>) JSONUtil.DeSerialize(value, new TypeToken<HashSet<Long>>() {
                }.getType()));
            }
            result = true;
        } catch (Exception ex) {
            _logger.error(ex.getMessage());
            ex.printStackTrace();
        }
        return result;
    }

    public boolean RestoreRequestVerify() {
        boolean result = false;
        try {
            database.getRequestVerifyHashMap().clear();
            database.getUserRequestBox().clear();
            database.getAngelRequestsBox().clear();

            Map<String, String> hgetAll = hgetAll(RequestVerify.class.getName());
            for (Map.Entry<String, String> entrySet : hgetAll.entrySet()) {
                String key = entrySet.getKey();
                String value = entrySet.getValue();
                database.getRequestVerifyHashMap().put(ConvertUtils.toLong(key), (RequestVerify) JSONUtil.DeSerialize(value, RequestVerify.class));
            }

            //User=>RequestVerify
            Map<String, String> userRF = hgetAll(RequestVerify.class.getName() + ":user");
            for (Map.Entry<String, String> entrySet : userRF.entrySet()) {
                String key = entrySet.getKey();
                String value = entrySet.getValue();

                database.getUserRequestBox().put(key, ConvertUtils.toLong(value));
            }
            //Angel=>RequestVerify
            Map<String, String> angelRF = hgetAll(RequestVerify.class.getName() + ":angel");
            for (Map.Entry<String, String> entrySet : angelRF.entrySet()) {
                String key = entrySet.getKey();
                String value = entrySet.getValue();

                database.getAngelRequestsBox().put(key, (List<Long>) JSONUtil.DeSerialize(value, new TypeToken<List<Long>>() {
                }.getType()));
            }
            result = true;
        } catch (Exception ex) {
            _logger.error(ex.getMessage());
            ex.printStackTrace();
        }
        return result;
    }

    public boolean RestoreFeed() {
        boolean result = false;
        try {
            database.getFeedHashMap().clear();

            Map<String, String> hgetAll = hgetAll(Feed.class.getName());
            for (Map.Entry<String, String> entrySet : hgetAll.entrySet()) {
                String key = entrySet.getKey();
                String value = entrySet.getValue();
                database.getFeedHashMap().put(ConvertUtils.toLong(key), (Feed) JSONUtil.DeSerialize(value, Feed.class));
            }
            result = true;
        } catch (Exception ex) {
            _logger.error(ex.getMessage());
            ex.printStackTrace();
        }
        return result;
    }

    public boolean RestorePopularLocation() {
        boolean result = false;
        try {
            database.getPopularLocationHashMap().clear();
            database.getOrderedPopularLocation().clear();

            Map<String, String> hgetAll = hgetAll(PopularLocation.class.getName());
            for (Map.Entry<String, String> entrySet : hgetAll.entrySet()) {
                String key = entrySet.getKey();
                String value = entrySet.getValue();
                database.getPopularLocationHashMap().put(ConvertUtils.toLong(key),
                        (PopularLocation) JSONUtil.DeSerialize(value, PopularLocation.class));
            }
            database.setOrderedPopularLocation(ConvertUtils.convertListString(lrange(PopularLocation.class.getName() + ":ordered")));
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

    public boolean RestoreSocialTrip() {
        boolean result = false;
        try {
            database.getSocialTripHashMap().clear();
            database.getUserIdRFSocialTrips().clear();

            Map<String, String> hgetAll = hgetAll(SocialTrip.class.getName());
            for (Map.Entry<String, String> entrySet : hgetAll.entrySet()) {
                String key = entrySet.getKey();
                String value = entrySet.getValue();
                database.getSocialTripHashMap().put(ConvertUtils.toLong(key), (SocialTrip) JSONUtil.DeSerialize(value, SocialTrip.class));
            }
            hgetAll = hgetAll(SocialTrip.class.getName() + ":user");
            for (Map.Entry<String, String> entrySet : hgetAll.entrySet()) {
                String key = entrySet.getKey();
                String value = entrySet.getValue();
                database.getUserIdRFSocialTrips().put(key,
                        (HashSet<Long>) JSONUtil.DeSerialize(value, new TypeToken<HashSet<Long>>() {
                        }.getType()));

            }
            result = true;
        } catch (Exception ex) {
            _logger.error(ex.getMessage());
            ex.printStackTrace();
        }
        return result;
    }

    public boolean RestoreSocialTripAttendance() {
        boolean result = false;
        try {
            database.getSocialTripAttendanceHashMap().clear();

            Map<String, String> hgetAll = hgetAll(SocialTripAttendance.class.getName());
            for (Map.Entry<String, String> entrySet : hgetAll.entrySet()) {
                String key = entrySet.getKey();
                String value = entrySet.getValue();
                database.getSocialTripAttendanceHashMap().put(ConvertUtils.toLong(key), (SocialTripAttendance) JSONUtil.DeSerialize(value, SocialTripAttendance.class));
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
        result = RestoreBroadcast();
        result &= RestoreLinkedLocation();
        result &= RestoreRequestMakeTrip();
        result &= RestorePlannedTrip();
        result &= RestoreTrip();
        result &= RestoreUser();
        result &= RestoreVerifiedCertificate();
        result &= RestoreRequestVerify();
        result &= RestoreAngelGroup();
        result &= RestoreAngelMemberGroup();

        result &= RestoreFeed();
        result &= RestorePopularLocation();
        result &= RestoreRating();
        result &= RestoreSocialTrip();
        result &= RestoreSocialTripAttendance();

        return result;
    }

//</editor-fold>
}
