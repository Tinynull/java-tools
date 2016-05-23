package com.zhaoliang.javase8.chapterone;

import java.io.File;
import java.util.Arrays;

/**
 * 练习题3
 * Created by zhaoliang on 2016/5/23.
 */
public class Test3 {
    public static void main(String[] args) {
        Test3 test3 = new Test3();
        File file = new File("E:\\picture\\搞笑图");
        System.out.println(Arrays.asList(test3.getJPGFile(file, "jpg")).toString());
    }

    private File[] getJPGFile(File file, String jpg) {
        return file.listFiles(pathName -> {
            return pathName.getName().endsWith(jpg);
        });
    }
}
