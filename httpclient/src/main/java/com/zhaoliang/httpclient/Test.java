package com.zhaoliang.httpclient;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;

public class Test {

    public static void main(String[] args) throws ClientProtocolException, IOException {
        String cookie = "SINAGLOBAL=6104957868810.743.1448334256508; " +
            "un=zliamthebest@163.com; wvr=6; SUS=SID-2077384743-1449106394-GZ-wdmnk-e95ffd9737b6d2dfb489a7a215161381; " +
            "SUE=es%3De075f94d76939b1d15c9a70dbf0e0682%26ev%3Dv1%26es2%3Daaaebf51b67a5ec6d338c9aecdcb2a0e%26rs0%3Dn0UjL" +
            "Q50QcRj9IAfjsuwkptIpB8xpA3wntuE7060S0G7Z8Q%252BrYFx6TNyHUntlDYP9LLPQbmaD6E9DViMq9atrrTLjNOqAW%252B4haqqW8gpQ" +
            "o2iTYzE%252F%252BMzsMe0mMbfPu0DDvwKm1GLv3mt7X74Qp21Jpz7YPc%252FymE1HkoQhpX2WDM%253D%26rv%3D0; SUP=cv%3D1%26bt" +
            "%3D1449106394%26et%3D1449192794%26d%3Dc909%26i%3D1381%26us%3D1%26vf%3D0%26vt%3D0%26ac%3D0%26st%3D0%26uid%3D2" +
            "077384743%26name%3Dzliamthebest%2540163.com%26nick%3Dzliamthebest%26fmp%3D%26lcp%3D; SUB=_2A257W-uKDeTxGeRO7" +
            "FUS-CrLzz-IHXVYEVpCrDV8PUNbvtBeLRShkW8NhHFLh0KGN28O4KIyFuFphXSGYg..; SUBP=0033WrSXqPxfM725Ws9jqgMF55529P9D9" +
            "WFjYHJ1yfzRO3IoRGff9zCq5JpX5KMt; SUHB=0k1T3RwW1UuFtJ; ALF=1480642394; SSOLoginState=1449106394;" +
            " _s_tentry=login.sina.com.cn; Apache=2439896059222.5195.1449106400016; " +
            "ULV=1449106400032:9:3:5:2439896059222.5195.1449106400016:1449033841057; " +
            "UOR=,,login.sina.com.cn; SWB=usrmdinst_0; WBStore=5955be0e3d5411da|undefined";
        String string = Request
            .Get("http://s.weibo.com/weibo/turbosun?topnav=1&wvr=6&Refer=top_hot")
            .addHeader("Cookie", cookie)
            .execute()
            .returnContent()
            .asString();
        if (string.contains("今晚月儿就长大了")) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }
    }

}
