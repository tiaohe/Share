package 策略模式;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class PaymentStrategyFactory {
    private static Map<String, Class<? extends PaymentStrategy>> strategies = new HashMap<>();

    static {
        // 注册支付策略类
        registerStrategy(AlipayStrategy.class);
        registerStrategy(WechatPayStrategy.class);
    }

    public static void registerStrategy(Class<? extends PaymentStrategy> strategyClass) {
        PaymentMethod annotation = strategyClass.getAnnotation(PaymentMethod.class);
        if (annotation != null) {
            String value = annotation.value();
            strategies.put(value, strategyClass);
        }
    }

    public static PaymentStrategy getStrategy(String paymentMethod) {
        Class<? extends PaymentStrategy> strategyClass = strategies.get(paymentMethod);
        if (strategyClass != null) {
            try {
                return strategyClass.getDeclaredConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @PaymentMethod("alipay")
    public class AlipayStrategy implements PaymentStrategy {
        @Override
        public void pay(double amount) {
            System.out.println("使用支付宝支付：" + amount + "元");
            // 具体的支付逻辑
        }
    }

    @PaymentMethod("wechatpay")
    public class WechatPayStrategy implements PaymentStrategy {
        @Override
        public void pay(double amount) {
            System.out.println("使用微信支付：" + amount + "元");
            // 具体的支付逻辑
        }
    }

}