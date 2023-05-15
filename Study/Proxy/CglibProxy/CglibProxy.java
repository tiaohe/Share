package Proxy.CglibProxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

//重写一个方法
public class CglibProxy implements MethodInterceptor {

    private final CglibClass cglibClass;

    public CglibProxy(CglibClass cglibClass){
        this.cglibClass = cglibClass;
    }

    public CglibClass getProxy(){
        //创建实例
        Enhancer enhancer = new Enhancer();
        //设置父类
        enhancer.setSuperclass(CglibClass.class);
        //设置回调
        enhancer.setCallback(this);
        //构建代理对象
        CglibClass proxy = (CglibClass) enhancer.create();
        return proxy;
    }


    /*
    * 拦截器
    * o  代理对象  不用
    * Method: 目标代理方法
    * objects :形参列表
    * methodProxy： 方法代理对象 不用
    *
    * */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Object invoke = method.invoke(cglibClass, objects);
        System.out.println("中国");
        return invoke;
    }
}

