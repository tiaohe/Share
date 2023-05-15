package Concurrency;

import java.util.function.IntConsumer;

/**
 * @PACKAGE_NAME: com.leetcode
 * @NAME: FizzBuzz_Semaphore
 * @USER: 28050
 * @DATE: 2023/2/6
 * @TIME: 13:01
 **/

//有问题
class FizzBuzz_wait {
    private final int n;
    private volatile int cnt = 1;

    public FizzBuzz_wait(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        while(cnt <= n){
            if(cnt % 3 != 0 || cnt % 5 == 0){
                wait();
                continue;
            }
            printFizz.run();
            ++cnt;
            notifyAll();
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        while(cnt <= n){
            if(cnt % 3 == 0 || cnt % 5 == 0){
                wait();
                continue;
            }

            printBuzz.run();
            ++cnt;
            notifyAll();
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while(cnt <= n){
            if(cnt % 15 != 0){
                wait();
                continue;
            }
            printFizzBuzz.run();
            ++cnt;
            notifyAll();
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        while(cnt <= n){
            if(cnt % 3 == 0 || cnt % 5 == 0){
                wait();
                continue;
            }
            printNumber.accept(cnt);
            ++cnt;
            notifyAll();
        }
    }
}

