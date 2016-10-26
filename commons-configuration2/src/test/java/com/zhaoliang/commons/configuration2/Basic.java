package com.zhaoliang.commons.configuration2;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * test
 *
 * Created by zhaoliang (weston_contribute@163.com) on 2016/10/14.
 */
public class Basic {
    private static Logger logger = Logger.getLogger(Basic.class);

    @Test
    public void loadProperties() throws Exception {
        Configurations configs = new Configurations();
        try {
            Configuration config = configs.properties(new File("database.properties"));
            assertNotNull(config);
            assertEquals(8199, config.getInt("database.port"));
            assertEquals("default", config.getString("no", "default"));
        } catch (ConfigurationException cex) {
            logger.error(cex);
        }
    }

    @Test
    public void loadXmlProperties() throws Exception {
        Configurations configurations = new Configurations();
        XMLConfiguration config = configurations.xml("path.xml");
        assertNotNull(config);

        // 获取属性
        String stage = config.getString("processing[@stage]");
        assertEquals("qa", stage);

        List<String> paths = config.getList(String.class, "processing.paths.path");
        List<String> expected = new ArrayList<String>() {{
            add("/data/path1");
            add("/data/otherpath");
            add("/var/log");
        }};
        assertArrayEquals(expected.toArray(), paths.toArray());

        String secondPath = config.getString("processing.paths.path(1)");
        assertEquals("/data/otherpath", secondPath);

    }

    /**
     * 并没有达到目的。
     *
     * @throws Exception
     */
    @Test
    public void saveProperty() throws Exception {
        Configurations configs = new Configurations();
        // obtain the configuration
        FileBasedConfigurationBuilder<XMLConfiguration> builder = configs.xmlBuilder("path.xml");
        XMLConfiguration config = builder.getConfiguration();

        String secondPath = config.getString("processing.paths.path(1)");
        assertEquals("/data/otherpath", secondPath);

        // update property
        config.addProperty("newProperty", "newValue");

        // save configuration
        builder.save();
    }

    /**
     * 并没有达到目的。
     * @throws Exception
     */
    @Test
    public void saveProperties() throws Exception {
        Configurations configurations = new Configurations();
        // obtain the configuration
        FileBasedConfigurationBuilder<PropertiesConfiguration> builder = configurations.propertiesBuilder(new File("database.properties"));
        PropertiesConfiguration config = builder.getConfiguration();

        assertEquals(8199, config.getInt("database.port"));

        config.addProperty("newKey","newValue");

        // save configuration
        builder.save();

    }
}