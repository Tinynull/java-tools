package com.zhaoliang.javatuples;

import org.javatuples.Pair;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by zhaoliang (weston_contribute@163.com) on 2016/12/20.
 */
public class PairTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void test1() throws Exception {
        Pair<String, Integer> pair = Pair.with("hello", 23);
        assertEquals("hello", pair.getValue0());
        assertEquals(Integer.valueOf(23), pair.getValue1());
    }
}