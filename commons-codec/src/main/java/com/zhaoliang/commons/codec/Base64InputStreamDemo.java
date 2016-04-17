package com.zhaoliang.commons.codec;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.codec.binary.Base64InputStream;

public class Base64InputStreamDemo {

	public static void main(String[] args) throws IOException {
		InputStream in = new ByteArrayInputStream("hello".getBytes());
		Base64InputStream base64InputStream = new Base64InputStream(in);
		base64InputStream.close();
	}
}
