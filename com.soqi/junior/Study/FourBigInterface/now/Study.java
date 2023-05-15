package FourBigInterface.now;

import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Study {
    public static void main(String[] args) {
//        1. `Function`：接受一个参数 T，返回一个结果 R。
//        2. `Predicate`：接受一个参数 T，返回一个 boolean 值。
//        3. `Consumer`：接受一个参数 T，没有返回值。
//        4. `Supplier`：不接受任何参数，返回一个结果 T。


        //函数型接口
//        1. Function接口：用于将一个对象转换为另一个对象。它的抽象方法apply(T t)接收一个参数t，然后返回一个R类型的结果。
//        常见场景：数据类型转换、数据映射、数据类型转换后再执行某种操作等。
        Function<String,Integer> f = Integer::parseInt;
        Integer res = f.apply("123");
        System.out.println(res);

        //消费型接口
//        2. Consumer接口：用于接收一个参数，然后执行某种操作。它的抽象方法accept(T t)接收一个参数t，但没有返回值。
//        常见场景：遍历集合元素，修改集合元素，输出数据等。
        Consumer<String>c = System.out::println;
        c.accept("hello,world");

        //供给型接口
//        3. Supplier接口：用于产生一个对象。它的抽象方法get()没有参数，但返回一个T类型的结果。
//        常见场景：延迟计算、数据提供等。
        //new Random()
        Supplier<Random>s = Random::new;
        Random random = s.get();



        //断言型接
//        4.Predicate接口：用于对一个对象进行判断，返回一个布尔值。它的抽象方法test(T t)接收一个参数t，返回一个boolean类型的结果。
        Predicate<Integer> p = num -> num % 2 == 0;
        boolean test = p.test(10);
        System.out.println(test);

        MathSum<Integer>add = (a, b) -> a + b;
        System.out.println(add.add(9,10));
    }
}

