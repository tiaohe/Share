package Proxy.JdkProxy;

public class Test {
    public static void main(String[] args) {
        JdkInterface jdkInterface = new JdkClass();

        JdkInterface proxy  = new JdkProxy(jdkInterface).getProxy();

        proxy.show();
    }
}

