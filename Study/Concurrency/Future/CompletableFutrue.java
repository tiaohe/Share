package Concurrency.Future;

import java.util.concurrent.CompletableFuture;


public class CompletableFutrue {
    public static void main(String[] args) {
        demo1();
//        demo2();
//        demo3();
//        dmeo4();
//        dmeo5();
//        demo6();
//        demo7();
//        demo8();
//        demo9();
//        demo10();
//        demo11();
//        demo12();
    }
    //        CompletableFuture的优点：
//        异步任务结束时，会自动回调某个对象的方法；
//        异步任务出错时，会自动回调某个对象的方法；
//        主线程设置好回调后，不再关心异步任务的执行。
        /*
        *  demo1:thenApply,进行变换
        * public <U> CompletionStage<U> thenApply(Function<? super T,? extends U> fn);
          public <U> CompletionStage<U> thenApplyAsync(Function<? super T,? extends U> fn);
          public <U> CompletionStage<U> thenApplyAsync(Function<? super T,? extends U> fn,Executor executor);
        *
        * */
    public static void demo1(){
        String join = CompletableFuture.supplyAsync(() -> "hello").thenApply(s -> s + ",world").join();
        System.out.println(join);
    }

//        Futrue：thenAccept,获取上一步结果，下一步使用
//        public CompletionStage<Void> thenAccept(Consumer<? super T> action);
//        public CompletionStage<Void> thenAcceptAsync(Consumer<? super T> action);
//        public CompletionStage<Void> thenAcceptAsync(Consumer<? super T> action,Executor executor);
    //thenAccept是针对结果进行消耗，因为他的入参是Consumer，有入参无返回值
    public static void demo2(){
        CompletableFuture.supplyAsync(() -> "hello").thenAccept(s -> System.out.println(s + ",world"));
    }

       /*
       * demo3：thenRun,不管上一步计算结果，直接执行下一步操作
          public CompletionStage<Void> thenRun(Runnable action);
          public CompletionStage<Void> thenRunAsync(Runnable action);
          public CompletionStage<Void> thenRunAsync(Runnable action,Executor executor);
          * thenRun它的入参是一个Runnable的实例，表示当得到上一步的结果时的操作。
       * */
    public static void demo3(){
        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {

            }
            return "hello";
        }).thenRun(() -> System.out.println("hello,world"));
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            break;
        }
    }


//        demo4：thenCombine,结合两个异步返回的CompletionStage的结果，进行转换
//        public <U,V> CompletionStage<V> thenCombine(CompletionStage<? extends U> other,BiFunction<? super T,? super U,? extends V> fn);
//        public <U,V> CompletionStage<V> thenCombineAsync(CompletionStage<? extends U> other,BiFunction<? super T,? super U,? extends V> fn);
//        public <U,V> CompletionStage<V> thenCombineAsync(CompletionStage<? extends U> other,BiFunction<? super T,? super U,? extends V> fn,Executor executor);
    public static void demo4(){
        String join1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "world";
        }), (a, b) -> a + "," + b).join();
        System.out.println(join1);
    }

//
//        demo5：结合两个异步返回的CompletionStage的结果，进行转换
//        public <U> CompletionStage<Void> thenAcceptBoth(CompletionStage<? extends U> other,BiConsumer<? super T, ? super U> action);
//        public <U> CompletionStage<Void> thenAcceptBothAsync(CompletionStage<? extends U> other,BiConsumer<? super T, ? super U> action);
//        public <U> CompletionStage<Void> thenAcceptBothAsync(CompletionStage<? extends U> other,BiConsumer<? super T, ? super U> action,     Executor executor);
//它需要原来的处理返回值，并且other代表的CompletionStage也要返回值之后，利用这两个返回值，进行消耗。
    public static void demo5(){
        CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        }).thenAcceptBoth(CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "world";
        }),(a,b)-> System.out.println(a + "," + b));
        while(true){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            break;
        }
    }
    /*
    * demo6：runAfterBoth，两个CompletionStage都运行完后执行
        public CompletionStage<Void> runAfterBoth(CompletionStage<?> other,Runnable action);
        public CompletionStage<Void> runAfterBothAsync(CompletionStage<?> other,Runnable action);
        public CompletionStage<Void> runAfterBothAsync(CompletionStage<?> other,Runnable action,Executor executor);
        *
       不关心这两个CompletionStage的结果，只关心这两个CompletionStage执行完毕，之后在进行操作（Runnable）
    * */
    public static void demo6(){
        CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hhhh";
        }).runAfterBothAsync(CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "1111";
        }),()-> System.out.println("hello,world"));
        while(true){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            break;
        }
    }
