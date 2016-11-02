package com.zhaoliang.junit.executionorder;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestMethodOrder {

    @Test
    public void testB() {
        System.out.println("second");
    }

    @Test
    public void testA() {
        System.out.println("first");
    }

    @Test
    public void testC() {
        System.out.println("third");
    }

    @Ignore("Test is ignored as a demonstration")
    @Test
    public void testSame() {
        assertThat(1, is(1));
    }
}