package com.zhaoliang.thread.study.three.IllegalMonitorStateException;

public class Test1 {

	/**
	 * 如果调用wait()时没有持有适当的锁，则抛出IllegalMonitorStateException，
	 * 它是RuntimeException的一个子类，因此，不需要try-catch语句进行捕捉异常。
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			String newString = new String("");
			newString.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
