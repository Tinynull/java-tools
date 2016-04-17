package com.zhaoliang.thread.study.one.t13;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		MyThread thread = new MyThread();
		thread.start();
		Thread.sleep(2000);
		thread.interrupt();
	}

}
