package FourBigInterface.old;

import java.util.function.Predicate;



//断言型 Predicate<T>
    //predicateTest boolean test()
public class PredicateTest {
    public static void main(String[] args) {
        System.out.println(TrueOrFlase(1, x -> (x & 1) != 1));
    }

    static boolean TrueOrFlase(int i, Predicate<Integer> p){
        return p.test(i);
    }
}
