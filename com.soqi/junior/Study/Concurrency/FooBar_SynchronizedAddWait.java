package Concurrency;

/**
 * @PACKAGE_NAME: com.Concurrency
 * @NAME: FooBar_SynchronizedAddWait
 * @USER: 28050
 * @DATE: 2023/2/3
 * @TIME: 13:09
 **/
public class FooBar_SynchronizedAddWait {
    private final int n;
    private final Object o = new Object();
    private volatile boolean foo = true;

    public FooBar_SynchronizedAddWait(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            synchronized(o){
                if(!foo){
                    o.wait();
                }

                printFoo.run();
                foo = false;
                o.notifyAll();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            synchronized(o){
                if(foo){
                    o.wait();
                }

                printBar.run();
                foo = true;
                o.notifyAll();
            }

        }
    }
}
