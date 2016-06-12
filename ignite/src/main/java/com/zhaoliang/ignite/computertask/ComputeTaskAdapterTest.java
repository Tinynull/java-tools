package com.zhaoliang.ignite.computertask;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCompute;
import org.apache.ignite.IgniteException;
import org.apache.ignite.Ignition;
import org.apache.ignite.cluster.ClusterNode;
import org.apache.ignite.compute.ComputeJob;
import org.apache.ignite.compute.ComputeJobAdapter;
import org.apache.ignite.compute.ComputeJobResult;
import org.apache.ignite.compute.ComputeTaskAdapter;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * ComputeTaskAdapter test.
 *
 * Created by zhaoliang(weston_contribute@163.com) on 2016/6/8.
 */
public class ComputeTaskAdapterTest {
    public static void main(String[] args) {

        ////////////////////////
        ///////start client/////
        ////////////////////////
        IgniteConfiguration cfg = new IgniteConfiguration();

        // Enable client mode.
        cfg.setClientMode(true);
        cfg.setPeerClassLoadingEnabled(true);

        // Start Ignite in client mode.
        Ignite ignite = Ignition.start(cfg);

        IgniteCompute compute = ignite.compute();

        // Execute task on the clustr and wait for its completion.
        int cnt = compute.execute(CharacterCountTask.class, "Hello Grid Enabled World!");

        System.out.println(">>> Total number of characters in the phrase is '" + cnt + "'.");
    }

    /**
     * Task to count non-white-space characters in a phrase.
     */
    private static class CharacterCountTask extends ComputeTaskAdapter<String, Integer> {
        // 1. Splits the received string into to words
        // 2. Creates a child job for each word
        // 3. Sends created jobs to other nodes for processing.
        @Override
        public Map<? extends ComputeJob, ClusterNode> map(List<ClusterNode> subgrid, String arg) {
            String[] words = arg.split(" ");
            Map<ComputeJob, ClusterNode> map = new HashMap<>(words.length);
            Iterator<ClusterNode> it = subgrid.iterator();
            for (final String word : arg.split(" ")) {
                // If we used all nodes, restart the iterator.
                if (!it.hasNext()) {
                    it = subgrid.iterator();
                }
                ClusterNode node = it.next();
                map.put(new ComputeJobAdapter() {
                    @Override
                    public Object execute() {
                        System.out.println(">>> Printing '" + word + "' on this node from grid job.");
                        // Return number of letters in the word.
                        return word.length();
                    }
                }, node);
            }
            return map;
        }

        @Override
        public Integer reduce(List<ComputeJobResult> results) {
            int sum = 0;
            for (ComputeJobResult res : results) {
                sum += res.<Integer>getData();
            }
            return sum;
        }
    }
}
