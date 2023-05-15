package Algorithm;

import java.util.Arrays;
import java.util.Random;

public class PdqSort {
    public static void main(String[] args) {
        Random r = new Random();
        int[]arr = new int[10];
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = r.nextInt(1000);
        }
        System.out.println(Arrays.toString(arr));
        pdqsort(arr, 0, arr.length - 1);

        System.out.println(Arrays.toString(arr));
    }

    public static void pdqsort(int[] array) {
        pdqsort(array, 0, array.length - 1);
    }

    private static void pdqsort(int[] array, int low, int high) {
        if (low >= high) {
            return;
        }
        //如果索引范围为空或只有一个元素，则直接返回，不需要进行排序。

        if (array[low] > array[high]) {
            swap(array, low, high);
        }

        //如果第一个元素大于最后一个元素，交换它们的位置，使得第一个元素变为较小的元素，最后一个元素变为较大的元素

        int pivot1 = array[low];
        int pivot2 = array[high];
        //将第一个元素作为第一个轴（pivot1）的值，将最后一个元素作为第二个轴（pivot2）的值。

        int i = low + 1;
        int lt = low + 1;
        int gt = high - 1;
        //初始化变量 i、lt 和 gt，分别表示当前遍历的元素索引、较小轴右侧元素的起始索引和较大轴左侧元素的起始索引。

        while (i <= gt) {
            if (array[i] < pivot1) {
                swap(array, i, lt);
                i++;
                lt++;
            } else if (array[i] > pivot2) {
                swap(array, i, gt);
                gt--;
            } else {
                i++;
            }
        }
        //遍历数组元素，将元素根据与轴的比较结果放置到对应的位置上，
        // 即较小的元素放到较小轴右侧，较大的元素放到较大轴左侧，相等的元素保持不变。

        int p = lt - 1;
        int q = gt + 1;

        swap(array, low, p);
        swap(array, high, q);
        //确定了较小轴右侧元素的结束索引 p 和较大轴左侧元素的起始索引 q。

        pdqsort(array, low, p - 1);
        pdqsort(array, p + 1, q - 1);
        pdqsort(array, q + 1, high);
        //对较小轴左侧、较大轴右侧和较大轴右侧之后的部分分别递归调用 pdqsort 方法进行排序。
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}