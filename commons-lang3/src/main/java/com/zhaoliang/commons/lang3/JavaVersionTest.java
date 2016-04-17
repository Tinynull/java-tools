package com.zhaoliang.commons.lang3;

import org.apache.commons.lang3.JavaVersion;

public class JavaVersionTest {

	public static void main(String[] args) {
		JavaVersion version = JavaVersion.JAVA_1_6;
		System.out.println(version.name());
		System.out.println(version.atLeast(JavaVersion.JAVA_1_5));
	}

}
