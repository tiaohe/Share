import java.util.Arrays;
import java.util.Comparator;

public class DIYSort {
    public static void main(String[] args) {
        String[] versions = {"1.1.1", "2", "1.2.3", "1.1", "2.0", "1"};

        // 使用自定义的比较器按照版本号进行排序
        Arrays.sort(versions, new VersionComparator());

        // 打印排序后的结果
        for (String version : versions) {
            System.out.println(version);
        }
    }

    // 自定义比较器
    static class VersionComparator implements Comparator<String> {
        @Override
        public int compare(String version1, String version2) {
            String[] parts1 = version1.split("\\."); // 拆分第一个版本号字符串
            String[] parts2 = version2.split("\\."); // 拆分第二个版本号字符串

            int length = Math.max(parts1.length, parts2.length); // 获取较长的版本号长度

            for (int i = 0; i < length; i++) {
                int num1 = (i < parts1.length) ? Integer.parseInt(parts1[i]) : 0; // 获取第一个版本号的每个部分数字
                int num2 = (i < parts2.length) ? Integer.parseInt(parts2[i]) : 0; // 获取第二个版本号的每个部分数字

                if (num1 < num2) {
                    return -1;
                } else if (num1 > num2) {
                    return 1;
                }
            }

            return 0;
        }
    }
}
