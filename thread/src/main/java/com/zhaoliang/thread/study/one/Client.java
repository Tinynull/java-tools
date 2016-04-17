package com.zhaoliang.thread.study.one;

public class Client {

	public static void main(String[] args) {

		Thread thread = new MyThread();
		// 如果多次调用start()方法，则会出现异常Exceptionin
		// thread"main"java.lang.IllegalThreadStateException。
		// thread.start();
		thread.start();

		System.out.println(Thread.currentThread().getName() + " over!");
	}

}
