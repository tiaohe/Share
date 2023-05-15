package Concurrency;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @PACKAGE_NAME: com.Concurrency
 * @NAME: SemaphoreTest
 * @USER: 28050
 * @DATE: 2023/2/7
 * @TIME: 20:40
 * <p>
 * //多资源抢占  10人抢5车位
 **/
public class SemaphoreTest {
    public static void main(String[] args) {
        int people = 10;
        int carStop = 5;

        Semaphore semaphore = new Semaphore(5);
        Random r = new Random();

        for (int i = 1; i <= people; i++) {
            new Thread(() -> {

                try {
                    //获取信号量
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "抢到");
                    TimeUnit.SECONDS.sleep(r.nextInt(3) + 1);
                    System.out.println(Thread.currentThread().getName() + "离开");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }
    }
}
