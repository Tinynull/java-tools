package com.zhaoliang.thread.study.four.condition;

public class Run {

	public static void main(String[] args) throws InterruptedException {
		MyService service = new MyService();
		
		ThreadA a = new ThreadA(service);
		a.setName("A");
		a.start();
		
		ThreadB b = new ThreadB(service);
		b.setName("B");
		b.start();
		
		Thread.sleep(3000);
		
		service.signalAll_A();
	}

}

class ThreadA extends Thread {
	private MyService myService;

	public ThreadA(MyService myService) {
		this.myService = myService;
	}

	@Override
	public void run() {
		myService.awaitA();
	}
}

class ThreadB extends Thread {
	private MyService myService;

	public ThreadB(MyService myService) {
		this.myService = myService;
	}

	@Override
	public void run() {
		myService.awaitB();
	}
}