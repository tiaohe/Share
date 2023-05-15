package Concurrency;

import java.util.concurrent.CountDownLatch;



public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch count = new CountDownLatch(6);

        for(int i = 0; i < 6; i++){
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "写完这道题");
                count.countDown();
            },String.valueOf(i)).start();
        }

        //到线程为0，或者 await（long，TimeUnit）线程为零或者指定时间
        count.await();

        System.out.println("收题");
    }
}
