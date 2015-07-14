///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.bikiegang.ridesharing.da;
//
//import com.bikiegang.ridesharing.config.ConfigInfo;
//import com.bikiegang.ridesharing.pojo.Activity;
//import com.bikiegang.ridesharing.pojo.PojoBase;
//import com.bikiegang.ridesharing.pojo.UserProfile;
//import com.bikiegang.ridesharing.utilities.Const;
//import com.bikiegang.ridesharing.annn.framework.dbconn.ClientManager;
//import com.bikiegang.ridesharing.annn.framework.dbconn.ManagerIF;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import org.apache.log4j.Logger;
//
///**
// *
// * @author root
// */
//public class UserProfileDA implements IDA {
//
//    @Override
//    public boolean DoAction(PojoBase value, int actionType) {
//        boolean result = false;
//
//        if (value == null) {
//            return result;
//        }
//
//        switch (actionType) {
//            case Const.RideSharing.ActionType.INSERT:
//                result = Insert((UserProfile) value);
//                break;
//            case Const.RideSharing.ActionType.UPDATE:
//                result = Update((UserProfile) value);
//                break;
//            case Const.RideSharing.ActionType.DELETE:
//                result = Delete((UserProfile) value);
//                break;
//        }
//
//        return result;
//    }
//
//    Logger logger = Logger.getLogger(this.getClass());
//
//    boolean Insert(UserProfile value) {
//
//        boolean result = false;
//        ManagerIF cm = ClientManager.getInstance(ConfigInfo.RIDESHARING_DB);
//
//        Connection cnn = cm.borrowClient();
//        try {
//            String query = ConfigInfo.USERPROFILE_INSERT_QUERY;
//            try (PreparedStatement stmt = cnn.prepareStatement(query)) {
//                stmt.setLong(1, value.getId());
//                stmt.setString(2, value.getUserId());
//                stmt.setString(3, value.getName());
//                stmt.setString(4, value.getDescription());
//                stmt.setLong(5, value.getCreatedTime());
//                int row = stmt.executeUpdate();
//                if (row > 0) {
//                    result = true;
//                }
//            }
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//        } finally {
//            cm.returnClient(cnn);
//        }
//        return result;
//    }
//
//    boolean Update(UserProfile value) {
//
//        boolean result = false;
//        ManagerIF cm = ClientManager.getInstance(ConfigInfo.RIDESHARING_DB);
//
//        Connection cnn = cm.borrowClient();
//        try {
//            String query = ConfigInfo.USERPROFILE_UPDATE_QUERY;
//            try (PreparedStatement stmt = cnn.prepareStatement(query)) {
//                stmt.setLong(1, value.getId());
//                stmt.setString(2, value.getUserId());
//                stmt.setString(3, value.getName());
//                stmt.setString(4, value.getDescription());
//                stmt.setLong(5, value.getCreatedTime());
//                stmt.setLong(6, value.getId());
//
//                int row = stmt.executeUpdate();
//                if (row > 0) {
//                    result = true;
//                }
//            }
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//        } finally {
//            cm.returnClient(cnn);
//        }
//        return result;
//    }
//
//    boolean Delete(UserProfile value) {
//
//        boolean result = false;
//        ManagerIF cm = ClientManager.getInstance(ConfigInfo.RIDESHARING_DB);
//
//        Connection cnn = cm.borrowClient();
//        try {
//            String query = ConfigInfo.USERPROFILE_DELETE_QUERY;
//            try (PreparedStatement stmt = cnn.prepareStatement(query)) {
//                stmt.setLong(1, value.getId());
//
//                int row = stmt.executeUpdate();
//                if (row > 0) {
//                    result = true;
//                }
//            }
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//        } finally {
//            cm.returnClient(cnn);
//        }
//        return result;
//    }
//}
