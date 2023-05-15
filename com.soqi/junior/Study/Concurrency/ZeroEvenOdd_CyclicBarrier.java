package Concurrency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.function.IntConsumer;

/**
 * @PACKAGE_NAME: com.Concurrency
 * @NAME: ZeroEvenOdd_CyclicBarrier
 * @USER: 28050
 * @DATE: 2023/2/3
 * @TIME: 13:22
 **/

//超时
class ZeroEvenOdd_CyclicBarrier {
    private final int n;
    private final CyclicBarrier[] cbs = new CyclicBarrier[3];

    public ZeroEvenOdd_CyclicBarrier(int n) {
        this.n = n;
        for (int i = 0; i < cbs.length; i++)
            cbs[i] = new CyclicBarrier(2);
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            printNumber.accept(0);
            try {
                if (i % 2 == 1) {
                    cbs[1].await();
                } else {
                    cbs[2].await();
                }
                cbs[0].await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {

            try {
                cbs[2].await();
                printNumber.accept(i);
                cbs[0].await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for(int i = 1; i < n; i += 2){
            try {
                cbs[1].await();
                printNumber.accept(i);
                cbs[0].await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}

