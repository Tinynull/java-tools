package com.zhaoliang.jackson.core.jacksonInFiveMinutes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: zhaoliang
 * @create: 2018-04-23
 * @description:
 **/
public class Test2 {
    public static void main(String[] args) {


        String str = "";
        String pattern = "^(^[1-9][0-9]{0,7}|0)([.][0-9])?$";

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher("104.5");
        System.out.println(m.matches());

        Matcher m2 = r.matcher("0.5sdfsdf");
        System.out.println(m2.matches());

        Matcher m3 = r.matcher("123.232");
        System.out.println(m3.matches());


        Matcher m4 = r.matcher("1000000000.232sfsfs");
        System.out.println(m4.matches());

        Matcher m5 = r.matcher("00001.2");
        System.out.println(m5.matches());
    }
}
