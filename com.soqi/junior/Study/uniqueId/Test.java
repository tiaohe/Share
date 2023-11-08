package uniqueId;

import java.util.stream.IntStream;

public class Test {
    public static void main(String[] args) {

        String prefix = "C202311089";

        for (int i = 10; i < 100; ++i) {
            System.out.println(prefix + "0101" + i);
        }
    }
}
