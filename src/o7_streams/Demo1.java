package o7_streams;

import java.util.Comparator;
import java.util.List;

public class Demo1
{

  public static void main(String[] args)
  {
    List<String> strings = List.of("Hello", "Bon jour", "Hola", "Labdien", "Hei", "Guten Tag");
    
    strings.stream()
           .map( String::toLowerCase )
           .sorted( Comparator.comparing( String::length ))
           .forEach( System.out::println );
  }

}
