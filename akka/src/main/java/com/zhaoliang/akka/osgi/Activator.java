package com.zhaoliang.akka.osgi;

import org.osgi.framework.BundleContext;

import akka.actor.ActorSystem;
import akka.osgi.ActorSystemActivator;

public class Activator extends ActorSystemActivator {

	@Override
	public void configure(BundleContext arg0, ActorSystem arg1) {
		
	}
	
	@Override
	public void start(BundleContext context) {
		super.start(context);
	}

	
	@Override
	public void stop(BundleContext context) {
		super.stop(context);
	}
	
}