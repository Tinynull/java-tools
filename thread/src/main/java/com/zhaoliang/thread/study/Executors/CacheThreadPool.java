package com.zhaoliang.thread.study.Executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CacheThreadPool {

	public static void main(String[] args) {
		testThreadPoolExecutor();
		testNewCachedThreadPool();
	}

	public static void testThreadPoolExecutor() {
		ExecutorService exec = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 10L, TimeUnit.SECONDS,
													  new SynchronousQueue<>());

		for (int i = 0; i < 5; i++)
			try {
				Thread.sleep(1000L);
				exec.execute(new MyThread(i));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		exec.shutdown();// 并不是终止线程的运行，而是禁止在这个Executor中添加新的任务
	}

	public static void testNewCachedThreadPool() {
		// 获取当前系统的CPU 数目
		int cpuNums = Runtime.getRuntime().availableProcessors();

		// ExecutorService通常根据系统资源情况灵活定义线程池大小
		int POOL_SIZE = 1;
		ExecutorService executorService = Executors.newFixedThreadPool(cpuNums * POOL_SIZE);
		executorService.shutdown();
	}

}
