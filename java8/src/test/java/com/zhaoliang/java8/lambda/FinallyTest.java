package com.zhaoliang.java8.lambda;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * 六个例子彻底理解finally语句块.
 * <p>
 * Created by zhaoliang (weston_contribute@163.com) on 2016/10/16.
 */
public class FinallyTest {

    @Test(expected = java.lang.ArithmeticException.class)
    public void name1() throws Exception {
        int i = 5 / 0;
        try {
            System.out.println("Try block");
        } catch (Exception e) {
            System.out.println("Catch block");
        } finally {
            System.out.println("Finally block");
        }

    }

    /**
     * test.
     *
     * @throws Exception ex
     */
    @Ignore
    @Test
    public void name2() throws Exception {
        try {
            System.out.println("Try block");
            System.exit(0);
        } catch (Exception e) {
            System.out.println("Catch block");
        } finally {
            System.out.println("Finally block");
        }
    }

    @Test
    public void name3() throws Exception {
        int test = test1();
        System.out.println(test);
    }

    private static int test1() {
        try {
            return 1;
        } catch (Exception e) {
            return 0;
        } finally {
            System.out.println("Finally block");
        }
    }

    private static int test2() {
        try {
            return 1;
        } catch (Exception e) {
            return 0;
        } finally {
            System.out.println("Finally block");
            return 3;
        }
    }

    @Test
    public void name4() throws Exception {
        int i = test2();
        Assert.assertEquals(3, i);
    }

    private static StringBuilder test3() {
        StringBuilder builder = new StringBuilder("hello ");
        try {
            return builder;
        } catch (Exception e) {
            return null;
        } finally {
            builder.append("world");
            System.out.println("Finally block");
        }
    }

    @Test
    public void name5() throws Exception {
        StringBuilder stringBuilder = test3();
        assert stringBuilder != null;
        Assert.assertEquals("hello world", stringBuilder.toString());
    }

    private static String test4() {
        StringBuilder builder = new StringBuilder("hello");
        try {
            return builder.toString();
        } catch (Exception e) {
            return null;
        } finally {
            builder.append(" world");
            System.out.println("Finally block");
        }
    }

    @Test
    public void name6() throws Exception {
        String s = test4();
        Assert.assertEquals("hello", s);
    }
}