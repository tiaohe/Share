package InterceptorListenerFilter.listener;
/*
*
*监听器通常用于监听 Web 应用程序中对象的创建、销毁等动作的发送，同时对监听的情况作出相应的处理，最常用于统计网站的在线人数、访问量等。
监听器大概分为以下几种
* ServletContextListener：用来监听 ServletContext 属性的操作，比如新增、修改、删除。
HttpSessionListener：用来监听 Web 应用种的 Session 对象，通常用于统计在线情况。
ServletRequestListener：用来监听 Request 对象的属性操作。
*
* */

import javax.servlet.ServletContextListener;

public class MyListener implements ServletContextListener {
}

