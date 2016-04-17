package com.zhaoliang.thread.study.three.notifywait;

public class Thread2 extends Thread {
	private Object lock;

	public Thread2(Object lock) {
		this.lock = lock;
	}

	@Override
	public void run() {
		synchronized (lock) {
			try {
				System.out.println("notify before");
				lock.notify();
				Thread.sleep(3000L);
				System.out.println("notify after");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
