import cn.hutool.core.lang.hash.Hash;

import java.util.*;
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


    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) return 0;
        var res = bfs(routes, source, target);
        return res;
    }

    private int bfs(int[][] routes, int source, int target) {
        var q = new LinkedList<Integer>();
        var map = new HashMap<Integer, Set<Integer>>();
        boolean[] vis = new boolean[routes.length];

        for (int i = 0; i < routes.length; ++i) {
            for (int j = 0; j < routes[i].length; ++j) {
                Set<Integer> set = map.getOrDefault(routes[i][j], new HashSet<>());
                set.add(i);
                map.put(routes[i][j], set);
            }
        }

        q.add(source);
        int step = 0;
        while (!q.isEmpty()) {
            ++step;
            int size = q.size();
            for (int i = 0; i < size; ++i) {
                Integer de = q.pop();
                for (Integer r : map.get(de)) {
                    if (!vis[r]) {
                        for (int j = 0; j < routes[r].length; j++) {
                            if (routes[r][j] == target) {
                                return step;
                            }
                            q.add(routes[r][j]);
                        }
                    }
                    vis[r] = true;
                }
            }
        }
        return -1;
    }

    public int countPalindrquencomicSubsee(String s) {
        var prefix = new int[26];
        var suffix = new int[26];
        var dp = new int[s.length()][26];

        int cnt = 0;

        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i) - 'a';

            for (int j = 0; j < 26; j++) {
                if (j != c) {
                    dp[i][c] += dp[i - 1][j];
                }
            }
            if (i > 0) {
                cnt += dp[i - 1][c];
            }

            dp[i][c]++;
            prefix[c]++;
            suffix[c]++;
        }
        return cnt;
    }

    public List<String> findHighAccessEmployees(List<List<String>> access_times) {
        List<String> res = new ArrayList<>();
        Map<String, List<Integer>> map = new HashMap<>();

        for (List<String> access_time : access_times) {
            if (map.containsKey(access_time.get(0))) {
                List<Integer> strings = map.get(access_time.get(0));
                strings.add(Integer.parseInt(access_time.get(1)));
                map.put(access_time.get(0), strings);
            } else {
                List strings = new ArrayList();
                strings.add(Integer.parseInt(access_time.get(1)));
                map.put(access_time.get(0), strings);
            }
        }

        for (String s : map.keySet()) {
            List<Integer> integers = map.get(s);
            Collections.sort(integers);
            for (int i = 0; i < integers.size() - 2; i++) {
                if (integers.get(i + 2) - integers.get(i) <= 100) {
                    res.add(s);
                    break;
                }
            }
        }
        return res;

    }

    public List<String> findHighAccessEmployees1(List<List<String>> access_times) {
        var map = new HashMap<String, List<Integer>>();
        access_times.forEach(t -> {
            int time = Integer.parseInt(t.get(1));
            map.computeIfAbsent(t.get(0), k -> new ArrayList<>()).add(time);
        });

        List<String> res = map.entrySet().stream()
                .filter(e -> {
                    List<Integer> value = e.getValue();
                    Collections.sort(value);
                    for (int i = 0; i < value.size() - 2; i++) {
                        if (value.get(i + 2) - value.get(i) < 100) {
                            return true;
                        }
                    }
                    return false;
                })
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        return res;
    }
    private static final int MOD = (int)1e9 + 7;
    public int countNicePairs(int[] nums) {
        var map = new HashMap<Integer, Integer>();
        int res = 0;

        for (int num : nums) {
            int rev = rev(num), v = map.getOrDefault(num - rev,  0);
            map.put(num - rev, v + 1);
            res = (res + v) % MOD;
        }
        return res;
    }

    int rev(int num) {
        int ret = 0;
        while (num > 0) {
            ret = ret * 10 + num % 10;
            num /= 10;
        }
        return ret;
    }



}
