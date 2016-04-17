package com.zhaoliang.thread.study.two.twoObjectTwoLock;

public class HasSelfPrivateNum {

	private int num = 0;

	// 关键字synchronized取的是对象锁。多个线程访问同一个对象的synchronized方法，一个线程取的对象锁后其他线程只能等待。
	public synchronized void addI(String username) {
		try {
			if ("a".equalsIgnoreCase(username)) {
				num = 100;
				System.out.println("a set over!");
				Thread.sleep(2000L);
			} else if ("b".equalsIgnoreCase(username)) {
				num = 200;
				System.out.println("b set over!");
				Thread.sleep(2000L);
			}
			System.out.println(username + " num=" + num);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
