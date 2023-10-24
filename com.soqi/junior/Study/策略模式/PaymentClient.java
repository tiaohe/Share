package 策略模式;

public class PaymentClient {
    private PaymentStrategy paymentStrategy;

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void performPayment(double amount) {
        paymentStrategy.pay(amount);
    }
}