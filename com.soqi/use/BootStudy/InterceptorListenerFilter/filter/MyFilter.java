package InterceptorListenerFilter.filter;


import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import java.io.IOException;
/*
*
* 过滤器的使用
  首先需要实现 Filter接口然后重写它的三个方法
  init 方法：在容器中创建当前过滤器的时候自动调用
  destory 方法：在容器中销毁当前过滤器的时候自动调用
  doFilter 方法：过滤的具体操作
  * 过滤器是处于客户端和服务器资源文件之间的一道过滤网，帮助我们过滤掉一些不符合要求的请求，通常用作 Session 校验，判断用户权限，如果不符合设定条件，则会被拦截到特殊的地址或者基于特殊的响应。
* */

@Log4j2
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("初始化过滤器1！！！");
        //Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        log.info("销毁过滤器1！！");
//        Filter.super.destroy();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("过滤器1开始！！！！");
        long start = System.currentTimeMillis();
        filterChain.doFilter(servletRequest, servletResponse);
        log.info("过滤器1结束！！！！耗时：" + (System.currentTimeMillis() - start) + "ms");
    }
}

