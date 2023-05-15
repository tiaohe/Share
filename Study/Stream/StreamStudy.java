package Stream;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.*;
import java.util.stream.Collectors;

public class StreamStudy {
    public static void main(String[] args) {
        //遍历
//        List<String> l1 = Arrays.asList("java", "cpp", "golang");
//        l1.stream().forEach(System.out::println);
        //过滤 filter
//        List<String> collect = l1.stream().filter(s -> s.length() > 3).collect(Collectors.toList());
//        collect.stream().forEach(System.out::println);
        //转化 map
//        List<String> collect = l1.stream().map(String::toUpperCase).collect(Collectors.toList());
//        collect.stream().forEach(System.out::println);
        //去重
//        Set set = new HashSet<>();
//        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 1);
//        System.out.println(integers.stream().distinct().collect(Collectors.toList()));
        //截取
//        System.out.println(integers.stream().limit(3).collect(Collectors.toList()));
        //跳过
//        System.out.println(integers.stream().skip(3).collect(Collectors.toList()));
        //排序
//        System.out.println(integers.stream().sorted().collect(Collectors.toList()));
        //统计  count  max min sum avg
//        List<Integer> l2 = Arrays.asList(1, 2, 3, 4, 5);
        //count
//        System.out.println(l2.stream().count());  //5
//        System.out.println(l2.stream().max(Integer::compareTo));//5
//        System.out.println(l2.stream().min(Integer::compareTo));//1
//        System.out.println(l2.stream().mapToInt(Integer::intValue).sum());//15
//        System.out.println(l2.stream().mapToDouble(Integer::intValue).average());//3
        //匹配 anyMatch  allMatch noneMatch
//        System.out.println(l2.stream().anyMatch(i -> i > 3));
//        System.out.println(l2.stream().allMatch(i -> i > 0));
//        System.out.println(l2.stream().noneMatch(i -> i < 0));
        //组合
//        List<List<Integer>> lists = Arrays.asList(
//                Arrays.asList(1, 2),
//                Arrays.asList(3, 4),
//                Arrays.asList(5, 6)
//        );
//        System.out.println(lists);
//        System.out.println(lists.stream().flatMap(Collection::stream).collect(Collectors.toList()));
        //归约 reduce

//        List<Integer> l2 = Arrays.asList(1, 2, 3, 4, 5);
//        Integer reduce = l2.stream().reduce(0, Integer::sum);
//        System.out.println(reduce);
        //分组
//        List<Student> students = Arrays.asList(
//                new Student("123", "11", 11),
//                new Student("456", "44", 44)
//        );
//        Map<Integer, List<Student>> collect = students.stream().collect(Collectors.groupingBy(Student::getAge));
//        System.out.println(collect);
        //分区
//        Map<Boolean, List<Student>> o = students.stream().collect(Collectors.partitioningBy(s -> s.getAge() > 20));
//        System.out.println(o);
        //并行流

//        List<Integer> l2 = Arrays.asList(1, 2, 3, 4, 5);
//        l2.parallelStream().forEach(System.out::println);

        //reduce
        int[] arr = {1, 2, 3, 4, 5, 1, 2};
//        int reduce = Arrays.stream(arr).parallel().reduce(Integer.MIN_VALUE, (a, b) -> a > b ? a : b);
//        System.out.println(reduce);

        //collect
//        Arrays.stream(arr).
//                parallel().
//                collect(Collectors.toList());
        //map
        int[] ints = Arrays.stream(arr).parallel().map(s -> s * 2).toArray();
        System.out.println(Arrays.toString(ints));
    }
}

