package com.zhaoliang.commons.configuration2;

import org.apache.commons.configuration2.interpol.Lookup;

/**
 * Created by zhaoliang (weston_contribute@163.com) on 2016/10/14.
 */
public class EchoLookup implements Lookup {
    public String lookup(String varName) {
        return "Value of variable " + varName;
    }
}
