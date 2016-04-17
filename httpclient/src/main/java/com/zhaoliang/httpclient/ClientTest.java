package com.zhaoliang.httpclient;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;

import org.apache.http.ParseException;

public class ClientTest {

	public static void main(String[] args) throws ParseException, IOException, URISyntaxException {
//		Fundamentals.testHTTPentity();
//		Fundamentals.testRedirectHandling2();
//		Fundamentals.testRequestRetryHandler();
//		Fundamentals.testRedirectHandling2();
//		Fundamentals.test();
//		Fundamentals.testResponse1();
//		Fundamentals.testResponse2();
//		Fundamentals.testResponse3();
//		Fundamentals.testResponse4();
		
		try {
			ConnectionManagement.testManagedConnections();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}

}
