package com.zhaoliang.thread.study.four.UseConditionWaitNotifyOK;

public class Run {

	public static void main(String[] args) throws InterruptedException {
		MyService service = new MyService();
		ThreadA threadA = new ThreadA(service);
		threadA.start();
		Thread.sleep(3000);
		
		//通知
		service.signal();
	}
}

/**
 * 运行MyService中的await();
 * @author zhaoliang
 *
 */
class ThreadA extends Thread {
	private MyService service;

	public ThreadA(MyService service) {
		super();
		this.service = service;
	}

	@Override
	public void run() {
		service.await();
	}
}