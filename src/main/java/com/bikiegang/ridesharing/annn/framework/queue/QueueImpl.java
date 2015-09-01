/*    */ package com.bikiegang.ridesharing.annn.framework.queue;
/*    */ 
/*    */ import java.util.concurrent.ArrayBlockingQueue;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ public class QueueImpl
/*    */   implements Queue
/*    */ {
/*  9 */   private static Logger logger = Logger.getLogger(QueueImpl.class);
/*    */   private ArrayBlockingQueue<QueueCommand> queue;
/*    */   private int workerNum;
/*    */   private int maxLength;
/*    */   private long msleepIdle;
/*    */   
/*    */   public QueueImpl(int workerNum, int maxLength) {
/* 16 */     this.workerNum = workerNum;
/* 17 */     this.maxLength = maxLength;
/* 18 */     this.msleepIdle = this.msleepIdle;
/* 19 */     this.queue = new ArrayBlockingQueue(maxLength);
/*    */   }
/*    */   
/*    */   public int size() {
/* 23 */     return this.queue.size();
/*    */   }
/*    */   
/*    */   public boolean put(QueueCommand i) {
/* 27 */     if (this.queue.remainingCapacity() < this.workerNum) {
/* 28 */       logger.error("Queue exceed max length!!!!!!");
/* 29 */       return false;
/*    */     }
/* 31 */     boolean ret = false;
/*    */     try {
/* 33 */       this.queue.put(i);
/* 34 */       ret = true;
/*    */     } catch (InterruptedException e) {
/* 36 */       logger.error("Exception in put", e);
/*    */     }
/* 38 */     return ret;
/*    */   }
/*    */   
/*    */   public QueueCommand take() {
/* 42 */     QueueCommand cmd = null;
/*    */     try {
/* 44 */       cmd = (QueueCommand)this.queue.take();
/*    */     } catch (InterruptedException e) {
/* 46 */       logger.error("Exception in take", e);
/*    */     }
/* 48 */     return cmd;
/*    */   }
/*    */   
/*    */   public void process() {
/* 52 */     for (int i = 0; i < this.workerNum; i++) {
/* 53 */       QueueWorker qw = new QueueWorker(this);
/* 54 */       new Thread(qw).start();
/*    */     }
/*    */   }
/*    */   
/*    */   public int getWorkerNum() {
/* 59 */     return this.workerNum;
/*    */   }
/*    */   
/*    */   public int getMaxLength() {
/* 63 */     return this.maxLength;
/*    */   }
/*    */   
/*    */   public int remaining() {
/* 67 */     return this.queue.remainingCapacity();
/*    */   }
/*    */ }


/* Location:              /root/Statup/BE/cloudbike.common/lib/nct.framework.jar!/com/nct/framework/queue/QueueImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */