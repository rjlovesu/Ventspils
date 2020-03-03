package o4_tools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Person{}

public class Demo1
{

  public static void main(String[] args)
  {
    List<Person> liste = new ArrayList<>();
    List<Person> syncListe = Collections.synchronizedList(liste);

    Map<String,String> map = new HashMap<>();
    Map<String,String> syncMap = Collections.synchronizedMap(map);
    
    Collections.unmodifiableList(liste);

  }

}
