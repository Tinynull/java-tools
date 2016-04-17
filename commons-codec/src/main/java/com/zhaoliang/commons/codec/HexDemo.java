package com.zhaoliang.commons.codec;

import org.apache.commons.codec.binary.Hex;

/**
 * 将字符按照其二进制值的字符串。如字符串"a"的值为97，这转换成十六进制的字符串"61"。
 * @author Administrator
 *
 */
public class HexDemo {

	public static void main(String[] args) {
		Hex hex = new Hex();
		byte[] code = hex.encode("a".getBytes());
		System.out.println(new String(code));
	}

}
