package Concurrency.Executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ThreadPool1 {
    public static void main(String[] args) {
        //固定线程池
        ExecutorService pool = Executors.newFixedThreadPool(5);
        for(int i = 0; i < 5; i++){
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(" 线程名" + Thread.currentThread().getName());
                }
            });
        }

        for(int i = 0; i < 8; i++){
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(" 线程名" + Thread.currentThread().getName());
                }
            });

        }

    }
}
