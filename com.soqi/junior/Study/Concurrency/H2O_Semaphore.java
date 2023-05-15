package Concurrency;

import java.util.concurrent.Semaphore;

/**
 * @PACKAGE_NAME: com.Concurrency
 * @USER: 28050
 * @DATE: 2023/2/4
 * @TIME: 9:09
 **/
class H2O_Semaphore {
    private final Semaphore h = new Semaphore(2);
    private final Semaphore o = new Semaphore(0);

    public H2O_Semaphore() {

    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        h.acquire();
        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        releaseHydrogen.run();
        o.release();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        o.acquire(2);
        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        releaseOxygen.run();
        h.release(2);
    }
}
