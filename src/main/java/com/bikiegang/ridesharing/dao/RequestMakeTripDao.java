package com.bikiegang.ridesharing.dao;

import com.bikiegang.ridesharing.annn.framework.common.LogUtil;
import com.bikiegang.ridesharing.annn.framework.gearman.GClientManager;
import com.bikiegang.ridesharing.annn.framework.gearman.JobEnt;
import com.bikiegang.ridesharing.annn.framework.util.JSONUtil;
import com.bikiegang.ridesharing.cache.RideSharingCA;
import com.bikiegang.ridesharing.config.ConfigInfo;
import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.pojo.RequestMakeTrip;
import com.bikiegang.ridesharing.pojo.User;
import com.bikiegang.ridesharing.utilities.Const;
import com.bikiegang.ridesharing.utilities.RequestLogger;
import org.apache.log4j.Logger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by hpduy17 on 6/26/15.
 */
public class RequestMakeTripDao {

    Logger logger = LogUtil.getLogger(this.getClass());
    private Database database = Database.getInstance();
    RideSharingCA cache = RideSharingCA.getInstance(ConfigInfo.REDIS_SERVER);

    public boolean insert(RequestMakeTrip obj) {
        boolean result = false;
        try {
            if (obj == null) {
                return false;
            }

            RequestLogger.getInstance().info(obj, (short) Const.RideSharing.ActionType.INSERT);

            //Step 1: put in hashmap
            database.getRequestMakeTripHashMap().put(obj.getId(), obj);
            //senderRequestsBox = new HashMap<>(); //<senderId,<receiverPlannedTripId,requestIds>>
            HashMap<String, HashMap<Long, Long>> senderRequestsBox = database.getSenderRequestsBox();
            HashMap<Long, Long> mapSender = senderRequestsBox.get(obj.getSenderId());
            if (mapSender == null) {
                mapSender = new HashMap<>();
                senderRequestsBox.put(obj.getSenderId(), mapSender);
            }
            //receiverRequestsBox = new HashMap<>(); //<receiverId,<receiverPlannedTripId,<requestIds>>>
            HashMap<String, HashMap<Long, List<Long>>> receiverRequestsBox = database.getReceiverRequestsBox();
            HashMap<Long, List<Long>> mapReceiver = receiverRequestsBox.get(obj.getReceiverId());
            if (mapReceiver == null) {
                mapReceiver = new HashMap<>();
                receiverRequestsBox.put(obj.getReceiverId(), mapReceiver);
            }

            if (obj.getSenderRole() == User.DRIVER) {
                mapSender.put(obj.getPassengerPlannedTripId(), obj.getId());
                List<Long> listRequest = mapReceiver.get(obj.getPassengerPlannedTripId());
                if (listRequest == null) {
                    listRequest = new ArrayList<>();
                    mapReceiver.put(obj.getPassengerPlannedTripId(), listRequest);
                }
                listRequest.add(obj.getId());
            } else {
                if (obj.getSenderRole() == User.PASSENGER) {
                    mapSender.put(obj.getDriverPlannedTripId(), obj.getId());
                    List<Long> listRequest = mapReceiver.get(obj.getDriverPlannedTripId());
                    if (listRequest == null) {
                        listRequest = new ArrayList<>();
                        mapReceiver.put(obj.getDriverPlannedTripId(), listRequest);
                    }
                    listRequest.add(obj.getId());
                }
            }
            //Step 2: put redis
            result = cache.hset(obj.getClass().getName(), String.valueOf(obj.getId()),
                    JSONUtil.Serialize(obj));
            result &= cache.hset(obj.getClass().getName() + ":sender", String.valueOf(obj.getSenderId()),
                    JSONUtil.Serialize(mapSender));
            result &= cache.hset(obj.getClass().getName() + ":receiver", String.valueOf(obj.getReceiverId()),
                    JSONUtil.Serialize(mapReceiver));

            if (result) {
                //Step 3: put job gearman
                short actionType = Const.RideSharing.ActionType.INSERT;
                JobEnt job = new JobEnt(0l, 0l, obj.getClass().getName(), actionType,
                        System.currentTimeMillis(), "", JSONUtil.Serialize(obj), "", "");
                result &= GClientManager.getInstance(ConfigInfo.RIDESHARING_WORKER_GEARMAN).push(job);
                if (!result) {
                    logger.error(String.format("Can't not insert DB with value=%s", obj));
                } else {
                    logger.info(String.format("Insert RequestMakeTrip success with value=%s",
                            JSONUtil.Serialize(obj)));
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

    public boolean delete(RequestMakeTrip obj) {
        boolean result = false;
        try {
            if (obj == null) {
                return false;
            }
            RequestLogger.getInstance().info(obj, (short) Const.RideSharing.ActionType.DELETE);

            //Step 1: put in hashmap
            database.getRequestMakeTripHashMap().remove(obj.getId());
            //senderRequestsBox = new HashMap<>(); //<senderId,<receiverPlannedTripId,requestIds>>
            HashMap<String, HashMap<Long, Long>> senderRequestsBox = database.getSenderRequestsBox();
            HashMap<Long, Long> mapSender = senderRequestsBox.get(obj.getSenderId());
            if (mapSender == null) {
                mapSender = new HashMap<>();
                senderRequestsBox.put(obj.getSenderId(), mapSender);
            }
            //receiverRequestsBox = new HashMap<>(); //<receiverId,<receiverPlannedTripId,<requestIds>>>
            HashMap<String, HashMap<Long, List<Long>>> receiverRequestsBox = database.getReceiverRequestsBox();
            HashMap<Long, List<Long>> mapReceiver = receiverRequestsBox.get(obj.getReceiverId());
            if (mapReceiver == null) {
                mapReceiver = new HashMap<>();
                receiverRequestsBox.put(obj.getReceiverId(), mapReceiver);
            }

            if (obj.getSenderRole() == User.DRIVER) {
                mapSender.remove((Long) obj.getPassengerPlannedTripId());
                List<Long> listRequest = mapReceiver.get(obj.getPassengerPlannedTripId());
                if (listRequest == null) {
                    listRequest = new ArrayList<>();
                    mapReceiver.put(obj.getPassengerPlannedTripId(), listRequest);
                }
                listRequest.remove((Long) obj.getId());
            } else {
                if (obj.getSenderRole() == User.PASSENGER) {
                    mapSender.remove((Long) obj.getDriverPlannedTripId());
                    List<Long> listRequest = mapReceiver.get(obj.getDriverPlannedTripId());
                    if (listRequest == null) {
                        listRequest = new ArrayList<>();
                        mapReceiver.put(obj.getDriverPlannedTripId(), listRequest);
                    }
                    listRequest.remove((Long) obj.getId());
                }
            }
            //Step 2: put redis
            result = cache.hdel(obj.getClass().getName(), String.valueOf(obj.getId()));
            result &= cache.hset(obj.getClass().getName() + ":sender", String.valueOf(obj.getSenderId()),
                    JSONUtil.Serialize(mapSender));
            result &= cache.hset(obj.getClass().getName() + ":receiver", String.valueOf(obj.getReceiverId()),
                    JSONUtil.Serialize(mapReceiver));
            if (result) {
                //Step 3: put job gearman
                short actionType = Const.RideSharing.ActionType.DELETE;
                JobEnt job = new JobEnt(0l, 0l, obj.getClass().getName(), actionType,
                        System.currentTimeMillis(), "", JSONUtil.Serialize(obj), "", "");
                result &= GClientManager.getInstance(ConfigInfo.RIDESHARING_WORKER_GEARMAN).push(job);
                if (!result) {
                    logger.error(String.format("Can't not delete DB with value=%s", obj));
                } else {
                    logger.info(String.format("Remove RequestMakeTrip success with value=%s",
                            JSONUtil.Serialize(obj)));
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

    public boolean update(RequestMakeTrip obj) {
        boolean result = false;
        try {
            if (obj == null) {
                return false;
            }
            RequestLogger.getInstance().info(obj, (short) Const.RideSharing.ActionType.UPDATE);

            //Step 1: put in hashmap
            database.getRequestMakeTripHashMap().put(obj.getId(), obj);
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
                    logger.info(String.format("Update RequestMakeTrip success with value=%s",
                            JSONUtil.Serialize(obj)));
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
