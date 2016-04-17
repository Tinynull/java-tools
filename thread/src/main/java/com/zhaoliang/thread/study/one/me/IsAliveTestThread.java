package com.zhaoliang.thread.study.one.me;

public class IsAliveTestThread extends Thread {

	@Override
	public void run() {
		System.out.println(getName() + "start");
		try {
			Thread.sleep(5000L);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}

		try {
			Thread.sleep(5000L);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(getName() + "end");
	}
}
