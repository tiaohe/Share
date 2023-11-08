import java.util.stream.IntStream;

public class MaxDotProductofTwoSubsequences1458 {
    public static void main(String[] args) {
        /**
         *
         */
    }

    public int maxDotProduct(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        var f = new int[m][n];

        f[0][0] = nums1[0] * nums2[0];

        IntStream.range(1, n).forEach(i -> f[0][i] = Math.max(f[0][i - 1], nums1[0] * nums2[i]));
        IntStream.range(1, m).forEach(i -> f[i][0] = Math.max(f[i - 1][0], nums1[i] * nums2[0]));
        IntStream.range(1, m).forEach(i -> {
            IntStream.range(1, n).forEach(j -> {
                f[i][j] = getMax(f[i - 1][j],
                        f[i][j - 1],
                        nums1[i] * nums2[j],
                        f[i - 1][j - 1] + nums1[i] * nums2[j]);
            });
        });
        return f[m - 1][n - 1];
    }

    int getMax(int a, int b, int c, int d) {
        return Math.max(Math.max(Math.max(a, b), c), d);
    }
}
