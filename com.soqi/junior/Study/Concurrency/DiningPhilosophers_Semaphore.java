package Concurrency;

import java.util.concurrent.Semaphore;

/**
 * @PACKAGE_NAME: com.Concurrency
 * @NAME: DiningPhilosophers_Semaphore
 * @USER: 28050
 * @DATE: 2023/2/7
 * @TIME: 13:00
 **/
class DiningPhilosophers_Semaphore {
    private final Semaphore mutex;
    private final Semaphore[]sema;
    public DiningPhilosophers_Semaphore() {
        mutex = new Semaphore(1);
        sema = new Semaphore[]{
                new Semaphore(1),
                new Semaphore(1),
                new Semaphore(1),
                new Semaphore(1),
                new Semaphore(1)
        };

    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {

        mutex.acquire();
        sema[philosopher].acquire();
        sema[(philosopher + 1) % 5].acquire();

        pickLeftFork.run();
        pickRightFork.run();

        eat.run();

        putLeftFork.run();
        sema[philosopher].release();
        putRightFork.run();
        sema[(philosopher + 1) % 5].release();
        mutex.release();

    }
}
