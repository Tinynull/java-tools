package com.zhaoliang.thread.study.three.stack_1;

public class C {
	private MyStack myStack;

	public C(MyStack myStack) {
		this.myStack = myStack;
	}

	public void popService() {
		myStack.pop();
	}
}
