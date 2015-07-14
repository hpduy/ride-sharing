///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.bikiegang.ridesharing.da;
//
//import com.bikiegang.ridesharing.config.ConfigInfo;
//import com.bikiegang.ridesharing.pojo.PojoBase;
//import com.bikiegang.ridesharing.pojo.Rating;
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
//public class RatingDA implements IDA {
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
//                result = Insert((Rating) value);
//                break;
//            case Const.RideSharing.ActionType.UPDATE:
//                result = Update((Rating) value);
//                break;
//            case Const.RideSharing.ActionType.DELETE:
//                result = Delete((Rating) value);
//                break;
//        }
//
//        return result;
//    }
//
//    Logger logger = Logger.getLogger(this.getClass());
//
//    boolean Insert(Rating value) {
//
//        boolean result = false;
//        ManagerIF cm = ClientManager.getInstance(ConfigInfo.RIDESHARING_DB);
//
//        Connection cnn = cm.borrowClient();
//        try {
//            String query = ConfigInfo.RATING_INSERT_QUERY;
//            try (PreparedStatement stmt = cnn.prepareStatement(query)) {
//                stmt.setLong(1, value.getId());
//                stmt.setString(2, value.getAssessorId());
//                stmt.setLong(3, value.getProfileId());
//                stmt.setDouble(4, value.getValue());
//                stmt.setLong(5, value.getCreatedTime());
//                stmt.setString(6, value.getComment());
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
//    boolean Update(Rating value) {
//
//        boolean result = false;
//        ManagerIF cm = ClientManager.getInstance(ConfigInfo.RIDESHARING_DB);
//
//        Connection cnn = cm.borrowClient();
//        try {
//            String query = ConfigInfo.RATING_UPDATE_QUERY;
//            try (PreparedStatement stmt = cnn.prepareStatement(query)) {
//                stmt.setLong(1, value.getId());
//                stmt.setString(2, value.getAssessorId());
//                stmt.setLong(3, value.getProfileId());
//                stmt.setDouble(4, value.getValue());
//                stmt.setLong(5, value.getCreatedTime());
//                stmt.setString(6, value.getComment());
//                stmt.setLong(7, value.getId());
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
//    boolean Delete(Rating value) {
//
//        boolean result = false;
//        ManagerIF cm = ClientManager.getInstance(ConfigInfo.RIDESHARING_DB);
//
//        Connection cnn = cm.borrowClient();
//        try {
//            String query = ConfigInfo.RATING_DELETE_QUERY;
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
