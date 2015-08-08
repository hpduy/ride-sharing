package com.bikiegang.ridesharing.dao;

import com.bikiegang.ridesharing.annn.framework.common.LogUtil;
import com.bikiegang.ridesharing.annn.framework.gearman.GClientManager;
import com.bikiegang.ridesharing.annn.framework.gearman.JobEnt;
import com.bikiegang.ridesharing.annn.framework.util.JSONUtil;
import com.bikiegang.ridesharing.cache.RideSharingCA;
import com.bikiegang.ridesharing.config.ConfigInfo;
import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.pojo.LatLng;
import com.bikiegang.ridesharing.pojo.PlannedTrip;
import com.bikiegang.ridesharing.utilities.Const;
import org.apache.log4j.Logger;

import java.util.HashSet;

/**
 * Created by hpduy17 on 6/26/15.
 */
public class PlannedTripDao {

    Logger logger = LogUtil.getLogger(this.getClass());
    RideSharingCA cache = RideSharingCA.getInstance(ConfigInfo.REDIS_SERVER);
    private Database database = Database.getInstance();

    public boolean insert(PlannedTrip obj) {
        boolean result = false;
        try {
            if (obj == null) {
                return false;
            }
            //Step 1: put in hashmap
            database.getPlannedTripHashMap().put(obj.getId(), obj);
            HashSet<Long> setRfRole = database.getRoleRFPlannedTrips().get(obj.getRole());
            HashSet<Long> setRfUser = database.getUserIdRFPlanedTrips().get(obj.getCreatorId());
            HashSet<Long> setRfGroup = database.getGroupIdRFPlannedTrips().get(obj.getGroupId());

            if (setRfRole == null) {
                setRfRole = new HashSet<>();
                database.getRoleRFPlannedTrips().put(obj.getRole(), setRfRole);
            }
            if (setRfUser == null) {
                setRfUser = new HashSet<>();
                database.getUserIdRFPlanedTrips().put(obj.getCreatorId(), setRfUser);
            }

            if (setRfGroup == null) {
                setRfGroup = new HashSet<>();
                database.getGroupIdRFPlannedTrips().put(obj.getGroupId(), setRfGroup);
            }

            setRfRole.add(obj.getId());
            setRfUser.add(obj.getId());
            setRfGroup.add(obj.getGroupId());

            // put start location into geocell
            database.getGeoCellStartLocation().putToCell(obj.getStartLocation(),obj.getId());
            //Step 2: put redis
            result = cache.hset(obj.getClass().getName(), String.valueOf(obj.getId()),
                    JSONUtil.Serialize(obj));
            result &= cache.hset(obj.getClass().getName() + ":user", String.valueOf(obj.getCreatorId()),
                    JSONUtil.Serialize(setRfUser));
            result &= cache.hset(obj.getClass().getName() + ":role", String.valueOf(obj.getRole()),
                    JSONUtil.Serialize(setRfRole));
            result &= cache.hset(obj.getClass().getName() + ":group", String.valueOf(obj.getGroupId()),
                    JSONUtil.Serialize(setRfGroup));

            if (result) {
                //Step 3: put job gearman
                short actionType = Const.RideSharing.ActionType.INSERT;
                JobEnt job = new JobEnt(0l, 0l, obj.getClass().getName(), actionType,
                        System.currentTimeMillis(), "", JSONUtil.Serialize(obj), "", "");
                result &= GClientManager.getInstance(ConfigInfo.RIDESHARING_WORKER_GEARMAN).push(job);
                if (!result) {
                    logger.error(String.format("Can't not insert DB with value=%s", obj));
                } else {
                    logger.info(String.format("Insert PlannedTrip success with value "
                            + "=%s", JSONUtil.Serialize(obj)));
                }
            } else {
                logger.error(String.format("Can't not insert redis with key=%s, "
                        + "field=%s, value=%s",
                        obj.getClass().getName(), String.valueOf(obj.getId()),
                        JSONUtil.Serialize(obj)));
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            ex.printStackTrace();
        }
        return result;
    }

    public boolean delete(PlannedTrip obj) {
        boolean result = false;
        try {
            if (obj == null) {
                return false;
            }
            //Step 1: put in hashmap
            database.getPlannedTripHashMap().remove(obj.getId());
            HashSet<Long> mapRfRole = database.getRoleRFPlannedTrips().get(obj.getRole());
            HashSet<Long> mapRfUser = database.getUserIdRFPlanedTrips().get(obj.getCreatorId());
            HashSet<Long> mapRfGroup = database.getGroupIdRFPlannedTrips().get(obj.getGroupId());

            if (mapRfRole == null) {
                mapRfRole = new HashSet<>();
                database.getRoleRFPlannedTrips().put(obj.getRole(), mapRfRole);
            }
            if (mapRfUser == null) {
                mapRfUser = new HashSet<>();
                database.getUserIdRFPlanedTrips().put(obj.getCreatorId(), mapRfUser);
            }
            if (mapRfGroup != null) {
                mapRfGroup = new HashSet<>();
                database.getGroupIdRFPlannedTrips().put(obj.getGroupId(), mapRfGroup);
            }

            mapRfRole.remove((Long) obj.getId());
            mapRfUser.remove((Long) obj.getId());
            mapRfGroup.remove((Long) obj.getId());
            // remove from cell
            // put start location into geocell
            database.getGeoCellStartLocation().removeFromCell(obj.getStartLocation(), obj.getId());
            //Step 2: put redis
            result = cache.hdel(obj.getClass().getName(),
                    String.valueOf(obj.getId()));
            result &= cache.hset(obj.getClass().getName() + ":user",
                    String.valueOf(obj.getCreatorId()), JSONUtil.Serialize(mapRfUser));
            result &= cache.hset(obj.getClass().getName() + ":role",
                    String.valueOf(obj.getCreatorId()), JSONUtil.Serialize(mapRfRole));
            result &= cache.hset(obj.getClass().getName() + ":group", String.valueOf(obj.getGroupId()),
                    JSONUtil.Serialize(mapRfGroup));

            if (result) {
                //Step 3: put job gearman
                short actionType = Const.RideSharing.ActionType.DELETE;
                JobEnt job = new JobEnt(0l, 0l, obj.getClass().getName(), actionType,
                        System.currentTimeMillis(), "", JSONUtil.Serialize(obj), "", "");
                result &= GClientManager.getInstance(ConfigInfo.RIDESHARING_WORKER_GEARMAN).push(job);
                if (!result) {
                    logger.error(String.format("Can't not delete DB with value=%s", obj));
                } else {
                    logger.info(String.format("Remove PlannedTrip success with value "
                            + "=%s", JSONUtil.Serialize(obj)));
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

    public boolean update(PlannedTrip obj) {
        boolean result = false;
        try {
            if (obj == null) {
                return false;
            }
            //update geocell
            LatLng oldStartLocation = new LatLng(database.getPlannedTripHashMap().get(obj.getId()).getStartLocation());
            database.getGeoCellStartLocation().updateInCell(oldStartLocation,obj.getStartLocation(),obj.getId());
            //Step 1: put in hashmap
            database.getPlannedTripHashMap().put(obj.getId(), obj);
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
                    logger.info(String.format("Update PlannedTrip success with value "
                            + "=%s", JSONUtil.Serialize(obj)));
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
