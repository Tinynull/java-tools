package com.zhaoliang.commons.configuration2;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

/**
 * Created by zhaoliang (weston_contribute@163.com) on 2016/10/14.
 */
public class FileBasedConfigurationTest {

    private Configuration config;
    private FileBasedConfigurationBuilder<FileBasedConfiguration> builder;


    @Before
    public void setUp() throws Exception {

        Parameters params = new Parameters();
        File propertiesFile = new File("C:\\Users\\zhaoliang\\Desktop\\database.properties");

        builder = new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
                .configure(params.fileBased().setFile(propertiesFile));

        // enable auto save mode
        builder.setAutoSave(true);
        try {
            config = builder.getConfiguration();
            Assert.assertEquals("admin", config.getString("database.user"));
        } catch (ConfigurationException cex) {
            // loading of the configuration file failed
        }
    }


    @Test
    public void addProperty() throws Exception {
        // Some manipulations on the configuration object
        config.addProperty("newProperty", "new");
        config.setProperty("updateProperty", "changedValue1");

        try {
            // Make changes persistent
            builder.save();
        } catch (ConfigurationException cex) {
            // saving of the configuration file failed
        }

    }
}
