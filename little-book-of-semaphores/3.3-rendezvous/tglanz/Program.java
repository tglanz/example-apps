package tglanz;

import java.util.List;
import java.util.concurrent.Semaphore;

public class Program {

  record Context(Semaphore arrivedA1, Semaphore arrivedB1) {}

  record WorkA(Context context) implements Runnable {
    @Override
    public void run() {
      System.out.println("a1");

      context().arrivedA1().release();

      try {
        context().arrivedB1().acquire();
      } catch (Exception e) {
        e.printStackTrace();
      }

      System.out.println("a2");
    }
  }

  record WorkB(Context context) implements Runnable {
    @Override
    public void run() {
      System.out.println("b1");

      context().arrivedB1().release();

      try {
        context().arrivedA1().acquire();
      } catch (Exception e) {
        e.printStackTrace();
      }

      System.out.println("b2");
    }
  }

  public static void main(String[] args) throws Exception {

    /*
     * We want:
     *
     * a1 < a2
     * a1 < b2
     *
     * b1 < b2
     * b1 < a2
     */

    var context = new Context(new Semaphore(0), new Semaphore(0));

    var threadA = new Thread(new WorkA(context));
    var threadB = new Thread(new WorkB(context));

    threadA.start();
    threadB.start();

    threadA.join();
    threadB.join();
  }
}
