package com.example.filesystem;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteFileSystem;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.IgniteConfiguration;

/**
 * Created by zhaoliang (weston_contribute@163.com) on 2016/10/31.
 */
public class Test2 {
    public static void main(String[] args) {
        fileSystem();
    }

    public static void fileSystem() {
        IgniteConfiguration cfg = new IgniteConfiguration();

        // Enable client mode.
        cfg.setClientMode(false);
        Ignite ignite = Ignition.start(cfg);
//        IgniteFileSystem fs = ignite.fileSystem("myFileSystem");
//        System.out.println(fs.name());
    }
}
