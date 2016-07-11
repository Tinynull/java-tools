package com.zhaoliang.maven.shade;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;

/**
 * 测试{@link Base64}的编码和反编码方法。
 */
public class Base64Demo {
    public static void main(String[] args) throws UnsupportedEncodingException {
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


}
