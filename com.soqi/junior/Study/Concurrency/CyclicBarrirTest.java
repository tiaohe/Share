package Concurrency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @PACKAGE_NAME: com.Concurrency
 * @NAME: CyclicBarrirTest
 * @USER: 28050
 * @DATE: 2023/2/7
 * @TIME: 20:37
 *
 * 模拟召唤神龙
 **/
public class CyclicBarrirTest {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,() ->{
            System.out.println("召唤神龙");
        });

        for(int i = 0; i < 7; i++){
            final int temp = i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + "获得"+ temp);

                try {
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
