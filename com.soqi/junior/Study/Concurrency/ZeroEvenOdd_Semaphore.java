package Concurrency;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * @PACKAGE_NAME: com.Concurrency
 * @NAME: ZeroEvenOdd_Semaphore
 * @USER: 28050
 * @DATE: 2023/2/3
 * @TIME: 13:39
 **/
//超时
public class ZeroEvenOdd_Semaphore {
    private final int n;
    private final Semaphore zero = new Semaphore(1);
    private final Semaphore even = new Semaphore(0);
    private final Semaphore odd = new Semaphore(0);

    public ZeroEvenOdd_Semaphore(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for(int i = 1; i <= n; i++){
            zero.acquire();
            printNumber.accept(0);
            if(i % 2 == 1) {
                odd.release();
            }else{
                even.release();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for(int i = 2; i <= n; i += 2){
            even.acquire();
            printNumber.accept(i);
            zero.release();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for(int i = 1; i <= 2; i += 2){
            odd.acquire();
            printNumber.accept(i);
            zero.release();
        }
    }
}
