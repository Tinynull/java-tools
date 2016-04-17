package com.zhaoliang.akka.helloexample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.actor.ActorRef;

public class HelloWorld extends UntypedActor {

	private static final Logger logger = LoggerFactory
			.getLogger(HelloWorld.class);

	@Override
	public void preStart() {
		final ActorRef greeter = getContext().actorOf(
				Props.create(Greeter.class), "greeter");
		greeter.tell(Greeter.Msg.GREET, getSelf());
	}

	@Override
	public void onReceive(Object msg) {
		if (msg == Greeter.Msg.DONE) {
			logger.info(msg.toString());
			getContext().stop(getSelf());
		} else
			unhandled(msg);
	}
}
