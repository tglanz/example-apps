package tglanz;

import java.util.concurrent.Semaphore;
import java.util.ArrayList;
import java.util.List;

public class Program {

  static class SharedState {
    public int currentWaitCount = 0;
  }

  static record Context(
      int groupSize,
      SharedState state,
      Semaphore waitCountLock,
      Semaphore continueLock
    ) {}

  record Work(Context context) implements Runnable {
    @Override
    public void run() {

      try {
        var threadName = Thread.currentThread().getName();
        System.out.printf("Thread %s is at section A\n", threadName);

        context.waitCountLock.acquire();
        context.state.currentWaitCount++;
        context.waitCountLock.release();

        System.out.printf("wait count: %s\n", context.state.currentWaitCount);
        if (context.state.currentWaitCount == context.groupSize()) {
          context.continueLock.release();
        }

        context.continueLock.acquire();
        context.continueLock.release();
        System.out.printf("Thread %s at section B\n", threadName);
      } catch (Exception e) {
        e.printStackTrace();
      }

    }
  }

  public static void main(String[] args) throws Exception {

    final var groupSize = 10;
    final SharedState state = new SharedState();
    final Context context = new Context(groupSize, state, new Semaphore(1), new Semaphore(0));

    var threads = new ArrayList<Thread>(groupSize);
    for (int idx = 0; idx < groupSize; ++idx) {
      threads.add(new Thread(new Work(context)));
    }

    for (var thread : threads) {
      thread.start();
    }

    for (var thread : threads) {
      thread.join();
    }
  }
}
