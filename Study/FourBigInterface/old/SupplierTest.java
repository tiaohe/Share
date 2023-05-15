package FourBigInterface.old;

import java.util.function.Supplier;

//Supplier 供给 get()
    //无输入参数，返回一个结果
    //使用场景 符合条件时使用获取结果，运行结果提前定义，但不运行
public class SupplierTest {
    public static void main(String[] args) {
        System.out.println(get(() -> "StringBuilder"));
    }

    static String get(Supplier<String> supplier){
       return supplier.get();
    }
}
