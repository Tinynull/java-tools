package com.script.another;

import org.apache.commons.lang3.StringUtils;

import javax.script.*;
import java.io.IOException;
import java.util.List;

import static java.lang.System.err;
import static java.lang.System.out;

/**
 * Created by zhaoliang (weston_contribute@163.com) on 2016/11/14.
 */
public class ListScriptEngines {
    public static void main(String args[]) throws IOException, ScriptException, NoSuchMethodException {
        ScriptEngineManager manager = new ScriptEngineManager();

        // 得到所有的脚本引擎工厂
        List<ScriptEngineFactory> factories = manager.getEngineFactories();

        for (ScriptEngineFactory factory : factories) {
            out.printf("Name: %s%n" +
                            "Version: %s%n" +
                            "Language name: %s%n" +
                            "Language version: %s%n" +
                            "Extensions: %s%n" +
                            "Mime types: %s%n" +
                            "Names: %s%n",
                    factory.getEngineName(),
                    factory.getEngineVersion(),
                    factory.getLanguageName(),
                    factory.getLanguageVersion(),
                    factory.getExtensions(),
                    factory.getMimeTypes(),
                    factory.getNames());
        }

        System.out.println("---------------------------------------------");
        reverseString();
        System.out.println();
        System.out.println("---------------------------------------------");
        compileScript();
        System.out.println("---------------------------------------------");
        invocableTest();
        System.out.println(StringUtils.center("runPythonScript", 40, '-'));
//        runPythonScript();
//        runPythonScript2();
//        runPythonScript3();

    }

    /**
     * 和脚本语言进行交互
     * <p>
     * 上面例子只是运行了一个非常简单的脚本。这个脚本是孤立的，并未通过Java向这脚本传递任何的值。
     * 虽然从这个脚本返回了一个值，但这种返回方式是隐式的。
     * <p>
     * 脚本引擎除了这些简单的功能，还为我们提供了更强大的功能。甚至可以通过Java向脚本语言中传递参数，
     * 还可以将脚本语言中的变量的值取出来。这些功能要依靠ScriptEngine中的两个方法put和get。
     * <p>
     * put 有两个参数，一个是脚本变量名，另一个是变量的值，这个值是Object类型，因此，可以传递任何值。
     * <p>
     * get 有一个参数，就是脚本变量的名。
     * <p>
     * 下面的代码通过javascript脚本将一个字符串翻转(这个字符串是通过java传给javascript的)，然后
     * 通过java得到这个被翻转后的字符后，然后输出。
     */
    public static void reverseString() {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("javascript");
        try {
            engine.put("name", "abcdefg");
            engine.eval("var output ='' ;" +
                    "for (i = 0; i <= name.length; i++) {" +
                    " output = name.charAt(i) + output" +
                    "}");
            String name = (String) engine.get("output");
            out.printf("被翻转后的字符串：%s", name);
        } catch (ScriptException e) {
            err.println(e);
        }
    }

    private static void compileScript() {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("javascript");
        engine.put("counter", 0);
        if (engine instanceof Compilable) {
            Compilable compEngine = (Compilable) engine;
            try {
                CompiledScript script = compEngine.compile("function count() { " +
                        " counter = counter +1; " +
                        " return counter; " +
                        "}; count();");
                out.printf("Counter: %s%n", script.eval());
                out.printf("Counter: %s%n", script.eval());
                out.printf("Counter: %s%n", script.eval());
            } catch (ScriptException e) {
                err.println(e);
            }
        } else {
            err.println("这个脚本引擎不支持编译!");
        }
    }

    /**
     * 动态调用脚本语言的方法
     * <p>
     * 上面的例子只有一个函数，可以通过eval进行调用并将它的值返回。但如果脚本中有多个函数或
     * 想通过用户的输入来决定调用哪个函数，这就需要使用invoke方法进行动态调用。和编译一样，
     * 脚本引擎必须实现 Invocable接口 才可以动态调用脚本语言中的方法。下面的例子将演示如何
     * 通过动态调用的方式来运行上面的翻转字符串的javascript脚本。
     */
    private static void invocableTest() {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("javascript");
        String name = "abcdefg";
        if (engine instanceof Invocable) {
            try {
                engine.eval("function reverse(name) {" +
                        " var output =' ';" +
                        " for (i = 0; i <= name.length; i++) {" +
                        " output = name.charAt(i) + output" +
                        " } return output;}");
                Invocable invokeEngine = (Invocable) engine;
                Object o = invokeEngine.invokeFunction("reverse", name);
                out.printf("翻转后的字符串：%s", o);
            } catch (NoSuchMethodException e) {
                err.println(e);
            } catch (ScriptException e) {
                err.println(e);
            }
        } else {
            err.println("这个脚本引擎不支持动态调用");
        }
    }

//    public static void runPythonScript() throws FileNotFoundException {
//        ScriptEngineManager manager = new ScriptEngineManager();
//        ScriptEngine engine = manager.getEngineByName("python");
//
//        try {
//            engine.eval("print \"hello \" ");
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public static void runPythonScript2() throws FileNotFoundException {
//        PythonInterpreter interpreter = new PythonInterpreter();
//        interpreter
//                .execfile("Function.py");
//    }
//
//    public static void runPythonScript3() throws IOException, ScriptException, NoSuchMethodException {
//        ScriptEngineManager manager = new ScriptEngineManager();
//        ScriptEngine engine = manager.getEngineByName("python");
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(ListScriptEngines.class.getClassLoader().getResourceAsStream("MyTest.py")));
//        engine.eval(bufferedReader);
//        bufferedReader.close();
//        Invocable invokeEngine = (Invocable) engine;
//        Object o = invokeEngine.invokeFunction("hello", null);
//        out.printf("翻转后的字符串：%s", o);
//    }
}