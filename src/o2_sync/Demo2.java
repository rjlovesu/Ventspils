package o2_sync;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


public class Demo2
{
  static class JointCounter
  {
    private ReadWriteLock rwLock = new ReentrantReadWriteLock();
    private Lock rdLock = rwLock.readLock();
    private Lock wtLock = rwLock.writeLock();
    
    private int value = 0;
    
    void increment()
    {
      wtLock.lock();
      try
      {
        this.value++;
      }
      finally {
        wtLock.unlock();
      }
    }
    
    int getValue()
    {
      rdLock.lock();
      try
      {
        return this.value;
      }
      finally {
        rdLock.unlock();
      }
    }
  }

  public static void main(String[] args)
  {
    // TODO Auto-generated method stub

  }

}
