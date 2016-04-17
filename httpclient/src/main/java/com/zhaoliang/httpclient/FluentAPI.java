package com.zhaoliang.httpclient;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.protocol.HTTP;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class FluentAPI {

    /**
     * <p>
     * As of version of 4.2 HttpClient comes with an easy to use facade API
     * based on the concept of a fluent interface. Fluent facade API exposes
     * only the most fundamental functions of HttpClient and is intended for
     * simple use cases that do not require the full flexibility of HttpClient.
     * For instance, fluent facade API relieves the users from having to deal
     * with connection management and resource deallocation. Here are several
     * examples of HTTP requests executed through the HC fluent API
     * </p>
     *
     * @throws IOException
     * @throws ClientProtocolException
     */
    public static void easyToUseFacadeAPI1() throws ClientProtocolException,
        IOException {
        // Execute a GET with timeout settings and return response content as
        // String.
        Request.Get("http://somehost/").connectTimeout(1000)
               .socketTimeout(1000).execute().returnContent().asString();
    }

    public static void easyToUseFacadeAPI2() throws ClientProtocolException,
        IOException {
        // Execute a GET with timeout settings and return response content as
        // String.
        // Execute a POST with the 'expect-continue' handshake, using HTTP/1.1,
        // containing a request body as String and return response content as
        // byte array.
        Request.Post("http://somehost/do-stuff").useExpectContinue()
               .version(HttpVersion.HTTP_1_1)
               .bodyString("Important stuff", ContentType.DEFAULT_TEXT)
               .execute().returnContent().asBytes();
    }

    public static void easyToUseFacadeAPI3() throws ClientProtocolException,
        IOException {
        // Execute a POST with a custom header through the proxy containing a
        // request body
        // as an HTML form and save the result to the file
        Request.Post("http://somehost/some-form")
               .addHeader("X-Custom-header", "stuff")
               .viaProxy(new HttpHost("myproxy", 8080))
               .bodyForm(
                   Form.form().add("username", "vip")
                       .add("password", "secret").build()).execute()
               .saveContent(new File("result.dump"));
    }

    public static void easyToUseFacadeAPI4() throws ClientProtocolException,
        IOException {
        Executor executor = Executor.newInstance()
                                    .auth(new HttpHost("somehost"), "username", "password")
                                    .auth(new HttpHost("myproxy", 8080), "username", "password")
                                    .authPreemptive(new HttpHost("myproxy", 8080));
        executor.execute(Request.Get("http://somehost/")).returnContent()
                .asString();
        executor.execute(
            Request.Post("http://somehost/do-stuff")
                   .useExpectContinue()
                   .bodyString("Important stuff", ContentType.DEFAULT_TEXT))
                .returnContent().asString();
    }

    public static void testResponseHandling() throws ClientProtocolException,
        IOException {
        Document result = Request.Get("http://somehost/content").execute()
                                 .handleResponse(new ResponseHandler<Document>() {

                                     @Override
                                     public Document handleResponse(final HttpResponse response)
                                         throws IOException {
                                         StatusLine statusLine = response.getStatusLine();
                                         HttpEntity entity = response.getEntity();
                                         if (statusLine.getStatusCode() >= 300) {
                                             throw new HttpResponseException(statusLine
                                                                                 .getStatusCode(), statusLine
                                                                                 .getReasonPhrase());
                                         }
                                         if (entity == null) {
                                             throw new ClientProtocolException(
                                                 "Response contains no content");
                                         }
                                         DocumentBuilderFactory dbfac = DocumentBuilderFactory
                                             .newInstance();
                                         try {
                                             DocumentBuilder docBuilder = dbfac
                                                 .newDocumentBuilder();
                                             ContentType contentType = ContentType
                                                 .getOrDefault(entity);
                                             if (!contentType
                                                 .equals(ContentType.APPLICATION_XML)) {
                                                 throw new ClientProtocolException(
                                                     "Unexpected content type:"
                                                         + contentType);
                                             }
                                             String charset = contentType.getCharset().name();
                                             if (charset == null) {
                                                 charset = HTTP.DEF_CONTENT_CHARSET.name();
                                             }
                                             return docBuilder.parse(entity.getContent(),
                                                                     charset);
                                         } catch (ParserConfigurationException ex) {
                                             throw new IllegalStateException(ex);
                                         } catch (SAXException ex) {
                                             throw new ClientProtocolException(
                                                 "Malformed XML document", ex);
                                         }
                                     }
                                 });
        System.out.println(result.toString());
    }

}
