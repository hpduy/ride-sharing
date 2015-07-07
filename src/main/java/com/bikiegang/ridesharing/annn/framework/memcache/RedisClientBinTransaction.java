 package com.bikiegang.ridesharing.annn.framework.memcache;

 import com.bikiegang.ridesharing.annn.framework.common.LogUtil;
 import java.util.HashMap;
 import java.util.Iterator;
 import java.util.Map;
 import java.util.Map.Entry;
 import java.util.Set;
 import org.apache.log4j.Logger;

 public class RedisClientBinTransaction  {
     static Logger _logger = Logger.getLogger(RedisClientBinTransaction.class);
        RedisClientBin _client = null;
        Map<String, TaskEnt> _tasks = new HashMap();
    
     public RedisClientBinTransaction(RedisClientBin client) throws Exception {
         if (client == null) {
             throw new Exception("Client can not be null.");
                    }
         this._client = client;
            }
    
     public boolean add(String key, byte[] value) throws Exception {
         if (this._client.setnx(key, value)) {
             if (!this._tasks.containsKey(key)) {
                 TaskEnt item = new TaskEnt(TaskDef.DEL, key, value);
                 this._tasks.put(key, item);
                            }
             return true;
                    }
         return false;
            }
    
     public boolean set(String key, byte[] value) throws Exception {
         if (!this._tasks.containsKey(key)) {
             byte[] oldValue = this._client.get(key);
             TaskDef action = TaskDef.SET;
             if (oldValue == null) {
                 action = TaskDef.DEL;
                            }
             if (this._client.set(key, value)) {
                 TaskEnt item = new TaskEnt(action, key, oldValue);
                 this._tasks.put(key, item);
                 return true;
                            }
                    }  else {
             return this._client.set(key, value);
                    }
         return false;
            }
    
     public boolean delete(String key) throws Exception {
         if (!this._tasks.containsKey(key)) {
             byte[] oldValue = this._client.get(key);
             if (oldValue == null) {
                 return false;
                            }
             if (this._client.del(new String[]{key}).longValue() > 0L) {
                 TaskEnt item = new TaskEnt(TaskDef.ADD, key, oldValue);
                 this._tasks.put(key, item);
                 return true;
                            }
                    }  else {
             return this._client.del(new String[]{key}).longValue() > 0L;
                    }
        
         return false;
            }
    
     public boolean append(String key, byte[] value) throws Exception {
         if (!this._tasks.containsKey(key)) {
             byte[] oldValue = this._client.get(key);
             TaskDef action = TaskDef.SET;
             if (oldValue == null) {
                 action = TaskDef.DEL;
                            }
             if (this._client.append(key, value)) {
                 TaskEnt item = new TaskEnt(action, key, oldValue);
                 this._tasks.put(key, item);
                 return true;
                            }
                    }  else {
             return this._client.append(key, value);
                    }
         return false;
            }
    
        void executeTask(TaskEnt item)  {
         try  {
             switch (item.action.ordinal()) {
                 case 1:
                     this._client.setnx(item.key, item.value);
                     break;
                 case 2:
                     this._client.append(item.key, item.value);
                     break;
                 case 3:
                     this._client.del(new String[]{item.key});
                     break;
                 case 4:
                     this._client.set(item.key, item.value);
                            }
                    }  catch (Exception ex) {
             _logger.error(LogUtil.stackTrace(ex));
                    }
            }
    
     public void rollback() {
         try {
             Iterator iter = this._tasks.entrySet().iterator();
             while (iter.hasNext()) {
                 Map.Entry entry = (Map.Entry) iter.next();
                 executeTask((TaskEnt) entry.getValue());
                 iter.remove();
                            }
                    }  catch (Exception ex)  {
                    }
            }
    
     class TaskEnt  {
         public RedisClientBinTransaction.TaskDef action;
                public String key;
                public byte[] value;
        
         public TaskEnt(RedisClientBinTransaction.TaskDef action, String key, byte[] value)  {
             this.action = action;
             this.key = key;
             this.value = value;
                    }
            }
    
     public static enum TaskDef  {
         ADD(1), SET(2), DEL(3), APPEND(4), INCR(5), DECR(6);
        
                private int code;
        
         private TaskDef(int c) {
            this.code = c;
        }
        
         public int TaskDef()  {
             return this.code;
                    }
            }
     }

/* 
 * Qualified Name:     com.annn.framework.memcache.RedisClientBinTransaction
 * 
 */
