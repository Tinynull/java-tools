package com.zhaoliang.thread.study.two.dirtyread;

public class MyThread extends Thread {
	private PublicVar publicVar;

	public MyThread(PublicVar publicVar) {
		this.publicVar = publicVar;
	}

	@Override
	public void run() {
		super.run();
		publicVar.setValue("B", "BB");
	}
}
