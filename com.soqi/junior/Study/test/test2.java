package test;

import java.util.Arrays;
import java.util.Comparator;

public class test2 {

    public static final String TOKEN = "token";

    public static void main(String[] args) {

        String str = TOKEN;
        var arr = new int[10];
        Integer[] integerArr = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++) {
            integerArr[i] = arr[i];
        }
        //new Comparator 只接受包装类
        Arrays.sort(integerArr, (a, b) -> {
            int bitCountA = Integer.bitCount(a);
            int bitCountB = Integer.bitCount(b);

            return bitCountA != bitCountB ? bitCountA - bitCountB : a - b;
        });

        // 将排序结果转换回 int 类型数组
        int[] sortedArr = new int[integerArr.length];
        for (int i = 0; i < integerArr.length; i++) {
            sortedArr[i] = integerArr[i];
        }
    }

    public static String getToken(){
        return "qwer".toString();
    }
}
