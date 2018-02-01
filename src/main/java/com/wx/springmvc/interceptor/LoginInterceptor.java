package com.wx.springmvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 验证用户登录的拦截器
 * 
 * @author Administrator
 *
 */
public class LoginInterceptor implements HandlerInterceptor {

	private Logger log = LoggerFactory.getLogger(LoginInterceptor.class);

	/**
	 * 业务规则：判断用户是否登录，如果没有登录，重定向到登录页面，不放行；如果登录了就放行
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		log.info("===LoginInterceptor========preHandle========Controller方法执行前===");
		// 获取请求的URI路径
		String requestURI = request.getRequestURI();
		if (requestURI.contains("/login/out") || requestURI.contains("/login/in")) {
			return true;
		} else {
			String userName = (String) request.getSession().getAttribute("USER_SESSION");
			if (userName == null) {
				response.sendRedirect(request.getContextPath() + "/login/out");
				return false;
			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		log.info("===LoginInterceptor========postHandle========Controller方法执行后===");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		log.info("===LoginInterceptor========afterCompletion========页面视图解析渲染后===");
	}

}
