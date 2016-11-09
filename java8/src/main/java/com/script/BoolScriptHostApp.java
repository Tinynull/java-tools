package com.script;

import javax.script.*;
import java.util.Iterator;
import java.util.List;

/**
 * Created by zhaoliang (weston_contribute@163.com) on 2016/11/4.
 */
public class BoolScriptHostApp {

    /**
     * @param args
     */
    public static void main(String[] args) {
        ScriptEngineManager scriptManager = new ScriptEngineManager();
        List<ScriptEngineFactory> factories = scriptManager.getEngineFactories();
        Iterator<ScriptEngineFactory> iter = factories.iterator();
        while (iter.hasNext()) {
            ScriptEngineFactory factory = iter.next();
            System.out.println(factory.getEngineName());
        }

        List<Boolean> boolAnswers = null;
        ScriptEngine bsEngine = scriptManager.getEngineByExtension("bool");

        try {
            bsEngine.put("x", new Boolean(true));
            bsEngine.put("y", new Boolean(true));
            boolAnswers = (List<Boolean>) bsEngine.eval("x & y\n\n");
            printAnswers(boolAnswers);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        try {
            Compilable compiler = (Compilable) bsEngine;
            CompiledScript compiledScript = compiler.compile("x & y\n\n");
            Bindings bindings = new SimpleBindings();
            bindings.put("x", new Boolean(true));
            bindings.put("y", new Boolean(true));
            boolAnswers = (List<Boolean>) compiledScript.eval(bindings);
            printAnswers(boolAnswers);

            Invocable invocable = (Invocable) bsEngine;
            boolAnswers = (List<Boolean>) invocable.invokeFunction("eval", new Boolean(true), new Boolean(false));
            printAnswers(boolAnswers);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void printAnswers(List<Boolean> boolAnswers) {
        Iterator<Boolean> iter = boolAnswers.iterator();
        while (iter.hasNext()) {
            System.out.println("answer of boolean expression is: " + iter.next().toString());
        }
    }
}

