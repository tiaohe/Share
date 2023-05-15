package Concurrency.Executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ThreadPool2 {
    public static void main(String[] args) {
        //带缓存的线程池
        ExecutorService pool = Executors.newCachedThreadPool();

        for(int i = 0; i < 50; i++){
            int f = i;
            pool.submit(()->{
                System.out.println(f + "线程" + Thread.currentThread().getName());
            });
        }
    }
}
