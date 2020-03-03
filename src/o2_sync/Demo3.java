package o2_sync;

import java.util.concurrent.locks.StampedLock;

public class Demo3
{
  static class JointCounter
  {
    private StampedLock lock = new StampedLock();

    private int value = 0;

    int getValue()
    {
      long stamp = lock.tryOptimisticRead();
      int tmp = this.value;

      if (!lock.validate(stamp))
      {
        stamp = lock.readLock();
        try
        {
          return this.value;
        } finally {
          lock.unlockRead(stamp);
        }
      }
      else
      {
        return tmp;
      }
    }
    
    void increment()
    {
      long stamp = lock.writeLock();
      try
      {
        this.value++;
      }
      finally {
        lock.unlockWrite(stamp);
      }
    }
  }

  public static void main(String[] args)
  {
    // TODO Auto-generated method stub

  }

}
