package o2_sync;

import java.util.concurrent.atomic.LongAdder;

public class Demo5
{

  public static void main(String[] args) throws InterruptedException
  {
     LongAdder adder = new LongAdder();
     
     Runnable increment = () -> {
       
       for( int i=0; i<1_000_000; i++ )
       {
         adder.add(1L);
       }
     };

     Thread th1 = new Thread( increment );
     Thread th2 = new Thread( increment );
     Thread th3 = new Thread( increment );
     Thread th4 = new Thread( increment );
     Thread th5 = new Thread( increment );
     
     th1.start();
     th2.start();
     th3.start();
     th4.start();
     th5.start();
     
     th1.join();
     th2.join();
     th3.join();
     th4.join();
     th5.join();
     
     System.out.println("Adder value " + adder.sum() );
  }

}
