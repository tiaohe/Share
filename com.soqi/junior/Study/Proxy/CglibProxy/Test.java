package Proxy.CglibProxy;

public class Test {
    public static void main(String[] args) {
        CglibClass cglibClass = new CglibClass();
        CglibClass proxy = new CglibProxy(cglibClass).getProxy();
        proxy.show();
    }
}

