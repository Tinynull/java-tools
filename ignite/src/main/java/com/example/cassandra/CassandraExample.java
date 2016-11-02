/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.cassandra;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.IgniteException;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.log4j.Logger;

public class CassandraExample {

    private static final Logger LOGGER = Logger.getLogger(CassandraExample.class.getName());

    /**
     * Executes example.
     *
     * @param args Command line arguments, none required.
     * @throws IgniteException If example execution failed.
     */
    public static void main(String[] args) throws IgniteException {
        try (Ignite ignite = Ignition.start("ignite/ignite-config.xml")) {
            IgniteCache<Long, Long> longCache = ignite.getOrCreateCache(new CacheConfiguration<Long, Long>("cache1"));
            IgniteCache<String, String> strCache = ignite.getOrCreateCache(new CacheConfiguration<String, String>("cache2"));

            LOGGER.info("Running single operation write tests");
            longCache.put(1L, 1L);
            strCache.put("1", "1");
            LOGGER.info("Single operation write tests passed");
        }
    }


}
