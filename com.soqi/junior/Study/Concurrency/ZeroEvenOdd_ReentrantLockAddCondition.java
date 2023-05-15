package Concurrency;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

/**
 * @PACKAGE_NAME: com.Concurrency
 * @NAME: ZeroEvenOdd_ReentrantLockAddCondition
 * @USER: 28050
 * @DATE: 2023/2/3
 * @TIME: 13:12
 **/
class ZeroEvenOdd_ReentrantLockAddCondition {
    private final int n;
    private volatile int flag = 1;
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public ZeroEvenOdd_ReentrantLockAddCondition(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for(int i = 1; i <= n; i++) {
            lock.lock();
            printNumber.accept(0);
            ++flag;
            condition.signalAll();
            while (flag % 2 == 0) condition.await();
            lock.unlock();
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for(int i = 2; i <= n; i += 2){
            lock.lock();
            while(flag % 4 != 0)
                condition.await();
            printNumber.accept(i);
            ++flag;
            condition.signalAll();
            lock.unlock();
        }

    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for(int i = 1; i <= n; i += 2){
            lock.lock();
            while(flag % 4 != 2)
                condition.await();
            printNumber.accept(i);
            ++flag;
            condition.signalAll();
            lock.unlock();
        }
    }
}
