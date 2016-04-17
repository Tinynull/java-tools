package com.zhaoliang.thread.study.one.t4;

public class Main {

	public static void main(String[] args) {
		Thread sale = new MyThread();
		Thread a1 = new Thread(sale, "a");
		Thread a2 = new Thread(sale, "b");
		Thread a3 = new Thread(sale, "c");
		a1.start();
		a2.start();
		a3.start();
	}

}
