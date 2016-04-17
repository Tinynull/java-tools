package com.zhaoliang.commons.lang3;

import java.text.MessageFormat;

import org.apache.commons.lang3.StringUtils;

/**
 *
 */
public class StringUtilsTest {

    public static final int width = 15;
    public static final String message = "{0}|{1}|{2}";

    public static void main(String[] args) {
        System.out.println(StringUtils.rightPad("name", width) + "|");
        System.out.println(StringUtils.rightPad("zhaoliang", width) + "|");
        System.out.println(StringUtils.rightPad("xierui", width) + "|");
        System.out.println(StringUtils.rightPad("zhaodingguo", width) + "|");
        System.out.println(StringUtils.rightPad("zhanzhiliang", width) + "|");
        System.out.println(StringUtils.rightPad("zhaoya", width) + "|");

        System.out.println();

        System.out.println(MessageFormat.format(message, pRight("zhaoliang"), pRight("12"), pRight("miluozhong")));
        System.out.println(MessageFormat.format(message, pRight("wule"), pRight("12"), pRight("taolin")));
        System.out.println(MessageFormat.format(message, pRight("zhaoliang"), pRight("12"), pRight("miluozhong")));
        System.out.println(MessageFormat.format(message, pRight("mahansha"), pRight("12"), pRight("miluozhong")));
        System.out.println(MessageFormat.format(message, pRight("anzaitian"), pRight("12"), pRight("miluozhong")));

        System.out.println(StringUtils.center("leftPad", 80, '-'));

        System.out.println(MessageFormat.format(message, pLeft("zhaoliang"), pLeft("12"), pLeft("miluozhong")));
        System.out.println(MessageFormat.format(message, pLeft("wule"), pLeft("12"), pLeft("taolin")));
        System.out.println(MessageFormat.format(message, pLeft("zhaoliang"), pLeft("12"), pLeft("miluozhong")));
        System.out.println(MessageFormat.format(message, pLeft("mahansha"), pLeft("12"), pLeft("miluozhong")));
        System.out.println(MessageFormat.format(message, pLeft("anzaitian"), pLeft("12"), pLeft("miluozhong")));
    }

    public static String pRight(String s) {
        return StringUtils.rightPad(s, width);
    }

    public static String pLeft(String s) {
        return StringUtils.leftPad(s, width);
    }
}
