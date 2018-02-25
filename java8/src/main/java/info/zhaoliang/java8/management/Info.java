package info.zhaoliang.java8.management;

import sun.lwawt.*;

import java.lang.management.*;
import java.util.Date;
import java.util.List;

/**
 * Created by zhaoliang on 2017/7/13.
 */
public class Info {

    public static void main(String[] args) {
        systemInfo();
        heapMemoryUsageInfo();
        runtimeInfo();
        inputArguments();
    }

    public static void systemInfo() {
        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
        StringBuilder info = new StringBuilder();
        info.append("操作系统：").append(operatingSystemMXBean.getName()).append('\n')
                .append("系统架构：").append(operatingSystemMXBean.getArch()).append('\n')
                .append("可用核数：").append(operatingSystemMXBean.getAvailableProcessors()).append('\n')
                .append("架构版本：").append(operatingSystemMXBean.getVersion()).append('\n')
                .append("装载时间：").append(operatingSystemMXBean.getSystemLoadAverage()).append('\n');
        System.out.println(info);
    }

    public static void heapMemoryUsageInfo(){
        MemoryUsage heapMemoryUsage = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();
        System.out.println(heapMemoryUsage);
        MemoryUsage nonHeapMemoryUsage = ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage();
        System.out.println(nonHeapMemoryUsage);

        CompilationMXBean compilationMXBean = ManagementFactory.getCompilationMXBean();
        System.out.println(compilationMXBean);

        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        System.out.println(runtimeMXBean);
    }

    public static void runtimeInfo(){
        System.out.println("--------runtime info ----------");
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(runtimeMXBean.getBootClassPath()).append('\n')
                .append(runtimeMXBean.getClassPath()).append('\n')
                .append(runtimeMXBean.getInputArguments()).append('\n')
                .append(runtimeMXBean.getLibraryPath()).append('\n')
                .append(runtimeMXBean.getName()).append('\n')
                .append(runtimeMXBean.getSpecName()).append('\n')
                .append(runtimeMXBean.getSpecVendor()).append('\n')
                .append(runtimeMXBean.getSpecVersion()).append('\n')
                .append(runtimeMXBean.getStartTime()).append('\n')
                .append(new Date(runtimeMXBean.getUptime())).append('\n')
                .append(runtimeMXBean.getSystemProperties()).append('\n');
        System.out.println(stringBuffer);
    }

    public static void inputArguments(){
        List<String> inputArguments = ManagementFactory.getRuntimeMXBean().getInputArguments();
        inputArguments.forEach(System.out::println);

    }




}
