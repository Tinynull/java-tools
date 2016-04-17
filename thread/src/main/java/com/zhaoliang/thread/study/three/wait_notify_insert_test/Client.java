package com.zhaoliang.thread.study.three.wait_notify_insert_test;

public class Client {

	public static void main(String[] args) {
		DBTools dbTools = new DBTools();
		for (int i = 0; i < 20; i++) {
			BackupA backupA = new BackupA(dbTools);
			BackupB backupB = new BackupB(dbTools);
			backupA.start();
			backupB.start();
		}
	}

}
