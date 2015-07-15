
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bikiegang.ridesharing.da;

import com.bikiegang.ridesharing.config.ConfigInfo;
import com.bikiegang.ridesharing.pojo.PojoBase;
import com.bikiegang.ridesharing.pojo.User;
import com.bikiegang.ridesharing.utilities.Const;
import com.bikiegang.ridesharing.annn.framework.dbconn.ClientManager;
import com.bikiegang.ridesharing.annn.framework.dbconn.ManagerIF;
import com.bikiegang.ridesharing.annn.framework.util.JSONUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import org.apache.log4j.Logger;

/**
 *
 * @author root
 */
public class UserDA implements IDA {

    @Override
    public boolean DoAction(PojoBase value, int actionType) {
        boolean result = false;

        if (value == null) {
            return result;
        }

        switch (actionType) {
            case Const.RideSharing.ActionType.INSERT:
                result = Insert((User) value);
                break;
            case Const.RideSharing.ActionType.UPDATE:
                result = Update((User) value);
                break;
            case Const.RideSharing.ActionType.DELETE:
                result = Delete((User) value);
                break;
        }

        return result;
    }

    Logger logger = Logger.getLogger(this.getClass());

    boolean Insert(User value) {

        boolean result = false;
        ManagerIF cm = ClientManager.getInstance(ConfigInfo.RIDESHARING_DB);

        Connection cnn = cm.borrowClient();
        try {
            String query = ConfigInfo.USER_INSERT_QUERY;
            try (PreparedStatement stmt = cnn.prepareStatement(query)) {
                stmt.setString(1, value.getId());
                stmt.setString(2, value.getEmail());
                stmt.setString(3, value.getPassword());
                stmt.setString(4, value.getFacebookId());
                stmt.setString(5, value.getGoogleId());
                stmt.setString(6, value.getTwitterId());
                stmt.setString(7, value.getFirstName());
                stmt.setString(8, value.getLastName());
                stmt.setString(9, value.getProfilePictureLink());
                stmt.setString(10, value.getPhone());
                stmt.setString(11, JSONUtil.Serialize(query));
                stmt.setString(12, value.getBirthDay());
                stmt.setInt(13, value.getGender());
                stmt.setInt(14, value.getStatus());
                stmt.setBoolean(15, value.isBusy());
                stmt.setInt(16, value.getCurrentRole());

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

    boolean Update(User value) {

        boolean result = false;
        ManagerIF cm = ClientManager.getInstance(ConfigInfo.RIDESHARING_DB);

        Connection cnn = cm.borrowClient();
        try {
            String query = ConfigInfo.USER_UPDATE_QUERY;
            try (PreparedStatement stmt = cnn.prepareStatement(query)) {
                stmt.setString(1, value.getId());
                stmt.setString(2, value.getEmail());
                stmt.setString(3, value.getPassword());
                stmt.setString(4, value.getFacebookId());
                stmt.setString(5, value.getGoogleId());
                stmt.setString(6, value.getTwitterId());
                stmt.setString(7, value.getFirstName());
                stmt.setString(8, value.getLastName());
                stmt.setString(9, value.getProfilePictureLink());
                stmt.setString(10, value.getPhone());
                stmt.setString(11, JSONUtil.Serialize(query));
                stmt.setString(12, value.getBirthDay());
                stmt.setInt(13, value.getGender());
                stmt.setInt(14, value.getStatus());
                stmt.setBoolean(15, value.isBusy());
                stmt.setInt(16, value.getCurrentRole());
                stmt.setString(17, value.getId());

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

    boolean Delete(User value) {

        boolean result = false;
        ManagerIF cm = ClientManager.getInstance(ConfigInfo.RIDESHARING_DB);

        Connection cnn = cm.borrowClient();
        try {
            String query = ConfigInfo.USER_DELETE_QUERY;
            try (PreparedStatement stmt = cnn.prepareStatement(query)) {
                stmt.setString(1, value.getId());

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