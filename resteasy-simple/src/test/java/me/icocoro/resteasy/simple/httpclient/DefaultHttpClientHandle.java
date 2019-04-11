package me.icocoro.resteasy.simple.httpclient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLHandshakeException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import me.icocoro.resteasy.simple.util.MapUtil;
import me.icocoro.resteasy.simple.util.StringUtil;

/**
 * DefaultHttpClientHandle-httpclient4.2.1
 * 
 * @author shaozhipeng
 * 
 */
public class DefaultHttpClientHandle extends AbstractHttpClientHandle {
	private static final Log logger = LogFactory
			.getLog(DefaultHttpClientHandle.class);

	private static DefaultHttpClient httpClient;

	/**
	 * 私有的构造方法
	 */
	private DefaultHttpClientHandle() {
	}

	/**
	 * 请求恢复策略
	 */
	public static HttpRequestRetryHandler httpRequestRetryHandler = new HttpRequestRetryHandler() {

		public boolean retryRequest(IOException exception, int executionCount,
				HttpContext httpContext) {
			// 已重发两次后不再发送
			if (executionCount > 2) {
				return false;
			}

			if (exception instanceof HttpHostConnectException) {
				return true;
			}

			if (exception instanceof SSLHandshakeException) {
				return false;
			}

			HttpRequest httpRequest = (HttpRequest) httpContext
					.getAttribute(ExecutionContext.HTTP_REQUEST);
			boolean idempotent = httpRequest instanceof HttpEntityEnclosingRequest;
			if (!idempotent) {
				return true;
			}
			return false;
		}
	};

	/**
	 * HttpClient初始化配置
	 * 
	 * @return HttpClient
	 */
	public static HttpClient getHttpClient() {
		if (httpClient == null) {
			// 设置组件参数, HTTP协议的版本,1.1/1.0/0.9
			HttpParams params = new BasicHttpParams();
			HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
			HttpProtocolParams.setUserAgent(params, "HttpComponents/1.1");
			HttpProtocolParams.setUseExpectContinue(params, true);

			// 设置连接超时时间
			int REQUEST_TIMEOUT = 10 * 1000; // 设置请求超时10秒钟
			int SO_TIMEOUT = 10 * 1000; // 设置等待数据超时时间10秒钟
			params.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,
					REQUEST_TIMEOUT);
			params.setParameter(CoreConnectionPNames.SO_TIMEOUT, SO_TIMEOUT);

			// 设置访问协议
			SchemeRegistry schreg = new SchemeRegistry();
			schreg.register(new Scheme("http", 80, PlainSocketFactory
					.getSocketFactory()));
			schreg.register(new Scheme("https", 443, SSLSocketFactory
					.getSocketFactory()));

			// 多连接的线程安全的管理器
			PoolingClientConnectionManager pccm = new PoolingClientConnectionManager(
					schreg);
			// 每个主机的最大并行链接数
			pccm.setDefaultMaxPerRoute(20);
			// 客户端总并行链接最大数
			pccm.setMaxTotal(100);

			httpClient = new DefaultHttpClient(pccm, params);
		}
		return httpClient;
	}

	/**
	 * httpGet-get请求
	 * 
	 * @param url
	 * @return Object
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static Object httpGet(String url) throws ClientProtocolException,
			IOException {
		// HttpGet
		HttpGet httpGet = new HttpGet(url);
		// 处理请求结果
		ResponseHandler<Object> responseHandler = new ResponseHandler<Object>() {
			public Object handleResponse(HttpResponse response)
					throws ClientProtocolException, IOException {
				Header[] headers = response.getAllHeaders();
				// 输出头信息
				for (Header header : headers) {
					logger.info("header.getName()->" + header.getName()
							+ " header.getValue()->" + header.getValue());
				}
				HttpEntity httpEntity = response.getEntity();
				// 输出返回结果
				if (httpEntity != null) {
					logger.info("httpEntity->" + httpEntity.getContentType());
					logger.info("返回结果为:" + EntityUtils.toString(httpEntity));
				}
				return response.getStatusLine().getStatusCode();
			}
		};
		// 返回状态码
		Object object = getHttpClient().execute(httpGet, responseHandler);
		httpGet.abort();
		return object;
	}

	/**
	 * httpPost-post请求
	 * 
	 * @param url
	 * @param queryParam
	 * @param formParam
	 * @return Object
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static Object httpPost(String url, Map<String, Object> queryParam,
			Map<String, String> formParam) throws ClientProtocolException,
			IOException {
		List<NameValuePair> paramList = transformMap(formParam);
		UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(paramList,
				"utf8");
		// HttpPost
		HttpPost httpPost = new HttpPost(getRequestUrl(url, queryParam,
				formParam));
		httpPost.setEntity(formEntity);
		// 处理返回结果
		ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
			public String handleResponse(HttpResponse response)
					throws ClientProtocolException, IOException {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					return EntityUtils.toString(entity);
				} else {
					return null;
				}
			}
		};
		// 返回结果
		return getHttpClient().execute(httpPost, responseHandler);
	}

	/**
	 * 处理请求字符串
	 * 
	 * @param url
	 * @param params
	 * @param formParam
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private static String getRequestUrl(String url, Map<String, Object> params,
			Map<String, String> formParam) throws UnsupportedEncodingException {
		StringBuffer sb = new StringBuffer(url);
		// 获得原始参数串
		String paramurl = MapUtil.getUrlFromMap(params);
		logger.info("客户端请求的URL参数串为:" + paramurl);
		// 去掉参数中的特殊字符
		String readyTosign = StringUtil.specCharFilter(paramurl);
		logger.info("客户端请求的安全参数串为:" + readyTosign);
		sb.append("?").append(paramurl);
		return sb.toString();
	}

	public static void shutdown() {
		httpClient.getConnectionManager().shutdown();
	}
}
