package 策略模式;

public class User {
    private synchronized void test1() throws InterruptedException {
        System.out.println("1开始");
        Thread.sleep(1000);
        System.out.println("1结束");
    }

    private synchronized void test2() {
        System.out.println(2);
    }

    public static void main(String[] args) {
        User user = new User();
        new Thread(() -> {
            try {
                user.test1();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            user.test2();
        }).start();
    }
}
