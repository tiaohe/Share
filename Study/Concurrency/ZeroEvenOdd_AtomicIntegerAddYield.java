package Concurrency;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntConsumer;

/**
 * @PACKAGE_NAME: com.Concurrency
 * @NAME: ZeroEvenOdd_AtomicIntegerAddYield
 * @USER: 28050
 * @DATE: 2023/2/3
 * @TIME: 13:02
 **/
class ZeroEvenOdd_AtomicIntegerAddYield {
    private final int n;
    private final AtomicInteger d = new AtomicInteger(1);

    public ZeroEvenOdd_AtomicIntegerAddYield(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for(int i = 1; i <= n; i++){
            while(d.get() % 2 == 0)
                Thread.yield();
            printNumber.accept(0);
            d.getAndIncrement();
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for(int i = 2; i <= n; i += 2){
            while(d.get() % 4 != 0)
                Thread.yield();
            printNumber.accept(i);
            d.getAndIncrement();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for(int i = 1; i <= n; i += 2){
            while(d.get() % 4 != 2)
                Thread.yield();
            printNumber.accept(i);
            d.getAndIncrement();
        }
    }
}
