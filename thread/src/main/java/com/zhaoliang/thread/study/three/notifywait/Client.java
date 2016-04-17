package com.zhaoliang.thread.study.three.notifywait;

public class Client {

	public static void main(String[] args) throws InterruptedException {
		Object lock = new Object();
		Thread1 t1 = new Thread1(lock);
		Thread2 t2 = new Thread2(lock);
		t1.start();
		Thread.sleep(1000);
		t2.start();
	}

}
