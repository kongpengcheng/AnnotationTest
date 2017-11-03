package com.haier.ranghood.annotationproxy.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Harry.Kong.
 * Time 2017/11/2.
 * Email kongpengcheng@aliyun.com.
 * Description:
 */
@Documented
@Target(PARAMETER)
@Retention(RUNTIME)
public @interface ReqParam {
    String value() default "";
}