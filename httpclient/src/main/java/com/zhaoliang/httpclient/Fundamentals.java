package com.zhaoliang.httpclient;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.io.Reader;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.net.ssl.SSLException;

import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Fundamentals {

    public static void test() throws URISyntaxException {

        HttpGet httpget0 = new HttpGet(
            "http://www.google.com/search?hl=en&q=httpclient&btnG=Google+Search&aq=f&oq=");
        System.out.println(httpget0.getURI());

        URI uri = new URIBuilder().setScheme("http")
                                  .setHost("www.google.com")
                                  .setPath("/search")
                                  .setParameter("q", "httpclient")
                                  .setParameter("btnG", "Google Search")
                                  .setParameter("aq", "f")
                                  .setParameter("oq", "")
                                  .build();
        HttpGet httpget = new HttpGet(uri);
        System.out.println("httpget.getURI()=" + httpget.getURI());
        System.out.println("httpget.getMethod()=" + httpget.getMethod());
        System.out.println("httpget.toString()=" + httpget.toString());
        System.out.println("httpget.getRequestLine()="
                               + httpget.getRequestLine());
        System.out.println("httpget.getProtocolVersion()="
                               + httpget.getProtocolVersion());
        System.out.println("httpget.getURI()=" + httpget.getURI());

    }

    /**
     * <p>
     * HttpClient provides URIBuilder utility class to simplify creation and
     * modification of request URIs.
     * </p>
     */
    public static void testResponse1() {
        HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1,
                                                      HttpStatus.SC_OK, "OK");

        // 响应头：
        // HTTP/1.1 200 OK
        System.out.println(response.getProtocolVersion());
        System.out.println(response.getStatusLine().getProtocolVersion());
        System.out.println(response.getStatusLine().getStatusCode());
        System.out.println(response.getStatusLine().getReasonPhrase());
        System.out.println(response.getStatusLine().toString());
    }

    public static void testResponse2() {
        HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1,
                                                      HttpStatus.SC_OK, "OK");
        response.addHeader("Set-Cookie", "c1=a; path=/; domain=localhost");
        response.addHeader("Set-Cookie",
                           "c2=b; path=\"/\", c3=c; domain=\"localhost\"");
        Header h1 = response.getFirstHeader("Set-Cookie");
        System.out.println(h1);
        Header h2 = response.getLastHeader("Set-Cookie");
        System.out.println(h2);
        Header[] hs = response.getHeaders("Set-Cookie");
        System.out.println(hs.length);
    }

    /**
     * The most efficient way to obtain all headers of a given type is by using
     * the {@link HeaderIterator} interface.
     *
     * output: Set-Cookie: c1=a; path=/; domain=localhost Set-Cookie: c2=b;
     * path="/", c3=c; domain="localhost"
     */
    public static void testResponse3() {
        HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1,
                                                      HttpStatus.SC_OK, "OK");
        response.addHeader("Set-Cookie", "c1=a; path=/; domain=localhost");
        response.addHeader("Set-Cookie",
                           "c2=b; path=\"/\", c3=c; domain=\"localhost\"");
        HeaderIterator it = response.headerIterator("Set-Cookie");
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    /**
     * It also provides convenience methods to parse HTTP messages into
     * individual header elements.
     */
    public static void testResponse4() {
        HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1,
                                                      HttpStatus.SC_OK, "OK");
        response.addHeader("Set-Cookie", "c1=a; path=/; domain=localhost");
        response.addHeader("Set-Cookie",
                           "c2=b; path=\"/\", c3=c; domain=\"localhost\"");
        HeaderElementIterator it = new BasicHeaderElementIterator(
            response.headerIterator("Set-Cookie"));
        while (it.hasNext()) {
            HeaderElement elem = it.nextElement();
            System.out.println(elem.getName() + " = " + elem.getValue());
            NameValuePair[] params = elem.getParameters();
            for (int i = 0; i < params.length; i++) {
                System.out.println(" " + params[i]);
            }
        }
    }

    /**
     * <p>
     * HttpClient distinguishes three kinds of entities, depending on where
     * their content originates:
     * </p>
     * <p>
     * streamed: The content is received from a stream, or generated on the fly.
     * In particular, this category includes entities being received from HTTP
     * responses. Streamed entities are generally not repeatable.
     * </p>
     * <p>
     * self-contained: The content is in memory or obtained by means that are
     * independent from a connection or other entity. Self-contained entities
     * are generally repeatable. This type of entities will be mostly used for
     * entity enclosing HTTP requests.
     * </p>
     * <p>
     * wrapping: The content is obtained from another entity.
     * </p>
     * <p>
     * This distinction is important for connection management when streaming
     * out content from an HTTP response. For request entities that are created
     * by an application and only sent using HttpClient, the difference between
     * streamed and self-contained is of little importance. In that case, it is
     * suggested to consider non-repeatable entities as streamed, and those that
     * are repeatable as self-contained.
     * </p>
     * <p>
     * An entity can be repeatable, meaning its content can be read more than
     * once. This is only possible with self contained entities (like
     * ByteArrayEntity or StringEntity)
     * </p>
     * <p>
     * When creating an entity for a outgoing message, this meta data has to be
     * supplied by the creator of the entity.
     * </p>
     *
     * @throws ParseException
     * @throws IOException
     */
    public static void testHTTPentity() throws ParseException, IOException {
        StringEntity myEntity = new StringEntity("important message",
                                                 ContentType.create("text/plain", "UTF-8"));
        System.out.println(myEntity.getContentType());
        System.out.println(myEntity.getContentLength());
        System.out.println(EntityUtils.toString(myEntity));
        System.out.println(EntityUtils.toByteArray(myEntity).length);
    }

    /**
     * <p>
     * Ensuring release of low level resources
     * </p>
     *
     * In order to ensure proper release of system resources one must close
     * either the content stream associated with the entity or the response
     * itself
     *
     * @throws IllegalStateException
     * @throws IOException
     */
    public static void releaseResources() throws IllegalStateException,
        IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet("http://localhost/");
        CloseableHttpResponse response = httpclient.execute(httpget);
        try {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream instream = entity.getContent();
                try {
                    // do something useful
                } finally {
                    instream.close();
                }
            }
        } finally {
            response.close();
        }
    }

    /**
     * <p>
     * The recommended way to consume the content of an entity is by using its
     * HttpEntity#getContent() or HttpEntity#writeTo(OutputStream) methods.
     * HttpClient also comes with the EntityUtils class, which exposes several
     * static methods to more easily read the content or information from an
     * entity. Instead of reading the java.io.InputStream directly, one can
     * retrieve the whole content body in a string / byte array by using the
     * methods from this class. However, the use of EntityUtils is strongly
     * discouraged unless the response entities originate from a trusted HTTP
     * server and are known to be of limited length.
     * </p>
     *
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static void testConsumingEntityContent1()
        throws ClientProtocolException, IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet("http://localhost/");
        CloseableHttpResponse response = httpclient.execute(httpget);
        try {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                long len = entity.getContentLength();
                if (len != -1 && len < 2048) {
                    System.out.println(EntityUtils.toString(entity));
                } else {
                    // Stream content out
                }
            }
        } finally {
            response.close();
        }
    }

    /**
     * <p>
     * In some situations it may be necessary to be able to read entity content
     * more than once. In this case entity content must be buffered in some way,
     * either in memory or on disk. The simplest way to accomplish that is by
     * wrapping the original entity with the {@link BufferedHttpEntity} class.
     * This will cause the content of the original entity to be read into a
     * in-memory buffer. In all other ways the entity wrapper will be have the
     * original one.
     * </p>
     *
     * @throws IOException
     */
    public static void testConsumingEntityContent2() throws IOException {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet("http://localhost/");
        CloseableHttpResponse response = httpclient.execute(httpget);
        try {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                entity = new BufferedHttpEntity(entity);
            }
        } finally {
            response.close();
        }

    }

    /**
     * <p>
     * HttpClient provides several classes that can be used to efficiently
     * stream out content throught HTTP connections. Instances of those classes
     * can be associated with entity enclosing requests such as POST and PUT in
     * order to enclose entity content into outgoing HTTP requests. HttpClient
     * provides several classes for most common data containers such as string,
     * byte array, input stream, and file: {@link StringEntity},
     * {@link ByteArrayEntity}, {@link InputStreamEntity}, and
     * {@link FileEntity}.
     * </p>
     */
    public static void testProducingEntityContent() {
        File file = new File("somefile.txt");
        FileEntity entity = new FileEntity(file, ContentType.create(
            "text/plain", "UTF-8"));
        HttpPost httppost = new HttpPost("http://localhost/action.do");
        httppost.setEntity(entity);
    }

    /**
     * <p>
     * Many applications need to simulate the process of submitting an HTML
     * form, for instance, in order to log in to a web application or submit
     * input data. HttpClient provides the entity class UrlEncodedFormEntity to
     * facilitate the process.
     *
     * 许多应用需要模拟HTML页面的表单提交，例如登录一个网页应用或提交输入的数据。{@link HttpClient} 提供一个
     * {@link UrlEncodedFormEntity}类来实现。
     * </p>
     */
    public static void testHTMLForms() {
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair("param1", "value1"));
        formparams.add(new BasicNameValuePair("param2", "value2"));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams,
                                                               Consts.UTF_8);
        HttpPost httppost = new HttpPost("http://localhost/handler.do");
        httppost.setEntity(entity);
    }

    /**
     * <p>
     * Generally it is recommended to let HttpClient choose the most appropriate
     * transfer encoding based on the properties of the HTTP message being
     * transferred. It is possible, however, to inform HttpClient that chunk
     * coding is preferred by setting HttpEntity#setChunked() to true. Please
     * note that HttpClient will use this flag as a hint only. This value will
     * be ignored when using HTTP protocol versions that do not support chunk
     * coding, such as HTTP/1.0.
     * </p>
     */
    public static void testContentChunking() {
        StringEntity entity = new StringEntity("important message",
                                               ContentType.create("plain/text", Consts.UTF_8));
        entity.setChunked(true);
        HttpPost httppost = new HttpPost("http://localhost/acrtion.do");
        httppost.setEntity(entity);
    }

    /**
     * <p>
     * The simplest and the most convenient way to handle responses is by using
     * the {@link ResponseHandler} interface, which includes the
     * handleResponse(HttpResponse response) method. This method completely
     * relieves the user from having to worry about connection management. When
     * using a ResponseHandler, HttpClient will automatically take care of
     * ensuring release of the connection back to the connection manager
     * regardless whether the request execution succeeds or causes an exception.
     * </p>
     *
     * <p>
     * 处理响应最简单方便的方法是使用{@link ResponseHandler}接口，这个接口里面包含了
     * {@link ResponseHandler#handleResponse(HttpResponse)}方法。这个方法完全减缓用户担心的连接管理。
     * 无论执行成功还是异常，它会自动的释放连接。
     * </p>
     *
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static void testResponseHandlers() throws ClientProtocolException,
        IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet("http://localhost/json");
        ResponseHandler<MyJsonObject> rh = new ResponseHandler<MyJsonObject>() {
            @Override
            public MyJsonObject handleResponse(final HttpResponse response)
                throws IOException {
                StatusLine statusLine = response.getStatusLine();
                HttpEntity entity = response.getEntity();
                if (statusLine.getStatusCode() >= 300) {
                    throw new HttpResponseException(statusLine.getStatusCode(),
                                                    statusLine.getReasonPhrase());
                }
                if (entity == null) {
                    throw new ClientProtocolException(
                        "Response contains no content");
                }
                Gson gson = new GsonBuilder().create();
                ContentType contentType = ContentType.getOrDefault(entity);
                Charset charset = contentType.getCharset();
                Reader reader = new InputStreamReader(entity.getContent(),
                                                      charset);
                return gson.fromJson(reader, MyJsonObject.class);
            }
        };
        MyJsonObject myjson = httpclient.execute(httpget, rh);
        System.out.println(myjson.toString());
    }

    /**
     * <p>
     * HttpClient interface represents the most essential contract for HTTP
     * request execution. It imposes no restrictions or particular details on
     * the request execution process and leaves the specifics of connection
     * management, state management, authentication and redirect handling up to
     * individual implementations. This should make it easier to decorate the
     * interface with additional functionality such as response content caching.
     * </p>
     * <p>
     * Generally HttpClient implementations act as a facade to a number of
     * special purpose handler or strategy interface implementations responsible
     * for handling of a particular aspect of the HTTP protocol such as redirect
     * or authentication handling or making decision about connection
     * persistence and keep alive duration. This enables the users to
     * selectively replace default implementation of those aspects with custom,
     * application specific ones.
     * </p>
     */
    public static void testHttpClientInterface() {
        ConnectionKeepAliveStrategy keepAliveStrat = new DefaultConnectionKeepAliveStrategy() {
            @Override
            public long getKeepAliveDuration(HttpResponse response,
                                             HttpContext context) {
                long keepAlive = super.getKeepAliveDuration(response, context);
                if (keepAlive == -1) {
                    // Keep connections alive 5 seconds if a keep-alive value
                    // has not be explicitly set by the server
                    keepAlive = 5000;
                }
                return keepAlive;
            }
        };
        CloseableHttpClient httpclient = HttpClients.custom()
                                                    .setKeepAliveStrategy(keepAliveStrat).build();
        System.out.println(httpclient.toString());
    }

    /**
     * {@link RequestConfig}叫请求配置，也就是用于某个GET或POST等方法请求上的，里面采用了
     * {@link Builder}构造者模式。 里面默认设置了这些参数。 <br>
     *
     * boolean expectContinueEnabled;<br>
     * HttpHost proxy;<br>
     * InetAddress localAddress;<br>
     * boolean staleConnectionCheckEnabled = true;<br>
     * String cookieSpec;<br>
     * boolean redirectsEnabled = true;<br>
     * boolean relativeRedirectsAllowed = true;<br>
     * boolean circularRedirectsAllowed;<br>
     * int maxRedirects = 50;<br>
     * boolean authenticationEnabled = true;<br>
     * Collection<String> targetPreferredAuthSchemes;<br>
     * Collection<String> proxyPreferredAuthSchemes;<br>
     * int connectionRequestTimeout = -1;<br>
     * int connectTimeout = -1;<br>
     * int socketTimeout = -1;<br>
     */
    public static void testRequestConfig() {
        Builder custom = RequestConfig.custom();
        RequestConfig requestConfig = custom.setConnectionRequestTimeout(3000)
                                            .setSocketTimeout(3000).setConnectionRequestTimeout(3000)
                                            .setRedirectsEnabled(false).build();

        HttpPost httpPost = new HttpPost("http://www.baidu.com");

        httpPost.setConfig(requestConfig);
    }

    /**
     * <p>
     * When an instance CloseableHttpClient is no longer needed and is about to
     * go out of scope the connection manager associated with it must be shut
     * down by calling the CloseableHttpClient#close() method.
     * </p>
     *
     * @throws IOException
     */
    public static void testHttpClientResourceDeallocation() throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            // <...>
        } finally {
            httpclient.close();
        }
    }

    /**
     * <p>
     * The HTTP protocol interceptor is a routine that implements a specific
     * aspect of the HTTP protocol. Usually protocol interceptors are expected
     * to act upon one specific header or a group of related headers of the
     * incoming message, or populate the outgoing message with one specific
     * header or a group of related headers. Protocol interceptors can also
     * manipulate content entities enclosed with messages - transparent content
     * compression / decompression being a good example. Usually this is
     * accomplished by using the 'Decorator' pattern where a wrapper entity
     * class is used to decorate the original entity. Several protocol
     * interceptors can be combined to form one logical unit.
     * </p>
     * <p>
     * Protocol interceptors can collaborate by sharing information - such as a
     * processing state - through the HTTP execution context. Protocol
     * interceptors can use HTTP context to store a processing state for one
     * request or several consecutive requests.
     * </p>
     * <p>
     * Usually the order in which interceptors are executed should not matter as
     * long as they do not depend on a particular state of the execution
     * context. If protocol interceptors have interdependencies and therefore
     * must be executed in a particular order, they should be added to the
     * protocol processor in the same sequence as their expected execution
     * order.
     * </p>
     * <p>
     * Protocol interceptors must be implemented as thread-safe. Similarly to
     * servlets, protocol interceptors should not use instance variables unless
     * access to those variables is synchronized. This is an example of how
     * local context can be used to persist a processing state between
     * consecutive requests:
     * </p>
     *
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static void testHTTPProtocolInterceptors()
        throws ClientProtocolException, IOException {
        CloseableHttpClient httpclient = HttpClients.custom()
                                                    .addInterceptorLast(new HttpRequestInterceptor() {

                                                        @Override
                                                        public void process(final HttpRequest request,
                                                                            final HttpContext context) throws HttpException,
                                                            IOException {
                                                            AtomicInteger count = (AtomicInteger) context
                                                                .getAttribute("count");
                                                            request.addHeader("Count",
                                                                              Integer.toString(count.getAndIncrement()));
                                                        }
                                                    }).build();
        AtomicInteger count = new AtomicInteger(1);
        HttpClientContext localContext = HttpClientContext.create();
        localContext.setAttribute("count", count);
        HttpGet httpget = new HttpGet("http://localhost/");
        for (int i = 0; i < 10; i++) {
            CloseableHttpResponse response = httpclient.execute(httpget,
                                                                localContext);
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    System.out.println(EntityUtils.toString(entity));
                }
            } finally {
                response.close();
            }
        }
    }

    /**
     * <p>
     * In order to enable a custom exception recovery mechanism one should
     * provide an implementation of the HttpRequestRetryHandler interface.
     * </p>
     *
     * <p>
     * Please note that one can use StandardHttpRequestRetryHandler instead of
     * the one used by default in order to treat those request methods defined
     * as idempotent by RFC-2616 as safe to retry automatically: GET, HEAD, PUT,
     * DELETE, OPTIONS, and TRACE.
     * </p>
     */
    public static void testRequestRetryHandler() {
        HttpRequestRetryHandler myRetryHandler = new HttpRequestRetryHandler() {
            public boolean retryRequest(IOException exception,
                                        int executionCount, HttpContext context) {
                if (executionCount >= 5) {
                    // Do not retry if over max retry count
                    System.out.println("executionCount");
                    return false;
                }
                if (exception instanceof InterruptedIOException) {
                    System.out.println("InterruptedIOException");
                    // Timeout
                    return true;
                }
                if (exception instanceof UnknownHostException) {
                    System.out.println("---UnknownHostException---");
                    // Unknown host
                    return true;
                }
                if (exception instanceof ConnectTimeoutException) {
                    System.out.println("---ConnectTimeoutException---");
                    // Connection refused
                    return true;
                }
                if (exception instanceof SSLException) {
                    System.out.println("---ConnectTimeoutException---");
                    // SSL handshake exception
                    return false;
                }
                if (exception instanceof SocketTimeoutException) {
                    System.out.println("---SocketTimeoutException---");
                    return true;
                }
                HttpClientContext clientContext = HttpClientContext
                    .adapt(context);
                HttpRequest request = clientContext.getRequest();
                boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
                if (idempotent) {
                    // Retry if the request is considered idempotent
                    return true;
                }
                return false;
            }
        };
        CloseableHttpClient httpclient = HttpClients.custom()
                                                    .setRetryHandler(myRetryHandler).build();
        try {
            CloseableHttpResponse execute = httpclient
                .execute(new HttpGet(
                    "http://www.mychinanews.com/news/n/17/1283505"));
            HttpEntity entity = execute.getEntity();
            String string = EntityUtils.toString(entity);
            System.out.println(string);
        } catch (IOException e) {

        }
        System.out.println(httpclient.toString());
    }

    public static void main(String[] args) {
        testRequestRetryHandler();
    }

    /**
     * <p>
     * HttpClient handles all types of redirects automatically, except those
     * explicitly prohibited by the HTTP specification as requiring user
     * intervention. See Other (status code 303) redirects on POST and PUT
     * requests are converted to GET requests as required by the HTTP
     * specification. One can use a custom redirect strategy to relaxe
     * restrictions on automatic redirection of POST methods imposed by the HTTP
     * specification.
     * </p>
     */
    public static void testRedirectHandling1() {
        LaxRedirectStrategy redirectStrategy = new LaxRedirectStrategy();
        CloseableHttpClient httpclient = HttpClients.custom()
                                                    .setRedirectStrategy(redirectStrategy).build();
        System.out.println(httpclient.toString());
    }

    /**
     * <p>
     * HttpClient often has to rewrite the request message in the process of its
     * execution. Per default HTTP/1.0 and HTTP/1.1 generally use relative
     * request URIs. Likewise, original request may get redirected from location
     * to another multiple times. The final interpreted absolute HTTP location
     * can be built using the original request and the context. The utility
     * method URIUtils#resolve can be used to build the interpreted absolute URI
     * used to generate the final request. This method includes the last
     * fragment identifier from the redirect requests or the original request.
     * </p>
     *
     * @throws URISyntaxException
     * @throws IOException
     * @throws ClientProtocolException
     */
    public static void testRedirectHandling2() throws URISyntaxException,
        ClientProtocolException, IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpClientContext context = HttpClientContext.create();
        HttpGet httpget = new HttpGet(
            "http://www.baidu.com/link?url=BsF6X_r42RGckUIdCGGw4JhjSEFXL2DYgl9718MnsF4nQJ4mkFsfQGRvQdcQ1verpEYACYcZlk9KysB4T3J9WK");
        CloseableHttpResponse response = httpclient.execute(httpget, context);
        try {
            HttpHost target = context.getTargetHost();
            List<URI> redirectLocations = context.getRedirectLocations();
            URI location = URIUtils.resolve(httpget.getURI(), target,
                                            redirectLocations);
            System.out.println("Final HTTP location: "
                                   + location.toASCIIString());
            // Expected to be an absolute URI
        } finally {
            response.close();
        }
    }

}
