package FourBigInterface.old;

import java.util.function.Function;



//Function<T,R>
//接受一个参数T，返回一个结果R    R apply(T t)

public class FunctionTest {
    public static void main(String[] args) {
        Integer add = add(1, x -> x + 1);
        System.out.println(add);
    }

    static Integer add(int num, Function<Integer,Integer> function){
        return function.apply(num);
    }
}
