/*    */ package com.bikiegang.ridesharing.annn.framework.queue;
/*    */ 
/*    */ import java.util.concurrent.atomic.AtomicInteger;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ public class QueueWorker implements Runnable
/*    */ {
/*  8 */   private static Logger logger = Logger.getLogger(QueueWorker.class);
/*    */   private Queue queue;
/* 10 */   private long _msleep_idle = 1000L;
/*    */   
/* 12 */   public static AtomicInteger jobRunning = new AtomicInteger();
/*    */   
/*    */   public QueueWorker(Queue queue) {
/* 15 */     this.queue = queue;
/*    */   }
/*    */   
/*    */   public void run() {
/*    */     try {
/*    */       for (;;) {
/* 21 */         QueueCommand command = this.queue.take();
/* 22 */         if (command != null) {
/*    */           try {
/* 24 */             jobRunning.incrementAndGet();
/*    */             
/* 26 */             command.execute();
/*    */           } catch (Exception ex) {
/* 28 */             throw ex;
/*    */           }
/*    */           finally {
/* 31 */             jobRunning.decrementAndGet();
/*    */           }
/*    */           
/*    */         }
/* 35 */         else if (this._msleep_idle > 0L) {
/* 36 */           Thread.sleep(this._msleep_idle);
/*    */         }
/*    */       }
/*    */     }
/*    */     catch (Exception ex) {
/* 41 */       logger.info("Error in exec QueueWorker", ex);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /root/Statup/BE/cloudbike.common/lib/nct.framework.jar!/com/nct/framework/queue/QueueWorker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */