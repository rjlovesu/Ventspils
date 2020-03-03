package o4_tools;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SimpleSemaphore
{
  private final int capacity;
  private int counter = 0;
  private final Lock lock;
  private final Condition ticketsAvailable;
  
  public SimpleSemaphore(int capacity)
  {
    this.capacity = capacity;
    this.lock = new ReentrantLock(true);
    this.ticketsAvailable = this.lock.newCondition();
  }
  
  public void aquire() throws InterruptedException
  {
    this.lock.lock();
    try
    {
      while( counter >= capacity )
        this.ticketsAvailable.await();
      
      counter++;
    }
    finally
    {
      this.lock.unlock();
    }
  }
  
  public void release()
  {
    this.lock.lock();
    try
    {
      counter--;
      this.ticketsAvailable.signalAll();
    }
    finally
    {
      this.lock.unlock();
    }
  }
}
