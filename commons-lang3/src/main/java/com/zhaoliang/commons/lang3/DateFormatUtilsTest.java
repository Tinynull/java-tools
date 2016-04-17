package com.zhaoliang.commons.lang3;

import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.StopWatch;

public class DateFormatUtilsTest {
    public static void main(String[] args) {
        StopWatch sw = new StopWatch();
        sw.start();
        String date = DateFormatUtils.ISO_DATE_FORMAT.format(new Date());
        System.out.println(date);
        sw.stop();
        System.out.println(sw.getNanoTime());
    }
}
