package com.zhaoliang.httpclient;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpClientConnection;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.ConnectionRequest;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;

public class ConnectionManagement {

	/**
	 * <p>
	 * HTTP connections are complex, stateful, thread-unsafe objects which need
	 * to be properly managed to function correctly. HTTP connections can only
	 * be used by one execution thread at a time. HttpClient employs a special
	 * entity to manage access to HTTP connections called HTTP connection
	 * manager and represented by the HttpClientConnectionManager interface. The
	 * purpose of an HTTP connection manager is to serve as a factory for new
	 * HTTP connections, to manage life cycle of persistent connections and to
	 * synchronize access to persistent connections making sure that only one
	 * thread can have access to a connection at a time. Internally HTTP
	 * connection managers work with instances of ManagedHttpClientConnection
	 * acting as a proxy for a real connection that manages connection state and
	 * controls execution of I/O operations. If a managed connection is released
	 * or get explicitly closed by its consumer the underlying connection gets
	 * detached from its proxy and is returned back to the manager. Even though
	 * the service consumer still holds a reference to the proxy instance, it is
	 * no longer able to execute any I/O operations or change the state of the
	 * real connection either intentionally or unintentionally.
	 * </p>
	 * 
	 * @throws InterruptedException
	 * @throws ExecutionException
	 * @throws IOException
	 */
	public static void testManagedConnections() throws InterruptedException,
			ExecutionException, IOException {
		HttpClientContext context = HttpClientContext.create();
		@SuppressWarnings("resource")
		HttpClientConnectionManager connMrg = new BasicHttpClientConnectionManager();
		System.out.println(connMrg.toString());
		HttpRoute route = new HttpRoute(new HttpHost("localhost", 80));
		// Request new connection. This can be a long process
		ConnectionRequest connRequest = connMrg.requestConnection(route, null);
		// Wait for connection up to 10 sec
		HttpClientConnection conn = connRequest.get(10, TimeUnit.SECONDS);
		try {
			// If not open
			if (!conn.isOpen()) {
				// establish connection based on its route info
				connMrg.connect(conn, route, 1000, context);
				// and mark it as route complete
				connMrg.routeComplete(conn, route, context);
			}
			// Do useful things with the connection.
		} finally {
			connMrg.releaseConnection(conn, null, 1, TimeUnit.MINUTES);
		}
	}

	/**
	 * <p>
	 * PoolingHttpClientConnectionManager is a more complex implementation that
	 * manages a pool of client connections and is able to service connection
	 * requests from multiple execution threads. Connections are pooled on a per
	 * route basis. A request for a route for which the manager already has a
	 * persistent connection available in the pool will be serviced by leasing a
	 * connection from the pool rather than creating a brand new connection.
	 * </p>
	 * <p>
	 * PoolingHttpClientConnectionManager maintains a maximum limit of
	 * connections on a per route basis and in total. Per default this
	 * implementation will create no more than 2 concurrent connections per
	 * given route and no more 20 connections in total. For many real-world
	 * applications these limits may prove too constraining, especially if they
	 * use HTTP as a transport protocol for their services.
	 * </p>
	 */
	public static void testPoolingConnectionManager() {
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		// Increase max total connection to 200
		cm.setMaxTotal(200);
		// Increase default max connection per route to 20
		cm.setDefaultMaxPerRoute(20);
		// Increase max connections for localhost:80 to 50
		HttpHost localhost = new HttpHost("locahost", 80);
		cm.setMaxPerRoute(new HttpRoute(localhost), 50);
		CloseableHttpClient httpClient = HttpClients.custom()
				.setConnectionManager(cm).build();
		System.out.println(httpClient.toString());
	}

