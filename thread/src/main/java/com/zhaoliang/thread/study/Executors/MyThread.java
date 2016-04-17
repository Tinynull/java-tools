package com.zhaoliang.thread.study.Executors;

public class MyThread implements Runnable {
	private int count = 0;
	public MyThread(int count) {
		this.count = count;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " : MyThread is running." + count);
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
	}

}
