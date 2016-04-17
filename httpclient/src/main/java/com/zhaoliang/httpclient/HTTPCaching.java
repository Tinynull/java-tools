package com.zhaoliang.httpclient;

public class HTTPCaching {

	/**
	 * <p>
	 * This is a simple example of how to set up a basic caching HttpClient. As
	 * configured, it will store a maximum of 1000 cached objects, each of which
	 * may have a maximum body size of 8192 bytes. The numbers selected here are
	 * for example only and not intended to be prescriptive or considered as
	 * recommendations.
	 * </p>
	 * <p>
	 * 这是一个设置HttpClient基本缓存的例子。如程序中配置的那样，会存储最大1000个缓存
	 * 的对象，每个对象最大内存为8kb。例子中设置的值只是作为演示，不是我们推荐的值。
	 * </p>
	 * 
	 */
	public static void testCaching() {
		// CacheConfig cacheConfig =
		// CacheConfig.custom().setMaxCacheEntries(1000)
		// .setMaxObjectSize(8192).build();
		// RequestConfig requestConfig = RequestConfig.custom()
		// .setConnectTimeout(30000).setSocketTimeout(30000).build();
		// CloseableHttpClient cachingClient = CachingHttpClients.custom()
		// .setCacheConfig(cacheConfig)
		// .setDefaultRequestConfig(requestConfig).build();
		// HttpCacheContext context = HttpCacheContext.create();
		// HttpGet httpget = new HttpGet("http://www.mydomain.com/content/");
		// CloseableHttpResponse response = cachingClient
		// .execute(httpget, context);
		// try {
		// CacheResponseStatus responseStatus = context
		// .getCacheResponseStatus();
		// switch (responseStatus) {
		// case CACHE_HIT:
		// System.out
		// .println("A response was generated from the cache with "
		// + "no requests sent upstream");
		// break;
		// case CACHE_MODULE_RESPONSE:
		// System.out
		// .println("The response was generated directly by the "
		// + "caching module");
		// break;
		// case CACHE_MISS:
		// System.out.println("The response came from an upstream server");
		// break;
		// case VALIDATED:
		// System.out.println("The response was generated from the cache "
		// + "after validating the entry with the origin server");
		// break;
		// }
		// } finally {
		// response.close();
		// }
	}
}
