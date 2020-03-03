package o1_intro;

public class Demo2
{
  public static void main(String[] args)
  {
    Runnable task = () ->  System.out.println("Hello from " + Thread.currentThread().getName());
    
    Thread th = new Thread( task, "MyThread" );
    th.start();
        
    System.out.println("Hello from " + Thread.currentThread().getName());
  }
}
