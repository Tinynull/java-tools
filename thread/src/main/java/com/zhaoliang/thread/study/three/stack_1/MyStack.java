package com.zhaoliang.thread.study.three.stack_1;

import java.util.ArrayList;
import java.util.List;

public class MyStack {

	private List<String> list = new ArrayList<String>();

	public synchronized void push() {
		try {
			if (list.size() == 1) {
				System.out.println("push " + Thread.currentThread().getName() + " wait");
				this.wait();
			}

			list.add("string=" + Math.random());
			System.out.println("push="+ list.size());
			this.notify();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public synchronized String pop() {
		try {
			if (list.size() == 0) {
				System.out.println("pop " + Thread.currentThread().getName() + " wait");
				this.wait();
			}
			String result = list.get(0);
			list.remove(0);
			this.notify();
			System.out.println("pop=" + list.size());
			return result;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "";
	}
}
