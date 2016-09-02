package com.zhaoliang.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 创建一个注解。
 * Annotation只有成员变量，没有方法。Annotation的成员变量在Annotation定义中
 * 以“无形参的方法”形式来声明，其方法名定义了该成员变量的名字，其返回值定义了该
 * 成员变量的类型。
 *
 * Created by zhaoliang(weston_contribute@163.com) on 2016/6/17.
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface MyAnnotation {

    /**
     * 也可以在定义Annotation的成员变量时，为其指定默认值，指定成员变量默认值使用default关键字。
     *
     */
    String name() default "annotation";

    int age() default 18;

}
