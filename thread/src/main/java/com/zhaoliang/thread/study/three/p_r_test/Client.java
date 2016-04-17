package com.zhaoliang.thread.study.three.p_r_test;

public class Client {

	public static void main(String[] args) {
		String lock = new String("haha");
		P p = new P(lock);
		C c = new C(lock);
		ThreadP threadP = new ThreadP(p);
		ThreadC threadC = new ThreadC(c);
		threadC.start();
		threadP.start();
	}

}
