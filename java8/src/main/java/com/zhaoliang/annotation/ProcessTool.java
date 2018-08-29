package com.zhaoliang.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.stream.Stream;

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
                System.out.println();
            }
        }

        Field[] fields = targetClass.getFields();
        for (Field f : fields) {
            if (f.isAnnotationPresent(MyAnnotation.class)) {
                System.out.println("被MyAnnotation注解修饰的field名：" + f.getName());
                MyAnnotation annotation = f.getAnnotation(MyAnnotation.class);
                System.out.println("方法" + f.getName() + "的MyTag注解内容为：" + annotation.name() + "，" + annotation.age());
            }
        }
    }

    public static void test1() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> forName = Class.forName("com.zhaoliang.annotation.MyAnnotationTest");
        MyAnnotationTest myAnnotationTest = (MyAnnotationTest) forName.newInstance();
        myAnnotationTest.info();

    }

    public static void test2() throws ClassNotFoundException {
        Class<?> myAnnotationTest = Class.forName("com.zhaoliang.annotation.MyAnnotationTest");
        Field[] fields = myAnnotationTest.getFields();
        System.out.println(fields.length);
        Stream.of(fields).forEach(field -> {
            if (field.isAnnotationPresent(MyAnnotation.class)) {
                System.out.println(field.getName());
                MyAnnotation annotation = field.getAnnotation(MyAnnotation.class);
                System.out.println(
                        "field = " + field.getName() + "的MyAnnotation注解内容为：" + annotation.name() + " , " + annotation.age());

            }
        });

        Stream.of(myAnnotationTest.getMethods()).forEach(method -> {
            if (method.isAnnotationPresent(MyAnnotation.class)) {
                System.out.println(method.getName());
                MyAnnotation annotation = method.getAnnotation(MyAnnotation.class);
                System.out.println(
                        "method = " + method.getName() + "的MyAnnotation注解内容为：" + annotation.name() + " , " + annotation.age());
            }
        });
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        ProcessTool.process("com.zhaoliang.annotation.MyAnnotationTest");
        test1();
        System.out.println("------------test2()---------------");
        test2();
    }
}
