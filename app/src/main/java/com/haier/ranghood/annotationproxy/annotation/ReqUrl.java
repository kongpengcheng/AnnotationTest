package com.haier.ranghood.annotationproxy.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Harry.Kong.
 * Time 2017/11/2.
 * Email kongpengcheng@aliyun.com.
 * Description:RUNTIME 运行时注解 class 编译时注解
 */
@Documented
@Target(METHOD)
@Retention(RUNTIME)
public @interface ReqUrl {
    String reqUrl() default "";
}