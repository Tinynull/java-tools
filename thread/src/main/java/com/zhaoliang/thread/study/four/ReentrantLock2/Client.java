package com.zhaoliang.thread.study.four.ReentrantLock2;

public class Client {

	public static void main(String[] args) {
		MyService myService = new MyService();
		Thread a = new MyThread(myService);
		Thread a1 = new MyThread(myService);
		Thread a2 = new MyThread(myService);
		Thread a3 = new MyThread(myService);
		Thread a4 = new MyThread(myService);
		Thread a5 = new MyThread(myService);
		a.start();
		a1.start();
		a2.start();
		a3.start();
		a4.start();
		a5.start();

	}

}
