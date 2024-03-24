#include <iostream>

#include <semaphore>
#include <thread>

using namespace std;

int main() {

  std::counting_semaphore<1> arrivedA1(0);
  std::counting_semaphore<1> arrivedB1(0);

  std::thread thread1([&]() {
    cout << "a1" << endl;
    arrivedA1.release();
    arrivedB1.acquire();
    cout << "a2" << endl;
  });

  std::thread thread2([&]() {
    cout << "b1" << endl;
    arrivedB1.release();
    arrivedA1.acquire();
    cout << "b2" << endl;
  });

  thread1.join();
  thread2.join();
}
