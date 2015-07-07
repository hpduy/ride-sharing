package com.bikiegang.ridesharing.annn.framework.memcache;

import com.bikiegang.ridesharing.annn.framework.common.Config;
import com.bikiegang.ridesharing.annn.framework.common.LogUtil;
import com.bikiegang.ridesharing.annn.framework.util.ConvertUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.log4j.Logger;
import org.cliffc.high_scale_lib.NonBlockingHashMap;
import redis.clients.jedis.BinaryClient;
import redis.clients.jedis.BinaryClient.LIST_POSITION;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Transaction;

public class RedisClient {

    private static Logger logger = LogUtil.getLogger(RedisClient.class);
    private static final Lock createLock_ = new ReentrantLock();
    private static Map<String, RedisClient> instances = new NonBlockingHashMap();
    private JedisPool pooledJedis;
    private String host;
    private Integer port;
    private Integer maxPool;
    private Integer timeout = Integer.valueOf(5000);

    public static RedisClient getInstance(String instanceName) {
        if (!instances.containsKey(instanceName)) {
            try {
                createLock_.lock();
                if (!instances.containsKey(instanceName)) {
                    instances.put(instanceName, new RedisClient(instanceName));
                }
            } finally {
                createLock_.unlock();
            }
        }
        return (RedisClient) instances.get(instanceName);
    }

    public RedisClient(String instanceName) {
        this.host = Config.getParam(instanceName, "host");
        this.port = Integer.valueOf(ConvertUtils.toInt(Config.getParam(instanceName, "port"), 4730));

        this.maxPool = Integer.valueOf(ConvertUtils.toInt(Config.getParam(instanceName, "maxpool"), 1024));
        this.timeout = Integer.valueOf(ConvertUtils.toInt(Config.getParam(instanceName, "timeout"), 5000));

        JedisPoolConfig jConfig = new JedisPoolConfig();
        jConfig.maxIdle = this.maxPool.intValue();
        jConfig.minIdle = 0;
        jConfig.maxActive = -1;
        jConfig.maxWait = -1L;
        jConfig.whenExhaustedAction = 2;
        jConfig.testOnBorrow = true;
        jConfig.testOnReturn = true;
        jConfig.testWhileIdle = true;
        jConfig.timeBetweenEvictionRunsMillis = 5000L;
        jConfig.numTestsPerEvictionRun = -1;
        jConfig.minEvictableIdleTimeMillis = 3600000L;
        jConfig.softMinEvictableIdleTimeMillis = -1L;
        jConfig.lifo = false;

        this.pooledJedis = new JedisPool(jConfig, this.host, this.port.intValue(), this.timeout.intValue());
    }

    protected Jedis borrowClient() {
        Jedis client = (Jedis) this.pooledJedis.getResource();

        return client;
    }

    protected void returnClient(Jedis client) {
        this.pooledJedis.returnResource(client);
    }

    protected void invalidClient(Jedis client) {
        this.pooledJedis.returnBrokenResource(client);
    }

    public List<String> lrange(String key, long start, long end) throws Exception {
        Jedis client = null;
        try {
            client = borrowClient();
            List result = client.lrange(key, (int) start, (int) end);
            returnClient(client);

            return result;
        } catch (Exception ex) {
            invalidClient(client);
            throw ex;
        }
    }

    public Long lpush(String key, String[] strings) throws Exception {
        Jedis client = null;
        try {
            client = borrowClient();
            Long result = client.lpush(key, strings);
            returnClient(client);

            return result;
        } catch (Exception ex) {
            invalidClient(client);
            throw ex;
        }
    }

    public Long linsert(String key, BinaryClient.LIST_POSITION where, String pivot, String value) throws Exception {
        Jedis client = null;
        try {
            client = borrowClient();
            Long result = client.linsert(key, where, pivot, value);
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
            Long result = client.llen(key);
            returnClient(client);

            return result;
        } catch (Exception ex) {
            invalidClient(client);
            throw ex;
        }
    }

    public Long lrem(String key, int count, String value) throws Exception {
        Jedis client = null;
        try {
            client = borrowClient();
            Long result = client.lrem(key, count, value);
            returnClient(client);

            return result;
        } catch (Exception ex) {
            invalidClient(client);
            throw ex;
        }
    }

    public boolean set(String key, String value) throws Exception {
        Jedis client = null;
        try {
            client = borrowClient();
            boolean result = "OK".equals(client.set(key, value));
            returnClient(client);

            return result;
        } catch (Exception ex) {
            invalidClient(client);
            throw ex;
        }
    }

