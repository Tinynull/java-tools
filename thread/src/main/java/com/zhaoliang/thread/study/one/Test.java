package com.zhaoliang.thread.study.one;

public class Test {

	/**
	 * 在控制台中输出的main其实就是一个名称叫作main的线程在执行main()方法中的代码。另外需要说明一下，
	 * 在控制台输出的main和main方法没有任何的关系，仅仅是名字相同而已。
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getName());
	}

}
