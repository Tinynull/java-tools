package com.zhaoliang.thread.study.two.twoObjectTwoLock;

public class Client {

	public static void main(String[] args) {
		HasSelfPrivateNum hasSelfPrivateNum = new HasSelfPrivateNum();
		AThread a = new AThread(hasSelfPrivateNum);
		a.start();
		BThread b = new BThread(hasSelfPrivateNum);
		b.start();
	}

}
