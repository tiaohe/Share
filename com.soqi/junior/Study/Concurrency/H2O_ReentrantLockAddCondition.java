package Concurrency;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @PACKAGE_NAME: com.Concurrency
 * @NAME: H2O_ReentrantLockAddCondition
 * @USER: 28050
 * @DATE: 2023/2/4
 * @TIME: 9:14
 **/
class H2O_ReentrantLockAddCondition {
    int hNum = 0;
    ReentrantLock lock = new ReentrantLock();
    Condition oZ = lock.newCondition();
    Condition hZ = lock.newCondition();

    public H2O_ReentrantLockAddCondition() {

    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        lock.lock();
        try{
            while(hNum == 2){
                hZ.signal();
                oZ.await();
            }
            // releaseHydrogen.run() outputs "H". Do not change or remove this line.
            releaseHydrogen.run();
            hNum++;
            if(hNum == 2)
                hZ.signal();
        }finally {
            lock.unlock();
        }

    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        lock.lock();
        try{
            while(hNum != 2)
                hZ.await();

            // releaseOxygen.run() outputs "O". Do not change or remove this line.
            releaseOxygen.run();
            hNum = 0;
            oZ.signalAll();
        }finally {
            lock.unlock();
        }


    }
}
