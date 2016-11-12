package noweWatkiwJSE6;
// http://www.informit.com/guides/content.aspx?g=java&seqNum=487
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
public class nowySposobNaWatki  implements Callable<Integer> 
{
  private int number;
 
  public nowySposobNaWatki( int number )
  {
    this.number = number;
  }
 
    public int getNumber()
  {
    return number;
  }
    @Override
    public Integer call() throws Exception {
    try
    {
      for( int i=0; i<5; i++ ) 
      {
        System.out.println( "Thread: " + number + "-" + i );
        Thread.sleep( 100 );
      }
    }
    catch( Exception e )
 {
      e.printStackTrace();
    }
    return number;
  }
 
 
    public static void main( String[] args )
  {
    System.out.println( "Start" );
 
      ExecutorService executorService = Executors.newFixedThreadPool( 5 );
    List<Future<Integer>> futureList = new ArrayList<Future<Integer>>();
    for( int i=0; i<10; i++ ) 
    {
    	nowySposobNaWatki ee = new nowySposobNaWatki( i );
    	futureList.add( executorService.submit( ee ) );
    }
 
       // Wait for the threads to be complete
    boolean liveThreads = true;
    while( liveThreads )
    {
      System.out.println( "Checking to see if any of the threads are running..." );
      liveThreads = false;
      for( Future<Integer> future : futureList )
      {
        if( !future.isDone() )
        {
          liveThreads = true;
        }
      }
      try
      {
        Thread.sleep( 500 );
      }
      catch( Exception e )
      {
        e.printStackTrace();
      }
    }
    System.out.println( "Results: " );
    for( Future<Integer> future : futureList )
    {
      try
      {
        System.out.println( "WResult: " + future.get() );
      }
      catch( Exception e )
      {
        e.printStackTrace();
      }
    }
    System.out.println( "Done" );
  }
}