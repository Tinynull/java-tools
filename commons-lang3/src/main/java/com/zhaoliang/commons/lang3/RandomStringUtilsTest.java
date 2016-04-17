package com.zhaoliang.commons.lang3;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomStringUtilsTest {

	public static void main(String[] args) {
		char[] c = { 'a', 'b', 'g', 'u' };
		String a = RandomStringUtils.random(5, c);
		System.out.println(a);
	}

}
