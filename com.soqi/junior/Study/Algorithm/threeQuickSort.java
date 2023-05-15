package Algorithm;

import java.util.Arrays;
import java.util.Random;

public class threeQuickSort {
    public static void main(String[] args) {
        Random r = new Random();
        int[]arr = new int[10];
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = r.nextInt(1000);
        }
        System.out.println(Arrays.toString(arr));
        sort(arr, 0, arr.length - 1);

        System.out.println(Arrays.toString(arr));
    }

    static void sort(int[]arr, int l, int r){
        if(l > r) return;
        int i = l, j = r;
        int t = l;
        //取随机
        //取中间
//         int mid = arr[l + (r - l) /2];
        int mid = arr[l + (int)Math.random()*(r - l)];

        //sssssss  mmmmmmmmm  bbbbbbbbb

        //  12 11 8 9 10
        //  10 11 8 9 12
        //  9  11 8 10 12
        //  9  8  11 10 12
        //  8  9  11 10 12
        //     i
        //         t
        //     j
        while(t <= j){
            if(arr[t] < mid){
                swap(arr, t++, i++);
            }else if(arr[t] > mid){
                swap(arr, t, j--);
            }else{
                ++t;
            }
        }

        sort(arr, l, i - 1);
        sort(arr, j + 1, r);
    }

    static void swap(int[]arr, int a, int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}

