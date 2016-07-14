package com.zhaoliang.regex;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhaoliang(weston_contribute@163.com) on 2016/7/6.
 */
public class Regex {
    public static void main(String[] args) {
        getStrings(true); //用正则表达式获取指定字符串内容中的指定内容
        System.out.println("********************");
        replace(false); //用正则表达式替换字符串内容
        System.out.println("********************");
        strSplit(false); //使用正则表达式切割字符串
        System.out.println("********************");
        strMatch(false); //字符串匹配
    }

    private static void strMatch(boolean isTest) {
        if (!isTest) {
            return;
        }
        String phone = "13539770000";
        //检查phone是否是合格的手机号(标准:1开头，第二位为3,5,8，后9位为任意数字)
        System.out.println(phone + ":" + phone.matches("1[358][0-9]{9,9}")); //true

        String str = "abcd12345efghijklmn";
        //检查str中间是否包含12345
        System.out.println(str + ":" + str.matches("\\w+12345\\w+")); //true
        System.out.println(str + ":" + str.matches("\\w+123456\\w+")); //false
    }

    private static void strSplit(boolean isTest) {
        if (!isTest) {
            return;
        }
        String str = "asfasf.sdfsaf.sdfsdfas.asdfasfdasfd.wrqwrwqer.asfsafasf.safgfdgdsg";
        String[] strs = str.split("\\.");
        for (String s : strs) {
            System.out.println(s);
        }
    }

    private static void getStrings(boolean isTest) {
        if (!isTest) {
            return;
        }
        String str = "rrwerqq84461376qqasfdasdfrrwerqq84461377qqasfdasdaa654645aafrrwerqq84461378qqasfdaa654646aaasdfrrwerqq84461379qqasfdasdfrrwerqq84461376qqasfdasdf";
        Pattern p = Pattern.compile("qq(.*?)qq");
        Matcher m = p.matcher(str);
        ArrayList<String> strs = new ArrayList<>();
        while (m.find()) {
            System.out.println(m.group());
            System.out.println(m.group(0));
            System.out.println(m.group(1));
            System.out.println(m.groupCount());
            System.out.println("---------------");
            strs.add(m.group(1));
        }
//        strs.stream().forEach(s -> System.out.println(s));
    }

    private static void replace(boolean isTest) {
        if (!isTest) {
            return;
        }
        String str = "asfas5fsaf5s4fs6af.sdaf.asf.wqre.qwr.fdsf.asf.asf.asf";
        //将字符串中的.替换成_，因为.是特殊字符，所以要用\.表达，又因为\是特殊字符，所以要用\\.来表达.
        str = str.replaceAll("\\.", "_");
        System.out.println(str);
    }
}