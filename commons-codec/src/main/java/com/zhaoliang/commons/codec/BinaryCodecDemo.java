package com.zhaoliang.commons.codec;

import org.apache.commons.codec.binary.BinaryCodec;

/**
 * 将字符按照其二进制值的字符串。如字符串"a"的值为97，这转换成八进制的字符串"01100001"。
 * 
 * @author Administrator
 *
 */
public class BinaryCodecDemo {

	public static void main(String[] args) {
		BinaryCodec binaryCodec = new BinaryCodec();
		byte[] code = binaryCodec.encode("a".getBytes());
		System.out.println(new String(code));
        BinaryCodecDemo binaryCodecDemo = new BinaryCodecDemo();
        binaryCodecDemo.addEdge();
	}

	public void addEdge(){
        System.out.println("added edge");
    }
}
