package com.zhaoliang.thread.study.three.IllegalMonitorStateException;

public class Test2 {

	/**
	 * 但线程不能永远等待下去，那样程序就停止不前，不继续向下运行了。如何使呈等待wait状态的线程继续运行呢？答案就是使用notify()方法。
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			String lock = new String();
			System.out.println("syn上面");
			synchronized (lock) {
				System.out.println("syn第一行");
				lock.wait();
				System.out.println("wait下的代码!");
			}
			System.out.println("syn下面的代码");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
