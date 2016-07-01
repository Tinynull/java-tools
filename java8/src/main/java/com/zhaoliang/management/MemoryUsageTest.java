package com.zhaoliang.management;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

/**
 * Created by zhaoliang(weston_contribute@163.com) on 2016/6/21.
 */
public class MemoryUsageTest {
    public static void main(String[] args) {
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage memoryUsage = memoryMXBean.getHeapMemoryUsage(); //椎内存使用情况
        long totalMemorySize = memoryUsage.getInit(); //初始的总内存
        long maxMemorySize = memoryUsage.getMax(); //最大可用内存
        long usedMemorySize = memoryUsage.getUsed(); //已使用的内存



        System.out.println("totalMemorySize=" + totalMemorySize / 1024 / 1024);
        System.out.println("maxMemorySize=" + (double)maxMemorySize / 1024 / 1024 / 1024);
        System.out.println("usedMemorySize=" + usedMemorySize);
    }
}
