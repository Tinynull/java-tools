package com.zhaoliang.commons.configuration2;

import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.interpol.ConfigurationInterpolator;
import org.apache.commons.configuration2.interpol.ExprLookup;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by zhaoliang (weston_contribute@163.com) on 2016/10/14.
 */
public class VariableInterpolation {
    PropertiesConfiguration config;

    @Before
    public void setUp() throws Exception {
        Configurations configurations = new Configurations();
        config = configurations.properties("variableInterpolation.properties");

    }

    @Test
    public void composite() throws Exception {
        String title = config.getString("application.title");
        assertEquals("Killer App 1.6.2", title);
    }

    /**
     * This prefix marks a variable to be a system property. Commons Configuration will
     * search for a system property with the given name and replace the variable by its
     * value. This is a very easy means for accessing the values of system properties in
     * every configuration implementation.
     *
     * @throws Exception
     */
    @Test
    public void systemProperty() throws Exception {
        assertEquals("C:\\Users\\zhaoliang\\settings.xml", config.getString("user.file"));
    }

    /**
     * The const prefix indicates that a variable is to be interpreted as a constant member
     * field of a class (i.e. a field with the static final modifiers). The name of the variable
     * must be of the form <full qualified class name>.<field name>. The specified class will be
     * loaded and the value of this field will be obtained.
     *
     * @throws Exception
     */
    @Test
    public void constProperty() throws Exception {
        int actual = config.getInt("action.key");
        int expected = 3;
        assertEquals(expected, actual);

    }

    /**
     * Variables can also reference OS-specific environment properties. This is indicated by the env prefix.
     *
     * @throws Exception
     */
    @Test
    public void environmentProperties() throws Exception {
        String javaHome = config.getString("java.home");
        String expected = "C:\\Program Files\\Java\\jdk1.8.0_65";
        assertEquals(expected, javaHome);

    }

    @Test
    public void exprProperties() throws Exception {
        String settings = config.getString("user.settings");
        System.out.println(settings);

    }

    @Test
    public void customizingInterpolation() throws Exception {
        ConfigurationInterpolator configurationInterpolator = new ConfigurationInterpolator();
        configurationInterpolator.registerLookup("echo", new EchoLookup());
        configurationInterpolator.registerLookup("expr", new ExprLookup());
        config.setInterpolator(configurationInterpolator);
        String echo = config.getString("echo");
        System.out.println(echo);
        String mySettings = config.getString("mysettings");
        System.out.println(mySettings);

    }
}
