package o7_streams;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Demo3
{

  public static void main(String[] args)
  {
    List<String> strings = List.of("Hello", "Bon jour", "Hola", "Labdien", "Hei", "Guten Tag");
    
    strings.parallelStream()
           .map( String::toLowerCase )
           .sorted( Comparator.comparing( String::length ))
           .forEach( System.out::println );
    
    
    long count = IntStream.range(0, 1_000)
                          .parallel()
                          .filter(i -> i%42 == 0 )
                          .count();
    
    List<Integer> list = IntStream.range(0, 1_000)
                                  .parallel()
                                  .filter(i -> i%42 == 0 )
                                  .mapToObj( i -> Integer.valueOf(i) )
                                  .collect( Collectors.toList() );
  }

}
