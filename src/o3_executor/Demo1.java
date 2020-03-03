package o3_executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo1
{

  public static void main(String[] args)
  {
    ExecutorService executor = Executors.newFixedThreadPool(4);
    
    Runnable task = () -> {  /* ... */ };

    executor.execute( task );
    
    executor.shutdown();
  }

}
