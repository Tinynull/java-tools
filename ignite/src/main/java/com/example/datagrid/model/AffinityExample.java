package com.example.datagrid.model;

import com.zhaoliang.ignite.Client;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.IgniteException;
import org.apache.ignite.cache.CacheMode;
import org.apache.ignite.cache.affinity.AffinityKey;
import org.apache.ignite.cluster.ClusterNode;
import org.apache.ignite.configuration.CacheConfiguration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * Created by zhaoliang(weston_contribute@163.com) on 2016/7/7.
 */
public class AffinityExample {

    private static final String CACHE_NAME_COMPANY = AffinityExample.class.getSimpleName() + "-company";
    private static final String CACHE_NAME_PERSON = AffinityExample.class.getSimpleName() + "-person";

    public static void main(String[] args) throws IgniteException {
        Ignite ignite = Client.getIgnite2();
        System.out.println();
        System.out.println(">>> Cache affinity example started.");

        CacheConfiguration<String, Company> companyCfg = new CacheConfiguration<>();
        companyCfg.setCacheMode(CacheMode.PARTITIONED);
        companyCfg.setName(CACHE_NAME_COMPANY);
        IgniteCache<String, Company> companyIgniteCache = ignite.getOrCreateCache(companyCfg);

        CacheConfiguration<AffinityKey<String>, Person> personCfg = new CacheConfiguration<>();
        personCfg.setName(CACHE_NAME_PERSON);
        IgniteCache<AffinityKey<String>, Person> personIgniteCache = ignite.getOrCreateCache(personCfg);


        for (int i = 0; i < 10; i++) {
            companyIgniteCache.put("company" + i, new Company("name" + i, "address" + i));
        }

        for (int i = 0; i < 10; i++) {
            personIgniteCache.put(new AffinityKey<>("person" + i, "company" + i),
                                  new Person("first", "last", companyIgniteCache.get("company" + i)));
        }

        List<String> companyKeys = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            companyKeys.add("company" + i);
        }

        System.out.println("**************************");
//        System.out.println(personIgniteCache.get(new AffinityKey<>("company0")));
        System.out.println(ignite.cache(CACHE_NAME_PERSON).get("person0"));
        System.out.println("**************************");

        System.out.println("**************************");

        Map<ClusterNode, Collection<String>> mappings = ignite.<String>affinity(CACHE_NAME_COMPANY).mapKeysToNodes(companyKeys);
        for (Map.Entry<ClusterNode, Collection<String>> mapping : mappings.entrySet()) {
            ClusterNode node = mapping.getKey();
            System.out.println(node.addresses());

            final Collection<String> mappedKeys = mapping.getValue();
            System.out.println(mappedKeys);

//            if (node != null) {
//                // Bring computations to the nodes where the data resides (i.e. collocation).
//                ignite.compute(ignite.cluster().forNode(node)).run(() -> {
//                    IgniteCache<AffinityKey<String>, Person> cache = ignite.cache(CACHE_NAME_PERSON);
//                    for(String s : mappedKeys){
//                        Person person = cache.localPeek(new AffinityKey<>("person" + s.substring(s.length() - 1, s.length())));
//                        System.out.println(person);
//                    }
//                });
//            }
        }

        System.out.println();
        System.out.println("----------------------------");
        System.out.println();
        List<String> personKeys = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            personKeys.add("person" + i);
        }

        Map<ClusterNode, Collection<String>> mappings1 = ignite.<String>affinity(CACHE_NAME_PERSON).mapKeysToNodes(personKeys);
        for (Map.Entry<ClusterNode, Collection<String>> mapping : mappings1.entrySet()) {
            ClusterNode node = mapping.getKey();
            System.out.println(node.addresses());

            final Collection<String> mappedKeys = mapping.getValue();
            System.out.println(mappedKeys);

//            if (node != null) {
//                // Bring computations to the nodes where the data resides (i.e. collocation).
//                ignite.compute(ignite.cluster().forNode(node)).run(() -> {
//                    IgniteCache<AffinityKey<String>, Person> cache = ignite.cache(CACHE_NAME_PERSON);
//                    for(String s : mappedKeys){
//                        Person person = cache.localPeek(new AffinityKey<>("person" + s.substring(s.length() - 1, s.length())));
//                        System.out.println(person);
//                    }
//                });
//            }
        }



        ignite.destroyCache(CACHE_NAME_COMPANY);
        ignite.destroyCache(CACHE_NAME_PERSON);
        ignite.close();
    }

}
