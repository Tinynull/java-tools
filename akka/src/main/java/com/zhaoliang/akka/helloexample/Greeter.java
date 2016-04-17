package com.zhaoliang.akka.helloexample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import akka.actor.UntypedActor;

public class Greeter extends UntypedActor {
	private static Logger logger = LoggerFactory.getLogger(Greeter.class);

	public static enum Msg {
		GREET, DONE;
	}

	@Override
	public void onReceive(Object msg) {
		if (msg == Msg.GREET) {
			logger.info("Hello World!");
			getSender().tell(Msg.DONE, getSelf());
		} else
			unhandled(msg);
	}

}
