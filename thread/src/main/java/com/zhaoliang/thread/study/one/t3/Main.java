package com.zhaoliang.thread.study.one.t3;

public class Main {

	public static void main(String[] args) {
		Thread t1 = new MyThread("A");
		Thread t2 = new MyThread("B");
		Thread t3 = new MyThread("C");
		t1.start();
		t2.start();
		t3.start();
	}

}
