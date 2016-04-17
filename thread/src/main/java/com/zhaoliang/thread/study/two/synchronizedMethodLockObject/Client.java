package com.zhaoliang.thread.study.two.synchronizedMethodLockObject;

public class Client {

	/**
	 * 关键字synchronized取的是对象锁。多个线程访问同一个对象的synchronized方法，一个线程取的对象锁后其他线程只能等待。
	 * 
	 * 这个例子说明Thread.sleep()并不释放对象锁。
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		MyObject myObject = new MyObject();
		ThreadA a = new ThreadA(myObject);
		a.setName("A");
		ThreadB b = new ThreadB(myObject);
		b.setName("B");
		a.start();
		b.start();
	}

}
