 package com.bikiegang.ridesharing.annn.framework.memcache;

 import com.bikiegang.ridesharing.annn.framework.common.LogUtil;
 import com.bikiegang.ridesharing.annn.framework.util.ConvertUtils;
 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import java.util.Map.Entry;
 import java.util.Set;
 import org.apache.log4j.Logger;
import redis.clients.jedis.BinaryClient;
 import redis.clients.jedis.BinaryClient.LIST_POSITION;
 import redis.clients.jedis.Jedis;
 import redis.clients.jedis.Transaction;

 public class RedisClientBin  {
     static final Logger logger = LogUtil.getLogger(RedisClientBin.class);
        private RedisClient redisClient;
    
     public static RedisClientBin getInstance(String instanceName)  {
         return new RedisClientBin(instanceName);
            }
    
     private RedisClientBin(String instanceName) {
         this.redisClient = RedisClient.getInstance(instanceName);
            }
    
     private Jedis borrowClient() {
         return this.redisClient.borrowClient();
            }
    
     private void returnClient(Jedis client) {
         this.redisClient.returnClient(client);
            }
    
     private void invalidClient(Jedis client) {
         this.redisClient.invalidClient(client);
            }
    
     private byte[] string2ByteArray(String key) {
         return key.getBytes();
            }
    
     private byte[][] string2ByteArray(String[] keys) {
         List ls = new ArrayList();
         for (int i = 0; i < keys.length; i++) {
             ls.add(keys[i].getBytes());
                    }
         return (byte[][]) ls.toArray();
            }
    
     private String byteArray2String(byte[] b) {
         return new String(b);
            }
    
     public Long expire(String key, int seconds) throws Exception {
         Jedis client = null;
         try {
             client = borrowClient();
             Long result = client.expire(key, seconds);
             returnClient(client);
            
             return result;
                    } catch (Exception ex) {
             invalidClient(client);
             throw ex;
                    }
            }
    
     public boolean set(String key, byte[] value) throws Exception {
         Jedis client = null;
         try  {
             client = borrowClient();
             boolean result = "OK".equals(client.set(string2ByteArray(key), value));
             returnClient(client);
            
             return result;
                    } catch (Exception ex) {
             invalidClient(client);
             throw ex;
                    }
            }
    
     public Long del(String[] keys) throws Exception {
         Jedis client = null;
         try {
             client = borrowClient();
             Long result = client.del(keys);
             returnClient(client);
            
             return result;
                    } catch (Exception ex) {
             invalidClient(client);
             throw ex;
                    }
            }
    
     public boolean setnx(String key, byte[] value) throws Exception {
         Jedis client = null;
         try {
             client = borrowClient();
             byte[] keyBin = ConvertUtils.encodeString(key);
             long result = client.setnx(keyBin, value).longValue();
             returnClient(client);
            
             return result == 1L;
                    } catch (Exception ex) {
             invalidClient(client);
             throw ex;
                    }
            }
    
     public boolean append(String key, byte[] value) throws Exception {
         Jedis client = null;
         try {
             client = borrowClient();
             byte[] keyBin = ConvertUtils.encodeString(key);
             long result = client.append(keyBin, value).longValue();
             returnClient(client);
            
             return result > 0L;
                    } catch (Exception ex) {
             invalidClient(client);
             throw ex;
                    }
            }
    
     public byte[] get(String key) throws Exception {
         Jedis client = null;
         try  {
             client = borrowClient();
             byte[] result = client.get(string2ByteArray(key));
             returnClient(client);
            
             return result;
                    } catch (Exception ex) {
             invalidClient(client);
             throw ex;
                    }
            }
    
     public List<byte[]> get(String[] keys) throws Exception {
         Jedis client = null;
         try {
             client = borrowClient();
             byte[][] arrKeys = new byte[keys.length][];
             for (int i = 0; i < keys.length; i++) {
                 arrKeys[i] = keys[i].getBytes();
                            }
             List result = client.mget(arrKeys);
             returnClient(client);
             return result;
                    }  catch (Exception ex) {
             invalidClient(client);
             throw ex;
                    }
            }
    
     public Long zadd(String key, double score, byte[] member) throws Exception {
         Jedis client = null;
         try  {
             client = borrowClient();
             Long result = client.zadd(string2ByteArray(key), score, member);
             returnClient(client);
            
             return result;
                    } catch (Exception ex) {
             invalidClient(client);
             throw ex;
                    }
            }
    
     public Long zadd(String key, Map<Double, byte[]> scoreMembers) throws Exception {
         Jedis client = null;
         try  {
             client = borrowClient();
             Long result = client.zadd(string2ByteArray(key), scoreMembers);
             returnClient(client);
            
             return result;
                    } catch (Exception ex) {
             invalidClient(client);
             throw ex;
                    }
            }
    
     public Long zrem(String key, byte[][] members) throws Exception {
         Jedis client = null;
         try {
             client = borrowClient();
             Long result = client.zrem(string2ByteArray(key), members);
             returnClient(client);
            
             return result;
                    } catch (Exception ex) {
             invalidClient(client);
             throw ex;
                    }
            }
    
     public Double zincrby(String key, double score, byte[] member) throws Exception {
         Jedis client = null;
         try  {
             client = borrowClient();
             Double result = client.zincrby(string2ByteArray(key), score, member);
             returnClient(client);
            
             return result;
                    } catch (Exception ex) {
             invalidClient(client);
             throw ex;
                    }
            }
    
     public Double zscore(String key, byte[] member) throws Exception {
         Jedis client = null;
         try  {
             client = borrowClient();
             Double result = client.zscore(string2ByteArray(key), member);
             returnClient(client);
            
             return result;
                    } catch (Exception ex) {
             invalidClient(client);
             throw ex;
                    }
            }
    
     public Set<byte[]> zrevrange(String key, long start, long end) throws Exception  {
         Jedis client = null;
         try  {
             client = borrowClient();
             Set result = client.zrevrange(string2ByteArray(key), (int) start, (int) end);
             returnClient(client);
            
             return result;
                    } catch (Exception ex) {
             invalidClient(client);
             throw ex;
                    }
            }
    
     public Set<byte[]> zrevrangebyscore(String key, double max, double min) throws Exception  {
         Jedis client = null;
         try  {
             client = borrowClient();
             Set result = client.zrevrangeByScore(string2ByteArray(key), max, min);
             returnClient(client);
            
             return result;
                    } catch (Exception ex) {
             invalidClient(client);
             throw ex;
                    }
            }
    
     public Set<byte[]> zrevrangebyscore(String key, double max, double min, int offset, int count) throws Exception  {
         Jedis client = null;
         try  {
             client = borrowClient();
             Set result = client.zrevrangeByScore(string2ByteArray(key), max, min, offset, count);
             returnClient(client);
            
             return result;
                    } catch (Exception ex) {
             invalidClient(client);
             throw ex;
                    }
            }
    
     public Set<byte[]> zrange(String key, long start, long end) throws Exception  {
         Jedis client = null;
         try  {
             client = borrowClient();
             Set result = client.zrange(string2ByteArray(key), (int) start, (int) end);
             returnClient(client);
            
             return result;
                    } catch (Exception ex) {
             invalidClient(client);
             throw ex;
                    }
            }
    
     public Set<byte[]> zrangebyscore(String key, double min, double max) throws Exception  {
         Jedis client = null;
         try  {
             client = borrowClient();
             Set result = client.zrangeByScore(string2ByteArray(key), min, max);
             returnClient(client);
            
             return result;
                    } catch (Exception ex) {
             invalidClient(client);
             throw ex;
                    }
            }
    
     public Set<byte[]> zrangebyscore(String key, double max, double min, int offset, int count) throws Exception  {
         Jedis client = null;
         try  {
             client = borrowClient();
             Set result = client.zrangeByScore(string2ByteArray(key), max, min, offset, count);
             returnClient(client);
            
             return result;
                    } catch (Exception ex) {
             invalidClient(client);
             throw ex;
                    }
            }
    
     public long zcount(String key, double min, double max) throws Exception  {
         Jedis client = null;
         try  {
             client = borrowClient();
             Long result = client.zcount(string2ByteArray(key), min, max);
             returnClient(client);
            
             return result.longValue();
                    } catch (Exception ex) {
             invalidClient(client);
             throw ex;
                    }
            }
    
     public Long zrank(String key, byte[] member) throws Exception {
         Jedis client = null;
         try  {
             client = borrowClient();
             Long result = client.zrank(string2ByteArray(key), member);
             returnClient(client);
            
             return result;
                    } catch (Exception ex) {
             invalidClient(client);
             throw ex;
                    }
            }
    
     public Long zcard(String key) throws Exception {
         Jedis client = null;
         try  {
             client = borrowClient();
             Long result = client.zcard(string2ByteArray(key));
             returnClient(client);
            
             return result;
                    } catch (Exception ex) {
             invalidClient(client);
             throw ex;
                    }
            }
    
     public List<byte[]> lrange(String key, long start, long end) throws Exception  {
         Jedis client = null;
         try  {
             client = borrowClient();
             List result = client.lrange(string2ByteArray(key), (int) start, (int) end);
             returnClient(client);
            
             return result;
                    } catch (Exception ex) {
             invalidClient(client);
             throw ex;
                    }
            }
    
     public Long lpush(String key, byte[][] strings) throws Exception {
         Jedis client = null;
         try {
             client = borrowClient();
             Long result = client.lpush(string2ByteArray(key), strings);
             returnClient(client);
            
             return result;
                    } catch (Exception ex) {
             invalidClient(client);
             throw ex;
                    }
            }
    
     public Long linsert(String key, BinaryClient.LIST_POSITION where, byte[] pivot, byte[] value) throws Exception {
         Jedis client = null;
         try {
             client = borrowClient();
             Long result = client.linsert(string2ByteArray(key), where, pivot, value);
             returnClient(client);
            
             return result;
                    } catch (Exception ex) {
             invalidClient(client);
             throw ex;
                    }
            }
    
     public Long llen(String key) throws Exception {
         Jedis client = null;
         try {
             client = borrowClient();
             Long result = client.llen(string2ByteArray(key));
             returnClient(client);
            
             return result;
                    } catch (Exception ex) {
             invalidClient(client);
             throw ex;
                    }
            }
    
     public Long lrem(String key, int count, byte[] value) throws Exception {
         Jedis client = null;
         try {
             client = borrowClient();
             Long result = client.lrem(string2ByteArray(key), count, value);
             returnClient(client);
            
             return result;
                    } catch (Exception ex) {
             invalidClient(client);
             throw ex;
                    }
            }
    
     public Long hset(String key, String field, byte[] value) throws Exception {
         Jedis client = null;
         try {
             client = borrowClient();
             Long result = client.hset(string2ByteArray(key), string2ByteArray(field), value);
             returnClient(client);
            
             return result;
                    } catch (Exception ex) {
             invalidClient(client);
             throw ex;
                    }
            }
    
     public Long hsetnx(String key, String field, byte[] value) throws Exception {
         Jedis client = null;
         try {
             client = borrowClient();
             Long result = client.hsetnx(string2ByteArray(key), string2ByteArray(field), value);
             returnClient(client);
            
             return result;
                    } catch (Exception ex) {
             invalidClient(client);
             throw ex;
                    }
            }
    
     public Long hdel(String key, String[] field) throws Exception {
         Jedis client = null;
         try {
             client = borrowClient();
             Long result = client.hdel(string2ByteArray(key), string2ByteArray(field));
             returnClient(client);
            
             return result;
                    } catch (Exception ex) {
             invalidClient(client);
             throw ex;
                    }
            }
    
     public byte[] hget(String key, String field) throws Exception {
         Jedis client = null;
         try {
             client = borrowClient();
             byte[] result = client.hget(string2ByteArray(key), string2ByteArray(field));
             returnClient(client);
            
             return result;
                    } catch (Exception ex) {
             invalidClient(client);
             throw ex;
                    }
            }
    
     public Map<String, byte[]> hgetAll(String key) throws Exception {
         Jedis client = null;
         try {
             client = borrowClient();
             Map result = new HashMap();
             Map<byte[], byte[]> map = client.hgetAll(string2ByteArray(key));
             Set<Entry<byte[], byte[]>> set = map.entrySet();
            for (Map.Entry entry : set) {
                 result.put(byteArray2String((byte[]) entry.getKey()), entry.getValue());
                            }
             returnClient(client);
            
             return result;
                    } catch (Exception ex) {
             invalidClient(client);
             throw ex;
                    }
            }
    
     public Transaction beginTransaction() throws Exception  {
         Jedis client = null;
         try {
             client = borrowClient();
             return client.multi();
                    }  catch (Exception ex) {
             invalidClient(client);
             throw ex;
                    }
            }
    
     public boolean commit(Transaction trans) {
         if (trans == null)  {
            return false;
        }
         try  {
             trans.exec();
             return true;
                    } catch (Exception ex) {
             logger.error(LogUtil.stackTrace(ex));
                    }
         return false;
            }
    
     public boolean rollback(Transaction trans) {
         if (trans == null)  {
            return false;
        }
         try  {
             trans.discard();
             return true;
                    } catch (Exception ex) {
             logger.error(LogUtil.stackTrace(ex));
                    }
         return false;
            }
    
     public boolean set(Transaction trans, String key, byte[] value) throws Exception {
         if (trans == null)  {
            return false;
        }
         try  {
             trans.set(string2ByteArray(key), value);
             return true;
                    } catch (Exception ex) {
             logger.error(LogUtil.stackTrace(ex));
                    }
        return false;
            }
    
     public long setnx(Transaction trans, String key, byte[] value) throws Exception  {
         if (trans == null)  {
            return 0L;
        }
         try  {
             trans.setnx(string2ByteArray(key), value);
             return 1L;
                    } catch (Exception ex) {
             logger.error(LogUtil.stackTrace(ex));
                    }
        return 0L;
            }
    
     public long delete(Transaction trans, String[] keys) throws Exception  {
         if (trans == null)  {
            return 0L;
        }
         try  {
             byte[][] arr = new byte[keys.length][];
             for (int i = 0; i < keys.length; i++) {
                 arr[i] = string2ByteArray(keys[i]);
                            }
             trans.del(arr);
             return 1L;
                    } catch (Exception ex) {
             logger.error(LogUtil.stackTrace(ex));
                    }
        return 0L;
            }
    
     public long lpush(Transaction trans, String key, byte[] value) throws Exception  {
         if (trans == null)  {
            return 0L;
        }
         try  {
             trans.lpush(string2ByteArray(key), value);
             return 1L;
                    } catch (Exception ex) {
             throw ex;
                    }
            }
    
     public long linsert(Transaction trans, String key, BinaryClient.LIST_POSITION where, byte[] pivot, byte[] value) throws Exception {
         if (trans == null)  {
            return 0L;
        }
         try  {
             trans.linsert(string2ByteArray(key), where, pivot, value);
             return 1L;
                    } catch (Exception ex) {
             throw ex;
                    }
            }
    
     public Long lrem(Transaction trans, String key, int count, byte[] value) throws Exception {
         if (trans == null)  {
            return Long.valueOf(0L);
        }
         try  {
             trans.lrem(string2ByteArray(key), count, value);
             return Long.valueOf(1L);
                    } catch (Exception ex) {
             throw ex;
                    }
            }
     }

/* 
 * Qualified Name:     com.annn.framework.memcache.RedisClientBin
 * 
 */
