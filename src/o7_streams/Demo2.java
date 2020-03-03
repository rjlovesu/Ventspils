package o7_streams;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Demo2
{

  public static void main(String[] args)
  {
    long count = IntStream.range(0, 1_000)
                          .filter(i -> i%42 == 0 )
                          .count();
    
    List<Integer> list = IntStream.range(0, 1_000)
                                  .filter(i -> i%42 == 0 )
                                  .mapToObj( i -> Integer.valueOf(i) )
                                  .collect( Collectors.toList() );
  }

}
