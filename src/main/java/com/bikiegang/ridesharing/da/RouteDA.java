/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bikiegang.ridesharing.da;

import com.bikiegang.ridesharing.annn.framework.common.LogUtil;
import com.bikiegang.ridesharing.config.ConfigInfo;
import com.bikiegang.ridesharing.pojo.PojoBase;
import com.bikiegang.ridesharing.utilities.Const;
import com.bikiegang.ridesharing.annn.framework.dbconn.ClientManager;
import com.bikiegang.ridesharing.annn.framework.dbconn.ManagerIF;
import com.bikiegang.ridesharing.annn.framework.util.JSONUtil;
import com.bikiegang.ridesharing.pojo.Route;
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

    Logger logger = LogUtil.getLogger(this.getClass());

    boolean Insert(Route value) {

        boolean result = false;
        ManagerIF cm = ClientManager.getInstance(ConfigInfo.RIDESHARING_DB);

        Connection cnn = cm.borrowClient();
        try {
            String query = ConfigInfo.ROUTE_INSERT_QUERY;
            try (PreparedStatement stmt = cnn.prepareStatement(query)) {
                stmt.setLong(1, value.getId());
                stmt.setString(2, JSONUtil.Serialize(value.getRawRoutingResult()));
                stmt.setDouble(3, value.getEstimatedFuel());
                stmt.setLong(4, value.getEstimatedTime());
                stmt.setString(5, JSONUtil.Serialize(value.getStartLocation()));
                stmt.setString(6, JSONUtil.Serialize(value.getWaypoints()));
                stmt.setString(7, JSONUtil.Serialize(value.getEndLocation()));
                stmt.setString(8, value.getOverViewPolyLine());
                stmt.setLong(9, value.getCreatedTime());
                stmt.setDouble(10, value.getSumDistance());
                stmt.setLong(11, value.getCreatedTime());
                stmt.setString(12, value.getTitle());

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
                stmt.setString(2, JSONUtil.Serialize(value.getRawRoutingResult()));
                stmt.setDouble(3, value.getEstimatedFuel());
                stmt.setLong(4, value.getEstimatedTime());
                stmt.setString(5, JSONUtil.Serialize(value.getStartLocation()));
                stmt.setString(6, JSONUtil.Serialize(value.getWaypoints()));
                stmt.setString(7, JSONUtil.Serialize(value.getEndLocation()));
                stmt.setString(8, value.getOverViewPolyLine());
                stmt.setLong(9, value.getCreatedTime());
                stmt.setDouble(10, value.getSumDistance());
                stmt.setLong(11, value.getCreatedTime());
                stmt.setString(12, value.getTitle());
                stmt.setLong(13, value.getId());

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
