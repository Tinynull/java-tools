package com.zhaoliang.thread.study.reentantlock;

public interface IBuffer {
	public void write();
	public void read() throws InterruptedException;
}
