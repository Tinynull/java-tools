package com.zhaoliang.thread.study.three.wait_notify_size5;

public class ThreadB extends Thread {

	private MyList myList;

	public ThreadB(MyList myList) {
		super();
		this.myList = myList;
	}

	@Override
	public void run() {
		synchronized (myList) {

			try {
				if (myList.size() != 5) {
					System.out.println("wait begin " + System.currentTimeMillis());
					myList.wait();
					System.out.println("wait end " + System.currentTimeMillis());
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
