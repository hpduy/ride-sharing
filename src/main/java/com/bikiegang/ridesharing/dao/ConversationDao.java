package com.bikiegang.ridesharing.dao;

import com.bikiegang.ridesharing.annn.framework.common.LogUtil;
import com.bikiegang.ridesharing.annn.framework.gearman.GClientManager;
import com.bikiegang.ridesharing.annn.framework.gearman.JobEnt;
import com.bikiegang.ridesharing.annn.framework.util.JSONUtil;
import com.bikiegang.ridesharing.cache.RideSharingCA;
import com.bikiegang.ridesharing.config.ConfigInfo;
import com.bikiegang.ridesharing.controller.ConversationController;
import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.pojo.Conversation;
import com.bikiegang.ridesharing.utilities.Const;
import com.bikiegang.ridesharing.utilities.RequestLogger;
import org.apache.log4j.Logger;

import java.util.HashSet;

/**
 * Created by hpduy17 on 6/26/15.
 */
public class ConversationDao {

    Logger logger = LogUtil.getLogger(this.getClass());
    private final Database database = Database.getInstance();
    RideSharingCA cache = RideSharingCA.getInstance(ConfigInfo.REDIS_SERVER);

    public boolean insert(Conversation obj) {
        boolean result = false;
        try {
            if (obj == null) {
                return false;
            }
            RequestLogger.getInstance().info(obj, (short) Const.RideSharing.ActionType.INSERT);
            //put hashmap
            database.getConversationHashMap().put(obj.getId(), obj);
            //private HashMap<String,Long> historyRFHashMap = new HashMap<>(); // <ownerId#partnerId, conversationId>
            String[] partnerIds = obj.getPartnerIds();
            if (partnerIds != null) {
                database.getHistoryRFHashMap()
                        .put(new ConversationController().combineUsersKey(obj.getOwnerId(),obj.getPartnerIds()), obj.getId());
            }
            //private HashMap<String,HashSet<Long>> userIdRFConsersations = new HashMap<>(); //<userId,<conversationId>>
            HashSet<Long> get = database.getUserIdRFConsersations().get(obj.getOwnerId());
            if (get == null) {
                get = new HashSet<>();
                database.getUserIdRFConsersations().put(obj.getOwnerId(), get);
            }
            get.add(obj.getId());
            //Step 2: put redis
            result = cache.hset(obj.getClass().getName(), String.valueOf(obj.getId()), JSONUtil.Serialize(obj));
            if (partnerIds != null) {
                for (String item : partnerIds) {
                    result &= cache.hset(obj.getClass().getName() + ":history", obj.getOwnerId() + "#" + item, String.valueOf(obj.getId()));
                }
            }
            result &= cache.hset(obj.getClass().getName() + ":user", String.valueOf(obj.getOwnerId()), JSONUtil.Serialize(get));

            if (result) {
                //Step 3: put job gearman
                short actionType = Const.RideSharing.ActionType.INSERT;
                JobEnt job = new JobEnt(0l, 0l, obj.getClass().getName(), actionType,
                        System.currentTimeMillis(), "", JSONUtil.Serialize(obj), "", "");
                result &= GClientManager.getInstance(ConfigInfo.RIDESHARING_WORKER_GEARMAN).push(job);
                if (!result) {
                    logger.error(String.format("Can't not insert DB with value=%s", JSONUtil.Serialize(obj)));
                } else {
                    logger.info(String.format("Insert Conversation success with value=%s", JSONUtil.Serialize(obj)));
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

    public boolean delete(Conversation obj) {
        boolean result = false;
        try {
            if (obj == null) {
                return false;
            }
            RequestLogger.getInstance().info(obj, (short) Const.RideSharing.ActionType.DELETE);
            //remove in hashmap
            database.getConversationHashMap().remove(obj.getId());
            //private HashMap<String,Long> historyRFHashMap = new HashMap<>(); // <ownerId#partnerId, conversationId>
            String[] partnerIds = obj.getPartnerIds();
            if (partnerIds != null) {
                for (String item : partnerIds) {
                    database.getHistoryRFHashMap().remove(obj.getOwnerId() + "#" + item);
                }
            }
            //private HashMap<String,HashSet<Long>> userIdRFConsersations = new HashMap<>(); //<userId,<conversationId>>
            HashSet<Long> get = database.getUserIdRFConsersations().get(obj.getOwnerId());
            if (get == null) {
                get = new HashSet<>();
                database.getUserIdRFConsersations().put(obj.getOwnerId(), get);
            }
            get.remove((Long) obj.getId());
            //Step 2: remove redis
            result = cache.hdel(obj.getClass().getName(), String.valueOf(obj.getId()));
            if (partnerIds != null) {
                for (String item : partnerIds) {
                    result &= cache.hdel(obj.getClass().getName() + ":history", obj.getOwnerId() + "#" + item);
                }
            }
            result &= cache.hset(obj.getClass().getName() + ":user", String.valueOf(obj.getOwnerId()), JSONUtil.Serialize(get));

            if (result) {
                //Step 3: put job gearman
                short actionType = Const.RideSharing.ActionType.DELETE;
                JobEnt job = new JobEnt(0l, 0l, obj.getClass().getName(), actionType,
                        System.currentTimeMillis(), "", JSONUtil.Serialize(obj), "", "");
                result &= GClientManager.getInstance(ConfigInfo.RIDESHARING_WORKER_GEARMAN).push(job);
                if (!result) {
                    logger.error(String.format("Can't not delete DB with value=%s", JSONUtil.Serialize(obj)));
                } else {
                    logger.info(String.format("Delete Conversation success with value=%s", JSONUtil.Serialize(obj)));
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

    public boolean update(Conversation obj) {
        boolean result = false;
        try {
            if (obj == null) {
                return false;
            }
            RequestLogger.getInstance().info(obj, (short) Const.RideSharing.ActionType.UPDATE);
            //Update hashmap
            database.getConversationHashMap().put(obj.getId(), obj);
            //Step 2: Update redis
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
                    logger.info(String.format("Update Conversation with value=%s", JSONUtil.Serialize(obj)));
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
