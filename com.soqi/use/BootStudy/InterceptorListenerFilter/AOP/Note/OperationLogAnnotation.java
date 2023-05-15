package InterceptorListenerFilter.AOP.Note;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperationLogAnnotation {
    String operModul() default ""; //操作模块

    String operType() default ""; //操作类型

    String operDesc() default ""; //操作说明

}