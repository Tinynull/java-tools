package com.zhaoliang.lang;

/**
 * Created by zhaoliang(weston_contribute@163.com) on 2016/7/5.
 */
public class PackageTest {
    public static void main(String[] args) {
        Package aPackage = Package.getPackage("java.lang");
        System.out.println(aPackage);
        System.out.println(PackageTest.class.getPackage().getSpecificationTitle() );
    }
}
