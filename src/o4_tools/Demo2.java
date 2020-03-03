package o4_tools;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Demo2
{

  public static void main(String[] args)
  {
     int[] array = new int[1_000_000];
     
     // set the array -> 0,1,2,3,4,5,...
     Arrays.parallelSetAll(array, i -> i );
     
     // set the array with random numbers
     Arrays.parallelSetAll(array, i -> ThreadLocalRandom.current().nextInt(1000) );
     
     // sort the array
     Arrays.parallelSort(array);
  }

}
