package com.zhaoliang.java8.lambda;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 本例介绍最广为人知的函数式编程概念map。它允许你将对象进行转换。例如在
 * 本例中，我们将 costBeforeTax 列表的每个元素转换成为税后的值。我们将
 * x -> x*x lambda表达式传到 map() 方法，后者将其应用到流中的每一个元素
 * 。然后用 forEach() 将列表元素打印出来。使用流API的收集器类，可以得到
 * 所有含税的开销。有 toList() 这样的方法将 map 或任何其他操作的结果合
 * 并起来。由于收集器在流上做终端操作，因此之后便不能重用流了。你甚至可以
 * 用流API的 reduce() 方法将所有数字合成一个，下一个例子将会讲到。
 *
 * Created by zhaoliang(weston_contribute@163.com) on 2016/4/23.
 */
public class MapReduceTest {
    public static void main(String[] args) {
        mapReduceTest1();
        mapReduce2();
        distinctTest();
        test();
        filterTest();
        test2();
    }

    public static void mapReduceTest1() {
        // 不使用lambda表达式为每个订单加上12%的税
        System.out.println("----------before java8------------");
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        for (Integer cost : costBeforeTax) {
            double price = cost + .12 * cost;
            System.out.println(price);
        }

        System.out.println("----------java8------------");
        // 使用lambda表达式
        List<Integer> costBeforeTax2 = Arrays.asList(100, 200, 300, 400, 500);
        costBeforeTax2.stream().map((cost) -> cost + .12 * cost).forEach(System.out::println);
    }

    /**
     * 在上个例子中，可以看到map将集合类（例如列表）元素进行转换的。
     * 还有一个 reduce() 函数可以将所有值合并成一个。Map和Reduce
     * 操作是函数式编程的核心操作，因为其功能，reduce 又被称为折叠
     * 操作。另外，reduce 并不是一个新的操作，你有可能已经在使用它。
     * SQL中类似 sum()、avg() 或者 count() 的聚集函数，实际上就是
     * reduce 操作，因为它们接收多个值并返回一个值。流API定义
     * 的 reduceh() 函数可以接受lambda表达式，并对所有值进行合并。
     * IntStream这样的类有类似 average()、count()、sum() 的内建
     * 方法来做 reduce 操作，也有mapToLong()、mapToDouble() 方法
     * 来做转换。这并不会限制你，你可以用内建方法，也可以自己定义。
     * 在这个Java 8的Map Reduce示例里，我们首先对所有价格应用 12%
     * 的VAT，然后用 reduce() 方法计算总和。
     */
    public static void mapReduce2() {
        // 为每个订单加上12%的税
        System.out.println("----------before java8------------");
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        double total = 0;
        for (Integer cost : costBeforeTax) {
            double price = cost + .12 * cost;
            total = total + price;
        }
        System.out.println("Total : " + total);

        System.out.println("----------java8------------");
        List<Integer> costBeforeTax2 = Arrays.asList(100, 200, 300, 400, 500);
        double bill = costBeforeTax2.stream().map((cost) -> cost + .12 * cost).reduce((sum, cost) -> sum + cost).get();
        System.out.println("Total : " + bill);
    }


    public static void filterTest() {
        // 创建一个字符串列表，每个字符串长度大于2
        final List<String> strList = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        List<String> filtered = strList.stream().filter(x -> x.length() > 8).collect(Collectors.toList());
        System.out.printf("Original List : %s, filtered list : %s %n", strList, filtered);
    }

    public static void test() {
        // 将字符串换成大写并用逗号链接起来
        List<String> G7 = Arrays.asList("USA", "Japan", "France", "Germany", "Italy", "U.K.", "Canada");
        String G7Countries = G7.stream().map(String::toUpperCase).collect(Collectors.joining(", "));
        System.out.println(G7Countries);
    }

    public static void distinctTest() {
        // 用所有不同的数字创建一个正方形列表
        List<Integer> numbers = Arrays.asList(9, 10, 3, 4, 7, 3, 4);
        List<Integer> distinct = numbers.stream().map(i -> i * i).distinct().collect(Collectors.toList());
        System.out.printf("Original List : %s,  Square Without duplicates : %s %n", numbers, distinct);
    }

    /**
     * IntStream、LongStream 和 DoubleStream 等流的类中，有个非常有用
     * 的方法叫做 summaryStatistics() 。可以返回 IntSummaryStatistics、
     * LongSummaryStatistics 或者 DoubleSummaryStatistic s，描述流中
     * 元素的各种摘要数据。在本例中，我们用这个方法来计算列表的最大值和最小
     * 值。它也有 getSum() 和 getAverage() 方法来获得列表的所有元素的总
     * 和及平均值。
     */
    public static void test2() {
        //获取数字的个数、最小值、最大值、总和以及平均值
        List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
        IntSummaryStatistics stats = primes.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("Highest prime number in List : " + stats.getMax());
        System.out.println("Lowest prime number in List : " + stats.getMin());
        System.out.println("Sum of all prime numbers : " + stats.getSum());
        System.out.println("Average of all prime numbers : " + stats.getAverage());
    }
}
