package com.zhaoliang.thread.study.Executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 当要加入的池的线程（或者任务）超过池最大尺寸时候，则进入此线程池需要排队等待。一旦池中有线程完毕，则排队等待的某个线程会入池执行。
 * 
 * @author zhaoliang
 *
 */
public class TestThread2 {
	public static void main(String args[]) {
		// testNewSingleThreadScheduledExecutor();
		testScheduleAtFixedRate();
	}
	
	public static void testNewSingleThreadScheduledExecutor() {
		// 创建一个线程池，它可安排在给定延迟后运行命令或者定期地执行
		ScheduledExecutorService pool = Executors.newSingleThreadScheduledExecutor();
		Thread t1 = new MyThread2(1);
		Thread t2 = new MyThread2(2);
		Thread t3 = new MyThread2(3);
		Thread t4 = new MyThread2(4);
		Thread t5 = new MyThread2(5);
		Thread t6 = new MyThread22();
		Thread t7 = new MyThread22();
		pool.execute(t1);
		pool.execute(t2);
		pool.execute(t3);
		pool.execute(t4);
		pool.execute(t5);
		pool.schedule(t6, 10, TimeUnit.SECONDS);
		pool.schedule(t7, 10, TimeUnit.SECONDS);
		pool.scheduleAtFixedRate(t1, 5, 1, TimeUnit.SECONDS);
		// 关闭线程池
		pool.shutdown();
	}

	/**
	 * 
	 */
	public static void testScheduleAtFixedRate() {
		ScheduledExecutorService pool = Executors.newSingleThreadScheduledExecutor();
		pool.scheduleAtFixedRate(new MyThread22(), 5, 1, TimeUnit.SECONDS);
	}
}

class MyThread2 extends Thread {
	long sleepTime;

	public MyThread2(long st) {
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

class MyThread22 extends Thread {

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " is running..." + " MyThread22");
	}
}
