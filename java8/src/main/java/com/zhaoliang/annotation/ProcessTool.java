package com.zhaoliang.annotation;

import java.lang.reflect.Method;

/**
 * Created by zhaoliang(weston_contribute@163.com) on 2016/6/17.
 */
public class ProcessTool {

    public static void process(String clazz) {
        Class targetClass = null;

        try {
            targetClass = Class.forName(clazz);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        for (Method m : targetClass.getMethods()) {
            if (m.isAnnotationPresent(MyAnnotation.class)) {
                System.out.println("被MyAnnotation注解修饰的方法名：" + m.getName());
                MyAnnotation annotation = m.getAnnotation(MyAnnotation.class);
                System.out.println("方法" + m.getName() + "的MyTag注解内容为：" + annotation.name() + "，" + annotation.age());
            } else {
                System.out.println("没被MyAnnotation注解修饰的方法名：" + m.getName());
            }
        }
    }

    public static void main(String[] args) {
        ProcessTool.process("com.zhaoliang.annotation.MyAnnotationTest");
    }
}
