package com.zhaoliang.junit.categories;

import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 * Created by zhaoliang (weston_contribute@163.com) on 2016/10/9.
 */
@Category({SlowTests.class, FastTests.class})
public class B {
    
    @Test
    public void c() {
    }
}
