package com.zhaoliang.ignite;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.lang.IgniteCallable;

import java.util.ArrayList;
import java.util.Collection;

/**
 * test.
 *
 * Created by zhaoliang(weston_contribute@163.com) on 2016/6/7.
 */
public class Main {

    /**
     * 我们写第一个计算应用，他会计算一句话中非空白字符的字符数量。作为
     * 一个例子，我们首先将一句话分割为多个单词，然后通过每个计算作业来
     * 计算每一个独立单词中的字符数量。最后，我们将从每个作业获得的结果
     * 简单相加来获得整个的数量。
     *
     * @param args 参数。
     */
    public static void main(String[] args) {
        try (Ignite ignite = Ignition.start("D:\\ignite-1.6.0\\examples\\config\\example-ignite.xml")) {

            Collection<IgniteCallable<Integer>> calls = new ArrayList<>();

            for (final String word : "Count characters using callable".split(" "))
                calls.add(word::length);

            // Execute collection of Callables on the grid.
            Collection<Integer> res = ignite.compute().call(calls);

            // Add up all the results.
            int sum = res.stream().mapToInt(Integer::intValue).sum();
            System.out.println("Total number of characters is '" + sum + "'.");
        }
    }
}
