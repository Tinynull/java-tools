package com.zhaoliang.thread.study.two.volatileTest;

public class Client {

	public static void main(String[] args) throws InterruptedException {
		PrintString printString = new PrintString();
		printString.printStringMethod();
		System.out.println("stop it stopThread=" + Thread.currentThread().getName());
		Thread.sleep(1000L);
		printString.setContinuePrint(false);
	}

}
