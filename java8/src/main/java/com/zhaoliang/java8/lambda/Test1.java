package com.zhaoliang.java8.lambda;

import java.util.Arrays;
import java.util.List;

/**
 * 首先做的就是使用lambda表达式替换匿名类，而实现Runnable接口是匿名类的
 * 最好示例。看一下Java 8之前的runnable实现方法，需要4行代码，而使用lambda
 * 表达式只需要一行代码。我们在这里做了什么呢？那就是用() -> {}代码块替代了整
 * 个匿名类。
 *
 * Created by zhaoliang(weston_contribute@163.com) on 2016/4/23.
 */
public class Test1 {
    public static void main(String[] args) {
        new Thread(() -> System.out.println("hello")).start();
        List<String> features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        features.forEach(System.out::println);
    }
}
