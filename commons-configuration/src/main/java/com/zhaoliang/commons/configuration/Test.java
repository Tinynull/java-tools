package com.zhaoliang.commons.configuration;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * commons-configuration.
 *
 * Created by zhaoliang(weston_contribute@163.com) on 2016/6/6.
 */
public class Test {
    public static void main(String[] args) throws ConfigurationException {
        PropertiesConfiguration configuration = new PropertiesConfiguration("test.properties");
        System.out.println(configuration.getProperties("kafka"));
        Configuration kafka = configuration.subset("kafka");
        System.out.println(kafka);
        System.out.println(kafka.getString("buffer.memory"));
        System.out.println(kafka.getString("key.serializer"));
        System.out.println(kafka.getString("lue.serializer"));
    }
}
