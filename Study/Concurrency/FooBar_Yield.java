package Concurrency;

/**
 * @PACKAGE_NAME: com.Concurrency
 * @NAME: FooBar_Yield
 * @USER: 28050
 * @DATE: 2023/2/3
 * @TIME: 13:09
 **/
public class FooBar_Yield {
    private final int n;
    private volatile boolean foo = true;

    public FooBar_Yield(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n;) {
            if(foo){
                printFoo.run();
                foo = false;
                i++;
            }else{
                Thread.yield();
            }

        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n;) {
            if(!foo){
                printBar.run();
                foo = true;
                i++;
            }else{
                Thread.yield();
            }

        }
    }
}
