 package com.bikiegang.ridesharing.annn.framework.gearman;
 
 import com.bikiegang.ridesharing.annn.framework.common.Config;
 import com.bikiegang.ridesharing.annn.framework.common.LogUtil;
 import com.bikiegang.ridesharing.annn.framework.util.ConvertUtils;
 import com.bikiegang.ridesharing.annn.framework.util.JSONUtil;
 import java.util.Map;
 import java.util.concurrent.ArrayBlockingQueue;
 import java.util.concurrent.atomic.AtomicLong;
 import org.apache.log4j.Logger;
 import org.cliffc.high_scale_lib.NonBlockingHashMap;
 import org.gearman.client.GearmanClient;
 import org.gearman.client.GearmanClientImpl;
 import org.gearman.client.GearmanJob;
 import org.gearman.client.GearmanJobImpl;
 import org.gearman.client.GearmanJobResult;
 import org.gearman.common.GearmanJobServerConnection;
 import org.gearman.common.GearmanNIOJobServerConnection;
 
 public class GClientManager
 {
   private static Logger logger = LogUtil.getLogger(GClientManager.class);
   private static Map<String, GClientManager> instances = new NonBlockingHashMap();
   private final AtomicLong totalQueueErrors = new AtomicLong();
   private ArrayBlockingQueue<GearmanClient> queue;
   private Integer maxQueue;
   private Integer minQueue;
   private String host;
   private Integer port;
   private String function;
 
   public static GClientManager getInstance(String name)
   {
     GClientManager instance = (GClientManager)instances.get(name);
     if (instance == null) {
       synchronized (GClientManager.class) {
         instance = (GClientManager)instances.get(name);
         if (instance == null) {
           instance = new GClientManager(name);
           instances.put(name, instance);
         }
       }
     }
     return instance;
   }
 
   public GClientManager(String name)
   {
     this.host = Config.getParam(name, "host");
     this.port = Integer.valueOf(ConvertUtils.toInt(Config.getParam(name, "port"), 4730));
 
     this.maxQueue = Integer.valueOf(ConvertUtils.toInt(Config.getParam(name, "maxqueue"), 1024));
     this.minQueue = Integer.valueOf(ConvertUtils.toInt(Config.getParam(name, "minqueue"), 256));
 
     this.function = Config.getParam(name, "function");
 
     this.queue = new ArrayBlockingQueue(this.maxQueue.intValue());
   }
 
   private GearmanClient borrowClient() {
     GearmanClient client = null;
     if (this.queue.size() > 0)
       synchronized (GClientManager.class) {
         try {
           client = (GearmanClient)this.queue.take();
         }
         catch (InterruptedException ex) {
         }
       }
     if (client == null) {
       client = new GearmanClientImpl();
       GearmanJobServerConnection connection = new GearmanNIOJobServerConnection(this.host, this.port.intValue());
       client.addJobServer(connection);
     }
 
     return client;
   }
 
   private void returnClient(GearmanClient client) {
     try {
       if ((client != null) && (this.queue.size() <= this.maxQueue.intValue()))
         this.queue.put(client);
       else
         destroyClient(client);
     }
     catch (InterruptedException e) {
       logger.error("Exception in put", e);
     }
   }
 
   private void destroyClient(GearmanClient client) {
     if (client != null) {
       client.shutdown();
       client = null;
     }
   }
 
   public boolean push(JobEnt jobIn)
   {
     String jsonData = JSONUtil.Serialize(jobIn);
 
     byte[] data = ConvertUtils.encodeString(jsonData);
 
     return push(data);
   }
 
   public boolean push(JobEnt jobIn, String function)
   {
     String jsonData = JSONUtil.Serialize(jobIn);
 
     byte[] data = ConvertUtils.encodeString(jsonData);
 
     return push(data, function);
   }
 
   private boolean push(byte[] data) {
     return push(data, this.function);
   }
 
   private boolean push(byte[] data, String function) {
     boolean result = false;
     GearmanClient client = borrowClient();
     try {
       GearmanJob job = GearmanJobImpl.createBackgroundJob(function, data, "");
       client.submit(job);
       GearmanJobResult res = (GearmanJobResult)job.get();
       result = res.jobSucceeded();
       returnClient(client);
     } catch (Exception ex) {
       this.totalQueueErrors.incrementAndGet();
       logger.error(ex);
       result = false;
       destroyClient(client);
     }
     return result;
   }
 
   public Long getTotalErrors()
   {
     return Long.valueOf(this.totalQueueErrors.get());
   }
 }

/* 
 * Qualified Name:     com.annn.framework.gearman.GClientManager
 * 
 */