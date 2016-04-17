package com.zhaoliang.thread.study.three.wait_notify_size5;

import java.util.ArrayList;
import java.util.List;

public class MyList {
	private List<String> list = new ArrayList<String>();

	public void add() {
		list.add("hello");
	}

	public int size() {
		return list.size();
	}
}
