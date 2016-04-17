package com.zhaoliang.thread.study.three.notifywait;

public class Thread1 extends Thread {
	private Object lock;
	
	public Thread1(Object lock) {
		this.lock = lock;
	}
	@Override
	public void run() {
		synchronized (lock) {
			try {
				System.out.println("wait before");
				lock.wait();
				System.out.println("wait after");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
