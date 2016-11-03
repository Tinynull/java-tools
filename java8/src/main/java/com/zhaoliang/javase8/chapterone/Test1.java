package com.zhaoliang.javase8.chapterone;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 练习题 2
 * Created by zhaoliang on 2016/5/23.
 */
public class Test1 {

    public static void main(String[] args) {
        Test1 t = new Test1();
        File file = new File("E:\\picture");
        System.out.println(t.subDirectory(file));
        System.out.println(Arrays.asList(t.subDirectory2(file)).toString());
        System.out.println(Arrays.asList(t.subDirectorywithLambda1(file)).toString());
        System.out.println(Arrays.asList(t.subDirectorywithLambda2(file)).toString());
    }

    public List<File> subDirectory(File file) {
        if (!file.isDirectory()) {
            return null;
        } else {
            File[] files = file.listFiles();
            if (files.length == 0) {
                return null;
            }
            List<File> fileResult = new ArrayList<>();
            for (File f : files) {
                if (f.isDirectory()) {
                    fileResult.add(f);
                }
            }
            return fileResult;
        }


    }


    /**
     * 传统的方式：使用内部类的方式。
     *
     * @param file
     * @return
     */
    public File[] subDirectory2(File file) {
        return file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
    }

    /**
     * lambda表达式。
     *
     * @param file
     * @return
     */
    public File[] subDirectorywithLambda1(File file) {
        return file.listFiles(pathname -> pathname.isDirectory());
    }

    /**
     * 简洁的lambda表达式。
     *
     * @param file
     * @return
     */
    public File[] subDirectorywithLambda2(File file) {
        return file.listFiles(File::isDirectory);
    }

}
