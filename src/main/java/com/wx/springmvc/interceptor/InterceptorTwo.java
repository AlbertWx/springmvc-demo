package com.wx.springmvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 自定义的拦截器二
 * 测试拦截器的执行规则
 * @author Administrator
 *
 */
public class InterceptorTwo implements HandlerInterceptor {
	
	private Logger log = LoggerFactory.getLogger(InterceptorTwo.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		log.info("=====InterceptorTwo========preHandle=======方法前");
//		return false;
		//开启
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		log.info("=====InterceptorTwo========postHandle=======方法后");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		log.info("=====InterceptorTwo========afterCompletion=======页面渲染后");
	}

}
