package com.bikiegang.ridesharing.dao;

import com.bikiegang.ridesharing.annn.framework.common.LogUtil;
import com.bikiegang.ridesharing.annn.framework.gearman.GClientManager;
import com.bikiegang.ridesharing.annn.framework.gearman.JobEnt;
import com.bikiegang.ridesharing.annn.framework.util.JSONUtil;
import com.bikiegang.ridesharing.cache.RideSharingCA;
import com.bikiegang.ridesharing.config.ConfigInfo;
import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.pojo.Trip;
import com.bikiegang.ridesharing.utilities.Const;
import com.bikiegang.ridesharing.utilities.RequestLogger;
import java.util.HashSet;
import org.apache.log4j.Logger;

/**
 * Created by hpduy17 on 6/26/15.
 */
public class TripDao {

    Logger logger = LogUtil.getLogger(this.getClass());
    private Database database = Database.getInstance();
    RideSharingCA cache = RideSharingCA.getInstance(ConfigInfo.REDIS_SERVER);

    public boolean insert(Trip obj) {
        boolean result = false;
        try {
            if (obj == null) {
                return false;
            }
            RequestLogger.getInstance().info(obj, (short) Const.RideSharing.ActionType.INSERT);

            //Step 1: put in hashmap
            database.getTripHashMap().put(obj.getId(), obj);
            //driverIdRFTrips = new HashMap<>(); // <userId,<tripId>>
            HashSet<Long> setRfDrive = database.getDriverIdRFTrips().get(obj.getDriverId());
            if (setRfDrive == null) {
                setRfDrive = new HashSet<>();
                database.getDriverIdRFTrips().put(obj.getDriverId(), setRfDrive);
            }
            setRfDrive.add(obj.getId());

            //passengerIdRFTrips = new HashMap<>(); // <userId,<tripId>>
            HashSet<Long> setRfPassenger = database.getPassengerIdRFTrips()
                    .get(obj.getPassengerId());
            if (setRfPassenger == null) {
                setRfPassenger = new HashSet<>();
                database.getPassengerIdRFTrips().put(obj.getPassengerId(), setRfPassenger);
            }
            setRfPassenger.add(obj.getId());

            //Step 2: put redis
            result = cache.hset(obj.getClass().getName(), String.valueOf(obj.getId()),
                    JSONUtil.Serialize(obj));
            result &= cache.hset(obj.getClass().getName() + ":drive", obj.getDriverId(),
                    JSONUtil.Serialize(setRfDrive));
            result &= cache.hset(obj.getClass().getName() + ":passenger", obj.getPassengerId(),
                    JSONUtil.Serialize(setRfPassenger));
            if (result) {
                //Step 3: put job gearman
                short actionType = Const.RideSharing.ActionType.INSERT;
                JobEnt job = new JobEnt(0l, 0l, obj.getClass().getName(), actionType,
                        System.currentTimeMillis(), "", JSONUtil.Serialize(obj), "", "");
                result &= GClientManager.getInstance(ConfigInfo.RIDESHARING_WORKER_GEARMAN).push(job);
                if (!result) {
                    logger.error(String.format("Can't not insert DB with value=%s", JSONUtil.Serialize(obj)));
                } else {
                    logger.info(String.format("Insert Trip success with value=%s",
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

    public boolean delete(Trip obj) {
        boolean result = false;
        try {
            if (obj == null) {
                return false;
            }
            RequestLogger.getInstance().info(obj, (short) Const.RideSharing.ActionType.DELETE);

            //Step 1: put in hashmap
            database.getTripHashMap().remove(obj.getId());
            //driverIdRFTrips = new HashMap<>(); // <userId,<tripId>>
            HashSet<Long> setRfDrive = database.getDriverIdRFTrips().get(obj.getDriverId());
            if (setRfDrive == null) {
                setRfDrive = new HashSet<>();
                database.getDriverIdRFTrips().put(obj.getDriverId(), setRfDrive);
            }
            setRfDrive.remove((Long) obj.getId());

            //passengerIdRFTrips = new HashMap<>(); // <userId,<tripId>>
            HashSet<Long> setRfPassenger = database.getPassengerIdRFTrips()
                    .get(obj.getPassengerId());
            if (setRfPassenger == null) {
                setRfPassenger = new HashSet<>();
                database.getPassengerIdRFTrips().put(obj.getPassengerId(), setRfPassenger);
            }
            setRfPassenger.remove((Long) obj.getId());
            //Step 2: put redis
            result = cache.hdel(obj.getClass().getName(), String.valueOf(obj.getId()));
            result &= cache.hset(obj.getClass().getName() + ":drive", obj.getDriverId(),
                    JSONUtil.Serialize(setRfDrive));
            result &= cache.hset(obj.getClass().getName() + ":passenger", obj.getPassengerId(),
                    JSONUtil.Serialize(setRfPassenger));

            if (result) {
                //Step 3: put job gearman
                short actionType = Const.RideSharing.ActionType.DELETE;
                JobEnt job = new JobEnt(0l, 0l, obj.getClass().getName(), actionType,
                        System.currentTimeMillis(), "", JSONUtil.Serialize(obj), "", "");
                result &= GClientManager.getInstance(ConfigInfo.RIDESHARING_WORKER_GEARMAN).push(job);
                if (!result) {
                    logger.error(String.format("Can't not delete DB with value=%s", JSONUtil.Serialize(obj)));
                } else {
                    logger.info(String.format("Remove Trip success with value=%s",
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

    public boolean update(Trip obj) {
        boolean result = false;
        try {
            if (obj == null) {
                return false;
            }
            RequestLogger.getInstance().info(obj, (short) Const.RideSharing.ActionType.UPDATE);

            //Step 1: put in hashmap
            database.getTripHashMap().put(obj.getId(), obj);
            //Step 2: put redis
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
                    logger.info(String.format("Update Trip success with value=%s",
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
