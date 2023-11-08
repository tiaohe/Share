import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Hello {
    public static void main(String[] args) {
        System.out.println("hello,world");
    }

    public int eliminateMaximum(int[] dist, int[] speed) {

        var pq = IntStream.range(0, dist.length)
                .mapToDouble(i -> dist[i] / speed[i])
                .boxed()
                .collect(Collectors.toCollection(PriorityQueue::new));

        int time = 0;
        while (!pq.isEmpty()) {
            if (pq.poll() <= time)
                return time;
            ++time;
        }

        return time;
    }


}
