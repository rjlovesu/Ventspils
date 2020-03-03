package o6_forkjoin;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Demo1
{

  public static void main(String[] args)
  {
    int[] array = new int[100_000];
    Arrays.parallelSetAll(array, i -> ThreadLocalRandom.current().nextInt(0, 1_000_000));
    
    int max = max(array,0,array.length);
    System.out.println("Max = " + max );
  }
  
  private static final int THRESHOLD = 10;
  
  public static int max(int[] array, int start, int end)
  {
    assert start < end;
    
    if( end - start < THRESHOLD )
    {
       int maxIndex = start;
       for(int i=start+1; i<end; i++)
       {
         if( array[i] > array[maxIndex] )
         {
           maxIndex = i;
         }
       }
       return array[maxIndex];
    }
    else
    {
      int mid = (start + end)/2;
      int leftValue = max(array, start, mid);
      int rightValue = max(array, mid, end);
      
      return Math.max(leftValue, rightValue);
    }
  }

}
