package com.zhaoliang.thread.study.two.dirtyread;

public class PublicVar {

	public String username = "A";
	public String password = "AA";

	public synchronized void setValue(String username, String password) {
		this.username = username;
		try {
			Thread.sleep(5000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		this.password = password;
	}

	public synchronized void getValue() {
		System.out.println("getValue method thread name=" + username
				+ ",password=" + password);
	}
}
