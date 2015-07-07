/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bikiegang.ridesharing.cache;

import com.bikiegang.ridesharing.annn.framework.common.Config;
import com.bikiegang.ridesharing.annn.framework.memcache.RedisClient;
import com.bikiegang.ridesharing.config.ConfigInfo;
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
}
