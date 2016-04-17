package com.zhaoliang.thread.study.two.synchronizedMethodLockObject;

public class MyObject {

	public synchronized void methodA() {
		try {
			System.out.println("begin methodA threadName="
					+ Thread.currentThread().getName());
			Thread.sleep(5000L);
			System.out.println("end");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
