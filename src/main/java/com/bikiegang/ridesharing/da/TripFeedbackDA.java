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
//import com.bikiegang.ridesharing.pojo.TripFeedback;
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
//public class TripFeedbackDA implements IDA {
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
//                result = Insert((TripFeedback) value);
//                break;
//            case Const.RideSharing.ActionType.UPDATE:
//                result = Update((TripFeedback) value);
//                break;
//            case Const.RideSharing.ActionType.DELETE:
//                result = Delete((TripFeedback) value);
//                break;
//        }
//
//        return result;
//    }
//
//    Logger logger = Logger.getLogger(this.getClass());
//
//    boolean Insert(TripFeedback value) {
//
//        boolean result = false;
//        ManagerIF cm = ClientManager.getInstance(ConfigInfo.RIDESHARING_DB);
//
//        Connection cnn = cm.borrowClient();
//        try {
//            String query = ConfigInfo.TRIPFEEDBACK_INSERT_QUERY;
//            try (PreparedStatement stmt = cnn.prepareStatement(query)) {
//                stmt.setLong(1, value.getId());
//                stmt.setLong(2, value.getTripId());
//                stmt.setString(3, value.getUserFeedBackId());
//                stmt.setString(4, value.getUserBeFeedbackId());
//                stmt.setInt(5, value.getValue());
//                stmt.setLong(6, value.getCreatedTime());
//                stmt.setString(7, value.getComment());
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
//    boolean Update(TripFeedback value) {
//
//        boolean result = false;
//        ManagerIF cm = ClientManager.getInstance(ConfigInfo.RIDESHARING_DB);
//
//        Connection cnn = cm.borrowClient();
//        try {
//            String query = ConfigInfo.TRIPFEEDBACK_UPDATE_QUERY;
//            try (PreparedStatement stmt = cnn.prepareStatement(query)) {
//                stmt.setLong(1, value.getId());
//                stmt.setLong(2, value.getTripId());
//                stmt.setString(3, value.getUserFeedBackId());
//                stmt.setString(4, value.getUserBeFeedbackId());
//                stmt.setInt(5, value.getValue());
//                stmt.setLong(6, value.getCreatedTime());
//                stmt.setString(7, value.getComment());
//                stmt.setLong(8, value.getId());
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
//    boolean Delete(TripFeedback value) {
//
//        boolean result = false;
//        ManagerIF cm = ClientManager.getInstance(ConfigInfo.RIDESHARING_DB);
//
//        Connection cnn = cm.borrowClient();
//        try {
//            String query = ConfigInfo.TRIPFEEDBACK_DELETE_QUERY;
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
