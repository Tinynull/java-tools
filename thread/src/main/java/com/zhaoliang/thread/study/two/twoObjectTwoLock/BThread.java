package com.zhaoliang.thread.study.two.twoObjectTwoLock;

public class BThread extends Thread {
	
	private HasSelfPrivateNum hasSelfPrivateNum;
	
	public BThread(HasSelfPrivateNum hasSelfPrivateNum) {
		this.hasSelfPrivateNum = hasSelfPrivateNum;
	}
	@Override
	public void run() {
		hasSelfPrivateNum.addI("b");
	}
}
