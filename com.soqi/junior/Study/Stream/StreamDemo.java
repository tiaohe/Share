package Stream;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.IntStream;

public class StreamDemo {
    public static void main(String[] args) {
        int[] ints = IntStream.range(1, 100000000).toArray();
        Instant cnow = Instant.now();
        int sum = IntStream.of(ints).sum();
        Instant clastnow = Instant.now();

        Duration between = Duration.between(cnow, clastnow);
        System.out.println(between + "ms");

        Instant bnow = Instant.now();
        int sum1 = IntStream.of(ints).parallel().sum();
        Instant blastnow = Instant.now();
        Duration between1 = Duration.between(bnow, blastnow);
        System.out.println(between1 + "ms");

        //无限流
        // nio bio aio
    }
}

