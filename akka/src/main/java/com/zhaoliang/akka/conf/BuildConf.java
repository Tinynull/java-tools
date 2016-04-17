package com.zhaoliang.akka.conf;

import akka.actor.ActorSystem;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class BuildConf {
	public static void main(String[] args) {
		Config myConfig = ConfigFactory.parseString("something=somethingelse");

		/**
		 * load the normal config stack (system props, then application.conf,
		 * then reference.conf)
		 */
		Config regularConfig = ConfigFactory.load();

		/**
		 * override regular stack with myConfig
		 */
		Config combind = myConfig.withFallback(regularConfig);

		/**
		 * put the result in between the overrides (system props) and defaults
		 * again
		 */
		Config complete = ConfigFactory.load(combind);

		/**
		 * create ActorSystem
		 */
		ActorSystem system = ActorSystem.create("zhaoliang", complete);
	}
}