    public long setnx(String key, String value) throws Exception {
        Jedis client = null;
        try {
            client = borrowClient();
            long result = client.setnx(key, value).longValue();
            returnClient(client);

            return result;
        } catch (Exception ex) {
            invalidClient(client);
            throw ex;
        }
    }

    public String get(String key) throws Exception {
        Jedis client = null;
        try {
            client = borrowClient();
            String result = client.get(key);
            returnClient(client);
            return result;
        } catch (Exception ex) {
            invalidClient(client);
            throw ex;
        }
    }

    public List<String> get(String[] keys) throws Exception {
        Jedis client = null;
        try {
            client = borrowClient();
            List result = client.mget(keys);
            returnClient(client);

            return result;
        } catch (Exception ex) {
            invalidClient(client);
            throw ex;
        }
    }

    public Long incr(String key) throws Exception {
        Jedis client = null;
        try {
            client = borrowClient();
            Long result = client.incr(key);
            returnClient(client);

            return result;
        } catch (Exception ex) {
            invalidClient(client);
            throw ex;
        }
    }

    public Long incrBy(String key, long by) throws Exception {
        Jedis client = null;
        try {
            client = borrowClient();
            Long result = client.incrBy(key, by);
            returnClient(client);

            return result;
        } catch (Exception ex) {
            invalidClient(client);
            throw ex;
        }
    }

    public Long decr(String key)
            throws Exception {
        Jedis client = null;
        try {
            client = borrowClient();
            Long result = client.decr(key);
            returnClient(client);

            return result;
        } catch (Exception ex) {
            invalidClient(client);
            throw ex;
        }
    }

    public Long decrBy(String key, long by) throws Exception {
        Jedis client = null;
        try {
            client = borrowClient();
            Long result = client.decrBy(key, by);
            returnClient(client);

            return result;
        } catch (Exception ex) {
            invalidClient(client);
            throw ex;
        }
    }

    public Double zincrby(String key, double score, String member) throws Exception {
        Jedis client = null;
        try {
            client = borrowClient();
            Double result = client.zincrby(key, score, member);
            returnClient(client);

            return result;
        } catch (Exception ex) {
            invalidClient(client);
            throw ex;
        }
    }

    public Set<String> zrevrange(String key, long start, long end) throws Exception {
        Jedis client = null;
        try {
            client = borrowClient();
            Set result = client.zrevrange(key, start, end);
            returnClient(client);

            return result;
        } catch (Exception ex) {
            invalidClient(client);
            throw ex;
        }
    }

    public Set<String> zrangebyscore(String key, double min, double max) throws Exception {
        Jedis client = null;
        try {
            client = borrowClient();
            Set result = client.zrangeByScore(key, min, max);
            returnClient(client);

            return result;
        } catch (Exception ex) {
            invalidClient(client);
            throw ex;
        }
    }

    public Set<String> zrangebyscore(String key, double max, double min, int offset, int count) throws Exception {
        Jedis client = null;
        try {
            client = borrowClient();
            Set result = client.zrangeByScore(key, max, min, offset, count);
            returnClient(client);

            return result;
        } catch (Exception ex) {
            invalidClient(client);
            throw ex;
        }
    }

    public Set<String> zrevrangebyscore(String key, double max, double min) throws Exception {
        Jedis client = null;
        try {
            client = borrowClient();
            Set result = client.zrevrangeByScore(key, max, min);
            returnClient(client);

            return result;
        } catch (Exception ex) {
            invalidClient(client);
            throw ex;
        }
    }

    public Set<String> zrevrangebyscore(String key, double max, double min, int offset, int count) throws Exception {
        Jedis client = null;
        try {
            client = borrowClient();
            Set result = client.zrevrangeByScore(key, max, min, offset, count);
            returnClient(client);

            return result;
        } catch (Exception ex) {
            invalidClient(client);
            throw ex;
        }
    }

    public Long zadd(String key, double score, String member) throws Exception {
        Jedis client = null;
        try {
            client = borrowClient();
            Long result = client.zadd(key, score, member);
            returnClient(client);

            return result;
        } catch (Exception ex) {
            invalidClient(client);
            throw ex;
        }
    }

    public Long zadd(String key, Map<Double, String> scoreMembers) throws Exception {
        Jedis client = null;
        try {
            client = borrowClient();
            Long result = client.zadd(key, scoreMembers);
            returnClient(client);

            return result;
        } catch (Exception ex) {
            invalidClient(client);
            throw ex;
        }
    }

