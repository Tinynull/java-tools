package com.zhaoliang.netty;

import java.util.Date;

/**
 * this is a object
 * Created by zhaoliang(weston_contribute@163.com) on 2016/8/1.
 */
public class UnixTime {

    private final long value;

    public UnixTime() {
        this(System.currentTimeMillis() / 1000L + 2208988800L);
    }

    public UnixTime(long value) {
        this.value = value;
    }

    public long value() {
        return value;
    }

    @Override
    public String toString() {
        return new Date((value() - 2208988800L) * 1000L).toString();
    }
}
