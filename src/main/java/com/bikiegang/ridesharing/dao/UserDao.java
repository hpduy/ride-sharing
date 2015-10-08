package com.bikiegang.ridesharing.dao;

import com.bikiegang.ridesharing.annn.framework.common.LogUtil;
import com.bikiegang.ridesharing.annn.framework.gearman.GClientManager;
import com.bikiegang.ridesharing.annn.framework.gearman.JobEnt;
import com.bikiegang.ridesharing.annn.framework.util.JSONUtil;
import com.bikiegang.ridesharing.cache.RideSharingCA;
import com.bikiegang.ridesharing.config.ConfigInfo;
import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.pojo.User;
import com.bikiegang.ridesharing.utilities.Const;
import com.bikiegang.ridesharing.utilities.RequestLogger;
import org.apache.log4j.Logger;

import java.util.HashSet;

/**
 * Created by hpduy17 on 6/26/15.
 */
public class UserDao {

    Logger logger = LogUtil.getLogger(this.getClass());
    private Database database = Database.getInstance();
    RideSharingCA cache = RideSharingCA.getInstance(ConfigInfo.REDIS_SERVER);

    public boolean insert(User obj) {
        boolean result = false;
        try {
            if (obj == null) {
                return false;
            }
            RequestLogger.getInstance().info(obj, (short) Const.RideSharing.ActionType.INSERT);

            //Step 1: put in hashmap
            database.getUserHashMap().put(obj.getId(), obj);
            //facebookRFUserId = new HashMap<>();//<fbId, userId>
            database.getFacebookRFUserId().put(obj.getFacebookId(), obj.getId());
            //googleRFUserId = new HashMap<>();//<ggId, userId>
            database.getGoogleRFUserId().put(obj.getGoogleId(), obj.getId());
            //twitterRFUserId = new HashMap<>();//<twId, userId>
            database.getTwitterRFUserId().put(obj.getTwitterId(), obj.getId());
            //emailRFUserId = new HashMap<>();//<email, userId>
            database.getEmailRFUserId().put(obj.getEmail(), obj.getId());
            //linkedInRFUserId = new HashMap<>();//<lkId, userId>
            database.getLinkedInRFUserId().put(obj.getLinkedInId(), obj.getId());
            //organizationRFUserId = new HashMap<>(); //<organizationId, userId>
            HashSet<String> userIds = database.getOrganizationRFUserIds().get(obj.getOrganizationId());
            if (userIds == null) {
                userIds = new HashSet<>();
            }
            userIds.add(obj.getId());

            //Step 2: put redis
            result = cache.hset(obj.getClass().getName(), String.valueOf(obj.getId()), JSONUtil.Serialize(obj));
            result &= cache.hset(obj.getClass().getName() + ":facebook", String.valueOf(obj.getFacebookId()), obj.getId());
            result &= cache.hset(obj.getClass().getName() + ":google", String.valueOf(obj.getGoogleId()), obj.getId());
            result &= cache.hset(obj.getClass().getName() + ":twitter", String.valueOf(obj.getTwitterId()), obj.getId());
            result &= cache.hset(obj.getClass().getName() + ":email", String.valueOf(obj.getEmail()), obj.getId());
            result &= cache.hset(obj.getClass().getName() + ":linked", String.valueOf(obj.getLinkedInId()), obj.getId());
            result &= cache.hset(obj.getClass().getName() + ":organization", String.valueOf(obj.getOrganizationId()), obj.getId());

            if (result) {
                //Step 3: put job gearman
                short actionType = Const.RideSharing.ActionType.INSERT;
                JobEnt job = new JobEnt(0l, 0l, obj.getClass().getName(), actionType,
                        System.currentTimeMillis(), "", JSONUtil.Serialize(obj), "", "");
                result &= GClientManager.getInstance(ConfigInfo.RIDESHARING_WORKER_GEARMAN).push(job);
                if (!result) {
                    logger.error(String.format("Can't not insert DB with value=%s", JSONUtil.Serialize(obj)));
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
            RequestLogger.getInstance().info(obj, (short) Const.RideSharing.ActionType.DELETE);

            //Step 1: put in hashmap
            database.getUserHashMap().remove(obj.getId());
            //facebookRFUserId = new HashMap<>();//<fbId, userId>
            database.getFacebookRFUserId().remove(obj.getFacebookId());
            //googleRFUserId = new HashMap<>();//<ggId, userId>
            database.getGoogleRFUserId().remove(obj.getGoogleId());
            //twitterRFUserId = new HashMap<>();//<twId, userId>
            database.getTwitterRFUserId().remove(obj.getTwitterId());
            //emailRFUserId = new HashMap<>();//<email, userId>
            database.getEmailRFUserId().remove(obj.getEmail());
            //linkedInRFUserId = new HashMap<>();//<lkId, userId>
            database.getLinkedInRFUserId().remove(obj.getLinkedInId());
            //organizationRFUserId = new HashMap<>(); //<organizationId, userId>
            HashSet<String> userIds = database.getOrganizationRFUserIds().get(obj.getOrganizationId());
            if (userIds != null) {
                userIds.remove(obj.getId());
            }

            //Step 2: put redis
            result = cache.hdel(obj.getClass().getName(), String.valueOf(obj.getId()));
            result &= cache.hdel(obj.getClass().getName() + ":facebook", obj.getFacebookId());
            result &= cache.hdel(obj.getClass().getName() + ":google", obj.getGoogleId());
            result &= cache.hdel(obj.getClass().getName() + ":twitter", obj.getTwitterId());
            result &= cache.hdel(obj.getClass().getName() + ":email", obj.getEmail());
            result &= cache.hdel(obj.getClass().getName() + ":linked", obj.getLinkedInId());
            result &= cache.hdel(obj.getClass().getName() + ":organization", String.valueOf(obj.getOrganizationId()));

            if (result) {
                //Step 3: put job gearman
                short actionType = Const.RideSharing.ActionType.DELETE;
                JobEnt job = new JobEnt(0l, 0l, obj.getClass().getName(), actionType,
                        System.currentTimeMillis(), "", JSONUtil.Serialize(obj), "", "");
                result &= GClientManager.getInstance(ConfigInfo.RIDESHARING_WORKER_GEARMAN).push(job);
                if (!result) {
                    logger.error(String.format("Can't not delete DB with value=%s", JSONUtil.Serialize(obj)));
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
            RequestLogger.getInstance().info(obj, (short) Const.RideSharing.ActionType.UPDATE);

            //Step 1: put in hashmap
            database.getUserHashMap().put(obj.getId(), obj);
            //facebookRFUserId = new HashMap<>();//<fbId, userId>
            database.getFacebookRFUserId().put(obj.getFacebookId(), obj.getId());
            //googleRFUserId = new HashMap<>();//<ggId, userId>
            database.getGoogleRFUserId().put(obj.getGoogleId(), obj.getId());
            //twitterRFUserId = new HashMap<>();//<twId, userId>
            database.getTwitterRFUserId().put(obj.getTwitterId(), obj.getId());
            //emailRFUserId = new HashMap<>();//<email, userId>
            database.getEmailRFUserId().put(obj.getEmail(), obj.getId());
            //linkedInRFUserId = new HashMap<>();//<lkId, userId>
            database.getLinkedInRFUserId().put(obj.getLinkedInId(), obj.getId());
            //organizationRFUserId = new HashMap<>(); //<organizationId, userId>
            HashSet<String> userIds = database.getOrganizationRFUserIds().get(obj.getOrganizationId());
            if (userIds == null) {
                userIds = new HashSet<>();
            }
            userIds.add(obj.getId());
            //Step 2: put redis
            result = cache.hset(obj.getClass().getName(), String.valueOf(obj.getId()), JSONUtil.Serialize(obj));
            result &= cache.hset(obj.getClass().getName() + ":facebook", String.valueOf(obj.getFacebookId()), obj.getId());
            result &= cache.hset(obj.getClass().getName() + ":google", String.valueOf(obj.getGoogleId()), obj.getId());
            result &= cache.hset(obj.getClass().getName() + ":twitter", String.valueOf(obj.getTwitterId()), obj.getId());
            result &= cache.hset(obj.getClass().getName() + ":email", String.valueOf(obj.getEmail()), obj.getId());
            result &= cache.hset(obj.getClass().getName() + ":linked", String.valueOf(obj.getLinkedInId()), obj.getId());
            result &= cache.hset(obj.getClass().getName() + ":organization", String.valueOf(obj.getOrganizationId()), obj.getId());

            if (result) {
                //Step 3: put job gearman
                short actionType = Const.RideSharing.ActionType.UPDATE;
                JobEnt job = new JobEnt(0l, 0l, obj.getClass().getName(), actionType,
                        System.currentTimeMillis(), "", JSONUtil.Serialize(obj), "", "");
                result &= GClientManager.getInstance(ConfigInfo.RIDESHARING_WORKER_GEARMAN).push(job);
                if (!result) {
                    logger.error(String.format("Can't not update DB with value=%s", JSONUtil.Serialize(obj)));
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
