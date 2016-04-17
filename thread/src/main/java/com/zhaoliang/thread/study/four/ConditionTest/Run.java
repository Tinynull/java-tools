package com.zhaoliang.thread.study.four.ConditionTest;

public class Run {

	public static void main(String[] args) {
		MyService myService = new MyService();
		ThreadA threadA = new ThreadA(myService);
		ThreadB threadB = new ThreadB(myService);
		threadA.start();
		threadB.start();
	}

}

class ThreadA extends Thread {
	private MyService myService;

	public ThreadA(MyService myService) {
		this.myService = myService;
	}

	@Override
	public void run() {
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			myService.get();
		}
	}
}

class ThreadB extends Thread {
	private MyService myService;

	public ThreadB(MyService myService) {
		this.myService = myService;
	}

	@Override
	public void run() {
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			myService.set();
		}
	}
}
