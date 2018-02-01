package com.wx.springmvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 自定义的拦截器一
 * 测试拦截器的执行规则
 * @author Administrator
 * Spring Web MVC 的处理器拦截器类似于Servlet 开发中的过滤器Filter，用于对处理器进行预处理和后处理
 */
public class InterceptorOne implements HandlerInterceptor {
	
	private Logger log = LoggerFactory.getLogger(InterceptorOne.class);
	
	/*
	 * 拦截器运行规则总结：
	 * preHandle按拦截器定义顺序调用
	 * postHandler按拦截器定义逆序调用
	 * afterCompletion按拦截器定义逆序调用
	 * postHandler在拦截器链内所有拦截器返成功调用
	 * afterCompletion只有preHandle返回true才调用
	 */
	
	
	/**
	 * Controller执行前调用此方法
	 * 返回true表示继续执行，返回false中止执行
	 * 这里可以加入登录校验、权限拦截等
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		log.info("=====InterceptorOne========preHandle=======方法前");
		//false为关闭拦截器放行，拒绝执行后面的请求操作
		//return false;
		//true为开启拦截器放行
		return true;
	}
	
	/**
	 * controller执行后但未返回视图前调用此方法
	 * 这里可在返回用户前对模型数据进行加工处理，比如这里加入公用信息以便页面显示
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		log.info("=====InterceptorOne========postHandle=======方法后");
	}
	
	/**
	 * controller执行后且视图返回后调用此方法
	 * 这里可得到执行controller时的异常信息
	 * 这里可记录操作日志
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		log.info("=====InterceptorOne========afterCompletion=======页面渲染后");
	}

}
