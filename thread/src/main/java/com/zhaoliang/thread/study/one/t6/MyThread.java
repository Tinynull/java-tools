package com.zhaoliang.thread.study.one.t6;

public class MyThread extends Thread {

	public MyThread() {
		System.out.println("构造方法的打印：" + Thread.currentThread().getName());
	}

	@Override
	public synchronized void run() {
		System.out.println("run方法的打印：" + Thread.currentThread().getName());
	}

}
