package com.zhaoliang.thread.study.one.t3;

public class MyThread extends Thread {

	private int count = 5;

	public MyThread(String threadName) {
		super();
		this.setName(threadName);
	}

	@Override
	public void run() {
		while (count > 0) {
			System.out.println("有线程  " + getName() + " 计算出值：" + count);
			count--;
		}
	}
}
