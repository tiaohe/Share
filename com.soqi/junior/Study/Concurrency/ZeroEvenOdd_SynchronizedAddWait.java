package Concurrency;

import java.util.function.IntConsumer;

/**
 * @PACKAGE_NAME: com.Concurrency
 * @NAME: ZeroEvenOdd_SynchronizedAddWait
 * @USER: 28050
 * @DATE: 2023/2/3
 * @TIME: 12:47
 **/

//synchronized
class ZeroEvenOdd_SynchronizedAddWait {
    private final int n;
    private volatile int flag = 1;

    public ZeroEvenOdd_SynchronizedAddWait(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for(int i = 1; i <= n; i++){
            synchronized(this){
                while(flag % 2 == 0)
                    wait();

                printNumber.accept(0);
                flag++;
                notifyAll();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for(int i = 2; i <= n; i += 2){
            synchronized(this){
                while(flag % 4 != 0)
                    wait();
                printNumber.accept(i);
                flag++;
                notifyAll();
            }
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for(int i = 1; i <= n; i += 2){
            synchronized(this){
                while(flag % 4 != 2)
                    wait();
                printNumber.accept(i);
                flag++;
                notifyAll();
            }
        }
    }
}
