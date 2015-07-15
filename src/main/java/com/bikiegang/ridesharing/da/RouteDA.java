/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bikiegang.ridesharing.da;

import com.bikiegang.ridesharing.config.ConfigInfo;
import com.bikiegang.ridesharing.pojo.PojoBase;
import com.bikiegang.ridesharing.pojo.Route;
import com.bikiegang.ridesharing.utilities.Const;
import com.bikiegang.ridesharing.annn.framework.dbconn.ClientManager;
import com.bikiegang.ridesharing.annn.framework.dbconn.ManagerIF;
import java.sql.Connection;
import java.sql.PreparedStatement;
import org.apache.log4j.Logger;

/**
 *
 * @author root
 */
public class RouteDA implements IDA {

    @Override
    public boolean DoAction(PojoBase value, int actionType) {
        boolean result = false;

        if (value == null) {
            return result;
        }

        switch (actionType) {
            case Const.RideSharing.ActionType.INSERT:
                result = Insert((Route) value);
                break;
            case Const.RideSharing.ActionType.UPDATE:
                result = Update((Route) value);
                break;
            case Const.RideSharing.ActionType.DELETE:
                result = Delete((Route) value);
                break;
        }

        return result;
    }

    Logger logger = Logger.getLogger(this.getClass());

    boolean Insert(Route value) {

        boolean result = false;
        ManagerIF cm = ClientManager.getInstance(ConfigInfo.RIDESHARING_DB);

        Connection cnn = cm.borrowClient();
        try {
            String query = ConfigInfo.ROUTE_INSERT_QUERY;
            try (PreparedStatement stmt = cnn.prepareStatement(query)) {
                stmt.setLong(1, value.getId());
                stmt.setLong(2, value.getGoTime());
                stmt.setLong(3, value.getArriveTime());
                stmt.setDouble(4, value.getSumDistance());
                stmt.setLong(5, value.getEstimatedTime());
                stmt.setDouble(6, value.getEstimatedPrice());
                stmt.setDouble(7, value.getOwnerPrice());
                stmt.setDouble(8, value.getEstimatedFuel());
                stmt.setString(9, value.getCreatorId());
                stmt.setInt(10, value.getRole());
                stmt.setInt(11, value.getType());
                stmt.setString(12, value.getRouteTrailPolyLine());
                stmt.setString(13, value.getRawRoutingResult().toJSONString());

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

    boolean Update(Route value) {

        boolean result = false;
        ManagerIF cm = ClientManager.getInstance(ConfigInfo.RIDESHARING_DB);

        Connection cnn = cm.borrowClient();
        try {
            String query = ConfigInfo.ROUTE_UPDATE_QUERY;
            try (PreparedStatement stmt = cnn.prepareStatement(query)) {
                stmt.setLong(1, value.getId());
                stmt.setLong(2, value.getGoTime());
                stmt.setLong(3, value.getArriveTime());
                stmt.setDouble(4, value.getSumDistance());
                stmt.setLong(5, value.getEstimatedTime());
                stmt.setDouble(6, value.getEstimatedPrice());
                stmt.setDouble(7, value.getOwnerPrice());
                stmt.setDouble(8, value.getEstimatedFuel());
                stmt.setString(9, value.getCreatorId());
                stmt.setInt(10, value.getRole());
                stmt.setInt(11, value.getType());
                stmt.setString(12, value.getRouteTrailPolyLine());
                stmt.setString(13, value.getRawRoutingResult().toJSONString());
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

    boolean Delete(Route value) {

        boolean result = false;
        ManagerIF cm = ClientManager.getInstance(ConfigInfo.RIDESHARING_DB);

        Connection cnn = cm.borrowClient();
        try {
            String query = ConfigInfo.ROUTE_DELETE_QUERY;
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
