package com.zhaoliang.junit.rule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import static org.junit.Assert.assertEquals;

/**
 * Created by zhaoliang (weston_contribute@163.com) on 2016/11/1.
 */

public class NameRuleTest {
    @Rule
    public TestName name = new TestName();

    @Test
    public void testA() {
        System.out.println(name.getMethodName());
        assertEquals("testA", name.getMethodName());

    }

    @Test
    public void testB() {
        System.out.println(name.getMethodName());
        assertEquals("testB", name.getMethodName());
    }

    @Test
    public void name() throws Exception {
        System.out.println(this.getClass().getMethod(cleanMethodName(name.getMethodName())));

    }

    /**
     * If using "parameterized test" junit will append an identifier to the end of the method name which prevents it
     * from being found via reflection.  This method removes that suffix.
     */
    private static String cleanMethodName(final String methodName) {
        if (methodName.endsWith("]")) {
            return methodName.substring(0, methodName.indexOf("["));
        }

        return methodName;
    }
}