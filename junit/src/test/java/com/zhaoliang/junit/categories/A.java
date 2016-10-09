package com.zhaoliang.junit.categories;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 * Created by zhaoliang (weston_contribute@163.com) on 2016/10/9.
 */
public class A {
    @Test
    public void a() {
        fail();
    }

    @Category(SlowTests.class)
    @Test
    public void b() {
    }
}
