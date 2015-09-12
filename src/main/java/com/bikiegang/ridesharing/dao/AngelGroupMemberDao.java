package com.bikiegang.ridesharing.dao;

import com.bikiegang.ridesharing.annn.framework.common.LogUtil;
import com.bikiegang.ridesharing.annn.framework.gearman.GClientManager;
import com.bikiegang.ridesharing.annn.framework.gearman.JobEnt;
import com.bikiegang.ridesharing.annn.framework.util.JSONUtil;
import com.bikiegang.ridesharing.cache.RideSharingCA;
import com.bikiegang.ridesharing.config.ConfigInfo;
import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.pojo.AngelGroupMember;
import com.bikiegang.ridesharing.utilities.Const;
import com.bikiegang.ridesharing.utilities.RequestLogger;
import java.util.HashSet;
import org.apache.log4j.Logger;

/**
 * Created by hpduy17 on 6/26/15.
 */
public class AngelGroupMemberDao {

    Logger logger = LogUtil.getLogger(this.getClass());
    private Database database = Database.getInstance();
    RideSharingCA cache = RideSharingCA.getInstance(ConfigInfo.REDIS_SERVER);

    public boolean insert(AngelGroupMember obj) {
        boolean result = false;
        try {
            if (obj == null) {
                return false;
            }
            RequestLogger.getInstance().info(obj, (short) Const.RideSharing.ActionType.INSERT);
            //Step 1: put in hashmap
            database.getAngelGroupMemberHashMap().put(obj.getId(), obj);

            //hashmap UserIdRFAngelGroups // <userId,<angelGroupId>>
            HashSet<Long> angelGroupIds = database.getUserIdRFAngelGroups().get(obj.getAngelId());
            if (angelGroupIds == null) {
                angelGroupIds = new HashSet<>();
                database.getUserIdRFAngelGroups().put(obj.getAngelId(), angelGroupIds);
            }
            angelGroupIds.add(obj.getGroupId());

            //Hashmap AngelGroupIdRFUsers // <angelGroupId,<userId>>
            HashSet<String> userIds = database.getAngelGroupIdRFUsers().get(obj.getGroupId());
            if (userIds == null) {
                userIds = new HashSet<>();
                database.getAngelGroupIdRFUsers().put(obj.getGroupId(), userIds);
            }
            userIds.add(obj.getAngelId());

            //Hashmap UserAndGroupRFAngelGroupMember // <userId#angelGroupId, angelGroupMemberId>
            database.getUserAndGroupRFAngelGroupMember().put(obj.getAngelId() + "#" + obj.getGroupId(), obj.getId());

            //Step 2: put redis
            result = cache.hset(obj.getClass().getName(), String.valueOf(obj.getId()), JSONUtil.Serialize(obj));
            result &= cache.hset(obj.getClass().getName() + ":user", obj.getAngelId(), JSONUtil.Serialize(angelGroupIds));
            result &= cache.hset(obj.getClass().getName() + ":angelgroup", String.valueOf(obj.getGroupId()), JSONUtil.Serialize(userIds));
            result &= cache.hset(obj.getClass().getName() + ":userandgroup", obj.getAngelId() + "#" + obj.getGroupId(), String.valueOf(obj.getId()));

            if (result) {
                //Step 3: put job gearman
                short actionType = Const.RideSharing.ActionType.INSERT;
                JobEnt job = new JobEnt(0l, 0l, obj.getClass().getName(), actionType,
                        System.currentTimeMillis(), "", JSONUtil.Serialize(obj), "", "");
                result &= GClientManager.getInstance(ConfigInfo.RIDESHARING_WORKER_GEARMAN).push(job);
                if (!result) {
                    logger.error(String.format("Can't not insert DB with value=%s", obj));
                } else {
                    logger.info(String.format("Insert AngelGroupMember success with value=%s", JSONUtil.Serialize(obj)));
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

    public boolean delete(AngelGroupMember obj) {
        boolean result = false;
        try {
            if (obj == null) {
                return false;
            }
            RequestLogger.getInstance().info(obj, (short) Const.RideSharing.ActionType.DELETE);
            //Step 1: remove in hashmap
            database.getAngelGroupMemberHashMap().remove(obj.getId());
            //hashmap UserIdRFAngelGroups // <userId,<angelGroupId>>
            HashSet<Long> angelGroupIds = database.getUserIdRFAngelGroups().get(obj.getAngelId());
            if (angelGroupIds == null) {
                angelGroupIds = new HashSet<>();
                database.getUserIdRFAngelGroups().put(obj.getAngelId(), angelGroupIds);
            }
            angelGroupIds.remove((Long) obj.getGroupId());
            //Hashmap AngelGroupIdRFUsers // <angelGroupId,<userId>>
            HashSet<String> userIds = database.getAngelGroupIdRFUsers().get(obj.getGroupId());
            if (userIds == null) {
                userIds = new HashSet<>();
                database.getAngelGroupIdRFUsers().put(obj.getGroupId(), userIds);
            }
            userIds.remove(obj.getAngelId());
            //Hashmap UserAndGroupRFAngelGroupMember // <userId#angelGroupId, angelGroupMemberId>
            database.getUserAndGroupRFAngelGroupMember().remove(obj.getAngelId() + "#" + obj.getGroupId());

            //Step 2: remove redis
            result = cache.hdel(obj.getClass().getName(), String.valueOf(obj.getId()));
            result &= cache.hset(obj.getClass().getName() + ":user", obj.getAngelId(), JSONUtil.Serialize(angelGroupIds));
            result &= cache.hset(obj.getClass().getName() + ":angelgroup", String.valueOf(obj.getGroupId()), JSONUtil.Serialize(userIds));
            result &= cache.hdel(obj.getClass().getName() + ":userandgroup", obj.getAngelId() + "#" + obj.getGroupId());

            if (result) {
                //Step 3: put job gearman
                short actionType = Const.RideSharing.ActionType.DELETE;
                JobEnt job = new JobEnt(0l, 0l, obj.getClass().getName(), actionType,
                        System.currentTimeMillis(), "", JSONUtil.Serialize(obj), "", "");
                result &= GClientManager.getInstance(ConfigInfo.RIDESHARING_WORKER_GEARMAN).push(job);
                if (!result) {
                    logger.error(String.format("Can't not delete DB with value=%s", obj));
                } else {
                    logger.info(String.format("Delete AngelGroupMember success with value=%s", JSONUtil.Serialize(obj)));
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

    public boolean update(AngelGroupMember obj) {
        boolean result = false;
        try {
            if (obj == null) {
                return false;
            }
            RequestLogger.getInstance().info(obj, (short) Const.RideSharing.ActionType.UPDATE);
            //Step 1: put in hashmap
            database.getAngelGroupMemberHashMap().put(obj.getId(), obj);

            //hashmap UserIdRFAngelGroups // <userId,<angelGroupId>>
            HashSet<Long> angelGroupIds = database.getUserIdRFAngelGroups().get(obj.getAngelId());
            if (angelGroupIds == null) {
                angelGroupIds = new HashSet<>();
                database.getUserIdRFAngelGroups().put(obj.getAngelId(), angelGroupIds);
            }
            angelGroupIds.add(obj.getGroupId());

            //Hashmap AngelGroupIdRFUsers // <angelGroupId,<userId>>
            HashSet<String> userIds = database.getAngelGroupIdRFUsers().get(obj.getGroupId());
            if (userIds == null) {
                userIds = new HashSet<>();
                database.getAngelGroupIdRFUsers().put(obj.getGroupId(), userIds);
            }
            userIds.add(obj.getAngelId());

            //Hashmap UserAndGroupRFAngelGroupMember // <userId#angelGroupId, angelGroupMemberId>
            database.getUserAndGroupRFAngelGroupMember().put(obj.getAngelId() + "#" + obj.getGroupId(), obj.getId());

            //Step 2: put redis
            result = cache.hset(obj.getClass().getName(), String.valueOf(obj.getId()), JSONUtil.Serialize(obj));
            result &= cache.hset(obj.getClass().getName() + ":user", obj.getAngelId(), JSONUtil.Serialize(angelGroupIds));
            result &= cache.hset(obj.getClass().getName() + ":angelgroup", String.valueOf(obj.getGroupId()), JSONUtil.Serialize(userIds));
            result &= cache.hset(obj.getClass().getName() + ":userandgroup", obj.getAngelId() + "#" + obj.getGroupId(), String.valueOf(obj.getId()));

            if (result) {
                //Step 3: put job gearman
                short actionType = Const.RideSharing.ActionType.UPDATE;
                JobEnt job = new JobEnt(0l, 0l, obj.getClass().getName(), actionType,
                        System.currentTimeMillis(), "", JSONUtil.Serialize(obj), "", "");
                result &= GClientManager.getInstance(ConfigInfo.RIDESHARING_WORKER_GEARMAN).push(job);
                if (!result) {
                    logger.error(String.format("Can't not update DB with value=%s", obj));
                } else {
                    logger.info(String.format("Update AngelGroupMember with value=%s", JSONUtil.Serialize(obj)));
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
