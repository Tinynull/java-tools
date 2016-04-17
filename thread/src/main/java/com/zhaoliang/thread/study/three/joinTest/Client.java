package com.zhaoliang.thread.study.three.joinTest;

public class Client {

	public static void main(String[] args) throws InterruptedException {
		MyThread myThread = new MyThread();
		myThread.start();
		myThread.join();
		System.out.println(Thread.currentThread().getName() + " over!");
	}

}
