package com.zhaoliang.thread.study.one.t13_1;

public class MyThread extends Thread {

	@Override
	public void run() {
		try {

			super.run();
			for (int i = 0; i < 500000; i++) {
				if (Thread.interrupted()) {
					System.out.println("已经是停止状态了!我要退出了!");
					throw new InterruptedException();
				}
				System.out.println("i=" + (i + 1));
			}
			System.out.println("end");
		} catch (InterruptedException e) {
			System.out.println(getName() + "线程已经推出。");
		}
	}
}
