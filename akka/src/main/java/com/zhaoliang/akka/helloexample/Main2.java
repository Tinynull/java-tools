package com.zhaoliang.akka.helloexample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.Terminated;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class Main2 {

	private static Logger logger = LoggerFactory.getLogger(Main2.class);

	public static void main(String[] args) {
		ActorSystem system = ActorSystem.create("HelloSystem");
		ActorRef a = system.actorOf(Props.create(HelloWorld.class),
				"helloWorld");
		
		/*
		 * 这种传递参数的方式是值得学习的。{@link Props.create(Class clazz,Object obj)}
		 * 将obj作为一个参数传入clazz类的构造函数中去。
		 */
		system.actorOf(Props.create(Terminator.class, a), "terminator");
	}

	public static class Terminator extends UntypedActor {

		private final LoggingAdapter log = Logging.getLogger(getContext()
				.system(), this);
		private final ActorRef ref;

		public Terminator(ActorRef ref) {
			this.ref = ref;

			// 这句话不懂(?)
			getContext().watch(ref);
		}

		@Override
		public void onReceive(Object msg) {
			if (msg instanceof Terminated) {
				log.info("{} has terminated, shutting down system", ref.path());
				getContext().system().shutdown();
			} else {
				unhandled(msg);
			}
		}

	}
}
