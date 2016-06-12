package com.zhaoliang.ignite;

import org.apache.ignite.IgniteException;
import org.apache.ignite.lifecycle.LifecycleBean;
import org.apache.ignite.lifecycle.LifecycleEventType;

/**
 * Created by zhaoliang(weston_contribute@163.com) on 2016/6/11.
 */
public class MyLifecycleBean implements LifecycleBean {
    @Override
    public void onLifecycleEvent(LifecycleEventType evt) throws IgniteException {
        switch (evt) {
            case BEFORE_NODE_START:
                beforeNodeStart();
                break;
            case AFTER_NODE_START:
                break;
            case BEFORE_NODE_STOP:
                break;
            case AFTER_NODE_STOP:
                afterNodeStop();
                break;
        }
    }

    public static void beforeNodeStart() {
        System.out.println("BEFORE_NODE_START");
    }

    public static void afterNodeStop() {
        System.out.println("after_Node_Stop");

    }
}
