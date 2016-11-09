package com.script;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * Created by zhaoliang (weston_contribute@163.com) on 2016/11/4.
 */
public class TestScript3 {
    public static void main(String[] args) throws Exception {
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("javascript");
        String exp = "a+b";
        scriptEngine.put("a", 4);
        scriptEngine.put("b", 15);
        Object result = scriptEngine.eval(exp);
        System.out.println(exp + "=" + result);
    }
}
