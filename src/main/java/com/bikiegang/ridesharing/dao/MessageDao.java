package com.bikiegang.ridesharing.dao;

import com.bikiegang.ridesharing.annn.framework.common.LogUtil;
import com.bikiegang.ridesharing.annn.framework.gearman.GClientManager;
import com.bikiegang.ridesharing.annn.framework.gearman.JobEnt;
import com.bikiegang.ridesharing.annn.framework.util.JSONUtil;
import com.bikiegang.ridesharing.cache.RideSharingCA;
import com.bikiegang.ridesharing.config.ConfigInfo;
import com.bikiegang.ridesharing.controller.ConversationController;
import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.database.IdGenerator;
import com.bikiegang.ridesharing.pojo.Message;
import com.bikiegang.ridesharing.utilities.Const;
import com.bikiegang.ridesharing.utilities.RequestLogger;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by hpduy17 on 6/26/15.
 */
public class MessageDao {

    Logger logger = LogUtil.getLogger(this.getClass());
    private final Database database = Database.getInstance();
    RideSharingCA cache = RideSharingCA.getInstance(ConfigInfo.REDIS_SERVER);

    public boolean insert(Message obj) {
        boolean result = false;
        try {
            if (obj == null) {
                return false;
            }
            RequestLogger.getInstance().info(obj, (short) Const.RideSharing.ActionType.INSERT);
            //put hashmap
            database.getMessageLinkedHashMap().put(obj.getMsgId(), obj);
            //HashMap<Long, List<String>> conversationIdRFMessages = new HashMap<>();
            List<String> chaters = new ArrayList<>();
            chaters.add(obj.getSenderId());
            chaters.addAll(Arrays.asList(obj.getReceiverIds()));
            for (String owner : chaters) {
                List<String> temp = new ArrayList<>(chaters);
                temp.remove(owner);
                String[] partners = temp.toArray(new String[temp.size()]);
                String key = new ConversationController().combineUsersKey(owner, partners);
                long conversationId;
                try {
                    conversationId = database.getHistoryRFHashMap().get(key);
                    if (conversationId == 0) {
                        throw new Exception();
                    }
                } catch (Exception ex) {
                    conversationId = IdGenerator.getConversationId();
                    new ConversationController().insertConversation(conversationId, owner, partners);
                }
                List<String> get = database.getConversationIdRFMessages().get(conversationId);
                if (get == null) {
                    get = new ArrayList<>();
                    database.getConversationIdRFMessages().put(obj.getConversationId(), get);
                }
                get.add(obj.getMsgId());
                //Step 2: put redis
                result = cache.hset(obj.getClass().getName(), String.valueOf(obj.getMsgId()), JSONUtil.Serialize(obj));
                result &= cache.hset(obj.getClass().getName() + ":conversation", String.valueOf(conversationId), JSONUtil.Serialize(get));

                if (result) {
                    //Step 3: put job gearman
                    short actionType = Const.RideSharing.ActionType.INSERT;
                    JobEnt job = new JobEnt(0l, 0l, obj.getClass().getName(), actionType,
                            System.currentTimeMillis(), "", JSONUtil.Serialize(obj), "", "");
                    result &= GClientManager.getInstance(ConfigInfo.RIDESHARING_WORKER_GEARMAN).push(job);
                    if (!result) {
                        logger.error(String.format("Can't not insert DB with value=%s", JSONUtil.Serialize(obj)));
                    } else {
                        logger.info(String.format("Insert Message success with value=%s", JSONUtil.Serialize(obj)));
                    }
                } else {
                    logger.error(String.format("Can't not insert redis with key=%s, field=%s, value=%s",
                            obj.getClass().getName(), String.valueOf(obj.getMsgId()), JSONUtil.Serialize(obj)));
                }
            }

        } catch (Exception ex) {
            logger.error(ex.getStackTrace());
            ex.printStackTrace();
        }
        return result;
    }

    public boolean delete(Message obj) {
        boolean result = false;
        try {
            if (obj == null) {
                return false;
            }
            RequestLogger.getInstance().info(obj, (short) Const.RideSharing.ActionType.DELETE);
            //remove in hashmap
            database.getMessageLinkedHashMap().remove(obj.getMsgId());
            //HashMap<Long, List<String>> conversationIdRFMessages = new HashMap<>();
            List<String> get = database.getConversationIdRFMessages().get(obj.getConversationId());
            if (get == null) {
                get = new ArrayList<>();
                database.getConversationIdRFMessages().put(obj.getConversationId(), get);
            }
            get.remove(obj.getMsgId());
            //Step 2: remove redis
            result = cache.hdel(obj.getClass().getName(), String.valueOf(obj.getMsgId()));
            result &= cache.hset(obj.getClass().getName() + ":conversation", String.valueOf(obj.getConversationId()), JSONUtil.Serialize(get));

            if (result) {
                //Step 3: put job gearman
                short actionType = Const.RideSharing.ActionType.DELETE;
                JobEnt job = new JobEnt(0l, 0l, obj.getClass().getName(), actionType,
                        System.currentTimeMillis(), "", JSONUtil.Serialize(obj), "", "");
                result &= GClientManager.getInstance(ConfigInfo.RIDESHARING_WORKER_GEARMAN).push(job);
                if (!result) {
                    logger.error(String.format("Can't not delete DB with value=%s", JSONUtil.Serialize(obj)));
                } else {
                    logger.info(String.format("Delete Message success with value=%s", JSONUtil.Serialize(obj)));
                }
            } else {
                logger.error(String.format("Can't not delete redis with key=%s, field=%s, value=%s",
                        obj.getClass().getName(), String.valueOf(obj.getMsgId()), JSONUtil.Serialize(obj)));
            }
        } catch (Exception ex) {

            logger.error(ex.getStackTrace());
            ex.printStackTrace();
        }
        return result;
    }

    public boolean update(Message obj) {
        boolean result = false;
        try {
            if (obj == null) {
                return false;
            }
            RequestLogger.getInstance().info(obj, (short) Const.RideSharing.ActionType.UPDATE);
            //Update hashmap
            database.getMessageLinkedHashMap().put(obj.getMsgId(), obj);
            //Step 2: Update redis
            result = cache.hset(obj.getClass().getName(), String.valueOf(obj.getMsgId()), JSONUtil.Serialize(obj));
            if (result) {
                //Step 3: put job gearman
                short actionType = Const.RideSharing.ActionType.UPDATE;
                JobEnt job = new JobEnt(0l, 0l, obj.getClass().getName(), actionType,
                        System.currentTimeMillis(), "", JSONUtil.Serialize(obj), "", "");
                result &= GClientManager.getInstance(ConfigInfo.RIDESHARING_WORKER_GEARMAN).push(job);
                if (!result) {
                    logger.error(String.format("Can't not update DB with value=%s", obj));
                } else {
                    logger.info(String.format("Update Message with value=%s", JSONUtil.Serialize(obj)));
                }
            } else {
                logger.error(String.format("Can't not update redis with key=%s, field=%s, value=%s",
                        obj.getClass().getName(), String.valueOf(obj.getMsgId()), JSONUtil.Serialize(obj)));
            }
        } catch (Exception ex) {

            logger.error(ex.getStackTrace());
            ex.printStackTrace();
        }
        return result;
    }
}
