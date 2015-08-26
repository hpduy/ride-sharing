/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bikiegang.ridesharing.da;

import com.bikiegang.ridesharing.annn.framework.common.LogUtil;
import com.bikiegang.ridesharing.annn.framework.dbconn.ClientManager;
import com.bikiegang.ridesharing.annn.framework.dbconn.ManagerIF;
import com.bikiegang.ridesharing.annn.framework.util.JSONUtil;
import com.bikiegang.ridesharing.config.ConfigInfo;
import com.bikiegang.ridesharing.pojo.PojoBase;
import com.bikiegang.ridesharing.pojo.Trip;
import com.bikiegang.ridesharing.utilities.Const;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author root
 */
public class TripDA implements IDA {

    @Override
    public boolean DoAction(PojoBase value, int actionType) {
        boolean result = false;

        if (value == null) {
            return result;
        }

        switch (actionType) {
            case Const.RideSharing.ActionType.INSERT:
                result = Insert((Trip) value);
                break;
            case Const.RideSharing.ActionType.UPDATE:
                result = Update((Trip) value);
                break;
            case Const.RideSharing.ActionType.DELETE:
                result = Delete((Trip) value);
                break;
        }

        return result;
    }

    Logger logger = LogUtil.getLogger(this.getClass());

    boolean Insert(Trip value) {

        boolean result = false;
        ManagerIF cm = ClientManager.getInstance(ConfigInfo.RIDESHARING_DB);

        Connection cnn = cm.borrowClient();
        try {
            String query = ConfigInfo.TRIP_INSERT_QUERY;
            try (PreparedStatement stmt = cnn.prepareStatement(query)) {
                stmt.setLong(1, value.getId());
                stmt.setLong(2, value.getStartTime());
                stmt.setString(3, value.getDriverId());
                stmt.setString(4, value.getPassengerId());
                stmt.setDouble(5, value.getRealDistance());
                stmt.setLong(6, value.getEndTime());
                stmt.setDouble(7, value.getFarePaid());
                stmt.setString(8, JSONUtil.Serialize(value.getSensitiveLocationId()));// change to LatLng
                stmt.setString(9, value.getBreakReason());
                stmt.setBoolean(10, value.isSmoothBreak());
                stmt.setBoolean(11, value.isDangerTrip());
                stmt.setLong(12, value.getDriverPlannedTripId());
                stmt.setLong(13, value.getPassengerPlannedTripId());
                stmt.setString(14, value.getTripTrailPolyLine());
                stmt.setInt(15, value.getTripStatus());

                int row = stmt.executeUpdate();
                if (row > 0) {
                    result = true;
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            cm.returnClient(cnn);
        }
        return result;
    }

    boolean Update(Trip value) {

        boolean result = false;
        ManagerIF cm = ClientManager.getInstance(ConfigInfo.RIDESHARING_DB);

        Connection cnn = cm.borrowClient();
        try {
            String query = ConfigInfo.TRIP_UPDATE_QUERY;
            try (PreparedStatement stmt = cnn.prepareStatement(query)) {
                stmt.setLong(1, value.getId());
                stmt.setLong(2, value.getStartTime());
                stmt.setString(3, value.getDriverId());
                stmt.setString(4, value.getPassengerId());
                stmt.setDouble(5, value.getRealDistance());
                stmt.setLong(6, value.getEndTime());
                stmt.setDouble(7, value.getFarePaid());
                stmt.setString(8, JSONUtil.Serialize(value.getSensitiveLocationId()));// change to LatLng
                stmt.setString(9, value.getBreakReason());
                stmt.setBoolean(10, value.isSmoothBreak());
                stmt.setBoolean(11, value.isDangerTrip());
                stmt.setLong(12, value.getDriverPlannedTripId());
                stmt.setLong(13, value.getPassengerPlannedTripId());
                stmt.setString(14, value.getTripTrailPolyLine());
                stmt.setInt(15, value.getTripStatus());
                stmt.setLong(16, value.getId());

                int row = stmt.executeUpdate();
                if (row > 0) {
                    result = true;
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            cm.returnClient(cnn);
        }
        return result;
    }

    boolean Delete(Trip value) {

        boolean result = false;
        ManagerIF cm = ClientManager.getInstance(ConfigInfo.RIDESHARING_DB);

        Connection cnn = cm.borrowClient();
        try {
            String query = ConfigInfo.TRIP_DELETE_QUERY;
            try (PreparedStatement stmt = cnn.prepareStatement(query)) {
                stmt.setLong(1, value.getId());

                int row = stmt.executeUpdate();
                if (row > 0) {
                    result = true;
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            cm.returnClient(cnn);
        }
        return result;
    }
}