//    demo7：applyToEither，谁计算的快，我就用那个CompletionStage的结果进行下一步的转化操作
//    public <U> CompletionStage<U> applyToEither(CompletionStage<? extends T> other, Function<? super T, U> fn);
//    public <U> CompletionStage<U> applyToEitherAsync(CompletionStage<? extends T> other,Function<? super T, U> fn);
//    public <U> CompletionStage<U> applyToEitherAsync(CompletionStage<? extends T> other, Function<? super T, U> fn, Executor executor);
    //我们现实开发场景中，总会碰到有两种渠道完成同一个事情，所以就可以调用这个方法，找一个最快的结果进行处理
    public static void demo7(){
        String join = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "1";
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(900);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello,world";
        }), s -> s).join();
        System.out.println(join);
    }

    /*demo8：acceptEither，谁计算的快，我就用那个CompletionStage的结果进行下一步的转化操作
    public CompletionStage<Void> acceptEither(CompletionStage<? extends T> other,Consumer<? super T> action);
    public CompletionStage<Void> acceptEitherAsync(CompletionStage<? extends T> other,Consumer<? super T> action);
    public CompletionStage<Void> acceptEitherAsync(CompletionStage<? extends T> other,Consumer<? super T> action,Executor executor);
    */
    public static void demo8(){
        CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "1";
        }).acceptEither(CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(900);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello,world";
        }), System.out::println);
        while(true){
            try {
                Thread.sleep(1100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            break;
        }
    }

   /* demo9：runAfterEither，两个CompletionStage，任何一个完成了都会执行下一步的操作（Runnable）
    public CompletionStage<Void> runAfterEither(CompletionStage<?> other,Runnable action);
    public CompletionStage<Void> runAfterEitherAsync(CompletionStage<?> other,Runnable action);
    public CompletionStage<Void> runAfterEitherAsync(CompletionStage<?> other,Runnable action,Executor executor);
    */
    public static void demo9(){
        CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "11";
        }).runAfterEither(CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(900);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "22";
        }),()-> System.out.println("hello,world"));
        while(true){
            try {
                Thread.sleep(1100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            break;
        }
    }

//    demo10：exceptionally,当运行时出现了异常，可以通过exceptionally进行补偿
//    public CompletionStage<T> exceptionally(Function<Throwable, ? extends T> fn);
    public static void demo10(){
        String res = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(900);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (true) {
                throw new RuntimeException("异常情况");
            }
            return "s1";
        }).exceptionally(e -> {
            System.out.println(e.getMessage());
            return "hello,world";
        }).join();
        System.out.println(res);
    }
/*
    demo11：whenComplete
    当运行完成时，对结果的记录。这里的完成时有两种情况，一种是正常执行，返回值。另外一种是遇到异常抛出造成程序的中断。这里为什么要说成记录，因为这几个方法都会返回CompletableFuture，
    当Action执行完毕后它的结果返回原始的CompletableFuture的计算结果或者返回异常。所以不会对结果产生任何的作用。

    public CompletionStage<T> whenComplete(BiConsumer<? super T, ? super Throwable> action);
    public CompletionStage<T> whenCompleteAsync(BiConsumer<? super T, ? super Throwable> action);
    public CompletionStage<T> whenCompleteAsync(BiConsumer<? super T, ? super Throwable> action,Executor executor);
*/
    public static void demo11(){
        String res = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(900);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (true) {
                throw new RuntimeException("异常");
            }
            return "11";
        }).whenComplete((s, t) -> {
            System.out.println(s);
            System.out.println(t.getMessage());
        }).exceptionally(e -> {
            System.out.println(e.getMessage());
            return "hello.world";
        }).join();
        System.out.println(res);
    }
//    这里也可以看出，如果使用了exceptionally，就会对最终的结果产生影响，它没有口子返回如果没有异常时的正确的值，这也就引出下面我们要介绍的handle。

    /*demo12：handle
    运行完成时，对结果的处理。这里的完成时有两种情况，一种是正常执行，返回值。另外一种是遇到异常抛出造成程序的中断
    public <U> CompletionStage<U> handle(BiFunction<? super T, Throwable, ? extends U> fn);
    public <U> CompletionStage<U> handleAsync(BiFunction<? super T, Throwable, ? extends U> fn);
    public <U> CompletionStage<U> handleAsync(BiFunction<? super T, Throwable, ? extends U> fn,Executor executor);
*/
    public static void demo12(){
        String res = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(900);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (true) {
                throw new RuntimeException("异常");
            }
            return "11";
        }).handle((s, t) -> {
            if (t != null) {
                return "hello, world";
            }
            return s;
        }).join();
        System.out.println(res);
    }
    //异常就是hello，world， 未出现异常就是11;
    //上面就是CompletionStage接口中方法的使用实例，CompletableFuture同样也同样实现了Future，所以也同样可以使用get进行阻塞获取值
}
