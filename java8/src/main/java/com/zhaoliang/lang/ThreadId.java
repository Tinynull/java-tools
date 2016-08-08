package com.zhaoliang.lang;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * {@link ThreadLocal} 学习。
 *
 * http://blog.csdn.net/lufeng20/article/details/24314381
 * http://qifuguang.me/2015/09/02/[Java%E5%B9%B6%E5%8F%91%E5%8C%85%E5%AD%A6%E4%B9%A0%E4%B8%83]%E8%A7%A3%E5%AF%86ThreadLocal/
 *
 *
 * Created by zhaoliang(weston_contribute@163.com) on 2016/8/8.
 */
public class ThreadId {
    private static final AtomicInteger nextId = new AtomicInteger(0);

    private static final ThreadLocal<Integer> threadId = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return nextId.getAndIncrement();
        }
    };

    public static Integer getThreadId() {
        return threadId.get();
    }
}
