package Concurrency;

import java.util.HashSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;



public class CountDown {
    public static void main(String[] args) throws InterruptedException {
        m2();
        new HashSet();
    }

    public static void m1() throws InterruptedException {
        CountDownLatch count = new CountDownLatch(1);

        for(int i = 0; i < 5; i++){
            new Thread(()->{
                try {
                    count.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            }).start();
        }

        Thread.sleep(2000);
        count.countDown();
    }

    public static void m2() throws InterruptedException {
        CountDownLatch count = new CountDownLatch(5);
        for(int i = 0; i < 5; i++){
            final int idx = i;
            new Thread(()->{
                try {
                    Thread.sleep(1000 + ThreadLocalRandom.current().nextInt(1000));
                    System.out.println(idx + Thread.currentThread().getName());
                    count.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        count.await();
        System.out.println("汇总");
    }
}
