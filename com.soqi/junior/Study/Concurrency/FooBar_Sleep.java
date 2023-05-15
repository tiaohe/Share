package Concurrency;

/**
 * @PACKAGE_NAME: com.Concurrency
 * @NAME: FooBar_Sleep
 * @USER: 28050
 * @DATE: 2023/2/3
 * @TIME: 13:11
 **/
public class FooBar_Sleep {
    private final int n;
    private volatile int fCount;
    private volatile int pCount;

    public FooBar_Sleep(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            while(fCount != pCount){
                Thread.sleep(1);
            }

            printFoo.run();
            ++fCount;
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            while(fCount == pCount){
                Thread.sleep(1);
            }
            printBar.run();
            ++pCount;
        }
    }
}
