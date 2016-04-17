package com.zhaoliang.thread.study.Executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class NewSingleThreadScheduledExecutor {

	public static void main(String[] args) {
		// test1();
		// test2();
		test3();
	}

	/**
	 * 当添加多个线程是会出现什么情况？ 说明当添加多个线程时，会一个线程顺序执行。
	 */
	public static void test1() {
		ScheduledExecutorService pool = Executors
				.newSingleThreadScheduledExecutor();
		Thread t1 = new MyThread11(1);
		Thread t2 = new MyThread11(1);
		Thread t3 = new MyThread11(1);
		Thread t4 = new MyThread11(1);
		Thread t5 = new MyThread11(1);
		pool.execute(t1);
		pool.execute(t2);
		pool.execute(t3);
		pool.execute(t4);
		pool.execute(t5);
		pool.shutdown();
	}

	/**
	 * 当添加多个线程是调用scheduleAtFixedRate会出现什么情况？
	 * 实际上有多少个线程并不会因为调用scheduleAtFixedRate时而有前后顺序的影响。
	 */
	public static void test2() {
		ScheduledExecutorService pool = Executors
				.newSingleThreadScheduledExecutor();
		Thread t6 = new MyThread11(1);
		t6.setName("t6");
		Thread t7 = new MyThread222();
		t7.setName("t7");
		pool.scheduleAtFixedRate(t6, 1, 1, TimeUnit.SECONDS);
		pool.scheduleAtFixedRate(t7, 2, 1, TimeUnit.SECONDS);
	}

	/**
	 * 如果执行的线程抛出运行时异常，则线程会停止执行。 然而与说明文档中的意思不一样（Note however that if this single
	 * thread terminates due to a failure during execution prior to shutdown, a
	 * new one will take its place if needed to execute subsequent tasks.）
	 * 
	 * 创建并执行一个在给定初始延迟后首次启用的定期操作，随后，在每一次执行终止和下一次执行开始之间都存在给定的延迟。如果任务的任一执行遇到异常，
	 * 就会取消后续执行。否则，只能通过执行程序的取消或终止方法来终止
	 */
	public static void test3() {
		ScheduledExecutorService pool = Executors
				.newSingleThreadScheduledExecutor();
		Thread t6 = new MyThread333();
		pool.scheduleAtFixedRate(t6, 1, 1, TimeUnit.SECONDS);
	}
}

class MyThread11 extends Thread {
	long sleepTime;

	public MyThread11(long st) {
		this.sleepTime = st * 1000;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " is running...");
	}
}

class MyThread222 extends Thread {

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " is running..."
				+ " MyThread222");
	}
}

class MyThread333 extends Thread {

	private static int count = 0;

	@Override
	public void run() {
		count++;
		if (count == 4) {
			throw new RuntimeException("设置异常。");
		}

		System.out.println(Thread.currentThread().getName() + " is running..."
				+ " MyThread222");
	}
}
