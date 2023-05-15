package Proxy.StaticProxy;

public class StaticProxy implements StaticInterface{
    public final StaticClass staticClass;

    public StaticProxy(){
        staticClass = new StaticClass();
    }

    @Override
    public void show() {
        staticClass.show(); //你好
        System.out.println("中国");
    }
}

