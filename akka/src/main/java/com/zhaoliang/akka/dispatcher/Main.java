package com.zhaoliang.akka.dispatcher;

import akka.actor.ActorSystem;
import scala.concurrent.ExecutionContext;

/**
 * Created by zhaoliang on 2016/4/17.
 */
public class Main {
    ActorSystem system = ActorSystem.create("test");
    final ExecutionContext ex = system.dispatchers().lookup("cluster-dispatcher");
}
