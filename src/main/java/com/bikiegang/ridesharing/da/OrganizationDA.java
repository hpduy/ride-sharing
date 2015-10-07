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
import com.bikiegang.ridesharing.pojo.Organization;
import com.bikiegang.ridesharing.pojo.PojoBase;
import com.bikiegang.ridesharing.utilities.Const;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author root
 */
public class OrganizationDA implements IDA {

    @Override
    public boolean DoAction(PojoBase value, int actionType) {
        boolean result = false;

        if (value == null) {
            return result;
        }

        switch (actionType) {
            case Const.RideSharing.ActionType.INSERT:
                result = Insert((Organization) value);
                break;
            case Const.RideSharing.ActionType.UPDATE:
                result = Update((Organization) value);
                break;
            case Const.RideSharing.ActionType.DELETE:
                result = Delete((Organization) value);
                break;
        }

        return result;
    }

    Logger logger = LogUtil.getLogger(this.getClass());

    boolean Insert(Organization value) {

        boolean result = false;
        ManagerIF cm = ClientManager.getInstance(ConfigInfo.RIDESHARING_DB);

        Connection cnn = cm.borrowClient();
        try {
            String query = ConfigInfo.ORGANIZATION_INSERT_QUERY;
            try (PreparedStatement stmt = cnn.prepareStatement(query)) {
                stmt.setString(1, value.getOrganizationId());
                stmt.setString(2, value.getName());
                stmt.setString(3, value.getLogoImageLink());
                stmt.setString(4, value.getOrganizationId());
                stmt.setLong(5, value.getCreatedTime());

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

    boolean Update(Organization value) {

        boolean result = false;
        ManagerIF cm = ClientManager.getInstance(ConfigInfo.RIDESHARING_DB);

        Connection cnn = cm.borrowClient();
        try {
            String query = ConfigInfo.ORGANIZATION_UPDATE_QUERY;
            try (PreparedStatement stmt = cnn.prepareStatement(query)) {
                stmt.setString(1, value.getOrganizationId());
                stmt.setString(2, value.getName());
                stmt.setString(3, value.getLogoImageLink());
                stmt.setString(4, value.getOrganizationId());
                stmt.setLong(5, value.getCreatedTime());
                stmt.setString(6, value.getOrganizationId());

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

    boolean Delete(Organization value) {

        boolean result = false;
        ManagerIF cm = ClientManager.getInstance(ConfigInfo.RIDESHARING_DB);

        Connection cnn = cm.borrowClient();
        try {
            String query = ConfigInfo.ORGANIZATION_DELETE_QUERY;
            try (PreparedStatement stmt = cnn.prepareStatement(query)) {
                stmt.setString(1, value.getOrganizationId());

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