    public Double zscore(String key, String member) throws Exception {
        Jedis client = null;
        try {
            client = borrowClient();
            Double result = client.zscore(key, member);
            returnClient(client);

            return result;
        } catch (Exception ex) {
            invalidClient(client);
            throw ex;
        }
    }

    public Long zrem(String key, String[] members) throws Exception {
        Jedis client = null;
        try {
            client = borrowClient();
            Long result = client.zrem(key, members);
            returnClient(client);

            return result;
        } catch (Exception ex) {
            invalidClient(client);
            throw ex;
        }
    }

    public long zcount(String key, double min, double max) throws Exception {
        Jedis client = null;
        try {
            client = borrowClient();
            Long result = client.zcount(key, min, max);
            returnClient(client);

            return result.longValue();
        } catch (Exception ex) {
            invalidClient(client);
            throw ex;
        }
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

    public Long delete(String[] keys) throws Exception {
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

    public Long hset(String key, String field, String value) throws Exception {
        Jedis client = null;
        try {
            client = borrowClient();
            Long result = client.hset(key, field, value);
            returnClient(client);

            return result;
        } catch (Exception ex) {
            invalidClient(client);
            throw ex;
        }
    }

    public Long hsetnx(String key, String field, String value) throws Exception {
        Jedis client = null;
        try {
            client = borrowClient();
            Long result = client.hsetnx(key, field, value);
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
            Long result = client.hdel(key, field);
            returnClient(client);

            return result;
        } catch (Exception ex) {
            invalidClient(client);
            throw ex;
        }
    }

    public String hget(String key, String field) throws Exception {
        Jedis client = null;
        try {
            client = borrowClient();
            String result = client.hget(key, field);
            returnClient(client);

            return result;
        } catch (Exception ex) {
            invalidClient(client);
            throw ex;
        }
    }

    public Map<String, String> hgetAll(String key) throws Exception {
        Jedis client = null;
        try {
            client = borrowClient();
            Map result = new HashMap();
            Map<String, String> map = client.hgetAll(key);
            Set<Map.Entry<String, String>> set = map.entrySet();
            for (Map.Entry entry : set) {
                result.put(entry.getKey(), entry.getValue());
            }
            returnClient(client);

            return result;
        } catch (Exception ex) {
            invalidClient(client);
            throw ex;
        }
    }

    public Transaction beginTransaction() throws Exception {
        Jedis client = null;
        try {
            client = borrowClient();
            return client.multi();
        } catch (Exception ex) {
            invalidClient(client);
            throw ex;
        }
    }

    public boolean commit(Transaction trans) {
        if (trans == null) {
            return false;
        }
        try {
            trans.exec();
            return true;
        } catch (Exception ex) {
            logger.error(LogUtil.stackTrace(ex));
        }
        return false;
    }

    public boolean rollback(Transaction trans) {
        if (trans == null) {
            return false;
        }
        try {
            trans.discard();
            return true;
        } catch (Exception ex) {
            logger.error(LogUtil.stackTrace(ex));
        }
        return false;
    }

    public boolean set(Transaction trans, String key, String value) throws Exception {
        if (trans == null) {
            return false;
        }
        try {
            trans.set(key, value);
            return true;
        } catch (Exception ex) {
            logger.error(LogUtil.stackTrace(ex));
        }
        return false;
    }

    public long setnx(Transaction trans, String key, String value) throws Exception {
        if (trans == null) {
            return 0L;
        }
        try {
            trans.setnx(key, value);
            return 1L;
        } catch (Exception ex) {
            logger.error(LogUtil.stackTrace(ex));
        }
        return 0L;
    }

    public long delete(Transaction trans, String[] keys) throws Exception {
        if (trans == null) {
            return 0L;
        }
        try {
            trans.del(keys);
            return 1L;
        } catch (Exception ex) {
            logger.error(LogUtil.stackTrace(ex));
        }
        return 0L;
    }

    public long lpush(Transaction trans, String key, String value) throws Exception {
        if (trans == null) {
            return 0L;
        }
        try {
            trans.lpush(key, value);
            return 1L;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public long linsert(Transaction trans, String key, BinaryClient.LIST_POSITION where, String pivot, String value) throws Exception {
        if (trans == null) {
            return 0L;
        }
        try {
            trans.linsert(key, where, pivot, value);
            return 1L;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public Long lrem(Transaction trans, String key, int count, String value) throws Exception {
        if (trans == null) {
            return Long.valueOf(0L);
        }
        try {
            trans.lrem(key, count, value);
            return Long.valueOf(1L);
        } catch (Exception ex) {
            throw ex;
        }
    }
}

/* 
 * Qualified Name:     com.annn.framework.memcache.RedisClient
 * 
 */
