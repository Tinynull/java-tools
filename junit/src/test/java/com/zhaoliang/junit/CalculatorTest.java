package com.zhaoliang.junit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Created by zhaoliang (weston_contribute@163.com) on 2016/10/9.
 */
public class CalculatorTest {
    @Test
    public void evaluate() throws Exception {
        Calculator calculator = new Calculator();
        int sum = calculator.evaluate("1+2+3");
        assertEquals(6, sum);
    }

}