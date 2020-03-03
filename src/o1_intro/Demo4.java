package o1_intro;

public class Demo4
{
  static class JointCounter
  {
    private int value = 0;
    
    synchronized void increment()
    {
      this.value++;
    }
  }
  
  public static void main(String[] args) throws InterruptedException
  {
    JointCounter counter = new JointCounter();
    
    Runnable task = () -> {
      for(int i=0; i < 5_000; i++ )
        counter.increment();
    };

    Thread th1 = new Thread( task );
    Thread th2 = new Thread( task );
    
    th1.start();
    th2.start();
    
    th1.join();
    th2.join();
    
    System.out.println("Value " + counter.value );
  }
}
