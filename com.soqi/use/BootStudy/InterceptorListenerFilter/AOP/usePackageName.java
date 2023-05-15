package InterceptorListenerFilter.AOP;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Aspect
@Slf4j
@Component
public class usePackageName {
    @Pointcut("execution(* com.soqi.*.controller.*.*(..))")
//    @Pointcut("execution(* com.cj.controller..*.*(..))")   //切所有controller
    public void pointcut() {
    }

    @Before("pointcut()")
    public void beforeControllerMethod(JoinPoint joinPoint) {
        log.info("前置通知！！！！方法执行前执行");
    }

    @Around("pointcut()")
    public Object handleControllerMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String uuIdTrace = UUID.randomUUID().toString().replace("-", "");
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //IP地址
        String ipAddr = getRemoteHost(request);
//        String url = request.getRequestURL().toString();
        String requestMethod = request.getMethod();
        String requestURI = request.getRequestURI();
        String reqParam = preHandle(proceedingJoinPoint, request);//获取请求参数
        log.info(uuIdTrace + " 环绕通知，执行方法前，请求源IP:【{}】,请求方法：【{}】，请求URL:【{}】,请求参数:【{}】", ipAddr, requestMethod, requestURI, reqParam);
        long start = System.currentTimeMillis();

        Object result = proceedingJoinPoint.proceed();//执行方法，获取响应信息

        String respParam = postHandle(result);
        log.info(uuIdTrace + " 环绕通知，执行方法后，请求源IP:【{}】,请求URL:【{}】,返回参数:【{}】，执行耗时 : {}", ipAddr, requestURI, respParam, (System.currentTimeMillis() - start) + "ms");
        return result;
    }

    @After("pointcut()")
    public void afterControllerMethod(JoinPoint joinPoint) {
        log.info("后置通知：方法执行完后执行");
    }

    @AfterReturning(returning = "result", pointcut = "pointcut()")
    public void doAfterReturnint(Object result) {
        log.info("后置通知，方法执行完后执行，响应信息为：{}", JSONObject.toJSONString(result));
    }

    /**
     * 入参数据
     *
     * @param joinPoint
     * @param request
     * @return
     */
    private String preHandle(ProceedingJoinPoint joinPoint, HttpServletRequest request) {
        try {
            Map<String, Object> fieldsName = getFieldsName(joinPoint);
            return JSON.toJSONString(fieldsName);
        } catch (Exception e) {
            log.warn("切面获取请求参数出现异常", e);
            return null;
        }
    }

    private Map<String, Object> getFieldsName(JoinPoint joinPoint) throws Exception {
        String classType = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        // 参数值
        Object[] args = joinPoint.getArgs();
        Class<?>[] classes = new Class[args.length];
        for (int k = 0; k < args.length; k++) {
            // 对于接受参数中含有MultipartFile，ServletRequest，ServletResponse类型的特殊处理，我这里是直接返回了null。（如果不对这三种类型判断，会报异常）
            if (args[k] instanceof MultipartFile || args[k] instanceof ServletRequest || args[k] instanceof ServletResponse) {
                return null;
            }
            if (!args[k].getClass().isPrimitive()) {
                // 当方法参数是基础类型，但是获取到的是封装类型的就需要转化成基础类型
//                String result = args[k].getClass().getName();
//                Class s = map.get(result);

                // 当方法参数是封装类型
                Class s = args[k].getClass();

                classes[k] = s == null ? args[k].getClass() : s;
            }
        }
        ParameterNameDiscoverer pnd = new DefaultParameterNameDiscoverer();
        // 获取指定的方法，第二个参数可以不传，但是为了防止有重载的现象，还是需要传入参数的类型
        Method method = Class.forName(classType).getMethod(methodName, classes);
        // 参数名
        String[] parameterNames = pnd.getParameterNames(method);
        // 通过map封装参数和参数值
        HashMap<String, Object> paramMap = new HashMap();
        for (int i = 0; i < parameterNames.length; i++) {
            paramMap.put(parameterNames[i], args[i]);
        }
        return paramMap;
    }

    /**
     * 返回数据
     *
     * @param retVal
     * @return
     */
    private String postHandle(Object retVal) {
        if (null == retVal) {
            return "";
        }
        return JSON.toJSONString(retVal);
    }

    /**
     * 获取目标主机的ip
     *
     * @param request
     * @return
     */
    private String getRemoteHost(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }

}



