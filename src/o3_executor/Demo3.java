package o3_executor;

import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;


public class Demo3
{
  public static void main(String[] args) throws Exception
  {
    ExecutorService executor = Executors.newFixedThreadPool(4);
    
    LongAdder counter = new LongAdder();

    List<BigInteger> startValues = List.of( new BigInteger("1000000"), new BigInteger("2000000"), new BigInteger("3000000"), new BigInteger("4000000") );

    startValues.forEach( bInt -> executor.execute( () -> {  
      BigInteger tmp = bInt; 
      for(int i=0;i<1_000_000;i++)
      {
        if( tmp.isProbablePrime(20) )
        {
          counter.increment();
        }
        
        tmp = tmp.add( BigInteger.ONE );
      }
    } ) );
    
    executor.shutdown();
    
    System.out.println("Termination state " + executor.awaitTermination(10, TimeUnit.SECONDS ) );
    System.out.println("Count: " + counter.sum() );
  }

}
