package Concurrency.Executors;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


public class ThreadPool4 {
    public static void main(String[] args) {
        ScheduledExecutorService pool = Executors.newSingleThreadScheduledExecutor();

        System.out.println(LocalDateTime.now());
    }
}
