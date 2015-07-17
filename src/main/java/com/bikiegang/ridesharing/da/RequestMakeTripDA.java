/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bikiegang.ridesharing.da;

import com.bikiegang.ridesharing.config.ConfigInfo;
import com.bikiegang.ridesharing.pojo.PojoBase;
import com.bikiegang.ridesharing.pojo.RequestMakeTrip;
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
public class RequestMakeTripDA implements IDA {

    @Override
    public boolean DoAction(PojoBase value, int actionType) {
        boolean result = false;

        if (value == null) {
            return result;
        }

        switch (actionType) {
            case Const.RideSharing.ActionType.INSERT:
                result = Insert((RequestMakeTrip) value);
                break;
            case Const.RideSharing.ActionType.UPDATE:
                result = Update((RequestMakeTrip) value);
                break;
            case Const.RideSharing.ActionType.DELETE:
                result = Delete((RequestMakeTrip) value);
                break;
        }

        return result;
    }

    Logger logger = Logger.getLogger(this.getClass());

    boolean Insert(RequestMakeTrip value) {

        boolean result = false;
        ManagerIF cm = ClientManager.getInstance(ConfigInfo.RIDESHARING_DB);

        Connection cnn = cm.borrowClient();
        try {
            String query = ConfigInfo.REQUESTMAKETRIP_INSERT_QUERY;
            try (PreparedStatement stmt = cnn.prepareStatement(query)) {
                stmt.setLong(1, value.getId());
                stmt.setString(2, value.getSenderId());
                stmt.setString(3, value.getReceiverId());
                stmt.setInt(4, value.getSenderRole());
                stmt.setLong(5, value.getCreatedTime());
                stmt.setLong(6, value.getDriverPlannedTripId());
                stmt.setLong(7, value.getPassengerPlannedTripId());
                stmt.setInt(8, value.getStatus());

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

    boolean Update(RequestMakeTrip value) {

        boolean result = false;
        ManagerIF cm = ClientManager.getInstance(ConfigInfo.RIDESHARING_DB);

        Connection cnn = cm.borrowClient();
        try {
            String query = ConfigInfo.REQUESTMAKETRIP_UPDATE_QUERY;
            try (PreparedStatement stmt = cnn.prepareStatement(query)) {
                stmt.setLong(1, value.getId());
                stmt.setString(2, value.getSenderId());
                stmt.setString(3, value.getReceiverId());
                stmt.setInt(4, value.getSenderRole());
                stmt.setLong(5, value.getCreatedTime());
                stmt.setLong(6, value.getDriverPlannedTripId());
                stmt.setLong(7, value.getPassengerPlannedTripId());
                stmt.setInt(8, value.getStatus());
                stmt.setLong(9, value.getId());

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

    boolean Delete(RequestMakeTrip value) {

        boolean result = false;
        ManagerIF cm = ClientManager.getInstance(ConfigInfo.RIDESHARING_DB);

        Connection cnn = cm.borrowClient();
        try {
            String query = ConfigInfo.REQUESTMAKETRIP_DELETE_QUERY;
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
