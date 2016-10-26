package com.zhaoliang.commons.configuration2;

import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.BasicConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.convert.DefaultListDelimiterHandler;
import org.junit.Test;

/**
 * Created by zhaoliang (weston_contribute@163.com) on 2016/10/14.
 */
public class CreatingConfigurations {
    @Test
    public void basicConfigurationBuilder() throws Exception {
        Parameters params = new Parameters();
        BasicConfigurationBuilder<PropertiesConfiguration> builder =
                new BasicConfigurationBuilder<>(PropertiesConfiguration.class);
        builder.configure(params.basic()
                .setListDelimiterHandler(
                        new DefaultListDelimiterHandler(','))
                .setThrowExceptionOnMissing(true));
        PropertiesConfiguration config = builder.getConfiguration();

    }
}
