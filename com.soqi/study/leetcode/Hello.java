import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.function.Function;
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

    public int[] restoreArray(int[][] ajs) {
        var graph = Arrays.stream(ajs)
                .flatMapToInt(Arrays::stream)
                .distinct()
                .boxed()
                .collect(Collectors.toMap(
                        Function.identity(),
                        k -> new ArrayList<Integer>()
                ));

        for (var aj : ajs) {
            graph.get(aj[0]).add(aj[1]);
            graph.get(aj[1]).add(aj[0]);
        }

        int n = ajs.length + 1;
        var res = new int[n];

        var head = graph.entrySet().stream()
                .filter(e -> e.getValue().size() == 1)
                .findFirst()
                .map(Map.Entry::getKey)
                .orElse(0);
        res[0] = head;
        res[1] = graph.get(head).get(0);

        for (int i = 2; i < n; ++i) {
            ArrayList<Integer> nbs = graph.get(res[i - 1]);
            res[i] = (res[i - 2] == nbs.get(0)) ? nbs.get(1) : nbs.get(0);
        }

        return res;

    }


}
