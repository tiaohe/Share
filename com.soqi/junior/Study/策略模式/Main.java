package 策略模式;




public class Main {
    public static void main(String[] args) {

        String paymentMethod = "wechatpay";  // 设置支付方式

        PaymentStrategy paymentStrategy = PaymentStrategyFactory.getStrategy(paymentMethod);
        if (paymentStrategy != null) {
            double amount = 100.0;  // 设置支付金额
            paymentStrategy.pay(amount);
        } else {
            System.out.println("无效的支付方式");
        }
    }
}
