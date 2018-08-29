package com.zhaoliang.commons.configuration2;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.convert.DefaultListDelimiterHandler;
import org.apache.commons.configuration2.ex.ConfigurationException;

/**
 * 多个属性文件的操作。
 *
 * Created by zhaoliang(weston_contribute@163.com) on 2016/6/7.
 */
public class MuiltProperties {
    public static void main(String[] args) throws ConfigurationException {
        Parameters params = new Parameters();
        FileBasedConfigurationBuilder<PropertiesConfiguration> builder =
            new FileBasedConfigurationBuilder<>(PropertiesConfiguration.class)
                .configure(params.properties()
                                 .setFileName("usergui.properties")
                                 .setListDelimiterHandler(new DefaultListDelimiterHandler(',')));
        Configuration config = builder.getConfiguration();
        System.out.println(config.getString("colors.foreground"));
        System.out.println(config.getString("user.file"));
    }
}
