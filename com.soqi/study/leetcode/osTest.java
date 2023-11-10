public class osTest {
    public static void main(String[] args) {
//        String os = System.getProperty("os.name").toLowerCase();
//
//        if (os.contains("win")) {
//            System.out.println("win");
//        } else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
//            System.out.println("liunx");
//        } else {
//            System.out.println("cccc");
//        }

        String os = System.getProperty("os.name");
        //Windows操作系统
        if (os != null && os.toLowerCase().startsWith("windows")) {
            System.out.println(String.format("当前系统版本是:%s", os));
        } else if (os != null && os.toLowerCase().startsWith("linux")) {//Linux操作系统
            System.out.println(String.format("当前系统版本是:%s", os));
        } else { //其它操作系统
            System.out.println(String.format("当前系统版本是:%s", os));
        }

        System.out.println(Integer.MAX_VALUE);

    }
}
