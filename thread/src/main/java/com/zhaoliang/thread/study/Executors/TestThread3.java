package com.zhaoliang.thread.study.Executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 可变尺寸的线程池
 * 
 * @author hxm
 *
 */
public class TestThread3 {
	public static void main(String args[]) {
		// 创建一个可根据需要创建新线程的线程池，但是在以前构造的线程可用时将重用它们。
		ExecutorService pool = Executors.newCachedThreadPool();
		// 创建实现了runnable接口的对象
		Thread t1 = new MyThread3();
		Thread t2 = new MyThread3();
		Thread t3 = new MyThread3();
		Thread t4 = new MyThread3();
		Thread t5 = new MyThread3();
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

class MyThread3 extends Thread {

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " is running...");
	}
}
