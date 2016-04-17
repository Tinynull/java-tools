package com.zhaoliang.thread.study.three.wait_notify_size5;

public class ThreadA extends Thread {

	private MyList myList;

	public ThreadA(MyList myList) {
		super();
		this.myList = myList;
	}

	@Override
	public void run() {
		synchronized (myList) {

			try {
				for (int i = 0; i < 10; i++) {
					myList.add();
					if(i == 5){
						myList.notify();
						System.out.println("发出通知。");
					}
					System.out.println("添加了" + (i + 1) + "个元素");
					Thread.sleep(1000);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
