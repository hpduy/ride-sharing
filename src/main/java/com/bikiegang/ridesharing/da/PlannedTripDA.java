/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bikiegang.ridesharing.da;

import com.bikiegang.ridesharing.annn.framework.common.LogUtil;
import com.bikiegang.ridesharing.annn.framework.dbconn.ClientManager;
import com.bikiegang.ridesharing.annn.framework.dbconn.ManagerIF;
import com.bikiegang.ridesharing.config.ConfigInfo;
import com.bikiegang.ridesharing.pojo.PlannedTrip;
import com.bikiegang.ridesharing.pojo.PojoBase;
import com.bikiegang.ridesharing.utilities.Const;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author root
 */
public class PlannedTripDA implements IDA {

    @Override
    public boolean DoAction(PojoBase value, int actionType) {
        boolean result = false;

        if (value == null) {
            return result;
        }

        switch (actionType) {
            case Const.RideSharing.ActionType.INSERT:
                result = Insert((PlannedTrip) value);
                break;
            case Const.RideSharing.ActionType.UPDATE:
                result = Update((PlannedTrip) value);
                break;
            case Const.RideSharing.ActionType.DELETE:
                result = Delete((PlannedTrip) value);
                break;
        }

        return result;
    }

    Logger logger = LogUtil.getLogger(this.getClass());

    boolean Insert(PlannedTrip value) {

        boolean result = false;
        ManagerIF cm = ClientManager.getInstance(ConfigInfo.RIDESHARING_DB);

        Connection cnn = cm.borrowClient();
        try {
            String query = ConfigInfo.PLANNEDTRIP_INSERT_QUERY;
            try (PreparedStatement stmt = cnn.prepareStatement(query)) {
                stmt.setLong(1, value.getId());
                stmt.setLong(2, value.getArriveTime());
                stmt.setLong(3, value.getDepartureTime());
                stmt.setDouble(4, value.getOwnerPrice());
                stmt.setDouble(5, value.getEstimatedPrice());
                stmt.setString(6, value.getCreatorId());
                stmt.setInt(7, value.getRole());
                stmt.setInt(8, value.getType());
                stmt.setString(9, value.getPlannedTripTrailPolyLine());
                stmt.setLong(10, value.getTimePatternId());
                stmt.setBoolean(11, value.isHasHelmet());
                stmt.setLong(12, value.getRequestId());
                stmt.setLong(13, value.getRouteId());

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

    boolean Update(PlannedTrip value) {

        boolean result = false;
        ManagerIF cm = ClientManager.getInstance(ConfigInfo.RIDESHARING_DB);

        Connection cnn = cm.borrowClient();
        try {
            String query = ConfigInfo.PLANNEDTRIP_UPDATE_QUERY;
            try (PreparedStatement stmt = cnn.prepareStatement(query)) {
                stmt.setLong(1, value.getId());
                stmt.setLong(2, value.getArriveTime());
                stmt.setLong(3, value.getDepartureTime());
                stmt.setDouble(4, value.getOwnerPrice());
                stmt.setDouble(5, value.getEstimatedPrice());
                stmt.setString(6, value.getCreatorId());
                stmt.setInt(7, value.getRole());
                stmt.setInt(8, value.getType());
                stmt.setString(9, value.getPlannedTripTrailPolyLine());
                stmt.setLong(10, value.getTimePatternId());
                stmt.setBoolean(11, value.isHasHelmet());
                stmt.setLong(12, value.getRequestId());
                stmt.setLong(13, value.getRouteId());
                stmt.setLong(14, value.getId());

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

    boolean Delete(PlannedTrip value) {

        boolean result = false;
        ManagerIF cm = ClientManager.getInstance(ConfigInfo.RIDESHARING_DB);

        Connection cnn = cm.borrowClient();
        try {
            String query = ConfigInfo.PLANNEDTRIP_DELETE_QUERY;
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
