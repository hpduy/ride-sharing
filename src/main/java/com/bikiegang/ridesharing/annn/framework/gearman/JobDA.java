 package com.bikiegang.ridesharing.annn.framework.gearman;
 
 import com.bikiegang.ridesharing.annn.framework.common.Config;
 import com.bikiegang.ridesharing.annn.framework.common.LogUtil;
 import com.bikiegang.ridesharing.annn.framework.dbconn.ClientManager;
 import com.bikiegang.ridesharing.annn.framework.dbconn.ManagerIF;
 import com.bikiegang.ridesharing.annn.framework.util.ConvertUtils;
 import java.sql.Connection;
 import java.sql.PreparedStatement;
 import java.sql.ResultSet;
 import java.sql.SQLException;
 import java.util.ArrayList;
 import java.util.List;
 import org.apache.log4j.Logger;
 
 public class JobDA
 {
   private static Logger logger = LogUtil.getLogger(JobDA.class);
   private static final String DEFAULT_TABLE_NAME = "data-log";
   private static final String INSERT_QUERY = "INSERT INTO `%s` (`UserId`,`ItemId`,`ItemKey`,`ActionType`,`Timestamp`,`DataLog`,`Note`,`ClientIP`)VALUES (?,?,?,?,?,?,?,?)";
   private static final String SELECT_BYID_QUERY = "SELECT `JobId`, `UserId`,`ItemId`,`ItemKey`,`ActionType`,`Timestamp`,`DataLog`,`Note`,`ClientIP` FROM `%s` WHERE `ItemID`=? ORDERBY `JobId` DESC";
   private static final String SELECT_BYKEY_QUERY = "SELECT `JobId`, `UserId`,`ItemId`,`ItemKey`,`ActionType`,`Timestamp`,`DataLog`,`Note`,`ClientIP` FROM `%s` WHERE `ItemKey`=? ORDERBY `JobId` DESC";
   private String instanceName;
   private String tableName;
 
   public JobDA(String instanceName)
   {
     this.instanceName = instanceName;
     this.tableName = getTableName(instanceName);
   }
 
   public boolean Insert(JobEnt job)
   {
     boolean result = false;
 
     ManagerIF cm = ClientManager.getInstance(this.instanceName);
     Connection cnn = cm.borrowClient();
     try
     {
       String query = String.format("INSERT INTO `%s` (`UserId`,`ItemId`,`ItemKey`,`ActionType`,`Timestamp`,`DataLog`,`Note`,`ClientIP`)VALUES (?,?,?,?,?,?,?,?)", new Object[] { this.tableName });
       PreparedStatement stmt = cnn.prepareStatement(query, 1);
       stmt.setLong(1, job.UserId.longValue());
       stmt.setLong(2, job.ItemId.longValue());
       stmt.setString(3, job.ItemKey);
       stmt.setShort(4, job.ActionType);
       stmt.setLong(5, job.Timestamp.longValue());
       stmt.setString(6, job.DataLog);
       stmt.setString(7, job.Note);
       stmt.setString(8, job.ClientIP);
 
       int row = stmt.executeUpdate();
       ResultSet resultSet = null;
       if (row > 0) {
         resultSet = stmt.getGeneratedKeys();
         resultSet.next();
         job.JobId = Long.valueOf(resultSet.getLong(1));
         result = true;
         resultSet.close();
       }
 
       stmt.close();
     } catch (Exception e) {
       logger.error(e.getMessage());
     } finally {
       cm.returnClient(cnn);
     }
 
     return result;
   }
 
   public List<JobEnt> getLogMulti(Long itemId) {
     List result = new ArrayList();
     String query = String.format("SELECT `JobId`, `UserId`,`ItemId`,`ItemKey`,`ActionType`,`Timestamp`,`DataLog`,`Note`,`ClientIP` FROM `%s` WHERE `ItemID`=? ORDERBY `JobId` DESC", new Object[] { this.tableName });
 
     ManagerIF cm = ClientManager.getInstance(this.instanceName);
     Connection cnn = cm.borrowClient();
     try
     {
       PreparedStatement stmt = cnn.prepareStatement(query);
       stmt.setLong(1, itemId.longValue());
       ResultSet resultSet = stmt.executeQuery();
       while (resultSet.next()) {
         result.add(createJobEntFromDB(resultSet));
       }
       resultSet.close();
       stmt.close();
     } catch (Exception e) {
       logger.error(e.getMessage());
     } finally {
       cm.returnClient(cnn);
     }
 
     return result;
   }
 
   public List<JobEnt> getLogMulti(String itemKey) {
     List result = new ArrayList();
     String query = String.format("SELECT `JobId`, `UserId`,`ItemId`,`ItemKey`,`ActionType`,`Timestamp`,`DataLog`,`Note`,`ClientIP` FROM `%s` WHERE `ItemKey`=? ORDERBY `JobId` DESC", new Object[] { this.tableName });
 
     ManagerIF cm = ClientManager.getInstance(this.instanceName);
     Connection cnn = cm.borrowClient();
     try
     {
       PreparedStatement stmt = cnn.prepareStatement(query);
       stmt.setString(1, itemKey);
       ResultSet resultSet = stmt.executeQuery();
       while (resultSet.next()) {
         result.add(createJobEntFromDB(resultSet));
       }
       resultSet.close();
       stmt.close();
     } catch (Exception e) {
       logger.error(e.getMessage());
     } finally {
       cm.returnClient(cnn);
     }
 
     return result;
   }
 
   private static String getTableName(String configDB) {
     return ConvertUtils.toString(Config.getParam(configDB, "tablename"), "data-log");
   }
 
   private static JobEnt createJobEntFromDB(ResultSet rs) throws SQLException
   {
     JobEnt result = new JobEnt();
     result.JobId = Long.valueOf(rs.getLong(1));
     result.UserId = Long.valueOf(rs.getLong(2));
     result.ItemId = Long.valueOf(rs.getLong(3));
     result.ItemKey = rs.getString(4);
     result.ActionType = rs.getShort(5);
     result.Timestamp = Long.valueOf(rs.getLong(6));
     result.DataLog = rs.getString(7);
     result.Note = rs.getString(8);
     result.ClientIP = rs.getString(9);
     return result;
   }
 }

/* 
 * Qualified Name:     com.annn.framework.gearman.JobDA
 * 
 */