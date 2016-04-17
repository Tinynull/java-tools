package com.zhaoliang.thread.study.one.t8;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		MyThread mythread = new MyThread();
		System.out.println("begin ==" + mythread.isAlive());
		mythread.start();
		System.out.println("end ==" + mythread.isAlive());
		Thread.sleep(2000L);
		System.out.println("end1 ==" + mythread.isAlive());
	}
}
