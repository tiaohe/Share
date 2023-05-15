package Proxy.JdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxy {

    private final JdkInterface jdkInterface;

    public JdkProxy(JdkInterface jdkInterface){
        this.jdkInterface = jdkInterface;
    }

    public JdkInterface getProxy(){
        //代理对象
        JdkInterface proxy = null;
        //类加载
        ClassLoader classLoader = jdkInterface.getClass().getClassLoader();
        //实现的接口
        Class<?>[] interfaces = jdkInterface.getClass().getInterfaces();

        //执行处理器
        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //String name = method.getName();

                Object invoke = method.invoke(jdkInterface, args);
                System.out.println("中国");
                return invoke;
            }
        };
        proxy = (JdkInterface) Proxy.newProxyInstance(classLoader, interfaces, handler);

        return proxy;
    }
}

