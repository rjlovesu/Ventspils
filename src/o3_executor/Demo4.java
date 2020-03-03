package o3_executor;

import java.math.BigInteger;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Demo4
{

  public static void main(String[] args) throws Exception
  {
    ExecutorService executor = Executors.newFixedThreadPool(4);
    
    Callable<Integer> task = () -> {  
      
      int counter = 0;
      for(int i = 1_000_000; i < 2_000_000; i++)
      {
        if( new BigInteger( String.valueOf(i)).isProbablePrime(20) )
          counter++;
      }
      return counter;
    };

    Future<Integer> future = executor.submit( task );
    
    // get is blocking
    System.out.println("Number of primes " + future.get() );
    
    executor.shutdown();
  }

}
