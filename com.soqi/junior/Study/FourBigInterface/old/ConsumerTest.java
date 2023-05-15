package FourBigInterface.old;

import java.util.function.Consumer;
//
//consumer<T> //消费  accept()
    //接受一个输入参数并且无返回值
    //使用场景： 打印 发短信
public class ConsumerTest {
    public static void main(String[] args) {
        hadle(1, x -> System.out.println(x + 1));
    }

    static void hadle(int value, Consumer<Integer>consumer){
        consumer.accept(value);
    }
}
