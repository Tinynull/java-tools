package com.zhaoliang.thread.study.one.t4;

public class MyThread extends Thread {

	private int count = 3;

	@Override
	public synchronized void run() {
		count--;
		System.out.println("由 " + getName() + " 计算，count=" + count);
	}

}
