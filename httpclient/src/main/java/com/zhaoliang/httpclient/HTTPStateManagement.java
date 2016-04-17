package com.zhaoliang.httpclient;

import org.apache.http.client.CookieStore;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.ClientCookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;

public class HTTPStateManagement {

    public static void testHTTPCookies() {
        BasicClientCookie cookie = new BasicClientCookie("name", "value");
        // Set effective domain and path attributes
        cookie.setDomain(".mycompany.com");
        cookie.setPath("/");
        // Set attributes exactly as sent by the server
        cookie.setAttribute(ClientCookie.PATH_ATTR, "/");
        cookie.setAttribute(ClientCookie.DOMAIN_ATTR, ".mycompany.com");
    }

    /**
     * <p>
     * Cookie policy can be set at the HTTP client and overridden on the HTTP
     * request level if required.
     * </p>
     */
    public static void testChoosingCookiePolicy() {
        RequestConfig globalConfig = RequestConfig.custom()
                                                  .setCookieSpec(CookieSpecs.BROWSER_COMPATIBILITY).build();
        CloseableHttpClient httpclient = HttpClients.custom()
                                                    .setDefaultRequestConfig(globalConfig).build();
        RequestConfig localConfig = RequestConfig.copy(globalConfig)
                                                 .setCookieSpec(CookieSpecs.STANDARD).build();
        HttpGet httpGet = new HttpGet("/");
        httpGet.setConfig(localConfig);
        System.out.println(httpclient.toString());
    }

    /**
     * <p>
     * In order to implement a custom cookie policy one should create a custom
     * implementation of the CookieSpec interface, create a CookieSpecProvider
     * implementation to create and initialize instances of the custom
     * specification and register the factory with HttpClient. Once the custom
     * specification has been registered, it can be activated the same way as a
     * standard cookie specification.
     * </p>
     */
    public static void testCustomCookiePolicy() {
        // PublicSuffixMatcher publicSuffixMatcher = PublicSuffixMatcherLoader
        // .getDefault();
        // Registry<CookieSpecProvider> r = RegistryBuilder
        // .<CookieSpecProvider> create()
        // .register(CookieSpecs.DEFAULT,
        // new DefaultCookieSpecProvider(publicSuffixMatcher))
        // .register(CookieSpecs.STANDARD,
        // new RFC6265CookieSpecProvider(publicSuffixMatcher))
        // .register("easy", new EasySpecProvider()).build();
        // RequestConfig requestConfig = RequestConfig.custom()
        // .setCookieSpec("easy").build();
        // CloseableHttpClient httpclient = HttpClients.custom()
        // .setDefaultCookieSpecRegistry(r)
        // .setDefaultRequestConfig(requestConfig).build();
    }

    /**
     * <p>
     * HttpClient can work with any physical representation of a persistent
     * cookie store that implements the CookieStore interface. The default
     * CookieStore implementation called BasicCookieStore is a simple
     * implementation backed by a java.util.ArrayList. Cookies stored in an
     * BasicClientCookie object are lost when the container object get garbage
     * collected. Users can provide more complex implementations if necessary.
     * </p>
     */
    public static void testCookiePersistence() {
        // Create a local instance of cookie store
        CookieStore cookieStore = new BasicCookieStore();
        // Populate cookies if needed
        BasicClientCookie cookie = new BasicClientCookie("name", "value");
        cookie.setDomain(".mycompany.com");
        cookie.setPath("/");
        cookieStore.addCookie(cookie);
        // Set the store
        CloseableHttpClient httpclient = HttpClients.custom()
                                                    .setDefaultCookieStore(cookieStore).build();
        System.out.println(httpclient.toString());
    }

}
