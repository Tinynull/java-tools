package com.zhaoliang.java8.lambda;

import org.junit.Test;

/**
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
     * 
     * @throws Exception
     */
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
        

    }
}
