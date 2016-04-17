package com.zhaoliang.thread.study.two.synchronizedMethodLockObject;

public class ThreadA extends Thread {
	private MyObject myObject;

	public ThreadA(MyObject myObject) {
		this.myObject = myObject;
	}

	@Override
	public void run() {
		super.run();
		myObject.methodA();
	}
}
