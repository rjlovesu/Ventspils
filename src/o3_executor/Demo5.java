package o3_executor;

import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;


public class Demo5
{
  public static void main(String[] args) throws Exception
  {
    ExecutorService executor = Executors.newFixedThreadPool(4);
    
    List<BigInteger> startValues = List.of( new BigInteger("1000000"), new BigInteger("2000000"), new BigInteger("3000000"), new BigInteger("4000000") );

    List<Future<Integer>> futures =
    startValues.stream().map( bInt -> executor.submit( () -> {  
      int counter = 0;
      BigInteger tmp = bInt; 
      for(int i=0;i<1_000_000;i++)
      {
        if( tmp.isProbablePrime(20) )
        {
          counter++;
        }
        
        tmp = tmp.add( BigInteger.ONE );
      }
      return counter;
    } ) ).collect( Collectors.toList() );
    
 
    int sum = 0;
    for( var future : futures )
    {
      sum += future.get();
    }
    
    System.out.println("Count: " + sum );
    
    executor.shutdown();
  }

}
