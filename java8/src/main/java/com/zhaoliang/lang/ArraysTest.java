package com.zhaoliang.lang;

import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * Created by zhaoliang(weston_contribute@163.com) on 2016/9/4.
 */
public class ArraysTest {
    private static int[] testData = new int[]{3, 2, 7, 4, 6, 5, 9, 8};

    public static void main(String[] args) {
        // 按照升序排序。
        Arrays.sort(testData);

        // 选择某个范围进行排序。
        Arrays.sort(testData, 1, testData.length - 1);

        // 降序
        Integer[] integers = {3, 2, 7, 4, 6, 5, 9, 8};
        Arrays.parallelSort(integers, (o1, o2) -> o2 - o1);
        System.out.println(Arrays.toString(integers));

        Integer[] integers1 = new Integer[integers.length];
        System.arraycopy(integers, 0, integers1, 1, integers.length - 1);
        System.out.println("integers = " + Arrays.toString(integers1));


        int[] testData1 = {3, 2, 7, 4, 6, 5, 9, 8};
        Arrays.parallelSort(testData1);
        System.out.println(Arrays.toString(testData1));
    }


}
