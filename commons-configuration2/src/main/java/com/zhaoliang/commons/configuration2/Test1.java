package com.zhaoliang.commons.configuration2;

import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.BasicConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.convert.DefaultListDelimiterHandler;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

/**
 * configuration.
 *
 * Created by zhaoliang(weston_contribute@163.com) on 2016/6/7.
 */
public class Test1 {
    public static void main(String[] args) throws IOException, ConfigurationException {
//        test1();
        variableInterpolation();

    }

    /**
     * @throws ConfigurationException
     * @throws IOException
     */
    private static void variableInterpolation() throws ConfigurationException, IOException {
        PropertiesConfiguration configuration = new PropertiesConfiguration();
        InputStream resourceAsStream = Test1.class.getClassLoader().getResourceAsStream("test.properties");
        configuration.read(new InputStreamReader(resourceAsStream));
        resourceAsStream.close();

        System.out.println(configuration.getString("application.title"));
        System.out.println(configuration.getString("user.file"));
        System.out.println(configuration.getString("action.key"));
        System.out.println(configuration.getString("java.home"));
        System.out.println(configuration.getString("os.name"));

    }

    public static void test1() {
        PropertiesConfiguration config = new PropertiesConfiguration();
        // Enable list delimiter handling using a slash as delimiter character
        config.setListDelimiterHandler(new DefaultListDelimiterHandler('/'));
        // Now add some properties
        config.addProperty("greeting", "Hello, how are you?");
        config.addProperty("colors.pie", new String[]{"#FF0000", "#00FF00", "#0000FF"});
        config.addProperty("colors.graph", "#808080/#00FFCC/#6422FF");

        // Access data
        String salut = config.getString("greeting");
        System.out.println(salut);
        List<Object> colPie = config.getList("colors.pie");
        System.out.println(colPie);

        String[] colGraph = config.getStringArray("colors.graph");
        System.out.println(Arrays.asList(colGraph));

        String firstPieColor = config.getString("colors.pie");
        System.out.println(firstPieColor);

    }

    public static void basicConfigurationBuilder() throws ConfigurationException {
        Parameters params = new Parameters();
        BasicConfigurationBuilder<PropertiesConfiguration> builder =
            new BasicConfigurationBuilder<>(PropertiesConfiguration.class)
                .configure(params.basic()
                                 .setListDelimiterHandler(new DefaultListDelimiterHandler(','))
                                 .setThrowExceptionOnMissing(true));
        PropertiesConfiguration config = builder.getConfiguration();
    }
}
