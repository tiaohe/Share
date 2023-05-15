package InterceptorListenerFilter.listener;

import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

@Configuration
public class MyHttpRequestListener  implements ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("request 监听器被销毁");
    }

    /**
     * 先被执行
     * @param sre
     */
    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        HttpServletRequest req = (HttpServletRequest) sre.getServletRequest();
        String requestURI = req.getRequestURI();
        System.out.println(requestURI+"--"+"被调用");
    }
}
