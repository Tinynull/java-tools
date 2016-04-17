package com.zhaoliang.thread.study.four.ReentrantLock2;

public class MyThread extends Thread {

	private MyService myService;

	public MyThread(MyService myService) {
		this.myService = myService;
	}

	@Override
	public void run() {
		myService.methodA();;
	}
}
