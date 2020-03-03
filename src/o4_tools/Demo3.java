package o4_tools;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

class SimpleUninterruptableBoundedBuffer<E>
{
  private final List<E> buffer;
  private final Semaphore availableSpace;
  private final Semaphore availableItems;
  
  public SimpleUninterruptableBoundedBuffer(int capacity)
  {
    this.buffer = Collections.synchronizedList(new  LinkedList<E>());
    this.availableSpace = new Semaphore(capacity);
    this.availableItems = new Semaphore(0);
  }
  
  public void put(E element)
  {
    this.availableSpace.acquireUninterruptibly();
    this.buffer.add(element);
    this.availableItems.release();
  }
  
  public E take()
  {
    this.availableItems.acquireUninterruptibly();
    E element = this.buffer.remove(0);
    this.availableSpace.release();
    return element;
  }
}

public class Demo3
{

  public static void main(String[] args)
  {
    SimpleUninterruptableBoundedBuffer<String> boundedBuffer = new SimpleUninterruptableBoundedBuffer<>(10);
    String stop = "STOP";
    AtomicInteger globalCounter = new AtomicInteger(0);
    
    Runnable producer = () -> {
      String name = Thread.currentThread().getName();
      for(int i=0; i<100; i++)
      {
        boundedBuffer.put( name + " " + i );
      }
      boundedBuffer.put(stop);
    };
    
    Runnable consumer = () -> {
      int counter = 0;
      while(true)
      {
        if( boundedBuffer.take() == stop )
          break;
        else
          counter++;
      }
      System.out.println("Count : " + counter );
      globalCounter.addAndGet(counter);
    };

    ExecutorService executor = Executors.newCachedThreadPool();
    
    for(int i=0; i<10; i++)
    {
      executor.execute( producer );
      executor.execute( consumer );
    }
    
    executor.shutdown();
    
    // Spin wait 
    while( executor.isTerminated() == false );
    
    System.out.println("Global count " + globalCounter.get() );
  }
}
