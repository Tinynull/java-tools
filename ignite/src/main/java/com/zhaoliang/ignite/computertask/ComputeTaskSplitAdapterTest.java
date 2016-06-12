package com.zhaoliang.ignite.computertask;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCompute;
import org.apache.ignite.Ignition;
import org.apache.ignite.compute.ComputeJob;
import org.apache.ignite.compute.ComputeJobAdapter;
import org.apache.ignite.compute.ComputeJobResult;
import org.apache.ignite.compute.ComputeTaskSplitAdapter;
import org.apache.ignite.configuration.IgniteConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by zhaoliang(weston_contribute@163.com) on 2016/6/8.
 */
public class ComputeTaskSplitAdapterTest {


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

        List<String> collect = Stream.generate(() -> RandomStringUtils.random(5)).limit(10000).collect(Collectors.toList());
        String words = "";
        for(String s : collect){
            words = words + " " + s;
        }

//        IgniteCompute compute = ignite.compute(ignite.cluster().forLocal());
        IgniteCompute compute = ignite.compute();

        // Execute task on the clustr and wait for its completion.
        int cnt = compute.execute(CharacterCountTask.class, "hello world  adf dfasd fassdf ");

        System.out.println(">>> Total number of characters in the phrase is '" + cnt + "'.");
        ignite.close();
    }

    /**
     * Task to count non-white-space characters in a phrase.
     */
    private static class CharacterCountTask extends ComputeTaskSplitAdapter<String, Integer> {
        // 1. Splits the received string into to words
        // 2. Creates a child job for each word
        // 3. Sends created jobs to other nodes for processing.
        @Override
        public List<ComputeJob> split(int gridSize, String arg) {
            String[] words = arg.split(" ");
            List<ComputeJob> jobs = new ArrayList<>(words.length);
            for (final String word : arg.split(" ")) {
                jobs.add(new ComputeJobAdapter() {
                    @Override
                    public Object execute() {
                        System.out.println(">>> Printing '" + word + "' on from compute job.");
                        // Return number of letters in the word.
                        return word.length();
                    }
                });
            }
            return jobs;
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
