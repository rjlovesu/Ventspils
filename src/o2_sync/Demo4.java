package o2_sync;

import java.util.concurrent.atomic.AtomicInteger;

public class Demo4
{
  static class JointCounter
  {
    private AtomicInteger counter = new AtomicInteger(0);


    int getValue()
    {
      return this.counter.get();
    }
    
    void increment()
    {
      this.counter.incrementAndGet();
    }
  }

  public static void main(String[] args)
  {
    // TODO Auto-generated method stub

  }

}
