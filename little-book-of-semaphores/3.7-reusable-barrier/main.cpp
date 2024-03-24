#include <algorithm>
#include <iostream>

#include <format>
#include <vector>

#include <semaphore>
#include <thread>

using namespace std;

int main() {
  const int n = 4;
  const int heats = 3;

  std::array<std::thread, n> threads;
  std::counting_semaphore<1> countMutex(1);
  std::counting_semaphore<n> gate(0);
  std::counting_semaphore<n> gate2(0);

  int stateK = 0;

  for (int tid = 0; tid < n; ++tid) {
    threads[tid] = std::thread([&, tid]() {

      for (int heat = 0; heat < heats; ++heat) {
          std::cout << std::format("Heat {}, Phase {}, Thread {}\n", heat, 1, tid);

          countMutex.acquire();
          {
            stateK++;
            if (stateK == n) {
              std::cout << std::format("Heat {}, All threads finished phase {}\n", heat, 1);
              gate.release(n);
            }
            countMutex.release();
          }

          gate.acquire();

          std::cout << std::format("Heat {}, Phase {}, Thread {}\n", heat, 2, tid);

          countMutex.acquire();
          {
            stateK--;
            if (stateK == 0) {
              std::cout << std::format("Heat {}, All threads finished phase {}\n", heat, 2);
              gate2.release(n);
            }
            countMutex.release();
          }

          gate2.acquire();
      }
    });
  }

  for (auto &thread : threads) {
    thread.join();
  }
}
