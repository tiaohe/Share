package Concurrency;

import java.util.Arrays;
import java.util.concurrent.locks.ReentrantLock;



class DiningPhilosophers_Lock {
    private final ReentrantLock[]mutes;

    public DiningPhilosophers_Lock() {
        this.mutes = new ReentrantLock[5];
        for(int i = 0; i < 5; i++){
            this.mutes[i] = new ReentrantLock();
        }
    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
        int[] forkNumbes= {philosopher, (philosopher + 1) % 5};
        Arrays.sort(forkNumbes);
        this.mutes[forkNumbes[0]].lock();
        this.mutes[forkNumbes[1]].lock();
        eat(pickLeftFork,pickRightFork,eat,putRightFork,putLeftFork);
        this.mutes[forkNumbes[1]].unlock();
        this.mutes[forkNumbes[0]].unlock();
    }

    private void eat(Runnable ... actions){
        for(Runnable act : actions){
            act.run();
        }
    }
}

