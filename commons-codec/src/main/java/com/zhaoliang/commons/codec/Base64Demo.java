package com.zhaoliang.commons.codec;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.BitSet;
import java.util.stream.IntStream;

/**
 * 测试{@link Base64}的编码和反编码方法。
 */
public class Base64Demo {
    public static void main(String[] args) throws UnsupportedEncodingException {
//        base64();
        base64jdk();
        Character z = 'z';

        System.out.println("z= " + Integer.toBinaryString(122) );
        System.out.println("l= " + Integer.toBinaryString(108) );
        System.out.println("k= " + Integer.toBinaryString(107) );

        bitSet();

    }

    private static void base64() throws UnsupportedEncodingException {
        byte[] bb = Base64.encodeBase64("man".getBytes());
        System.out.println(new String(bb));
        byte[] cc = Base64.decodeBase64(bb);
        System.out.println(new String(cc));

		/*
         * 其实 base64 算法在我们身边随处可见，大家可能记得迅雷的下载链接，它的样子大概如下 ：
		 * thunder://QUFodHRwOi8veGlhemFpLnpvbC5jb20uY24vZG93bi5waHA
		 * /c29mdGlkPTM0MTY3MSZzdWJjYXRpZD0zMyZzaXRlPTEwWlo=
		 */
        byte[] thunder1 = Base64
            .decodeBase64("QUFodHRwOi8veGlhemFpLnpvbC5jb20uY24vZG93bi5waHA/c29mdGlkPTM0MTY3MSZzdWJjYXRpZD0zMyZzaXRlPTEwWlo="
                              .getBytes());
        System.out.println(new String(thunder1, "utf-8"));

        System.out.println(new String(Base64.decodeBase64("CFWKH58nA"
                                                              .getBytes())));
    }

    private static void base64jdk(){
        byte[] bytes = java.util.Base64.getEncoder().encode("zl".getBytes());
        String t = new String(bytes);
        System.out.println(t);
    }

    private static void bitSet(){
        BitSet bitSet = new BitSet(127);
        bitSet.set(10);
        bitSet.set(11);
        bitSet.set(5);
        bitSet.set(15);
        bitSet.set(25);
        bitSet.set(35);

        System.out.println("word size is " + bitSet.size());
        System.out.println("word length is " + bitSet.length());


        System.out.println("------");
        IntStream stream = bitSet.stream();
        stream.forEach(System.out::println);
        stream.close();
        System.out.println("------");

    }

}
