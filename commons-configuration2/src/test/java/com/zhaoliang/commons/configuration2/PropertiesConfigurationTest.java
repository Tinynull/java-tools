package com.zhaoliang.commons.configuration2;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.convert.DefaultListDelimiterHandler;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoliang (weston_contribute@163.com) on 2016/10/14.
 */
public class PropertiesConfigurationTest {
    private Configuration config = null;
    private FileBasedConfigurationBuilder<FileBasedConfiguration> builder = null;

    @Before
    public void setUp() throws Exception {
        Parameters params = new Parameters();
        builder = new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
                .configure(params.properties().setListDelimiterHandler(new DefaultListDelimiterHandler(','))
                        .setFileName("usergui.properties"));
        try {
            config = builder.getConfiguration();
        } catch (ConfigurationException cex) {
        }

    }

    @After
    public void tearDown() throws Exception {
        builder.save();
    }

    @Test
    public void readProperties() throws Exception {
        Assert.assertEquals("zhaoliang", config.getString("name"));

    }

    @Test
    public void include() throws Exception {
        Assert.assertEquals(500, config.getInt("window.width"));
        Assert.assertEquals("#000080", config.getString("colors.foreground"));
    }

    @Test
    public void listAndArray() throws Exception {
        String[] colors = config.getStringArray("colors.pie");
        List<String> colorList = config.getList(String.class, "colors.pie");
        List<String> expected = new ArrayList<String>() {{
            add("#FF0000");
            add("#00FF00");
            add("#0000FF");
        }};
        Assert.assertArrayEquals(new String[]{"#FF0000", "#00FF00", "#0000FF"}, colors);
        Assert.assertEquals(expected, colorList);


    }
}
