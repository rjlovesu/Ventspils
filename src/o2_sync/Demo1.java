package o2_sync;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Demo1
{
  static class JointCounter
  {
    private Lock lock = new ReentrantLock();
    
    private int value = 0;
    
    int incrementAndGet()
    {
      lock.lock();
      try
      {
        this.value++;
        return value;
      }
      finally {
        lock.unlock();
      }
    }
  }

  public static void main(String[] args)
  {
    // TODO Auto-generated method stub

  }

}
