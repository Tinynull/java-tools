package com.zhaoliang.annotation.repeatable;

import java.lang.annotation.*;

/**
 * Created by zhaoliang (weston_contribute@163.com) on 2016/10/27.
 */
@Repeatable(Hints.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Hint {
    String value();
}
