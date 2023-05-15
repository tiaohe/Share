package Concurrency.Executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ThreadPool3 {
    public static void main(String[] args) {
        //单个线程的线程池
        ExecutorService pool = Executors.newSingleThreadExecutor();

        for(int i = 0; i < 6; i++){
            int f = i;
            pool.submit(() ->{
                System.out.println(f + "线程名" + Thread.currentThread().getName());
            });

        }
    }
}
