package com.zhaoliang.thread.study.one.me;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		Thread t = new IsAliveTestThread();
		t.start();
		Thread.sleep(2000L); 
		System.out.println("1 t.isAlive()=" + t.isAlive());
		t.interrupt();
		Thread.sleep(2000L);
		System.out.println("2 t.isAlive()=" + t.isAlive());

	}

}
