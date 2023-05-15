package Concurrency.Executors;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class ThreadPool_seven {
    public static void main(String[] args) {
        ThreadFactory factory = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                return thread;
            }
        };

        //1.corepoolsize  核心线程数
        //2.maximumpoolsize  最大线程数
        //3.存活时间
        //4.时间单位
//        5.阻塞队列
//        6. 线程工厂
//        7.拒绝策略

        ThreadPoolExecutor pool = new ThreadPoolExecutor(2,4,3, TimeUnit.HOURS, new LinkedBlockingDeque<>(2), factory, new ThreadPoolExecutor.AbortPolicy());
        //1.提示异常，拒绝执行多余的任务
        //               // new ThreadPoolExecutor.AbortPolicy()
        //
        //                //2.忽略堵塞队列中最旧的任务
        //                //new ThreadPoolExecutor.DiscardOldestPolicy()
        //
        //                //3.忽略最新的任务
        //                //new ThreadPoolExecutor.DiscardPolicy()
        //
        //                //4.使用调用该线程池的线程来执行任务
        //                //new ThreadPoolExecutor.CallerRunsPolicy()
        //
        //                //5.A自定义拒绝策略
        //                new RejectedExecutionHandler()

        for(int i = 0; i < 7; i++){
            int f = i;
            pool.submit(() ->{
                try {
                    Thread.sleep(f*100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "    --"+f );
            });

        }
    }
}
