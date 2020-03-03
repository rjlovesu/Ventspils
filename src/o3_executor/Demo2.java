package o3_executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class Demo2
{

  public static void main(String[] args)
  {
    ExecutorService executor = Executors.newFixedThreadPool(4, new ThreadFactory()
    {      
      @Override
      public Thread newThread(Runnable worker)
      {
        Thread th = new Thread(worker);
        th.setDaemon(true);
        return th;
      }
    });
    
    Runnable task = () -> {  /* ... */ };

    executor.execute( task );
   
  }

}
