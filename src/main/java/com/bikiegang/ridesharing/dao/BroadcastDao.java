package com.bikiegang.ridesharing.dao;

import com.bikiegang.ridesharing.annn.framework.common.LogUtil;
import com.bikiegang.ridesharing.annn.framework.gearman.GClientManager;
import com.bikiegang.ridesharing.annn.framework.gearman.JobEnt;
import com.bikiegang.ridesharing.annn.framework.util.JSONUtil;
import com.bikiegang.ridesharing.cache.RideSharingCA;
import com.bikiegang.ridesharing.config.ConfigInfo;
import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.pojo.Broadcast;
import com.bikiegang.ridesharing.utilities.Const;
import com.bikiegang.ridesharing.utilities.RequestLogger;
import org.apache.log4j.Logger;

import java.util.HashSet;

/**
 * Created by hpduy17 on 6/26/15.
 */
public class BroadcastDao {

    Logger logger = LogUtil.getLogger(this.getClass());
    private Database database = Database.getInstance();
    RideSharingCA cache = RideSharingCA.getInstance(ConfigInfo.REDIS_SERVER);

    public boolean insert(Broadcast obj) {
        boolean result = false;
        try {
            if (obj == null) {
                return false;
            }
            RequestLogger.getInstance().info(obj, (short) Const.RideSharing.ActionType.INSERT);
            //Step 1: put in hashmap
            database.getBroadcastHashMap().put(obj.getId(), obj);
            // userIdRFBroadcasts = new HashMap<>(); //<userId#type,<broadcastId>>
            HashSet<String> broadcastIds = database.getUserIdRFBroadcasts().get(obj.getUserId());
            if (broadcastIds == null) {
                broadcastIds = new HashSet<>();
                database.getUserIdRFBroadcasts().put(obj.getUserId() + "#" + obj.getType(), broadcastIds);
            }
            broadcastIds.add(obj.getId());

            //Step 2: put redis
            result = cache.hset(obj.getClass().getName(), String.valueOf(obj.getId()), JSONUtil.Serialize(obj));
            result &= cache.hset(obj.getClass().getName() + ":user", obj.getUserId() + "#" + obj.getType(), JSONUtil.Serialize(broadcastIds));
            if (result) {
                //Step 3: put job gearman
                short actionType = Const.RideSharing.ActionType.INSERT;
                JobEnt job = new JobEnt(0l, 0l, obj.getClass().getName(), actionType,
                        System.currentTimeMillis(), "", JSONUtil.Serialize(obj), "", "");
                result &= GClientManager.getInstance(ConfigInfo.RIDESHARING_WORKER_GEARMAN).push(job);
                if (!result) {
                    logger.error(String.format("Can't not insert DB with value=%s", obj));
                } else {
                    logger.info(String.format("Insert Broadcast success with value=%s", JSONUtil.Serialize(obj)));
                }
            } else {
                logger.error(String.format("Can't not insert redis with key=%s, field=%s, value=%s",
                        obj.getClass().getName(), String.valueOf(obj.getId()), JSONUtil.Serialize(obj)));
            }
        } catch (Exception ex) {

            logger.error(ex.getStackTrace());
            ex.printStackTrace();
        }
        return result;
    }

    public boolean delete(Broadcast obj) {
        boolean result = false;
        try {
            if (obj == null) {
                return false;
            }
            RequestLogger.getInstance().info(obj, (short) Const.RideSharing.ActionType.DELETE);
            //Step 1: remove in hashmap
            database.getBroadcastHashMap().remove(obj.getId());
            // userIdRFBroadcasts = new HashMap<>(); //<userId,<broadcastId>>
            HashSet<String> broadcastIds = database.getUserIdRFBroadcasts().get(obj.getUserId());
            if (broadcastIds == null) {
                broadcastIds = new HashSet<>();
                database.getUserIdRFBroadcasts().put(obj.getUserId() + "#" + obj.getType(), broadcastIds);
            }
            broadcastIds.remove(obj.getId());

            //Step 2: remove redis
            result = cache.hdel(obj.getClass().getName(), obj.getId());
            result &= cache.hset(obj.getClass().getName() + ":user",
                    obj.getUserId() + "#" + obj.getType(), JSONUtil.Serialize(broadcastIds));

            if (result) {
                //Step 3: put job gearman
                short actionType = Const.RideSharing.ActionType.DELETE;
                JobEnt job = new JobEnt(0l, 0l, obj.getClass().getName(), actionType,
                        System.currentTimeMillis(), "", JSONUtil.Serialize(obj), "", "");
                result &= GClientManager.getInstance(ConfigInfo.RIDESHARING_WORKER_GEARMAN).push(job);
                if (!result) {
                    logger.error(String.format("Can't not delete DB with value=%s", obj));
                } else {
                    logger.info(String.format("Delete Broadcast success with value=%s", JSONUtil.Serialize(obj)));
                }
            } else {
                logger.error(String.format("Can't not delete redis with key=%s, field=%s, value=%s",
                        obj.getClass().getName(), String.valueOf(obj.getId()), JSONUtil.Serialize(obj)));
            }
        } catch (Exception ex) {

            logger.error(ex.getStackTrace());
            ex.printStackTrace();
        }
        return result;
    }

    public boolean update(Broadcast obj) {
        boolean result = false;
        try {
            if (obj == null) {
                return false;
            }
            RequestLogger.getInstance().info(obj, (short) Const.RideSharing.ActionType.UPDATE);
            //Step 1: Update in hashmap
            database.getBroadcastHashMap().put(obj.getId(), obj);

            //Step 2: Update redis
            result = cache.hset(obj.getClass().getName(), String.valueOf(obj.getId()), JSONUtil.Serialize(obj));
            if (result) {
                //Step 3: put job gearman
                short actionType = Const.RideSharing.ActionType.UPDATE;
                JobEnt job = new JobEnt(0l, 0l, obj.getClass().getName(), actionType,
                        System.currentTimeMillis(), "", JSONUtil.Serialize(obj), "", "");
                result &= GClientManager.getInstance(ConfigInfo.RIDESHARING_WORKER_GEARMAN).push(job);
                if (!result) {
                    logger.error(String.format("Can't not update DB with value=%s", JSONUtil.Serialize(obj)));
                } else {
                    logger.info(String.format("Update broadcast with value=%s", JSONUtil.Serialize(obj)));
                }
            } else {
                logger.error(String.format("Can't not update redis with key=%s, field=%s, value=%s",
                        obj.getClass().getName(), String.valueOf(obj.getId()), JSONUtil.Serialize(obj)));
            }
        } catch (Exception ex) {

            logger.error(ex.getStackTrace());
            ex.printStackTrace();
        }
        return result;
    }
}
