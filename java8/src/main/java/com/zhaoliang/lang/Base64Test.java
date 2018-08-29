package com.zhaoliang.lang;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.StringJoiner;

/**
 * Created by zhaoliang (weston_contribute@163.com) on 2016/10/3.
 */
public class Base64Test {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String s = "wUzbsYLCTwjQYgvW2gkkl98eBWyzxrarV4kGrR8BFbd1dD15T2KiBUTJP8blRkL1HVKpsTuGFiCRbCDaZOMJbK";
        String theUrl = "https://segmentfault.com/q/1010000000801988";
        String s1 = Base64.getUrlEncoder().encodeToString(theUrl.getBytes());
        System.out.println(s1);
    }
}
