import java.util.concurrent.CompletableFuture;

public class ComletableFutureTest {
    public static void main(String[] args) {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            // 模拟一个耗时操作
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            return "Hello";
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "World");

        CompletableFuture<Object> anyFuture = CompletableFuture.anyOf(future1, future2);
        anyFuture.thenAccept(result -> {
            System.out.println("Any future completed");
            // 处理第一个完成的 CompletableFuture 的结果
            System.out.println("Result: " + result);
        });

        anyFuture.join(); // 等待任意 CompletableFuture 完成
    }
}
