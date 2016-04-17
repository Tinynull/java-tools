package com.zhaoliang.thread.study.three.joinTest;

public class MyThread extends Thread {

	@Override
	public void run() {
		int random = (int) (Math.random() * 10000);
		try {
			Thread.sleep(random);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("random=" + random);
	}
}
