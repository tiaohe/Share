package InterceptorListenerFilter.AOP.Note;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@Aspect
@Slf4j
@Component
public class useNote {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Pointcut("@annotation(com.*.project.anno.OperationLogAnnotation)")
    public void operationLogAspect(){}

    //@AfterReturning(returning  /**
    //     * 记录操作日志
    //     * @param joinPoint 方法的执行点
    //     * @param result  方法返回值
    //     * @throws Throwable
    //     */ = "result", value = "operLogPoinCut()")
    @AfterReturning(value = "operationLogAspect()", returning = "result")
    //@After(value = "operationLogAspect()")
    public void saveLog(JoinPoint joinPoint, Object result) throws Throwable{
        //获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        //获取request 的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);

        try {
            //返回值转换成map集合
            //Map<String, String> map = RMapUtil.getResultMap((R) result);
            String resString = result.toString();
            //Log log = new Log();
            //从切面植入点通过反射机制获取织入点处的方法
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            //获取切入点所在方法
            Method method = signature.getMethod();
            //获取操作
            OperationLogAnnotation annotation = method.getAnnotation(OperationLogAnnotation.class);

            if(annotation != null){
//                log.setModel(annotation.operModul());
//                log.setType(annotation.operType());
//                log.setDescription(annotation.operDesc());
            }
            //操作时间
//            log.setOpeartiontime(Timestamp.valueOf(sdf.format(AddTimeUtil.addhour(new Date(),8))));
//            //操作用户
//            log.setUsercode(request.getHeader("account"));
//            //操作ip
//            log.setIp(IPUtil.getIpAddr(request));
//            //返回值信息
//            log.setResult(resString);
//            //保存日志
//            logMapper.save(log);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

