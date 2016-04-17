package com.zhaoliang.thread.study.two.dirtyread;

public class Client {

	public static void main(String[] args) throws InterruptedException {
		PublicVar publicVar = new PublicVar();
		MyThread t = new MyThread(publicVar);
		t.start();
		Thread.sleep(200L);
		publicVar.getValue();
	}

}
