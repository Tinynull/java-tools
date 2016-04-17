package com.zhaoliang.thread.study.three.wait_notify_insert_test;

public class BackupB extends Thread {
	DBTools dbTools;

	public BackupB(DBTools dbTools) {
		this.dbTools = dbTools;
	}

	@Override
	public void run() {
		dbTools.backupB();
	}
}
