 package com.bikiegang.ridesharing.annn.framework.gearman;
 
 public class JobEnt
 {
   public Long JobId;
   public Long UserId;
   public Long ItemId;
   public String ItemKey;
   public short ActionType;
   public Long Timestamp;
   public String DataLog;
   public String Data;
   public String Note;
   public String ClientIP;
   public String ConfigParam;
 
   public JobEnt()
   {
     this.JobId = Long.valueOf(0L);
     this.UserId = Long.valueOf(0L);
     this.ItemId = Long.valueOf(0L);
     this.ItemKey = "";
     this.ActionType = 0;
     this.Timestamp = Long.valueOf(System.currentTimeMillis());
     this.DataLog = "";
     this.Data = "";
     this.Note = "";
     this.ClientIP = "";
     this.ConfigParam = "";
   }
 
   public JobEnt(Long userId, Long itemId, String itemKey, short actionType, Long timestamp, String dataLog, String data, String note, String clientIP)
   {
     this.JobId = Long.valueOf(0L);
     this.UserId = userId;
     this.ItemId = itemId;
     this.ItemKey = itemKey;
     this.ActionType = actionType;
     this.Timestamp = timestamp;
     this.DataLog = dataLog;
     this.Data = data;
     this.Note = note;
     this.ClientIP = clientIP;
   }
 
   public JobEnt(Long userId, Long itemId, String itemKey, short actionType, Long timestamp, String dataLog, String data, String note, String clientIP, String ConfigParam)
   {
     this.JobId = Long.valueOf(0L);
     this.UserId = userId;
     this.ItemId = itemId;
     this.ItemKey = itemKey;
     this.ActionType = actionType;
     this.Timestamp = timestamp;
     this.DataLog = dataLog;
     this.Data = data;
     this.Note = note;
     this.ClientIP = clientIP;
     this.ConfigParam = ConfigParam;
   }
 }

/* 
 * Qualified Name:     com.annn.framework.gearman.JobEnt
 * 
 */