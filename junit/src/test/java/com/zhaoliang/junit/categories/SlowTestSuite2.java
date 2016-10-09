package com.zhaoliang.junit.categories;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * test suite.
 *
 * Created by zhaoliang (weston_contribute@163.com) on 2016/10/9.
 */
@RunWith(Categories.class)
@Categories.IncludeCategory(SlowTests.class)
@Categories.ExcludeCategory(FastTests.class)
@Suite.SuiteClasses({A.class, B.class}) // Note that Categories is a kind of Suite
public class SlowTestSuite2 {
    // Will run A.b, but not A.a or B.c
}
