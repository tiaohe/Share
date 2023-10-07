import cn.hutool.core.lang.hash.Hash;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamTest {
    public static void main(String[] args) {
        switch ("你好") {
            case "你好":
                System.out.println("你好");
                break;
            case "你好吗":
                System.out.println("你好吗");
                break;
            default:
                System.out.println("default");
        }
        //switch 浮点型


//        //jdk8的stream流
//        List<String> list = Arrays.asList("hello", "world", "hello world");
//        list.stream().map(item -> item.toUpperCase()).forEach(System.out::println);
//        System.out.println("----------");
//        list.stream().map(String::toLowerCase).forEach(System.out::println);
//        //给我写一篇作文，作文的题目是：如何使用stream流
//        //1.流的创建;
//        List queue = IntStream.range(0, 10)
//                .filter(i -> i % 2 == 0)
//                .boxed()
//                .collect(Collectors.toList());
//        //2.中间操作 Map的stream流
//        Map<String, Integer> map = new HashMap();
//        map.put("123",123);
//        map.put("456",456);
//        map.put("789",789);
//
//        List<Map.Entry<String, Integer>> collect = map.entrySet().stream().collect(Collectors.toList());
//        collect.stream().forEach(System.out::println);
//        //3.终止操作
//        for (Map.Entry<String, Integer> entry : collect) {
//            System.out.println(entry.getKey());
//            System.out.println(entry.getValue());
//        }
    }
}

