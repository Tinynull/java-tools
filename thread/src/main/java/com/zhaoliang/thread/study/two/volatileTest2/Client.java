package com.zhaoliang.thread.study.two.volatileTest2;

public class Client {

	public static void main(String[] args) throws InterruptedException {
		PrintString printString = new PrintString();
		new Thread(printString).start();
		Thread.sleep(5000L);
		System.out.println("stop it stopThread=" + Thread.currentThread().getName());
		printString.setContinuePrint(false);
	}

}
