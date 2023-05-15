package FourBigInterface.old;




/*
*
*
* Java8 内置的四⼤核⼼函数式接⼝

    Consumer<T> : 消费型接⼝：有⼊参，⽆返回值
     void accept(T t);

    Supplier<T> : 供给型接⼝：⽆⼊参，有返回值
     T get();

    Function<T, R> : 函数型接⼝：有⼊参，有返回值
     R apply(T t);

    Predicate<T> : 断⾔型接⼝：有⼊参，有返回值，返回值类型确定是boolean
     boolean test(T t);
* */
public class TestSum {

    public static void main(String[] args) {
        System.out.println(show(1, 10, (x, y) -> x + y));
    }

    static Integer show(Integer x, Integer y, FuntionShow<Integer,Integer>q){
        return q.show(x,y);
    }


}

