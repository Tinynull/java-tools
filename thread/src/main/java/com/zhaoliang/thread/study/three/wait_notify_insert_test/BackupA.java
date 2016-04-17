package com.zhaoliang.thread.study.three.wait_notify_insert_test;

public class BackupA extends Thread {
	DBTools dbTools;

	public BackupA(DBTools dbTools) {
		this.dbTools = dbTools;
	}

	@Override
	public void run() {
		dbTools.backupA();
	}
}
