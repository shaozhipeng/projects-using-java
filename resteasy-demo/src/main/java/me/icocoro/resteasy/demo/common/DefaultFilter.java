package me.icocoro.resteasy.demo.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import me.icocoro.resteasy.demo.util.MapUtil;
import me.icocoro.resteasy.demo.util.Md5Util;
import me.icocoro.resteasy.demo.util.SignaturGenUtil;
import me.icocoro.resteasy.demo.util.StringUtil;

/**
 * DefaultFilter－验证URL，签名验证逻辑
 * 
 * @author shaozhipeng
 * 
 */
public class DefaultFilter extends HttpServlet implements Filter {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3450911784455562265L;

	public static final Log logger = LogFactory.getLog(DefaultFilter.class);

	/**
	 * doFilter
	 * 
	 * @param servletRequest
	 * @param servletResponse
	 * @param filterChain
	 * @throws IOException
	 * @throws ServletException
	 */
	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpServletRequest request = (HttpServletRequest) servletRequest;

		String keyCode = request.getParameter("keyCode");
		// 简单的普通用户验证，其它验证逻辑可再行设计、编写
		if ("guest".equals(keyCode)) {
			if (this.verifyGuest(request, response)) {
				filterChain.doFilter(request, response);
			} else
				return;
		} else {
			return;
		}
	}

	/**
	 * 普通用户验证
	 * 
	 * @param request
	 * @param response
	 * @return verifyGuest
	 * @throws IOException
	 * @throws ServletException
	 */
	private boolean verifyGuest(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		String path = request.getRequestURI();
		if (path.indexOf("/ws/demo/") < 0) {
			logger.error("guest请求只能访问/ws/demo/的接口");
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return false;
		}
		// 客户端请求的keyCode
		String keyCode = request.getParameter("keyCode");
		// 客户端请求的签名字符串
		String requestSignature = StringUtil.specCharFilter(request
				.getParameter("signature"));
		// 获得去掉签名的参数串
		String urlParam = MapUtil.getUrlFromMap(MapUtil.filter(
				MapUtil.getMapFromUrl(request.getQueryString()), "signature"));
		// 安全的参数串
		String safeParam = StringUtil.specCharFilter(urlParam);
		logger.info("安全的参数串:" + safeParam);
		// 自定义加密一段字符串
		keyCode = Md5Util.process("HelloWorld!Hereisresteasy_demo" + keyCode);
		// 自定义签名算法对参数串和MD5加密后的字符串进行签名
		String newSignature = StringUtil.specCharFilter(SignaturGenUtil
				.generator(safeParam, keyCode));
		if (requestSignature.equalsIgnoreCase(newSignature)) {
			return true;
		} else {
			logSignFailure(request);
			return false;
		}
	}

	/**
	 * 签名验证失败
	 * 
	 * @param request
	 */
	private void logSignFailure(HttpServletRequest request) {
		logger.info("签名出错!");
		logger.info("keyCode:" + request.getParameter("keyCode"));
		logger.info("signature:" + request.getParameter("signature"));
	}

	public void init(FilterConfig arg0) throws ServletException {
	}

}
