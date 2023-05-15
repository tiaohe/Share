package Concurrency;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @PACKAGE_NAME: com.Concurrency
 * @NAME: FooBar_Lock
 * @USER: 28050
 * @DATE: 2023/2/3
 * @TIME: 13:10
 **/
public class FooBar_Lock {
    private final int n;
    // true 变成公平锁 默认false 非公平
    private final ReentrantLock lock = new ReentrantLock(true);
    private final Condition con = lock.newCondition();
    private volatile boolean foo = true;

    public FooBar_Lock(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            lock.lock();
            try{
                if(!foo){
                    con.await();
                }
                printFoo.run();
                foo = false;
                con.signal();
            }finally{
                lock.unlock();
            }


        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            lock.lock();
            try{
                if(foo){
                    con.await();
                }
                printBar.run();
                foo = true;
                con.signal();
            }finally{
                lock.unlock();
            }

        }
    }
}
