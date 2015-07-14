///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.bikiegang.ridesharing.da;
//
//import com.bikiegang.ridesharing.config.ConfigInfo;
//import com.bikiegang.ridesharing.pojo.PojoBase;
//import com.bikiegang.ridesharing.pojo.VerifiedCertificate;
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
//public class VerifiedCertificateDA implements IDA {
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
//                result = Insert((VerifiedCertificate) value);
//                break;
//            case Const.RideSharing.ActionType.UPDATE:
//                result = Update((VerifiedCertificate) value);
//                break;
//            case Const.RideSharing.ActionType.DELETE:
//                result = Delete((VerifiedCertificate) value);
//                break;
//        }
//
//        return result;
//    }
//
//    Logger logger = Logger.getLogger(this.getClass());
//
//    boolean Insert(VerifiedCertificate value) {
//
//        boolean result = false;
//        ManagerIF cm = ClientManager.getInstance(ConfigInfo.RIDESHARING_DB);
//
//        Connection cnn = cm.borrowClient();
//        try {
//            String query = ConfigInfo.VERIFIEDCERTIFICATE_INSERT_QUERY;
//            try (PreparedStatement stmt = cnn.prepareStatement(query)) {
//                stmt.setLong(1, value.getId());
//                stmt.setString(2, value.getImage());
//                stmt.setString(3, value.getNote());
//                stmt.setLong(4, value.getCreatedTime());
//                stmt.setInt(5, value.getType());
//                stmt.setString(6, value.getOwnerId());
//                stmt.setString(7, value.getEndorserId());
//                stmt.setInt(8, value.getStatus());
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
//    boolean Update(VerifiedCertificate value) {
//
//        boolean result = false;
//        ManagerIF cm = ClientManager.getInstance(ConfigInfo.RIDESHARING_DB);
//
//        Connection cnn = cm.borrowClient();
//        try {
//            String query = ConfigInfo.VERIFIEDCERTIFICATE_UPDATE_QUERY;
//            try (PreparedStatement stmt = cnn.prepareStatement(query)) {
//                stmt.setLong(1, value.getId());
//                stmt.setString(2, value.getImage());
//                stmt.setString(3, value.getNote());
//                stmt.setLong(4, value.getCreatedTime());
//                stmt.setInt(5, value.getType());
//                stmt.setString(6, value.getOwnerId());
//                stmt.setString(7, value.getEndorserId());
//                stmt.setInt(8, value.getStatus());
//                stmt.setLong(9, value.getId());
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
//    boolean Delete(VerifiedCertificate value) {
//
//        boolean result = false;
//        ManagerIF cm = ClientManager.getInstance(ConfigInfo.RIDESHARING_DB);
//
//        Connection cnn = cm.borrowClient();
//        try {
//            String query = ConfigInfo.VERIFIEDCERTIFICATE_DELETE_QUERY;
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
