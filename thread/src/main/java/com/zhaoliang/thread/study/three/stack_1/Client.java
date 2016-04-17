package com.zhaoliang.thread.study.three.stack_1;

public class Client {

	public static void main(String[] args) {
		MyStack myStack = new MyStack();
		P p = new P(myStack);
		C c = new C(myStack);
		P_Thread p_Thread = new P_Thread(p);
		C_Thread c_Thread = new C_Thread(c);
		p_Thread.start();
		c_Thread.start();
	}

}
