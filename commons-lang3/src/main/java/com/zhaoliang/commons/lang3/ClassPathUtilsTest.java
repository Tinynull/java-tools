package com.zhaoliang.commons.lang3;

import org.apache.commons.lang3.ClassPathUtils;

public class ClassPathUtilsTest {

	public static void main(String[] args) {
		String s = ClassPathUtils.toFullyQualifiedName(
				ClassPathUtilsTest.class, "hello.txt");
		System.out.println(s);
		// com.zhaoliang.commons.lang3.study.hello.txt

		String a = ClassPathUtils.toFullyQualifiedPath(
				ClassPathUtilsTest.class, "");
		System.out.println(a);
		// com/zhaoliang/commons/lang3/study/
		
		Package package1 = ClassPathUtilsTest.class.getPackage();
		System.out.println(package1.getName());
		System.out.println(package1.getSpecificationTitle());
		System.out.println(ClassPathUtilsTest.class.toString());
		System.out.println(ClassPathUtilsTest.class.toString());
		
	}

}
