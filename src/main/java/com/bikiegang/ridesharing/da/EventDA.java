/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bikiegang.ridesharing.da;

import com.bikiegang.ridesharing.annn.framework.common.LogUtil;
import com.bikiegang.ridesharing.annn.framework.dbconn.ClientManager;
import com.bikiegang.ridesharing.annn.framework.dbconn.ManagerIF;
import com.bikiegang.ridesharing.annn.framework.util.ConvertUtils;
import com.bikiegang.ridesharing.annn.framework.util.StringUtils;
import com.bikiegang.ridesharing.config.ConfigInfo;
import com.bikiegang.ridesharing.pojo.Event;
import com.bikiegang.ridesharing.pojo.PojoBase;
import com.bikiegang.ridesharing.utilities.Const;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Arrays;

/**
 *
 * @author root
 */
public class EventDA implements IDA {

    @Override
    public boolean DoAction(PojoBase value, int actionType) {
        boolean result = false;

        if (value == null) {
            return result;
        }

        switch (actionType) {
            case Const.RideSharing.ActionType.INSERT:
                result = Insert((Event) value);
                break;
            case Const.RideSharing.ActionType.UPDATE:
                result = Update((Event) value);
                break;
            case Const.RideSharing.ActionType.DELETE:
                result = Delete((Event) value);
                break;
        }

        return result;
    }

    Logger logger = LogUtil.getLogger(this.getClass());

    boolean Insert(Event value) {

        boolean result = false;
        ManagerIF cm = ClientManager.getInstance(ConfigInfo.RIDESHARING_DB);

        Connection cnn = cm.borrowClient();
        try {
            String query = ConfigInfo.EVENT_INSERT_QUERY;
            try (PreparedStatement stmt = cnn.prepareStatement(query)) {
                stmt.setLong(1, value.getEventId());
                stmt.setBoolean(2, value.isFixedTime());
                stmt.setString(3, StringUtils.join(Arrays.asList(value.getEventTimes()), ","));
                stmt.setBoolean(4, value.isFixedUnitPrice());
                stmt.setInt(5, value.getUnitPrice());
                stmt.setString(6, value.getSuggestMessage());
                stmt.setString(7, value.getInfoLink());
                stmt.setString(8, value.getFeedBannerLink());
                stmt.setLong(9, value.getStartTime());
                stmt.setLong(10, value.getEndTime());

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

    boolean Update(Event value) {

        boolean result = false;
        ManagerIF cm = ClientManager.getInstance(ConfigInfo.RIDESHARING_DB);

        Connection cnn = cm.borrowClient();
        try {
            String query = ConfigInfo.EVENT_UPDATE_QUERY;
            try (PreparedStatement stmt = cnn.prepareStatement(query)) {
                stmt.setLong(1, value.getEventId());
                stmt.setBoolean(2, value.isFixedTime());
                stmt.setString(3, StringUtils.join(Arrays.asList(value.getEventTimes()), ","));
                stmt.setBoolean(4, value.isFixedUnitPrice());
                stmt.setInt(5, value.getUnitPrice());
                stmt.setString(6, value.getSuggestMessage());
                stmt.setString(7, value.getInfoLink());
                stmt.setString(8, value.getFeedBannerLink());
                stmt.setLong(9, value.getStartTime());
                stmt.setLong(10, value.getEndTime());
                stmt.setLong(11, value.getEventId());

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

    boolean Delete(Event value) {

        boolean result = false;
        ManagerIF cm = ClientManager.getInstance(ConfigInfo.RIDESHARING_DB);

        Connection cnn = cm.borrowClient();
        try {
            String query = ConfigInfo.EVENT_DELETE_QUERY;
            try (PreparedStatement stmt = cnn.prepareStatement(query)) {
                stmt.setLong(1, value.getEventId());

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
