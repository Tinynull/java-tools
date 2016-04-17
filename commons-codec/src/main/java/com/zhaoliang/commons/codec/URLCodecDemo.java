package com.zhaoliang.commons.codec;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.net.URLCodec;

public class URLCodecDemo {

	public static void main(String[] args) throws EncoderException {
		URLCodec urlCodec = new URLCodec();
		String u = urlCodec
				.encode("http://commons.apache.org/proper/commons-codec/userguide.html");
		System.out.println(u);
	}

}