	/**
	 * <p>
	 * When an HttpClient instance is no longer needed and is about to go out of
	 * scope it is important to shut down its connection manager to ensure that
	 * all connections kept alive by the manager get closed and system resources
	 * allocated by those connections are released.
	 * </p>
	 * 
	 * @throws IOException
	 */
	public static void testConnectionManagerShutdown() throws IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		httpClient.close();
	}

	/**
	 * <p>
	 * When equipped with a pooling connection manager such as
	 * PoolingClientConnectionManager, HttpClient can be used to execute
	 * multiple requests simultaneously using multiple threads of execution.
	 * </p>
	 * <p>
	 * The PoolingClientConnectionManager will allocate connections based on its
	 * configuration. If all connections for a given route have already been
	 * leased, a request for a connection will block until a connection is
	 * released back to the pool. One can ensure the connection manager does not
	 * block indefinitely in the connection request operation by setting
	 * 'http.conn-manager.timeout' to a positive value. If the connection
	 * request cannot be serviced within the given time period
	 * ConnectionPoolTimeoutException will be thrown.
	 * </p>
	 * <p>
	 * While HttpClient instances are thread safe and can be shared between
	 * multiple threads of execution, it is highly recommended that each thread
	 * maintains its own dedicated instance of HttpContext .
	 * </p>
	 * 
	 * @throws InterruptedException
	 */
	public static void testMultithreadedRequestExecution()
			throws InterruptedException {
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		CloseableHttpClient httpClient = HttpClients.custom()
				.setConnectionManager(cm).build();
		// URIs to perform GETs on
		String[] urisToGet = { "http://www.domain1.com/",
				"http://www.domain2.com/", "http://www.domain3.com/",
				"http://www.domain4.com/" };
		// create a thread for each URI
		GetThread[] threads = new GetThread[urisToGet.length];
		for (int i = 0; i < threads.length; i++) {
			HttpGet httpget = new HttpGet(urisToGet[i]);
			threads[i] = new GetThread(httpClient, httpget);
		}
		// start the threads
		for (int j = 0; j < threads.length; j++) {
			threads[j].start();
		}
		// join the threads
		for (int j = 0; j < threads.length; j++) {
			threads[j].join();
		}
	}

	/**
	 * <p>
	 * The HTTP specification does not specify how long a persistent connection
	 * may be and should be kept alive. Some HTTP servers use a non-standard
	 * Keep-Alive header to communicate to the client the period of time in
	 * seconds they intend to keep the connection alive on the server side.
	 * HttpClient makes use of this information if available. If the Keep-Alive
	 * header is not present in the response, HttpClient assumes the connection
	 * can be kept alive indefinitely. However, many HTTP servers in general use
	 * are configured to drop persistent connections after a certain period of
	 * inactivity in order to conserve system resources, quite often without
	 * informing the client. In case the default strategy turns out to be too
	 * optimistic, one may want to provide a custom keep-alive strategy.
	 * </p>
	 * <p>
	 * HTTP协议说明中没有指定是否需要保持持续连接或保持连接多久。在客户端想要与服务器保持连接的时间中，一些HTTP服务器使用非标准Keep-
	 * Alive头与客户端交互
	 * 。HttpClient使用这些信息是可能的。如果在响应中没有Keep-Alive头，HttpClient会假定保持链接无期限
	 * 。然而，许多HTTP服务器为了释放系统资源，会配置为一段没有任何操作后断开连接。但是默认的策略被证明太乐观，如果有人想提供一个保持连接的策略。
	 * </p>
	 * 
	 */
	public static void testConnectionKeepAliveStrategy() {
		ConnectionKeepAliveStrategy myStrategy = new ConnectionKeepAliveStrategy() {
			public long getKeepAliveDuration(HttpResponse response,
					HttpContext context) {
				// Honor 'keep-alive' header
				HeaderElementIterator it = new BasicHeaderElementIterator(
						response.headerIterator(HTTP.CONN_KEEP_ALIVE));
				while (it.hasNext()) {
					HeaderElement he = it.nextElement();
					String param = he.getName();
					String value = he.getValue();
					if (value != null && param.equalsIgnoreCase("timeout")) {
						try {
							return Long.parseLong(value) * 1000;
						} catch (NumberFormatException ignore) {
						}
					}
				}
				HttpHost target = (HttpHost) context
						.getAttribute(HttpClientContext.HTTP_TARGET_HOST);
				if ("www.naughty-server.com".equalsIgnoreCase(target
						.getHostName())) {
					// Keep alive for 5 seconds only
					return 5 * 1000;
				} else {
					// otherwise keep alive for 30 seconds
					return 30 * 1000;
				}
			}
		};
		CloseableHttpClient client = HttpClients.custom()
				.setKeepAliveStrategy(myStrategy).build();
		System.out.println(client.toString());
	}

	/**
	 * <p>
	 * HTTP connections make use of a java.net.Socket object internally to
	 * handle transmission of data across the wire. However they rely on the
	 * ConnectionSocketFactory interface to create, initialize and connect
	 * sockets. This enables the users of HttpClient to provide application
	 * specific socket initialization code at runtime.
	 * PlainConnectionSocketFactory is the default factory for creating and
	 * initializing plain (unencrypted) sockets.
	 * </p>
	 * <p>
	 * The process of creating a socket and that of connecting it to a host are
	 * decoupled, so that the socket could be closed while being blocked in the
	 * connect operation.
	 * </p>
	 * 
	 * @throws IOException
	 */
	public static void testConnectionSocketFactories() throws IOException {
		HttpClientContext clientContext = HttpClientContext.create();
		PlainConnectionSocketFactory sf = PlainConnectionSocketFactory
				.getSocketFactory();
		Socket socket = sf.createSocket(clientContext);
		int timeout = 1000; // ms
		HttpHost target = new HttpHost("localhost");
		InetSocketAddress remoteAddress = new InetSocketAddress(
				InetAddress.getByAddress(new byte[] { 127, 0, 0, 1 }), 80);
		sf.connectSocket(timeout, socket, target, remoteAddress, null,
				clientContext);
	}

	static class GetThread extends Thread {
		private final CloseableHttpClient httpClient;
		private final HttpContext context;
		private final HttpGet httpget;

		public GetThread(CloseableHttpClient httpClient, HttpGet httpget) {
			this.httpClient = httpClient;
			this.context = HttpClientContext.create();
			this.httpget = httpget;
		}

		@Override
		public void run() {
			try {
				CloseableHttpResponse response = httpClient.execute(httpget,
						context);
				try {
					HttpEntity entity = response.getEntity();
					if (entity != null) {
						// do something
					}
				} finally {
					response.close();
				}
			} catch (ClientProtocolException ex) {
				// Handle protocol errors
			} catch (IOException ex) {
				// Handle I/O errors
			}
		}
	}
}
