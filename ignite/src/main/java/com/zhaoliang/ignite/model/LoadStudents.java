package com.zhaoliang.ignite.model;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.IgniteConfiguration;

/**
 * Created by zhaoliang(weston_contribute@163.com) on 2016/6/12.
 */
public class LoadStudents {
    public static void main(String[] args) {
        loadData();
    }

    private static void loadData() {
        IgniteConfiguration cfg = new IgniteConfiguration();
        cfg.setClientMode(true);
        cfg.setPeerClassLoadingEnabled(true);

        Ignite ignite = Ignition.start(cfg);

        IgniteCache<Integer, Students> cache = ignite.getOrCreateCache("students");
//        cache.putIfAbsent(1, new Students.Builder(1).setOrgId(10001).setFirstName("zhao").setLastName("liang").setResume("this is a good man").setSalary(1.0).build());
//        cache.putIfAbsent(2, new Students.Builder(2).setOrgId(10002).setFirstName("cao").setLastName("zhifu").setResume("this is a doubi").setSalary(3.0).build());
//        cache.putIfAbsent(3, new Students.Builder(3).setOrgId(10003).setFirstName("he").setLastName("shaohua").setResume("this is a doubi too").setSalary(5.0).build());
//        cache.putIfAbsent(4, new Students.Builder(4).setOrgId(10004).setFirstName("song").setLastName("zihao").setResume("this is my boss").setSalary(7.0).build());
        cache.destroy();

        ignite.close();
    }
}
