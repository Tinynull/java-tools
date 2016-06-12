package com.zhaoliang.ignite.datagrid;

import com.zhaoliang.ignite.model.Students;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.query.TextQuery;
import org.apache.ignite.configuration.IgniteConfiguration;

/**
 * Created by zhaoliang(weston_contribute@163.com) on 2016/6/12.
 */
public class SQLTest {

    public static void main(String[] args) {
        IgniteConfiguration cfg = new IgniteConfiguration();
        cfg.setClientMode(true);
        cfg.setPeerClassLoadingEnabled(true);
        Ignite ignite = Ignition.start(cfg);
    }

    private static void test1(Ignite ignite) {

    }
}
