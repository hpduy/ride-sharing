package com.bikiegang.ridesharing.annn.framework.queue;

public abstract interface Queue
{
  public abstract QueueCommand take();
  
  public abstract boolean put(QueueCommand paramQueueCommand);
  
  public abstract void process();
  
  public abstract int size();
  
  public abstract int remaining();
  
  public abstract int getWorkerNum();
  
  public abstract int getMaxLength();
}


/* Location:              /root/Statup/BE/cloudbike.common/lib/nct.framework.jar!/com/nct/framework/queue/Queue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */