package com.zhaoliang.javase8.chapterone;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

/**
 * 练习4
 *
 * Created by zhaoliang on 2016/5/23.
 */
public class Test4 {
    public static void main(String[] args) {
        Test4 test4 = new Test4();
        File[] files;
        files = new File("E:\\picture").listFiles();
        test4.sortByDirectoryAndFile(files);
        Arrays.asList(files).forEach(System.out::println);
    }

    private void sortByDirectoryAndFile(File[] files) {
        Comparator<File> comp = (o1, o2) -> {
            int i = o1.getAbsolutePath().compareTo(o2.getAbsolutePath());
            if (i != 0) {
                return i;
            } else {
                return o1.getName().compareTo(o2.getName());
            }
        };
        Arrays.sort(files, comp);
    }
}
