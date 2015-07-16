package com.bikiegang.ridesharing.dao;

import com.bikiegang.ridesharing.annn.framework.gearman.GClientManager;
import com.bikiegang.ridesharing.annn.framework.gearman.JobEnt;
import com.bikiegang.ridesharing.annn.framework.util.JSONUtil;
import com.bikiegang.ridesharing.cache.RideSharingCA;
import com.bikiegang.ridesharing.config.ConfigInfo;
import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.pojo.User;
import com.bikiegang.ridesharing.utilities.Const;
import org.apache.log4j.Logger;

/**
 * Created by hpduy17 on 6/26/15.
 */
public class UserDao {

    Logger logger = Logger.getLogger(this.getClass());
    private Database database = Database.getInstance();
    RideSharingCA cache = RideSharingCA.getInstance(ConfigInfo.REDIS_SERVER);

    public boolean insert(User obj) {
        boolean result = false;
        try {
            if (obj == null) {
                return false;
            }
            //Step 1: put in hashmap
            database.getUserHashMap().put(obj.getId(), obj);
            database.getFacebookRFUserId().put(obj.getFacebookId(), obj.getId());
            database.getGoogleRFUserId().put(obj.getGoogleId(), obj.getId());
            database.getTwitterRFUserId().put(obj.getTwitterId(), obj.getId());
            database.getEmailRFUserId().put(obj.getEmail(), obj.getId());
            database.getLinkedInRFUserId().put(obj.getLinkedInId(), obj.getId());

            //Step 2: put redis
            result = cache.hset(obj.getClass().getName(), String.valueOf(obj.getId()), JSONUtil.Serialize(obj));
            result &= cache.hset(obj.getClass().getName() + ":facebook", String.valueOf(obj.getFacebookId()), obj.getId());
            result &= cache.hset(obj.getClass().getName() + ":google", String.valueOf(obj.getGoogleId()), obj.getId());
            result &= cache.hset(obj.getClass().getName() + ":twitter", String.valueOf(obj.getTwitterId()), obj.getId());
            result &= cache.hset(obj.getClass().getName() + ":email", String.valueOf(obj.getEmail()), obj.getId());
            result &= cache.hset(obj.getClass().getName() + ":linked", String.valueOf(obj.getLinkedInId()), obj.getId());

            if (result) {
                //Step 3: put job gearman
                short actionType = Const.RideSharing.ActionType.INSERT;
                JobEnt job = new JobEnt(0l, 0l, obj.getClass().getName(), actionType,
                        System.currentTimeMillis(), "", JSONUtil.Serialize(obj), "", "");
                result &= GClientManager.getInstance(ConfigInfo.RIDESHARING_WORKER_GEARMAN).push(job);
                if (!result) {
                    logger.error(String.format("Can't not insert DB with value=%s", obj));
                } else {
                    logger.info(String.format("Insert user success with value=%s",
                            JSONUtil.Serialize(obj)));
                }
            } else {
                logger.error(String.format("Can't not insert redis with key=%s, field=%s, value=%s",
                        obj.getClass().getName(), String.valueOf(obj.getId()), JSONUtil.Serialize(obj)));
            }
            result = true;
        } catch (Exception ex) {

            logger.error(ex.getStackTrace());
            ex.printStackTrace();
        }
        return result;
    }

    public boolean delete(User obj) {
        boolean result = false;
        try {
            if (obj == null) {
                return false;
            }
            //Step 1: put in hashmap
            database.getUserHashMap().remove(obj.getId());
            database.getFacebookRFUserId().remove(obj.getFacebookId());
            database.getGoogleRFUserId().remove(obj.getGoogleId());
            database.getTwitterRFUserId().remove(obj.getTwitterId());
            database.getEmailRFUserId().remove(obj.getEmail());
            database.getLinkedInRFUserId().remove(obj.getLinkedInId());

            //Step 2: put redis
            result = cache.hdel(obj.getClass().getName(), String.valueOf(obj.getId()));
            result &= cache.hdel(obj.getClass().getName() + ":facebook", obj.getFacebookId());
            result &= cache.hdel(obj.getClass().getName() + ":google", obj.getGoogleId());
            result &= cache.hdel(obj.getClass().getName() + ":twitter", obj.getTwitterId());
            result &= cache.hdel(obj.getClass().getName() + ":email", obj.getEmail());
            result &= cache.hdel(obj.getClass().getName() + ":linked", obj.getLinkedInId());

            if (result) {
                //Step 3: put job gearman
                short actionType = Const.RideSharing.ActionType.DELETE;
                JobEnt job = new JobEnt(0l, 0l, obj.getClass().getName(), actionType,
                        System.currentTimeMillis(), "", JSONUtil.Serialize(obj), "", "");
                result &= GClientManager.getInstance(ConfigInfo.RIDESHARING_WORKER_GEARMAN).push(job);
                if (!result) {
                    logger.error(String.format("Can't not delete DB with value=%s", obj));
                } else {
                    logger.info(String.format("Remove user success with value=%s",
                            JSONUtil.Serialize(obj)));
                }
            } else {
                logger.error(String.format("Can't not delete redis with key=%s, field=%s, value=%s",
                        obj.getClass().getName(), String.valueOf(obj.getId()), JSONUtil.Serialize(obj)));
            }
            result = true;
        } catch (Exception ex) {

            logger.error(ex.getStackTrace());
            ex.printStackTrace();
        }
        return result;
    }

    public boolean update(User obj) {
        boolean result = false;
        try {
            if (obj == null) {
                return false;
            }
            //Step 1: put in hashmap
            database.getUserHashMap().put(obj.getId(), obj);

            //Step 2: put redis
            result = cache.hset(obj.getClass().getName(), String.valueOf(obj.getId()), JSONUtil.Serialize(obj));

            if (result) {
                //Step 3: put job gearman
                short actionType = Const.RideSharing.ActionType.UPDATE;
                JobEnt job = new JobEnt(0l, 0l, obj.getClass().getName(), actionType,
                        System.currentTimeMillis(), "", JSONUtil.Serialize(obj), "", "");
                result &= GClientManager.getInstance(ConfigInfo.RIDESHARING_WORKER_GEARMAN).push(job);
                if (!result) {
                    logger.error(String.format("Can't not update DB with value=%s", obj));
                } else {
                    logger.info(String.format("Update user success with value=%s",
                            JSONUtil.Serialize(obj)));
                }
            } else {
                logger.error(String.format("Can't not update redis with key=%s, field=%s, value=%s",
                        obj.getClass().getName(), String.valueOf(obj.getId()), JSONUtil.Serialize(obj)));
            }
            result = true;
        } catch (Exception ex) {

            logger.error(ex.getStackTrace());
            ex.printStackTrace();
        }
        return result;
    }

}
