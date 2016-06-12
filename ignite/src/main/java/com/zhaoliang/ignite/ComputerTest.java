package com.zhaoliang.ignite;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCluster;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.lang.IgniteCallable;

import java.util.ArrayList;
import java.util.Collection;

/**
 * ComputerTest
 *
 * Created by zhaoliang(weston_contribute@163.com) on 2016/6/8.
 */
public class ComputerTest {
    public static void main(String[] args) {

        ////////////////////////
        ///////start client/////
        ////////////////////////
        IgniteConfiguration cfg = new IgniteConfiguration();

        // Enable client mode.
        cfg.setClientMode(true);

        // Start Ignite in client mode.
        Ignite ignite = Ignition.start(cfg);

        IgniteCluster cluster = ignite.cluster();

        Collection<IgniteCallable<Integer>> calls = new ArrayList<>();

        // Iterate through all the words in the sentence and create Callable jobs.
        for (final String word : "Count characters using callable".split(" ")){
            calls.add(word::length);
        }
        // Execute collection of Callables on the grid.
        Collection<Integer> res = ignite.compute().call(calls);

        // Add up all the results.
        int sum = res.stream().mapToInt(Integer::intValue).sum();
        System.out.println("Total number of characters is '" + sum + "'.");
    }
}
