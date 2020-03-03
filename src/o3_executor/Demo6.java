package o3_executor;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class Demo6
{

  public static void main(String[] args) throws Exception
  {
    int parallel = Math.max(1, Runtime.getRuntime().availableProcessors() - 1 );
    int bitLen = 4048;

    List<Callable<BigInteger>> tasks = new ArrayList<>();
    for (int i = 0; i < parallel; i++)
    {
      tasks.add(() -> BigInteger.probablePrime(bitLen, ThreadLocalRandom.current()));
    }
    
    ExecutorService executor = Executors.newFixedThreadPool(parallel);
    CompletionService<BigInteger> completionService = new ExecutorCompletionService<>(executor);
    tasks.forEach(completionService::submit);

    for (int i = 0; i < 2; i++)
    {
      System.out.println( completionService.take().get().bitLength() );
    }

    executor.shutdownNow();
  }

}
