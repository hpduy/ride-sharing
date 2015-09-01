/*    */ package com.bikiegang.ridesharing.annn.framework.queue;
/*    */ 
/*    */ import java.util.Map;
/*    */ import java.util.concurrent.atomic.AtomicInteger;
/*    */ import org.cliffc.high_scale_lib.NonBlockingHashMap;
/*    */ 
/*    */ 
/*    */ public class QueueManager
/*    */ {
/* 10 */   private static Map<String, QueueManager> instances = new NonBlockingHashMap();
/*    */   private Queue queue;
/*    */   
/*    */   public static QueueManager getInstance(String name)
/*    */   {
/* 15 */     QueueManager instance = (QueueManager)instances.get(name);
/* 16 */     if (instance == null) {
/* 17 */       synchronized (QueueManager.class) {
/* 18 */         if (instance == null) {
/* 19 */           instance = new QueueManager();
/* 20 */           instances.put(name, instance);
/*    */         }
/*    */       }
/*    */     }
/* 24 */     return instance;
/*    */   }
/*    */   
/*    */   public void init(int workerNum, int maxLength) {
/* 28 */     this.queue = new QueueImpl(workerNum, maxLength);
/*    */   }
/*    */   
/*    */   public void process() {
/* 32 */     this.queue.process();
/*    */   }
/*    */   
/*    */   public int size() {
/* 36 */     return this.queue.size();
/*    */   }
/*    */   
/*    */   public int remaining() {
/* 40 */     return this.queue.remaining();
/*    */   }
/*    */   
/*    */   public void put(QueueCommand cmd) {
/* 44 */     this.queue.put(cmd);
/*    */   }
/*    */   
/*    */   public boolean hasJobRunning() {
/* 48 */     return (this.queue.size() > 0) && (QueueWorker.jobRunning.get() > 0);
/*    */   }
/*    */ }


/* Location:              /root/Statup/BE/cloudbike.common/lib/nct.framework.jar!/com/nct/framework/queue/QueueManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */