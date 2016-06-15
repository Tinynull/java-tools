package com.zhaoliang.ignite.datagrid;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.binary.BinaryObject;
import org.apache.ignite.cache.query.ScanQuery;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.lang.IgniteBiPredicate;

/**
 * Created by zhaoliang(weston_contribute@163.com) on 2016/6/12.
 */
public class SQLTest {

    public static void main(String[] args) {
        test1();
    }

    /**
     *
     */
    private static void test1() {

        IgniteConfiguration cfg = new IgniteConfiguration();
        cfg.setClientMode(true);
        cfg.setPeerClassLoadingEnabled(true);
        Ignite ignite = Ignition.start(cfg);

        IgniteCache<Integer, BinaryObject> cache = ignite.cache("students").withKeepBinary();

        ScanQuery<Integer, BinaryObject> scan = new ScanQuery<>(
            new IgniteBiPredicate<Integer, BinaryObject>() {
                @Override
                public boolean apply(Integer key, BinaryObject student) {
                    return student.<Long>field("orgId") > 1;
                }
            }
        );

        // Execute queries for salary ranges.
        System.out.printf("People with salaries between 0 and 1000 (queried with SCAN query): ", cache.query(scan).getAll());
    }
}
