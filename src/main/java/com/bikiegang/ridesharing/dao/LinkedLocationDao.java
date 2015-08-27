package com.bikiegang.ridesharing.dao;

import com.bikiegang.ridesharing.annn.framework.common.LogUtil;
import com.bikiegang.ridesharing.annn.framework.gearman.GClientManager;
import com.bikiegang.ridesharing.annn.framework.gearman.JobEnt;
import com.bikiegang.ridesharing.annn.framework.util.JSONUtil;
import com.bikiegang.ridesharing.cache.RideSharingCA;
import com.bikiegang.ridesharing.config.ConfigInfo;
import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.geocoding.GeoCell;
import com.bikiegang.ridesharing.pojo.LinkedLocation;
import com.bikiegang.ridesharing.pojo.PlannedTrip;
import com.bikiegang.ridesharing.pojo.User;
import com.bikiegang.ridesharing.utilities.Const;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hpduy17 on 6/26/15.
 */
public class LinkedLocationDao {

    Logger logger = LogUtil.getLogger(this.getClass());
    private Database database = Database.getInstance();
    RideSharingCA cache = RideSharingCA.getInstance(ConfigInfo.REDIS_SERVER);

    public boolean insert(LinkedLocation obj) {
        boolean result = false;
        try {
            if (obj == null) {
                return false;
            }
            //Step 1: put in hashmap
            database.getLinkedLocationHashMap().put(obj.getId(), obj);
            //plannedTripIdRFLinkedLocations = new HashMap<>(); //<routeId,<LinkedLocationId>>
            List<Long> linkedLocationIds = database.getPlannedTripIdRFLinkedLocations().get(obj.getRefId());
            if (linkedLocationIds == null) {
                linkedLocationIds = new ArrayList<>();
                database.getPlannedTripIdRFLinkedLocations().put(obj.getRefId(), linkedLocationIds);
            }
            linkedLocationIds.add(obj.getId());
            // more (put to geocell)
                PlannedTrip pt = database.getPlannedTripHashMap().get(obj.getRefId());
                GeoCell<Long> geoCell = null;
                if (pt.getRole() == User.DRIVER) {
                    geoCell = database.getGeoCellDriver();
                }
                if (pt.getRole() == User.PASSENGER) {
                    geoCell = database.getGeoCellPassenger();
                }
                if (null != geoCell) {
                    geoCell.putToCell(obj, obj.getId());
                }

            //Step 2: put redis
            result = cache.hset(obj.getClass().getName(),
                    String.valueOf(obj.getId()), JSONUtil.Serialize(obj));
            result &= cache.hset(obj.getClass().getName() + ":plannedtrip",
                    String.valueOf(obj.getRefId()), JSONUtil.Serialize(linkedLocationIds));
            if (result) {
                //Step 3: put job gearman
                short actionType = Const.RideSharing.ActionType.INSERT;
                JobEnt job = new JobEnt(0l, 0l, obj.getClass().getName(), actionType,
                        System.currentTimeMillis(), "", JSONUtil.Serialize(obj), "", "");
                result &= GClientManager.getInstance(
                        ConfigInfo.RIDESHARING_WORKER_GEARMAN).push(job);
                if (!result) {
                    logger.error(String.format("Can't not insert DB with "
                            + "value=%s", obj));
                } else {
                    logger.info(String.format("Insert linkedlocation "
                            + "success with value=%s", JSONUtil.Serialize(obj)));
                }
            } else {
                logger.error(String.format("Can't not insert redis with key=%s, "
                        + "field=%s, value=%s",
                        obj.getClass().getName(), String.valueOf(obj.getId()),
                        JSONUtil.Serialize(obj)));
            }
        } catch (Exception ex) {

            logger.error(ex.getStackTrace());
            ex.printStackTrace();
        }
        return result;
    }

    public boolean delete(LinkedLocation obj) {
        boolean result = false;
        try {
            if (obj == null) {
                return false;
            }
            // remove from geo cell
                PlannedTrip pt = database.getPlannedTripHashMap().get(obj.getRefId());
                GeoCell<Long> geoCell = null;
                if (pt.getRole() == User.DRIVER) {
                    geoCell = database.getGeoCellDriver();
                }
                if (pt.getRole() == User.PASSENGER) {
                    geoCell = database.getGeoCellPassenger();
                }
                if (null != geoCell) {
                    geoCell.removeFromCell(obj, obj.getId());
                }

            //Step 1: put in hashmap
            database.getLinkedLocationHashMap().remove(obj.getId());
            //plannedTripIdRFLinkedLocations = new HashMap<>(); //<routeId,<LinkedLocationId>>
            List<Long> linkedLocationIds = database.getPlannedTripIdRFLinkedLocations().get(obj.getRefId());
            if (linkedLocationIds == null) {
                linkedLocationIds = new ArrayList<>();
                database.getPlannedTripIdRFLinkedLocations().put(obj.getRefId(), linkedLocationIds);
            }
            linkedLocationIds.remove((Long) obj.getId());

            //Step 2: put redis
            result = cache.hdel(obj.getClass().getName(),
                    String.valueOf(obj.getId()));
            result &= cache.hset(obj.getClass().getName() + ":plannedtrip",
                    String.valueOf(obj.getRefId()), JSONUtil.Serialize(linkedLocationIds));
            if (result) {
                //Step 3: put job gearman
                short actionType = Const.RideSharing.ActionType.DELETE;
                JobEnt job = new JobEnt(0l, 0l, obj.getClass().getName(), actionType,
                        System.currentTimeMillis(), "", JSONUtil.Serialize(obj), "", "");
                result &= GClientManager.getInstance(ConfigInfo.RIDESHARING_WORKER_GEARMAN).push(job);
                if (!result) {
                    logger.error(String.format("Can't not delete DB with value=%s", obj));
                } else {
                    logger.info(String.format("Remove linkedlocation "
                            + "success with value=%s", JSONUtil.Serialize(obj)));
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

    public boolean update(LinkedLocation obj) {
        boolean result = false;
        try {
            if (obj == null) {
                return false;
            }
            //get old linkedlocation
            LinkedLocation tmp = database.getLinkedLocationHashMap().get(obj.getId());
            if (tmp != null) {
                LinkedLocation older = new LinkedLocation(tmp);
                // more (put to geocell)
                    PlannedTrip pt = database.getPlannedTripHashMap().get(obj.getRefId());
                    GeoCell<Long> geoCell = null;
                    if (pt.getRole() == User.DRIVER) {
                        geoCell = database.getGeoCellDriver();
                    }
                    if (pt.getRole() == User.PASSENGER) {
                        geoCell = database.getGeoCellPassenger();
                    }
                    if (null != geoCell) {
                        geoCell.removeFromCell(older, older.getId());
                        geoCell.putToCell(obj, obj.getId());
                    }

            }
            //Step 1: put in hashmap
            database.getLinkedLocationHashMap().put(obj.getId(), obj);

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
                    logger.info(String.format("Update linkedlocation "
                            + "success with value=%s", JSONUtil.Serialize(obj)));
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
