package com.zhaoliang.thread.study.two.synchronizedMethodLockObject;

public class ThreadB extends Thread {
	private MyObject myObject;

	public ThreadB(MyObject myObject) {
		this.myObject = myObject;
	}

	@Override
	public void run() {
		super.run();
		myObject.methodA();
	}
}
