 package com.bikiegang.ridesharing.annn.framework.gearman;
 
 import com.bikiegang.ridesharing.annn.framework.common.LogUtil;
 import java.util.Map;
 import java.util.Map.Entry;
 import org.apache.log4j.Logger;
 import org.gearman.common.GearmanNIOJobServerConnection;
 import org.gearman.worker.DefaultGearmanFunctionFactory;
 import org.gearman.worker.GearmanWorker;
 import org.gearman.worker.GearmanWorkerImpl;
 
 public class GWorkerRunner2
   implements Runnable
 {
   private static Logger logger = LogUtil.getLogger(GWorkerRunner.class);
   GearmanNIOJobServerConnection conn;
   Map<String, String> mapFunctions;
   GearmanWorker worker;
 
   public GWorkerRunner2(String host, int port, Map<String, String> funs)
   {
     this.conn = new GearmanNIOJobServerConnection(host, port);
     this.mapFunctions = funs;
     this.worker = new GearmanWorkerImpl();
   }
 
   public void run()
   {
     try {
       this.worker.addServer(this.conn);
 
       for (Map.Entry entry : this.mapFunctions.entrySet()) {
         DefaultGearmanFunctionFactory gearFunc = new DefaultGearmanFunctionFactory((String)entry.getKey(), (String)entry.getValue());
         this.worker.registerFunctionFactory(gearFunc);
       }
       this.worker.work();
     }
     catch (Exception ex) {
       logger.error(LogUtil.stackTrace(ex));
     }
   }
 
   public void stop() {
     this.worker.stop();
   }
 
   public boolean isRunning() {
     return this.worker.isRunning();
   }
 }

/* 
 * Qualified Name:     com.annn.framework.gearman.GWorkerRunner2
 * 
 */