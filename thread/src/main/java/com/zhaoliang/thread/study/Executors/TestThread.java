package com.zhaoliang.thread.study.Executors;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 自定义线程池
 * @author zhaoliang
 *
 */
public class TestThread {
	public static void main(String args[]) {
		// 创建等待队列
		BlockingQueue<Runnable> bqueue = new ArrayBlockingQueue<Runnable>(20);
		// 创建一个单线程执行程序，它可安排在给定延迟后运行命令或者定期地执行。
		ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 3, 2, TimeUnit.MILLISECONDS, bqueue);

		// 创建实现了runnable接口的对象
		Thread t1 = new MyThread1();
		Thread t2 = new MyThread1();
		Thread t3 = new MyThread1();
		Thread t4 = new MyThread1();
		Thread t5 = new MyThread1();
		// 将线程放入池中进行执行
		pool.execute(t1);
		pool.execute(t2);
		pool.execute(t3);
		pool.execute(t4);
		pool.execute(t5);
		// 关闭线程池
		pool.shutdown();
	}
}

class MyThread1 extends Thread {

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " is running...");
		try {
			Thread.sleep(100L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}