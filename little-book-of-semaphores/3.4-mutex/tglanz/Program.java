package tglanz;

import java.util.List;
import java.util.concurrent.Semaphore;

public class Program {

  public static void main(String[] args) throws Exception {

    /*
     * We want:
     *
     * a1 X a2
     */

    var mutex = new Semaphore(1);

    var counter = new int[]{0};

    Runnable runnable = () -> {
      try {
        mutex.acquire();
        counter[0] += 1;
        mutex.release();
      } catch (Exception e) {
        e.printStackTrace();
      }
    };

    var threadA = new Thread(runnable);
    var threadB = new Thread(runnable);

    threadA.start();
    threadB.start();

    threadA.join();
    threadB.join();

    System.out.printf("Counter: %s\n", counter[0]);
  }
}
