package tglanz;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Program {

  public static void main(String[] args) throws Exception {

    /*
     * We want:
     *
     * a1 X a2
     */

    var maxAmountOfThreadsInCriticalSection = 10;
    var multiplex = new Semaphore(maxAmountOfThreadsInCriticalSection);
    var counter = new int[]{0};

    var threadCount = 20;

    Runnable runnable = () -> {
      try {
        multiplex.acquire();
        counter[0] += 1;
        multiplex.release();
      } catch (Exception e) {
        e.printStackTrace();
      }
    };

    var threads = new ArrayList<Thread>(threadCount);
    for (int idx = 0; idx < threadCount; ++idx) {
      threads.add(new Thread(runnable));
    }

    for (var thread : threads) {
      thread.start();
    }

    for (var thread : threads) {
      thread.join();
    }

    System.out.printf("Counter: %s\n", counter[0]);
  }
}
