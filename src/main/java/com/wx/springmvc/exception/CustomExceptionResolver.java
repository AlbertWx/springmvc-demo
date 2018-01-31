package com.wx.springmvc.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * spring mvc的自定义异常处理器
 * 
 * @author Administrator
 *
 */
public class CustomExceptionResolver implements HandlerExceptionResolver {
	
	private Logger log = LoggerFactory.getLogger(CustomExceptionResolver.class);
	
	/**
	 * Object handler 记录发生异常的地方的信息  可能为controller层，service层，dao层的方法，
	 * 包括 方法的权限 方法的返回值类型 包名+类名+方法名（形参的包名类名） 等信息拼接起来的字符串
	 */
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		// TODO Auto-generated method stub
		log.info("===========开始进入spring mvc的自定义异常处理类===========");
		log.info("异常的位置（类方法的全路径）=============" + handler.toString());
		log.info("===========打印异常的信息，开始===========");
		log.info(ex.getMessage());
		log.info(ex.toString());
		log.info("===========打印异常的信息，结束===========");
		
		ModelAndView modelAndView = new ModelAndView();
		
		//判断是否是自定义的异常
		if(ex instanceof CustomException) {
			//如果是自定义的异常，说明知道异常的原因
			CustomException ce = (CustomException) ex;
			//已知的异常的信息，在异常的产生抛出的地方已经说明了原因，展示到页面
			modelAndView.addObject("error", ce.getMessage());
		}else {
			// 未知的异常信息， 展示到页面
			modelAndView.addObject("error", "未知错误！！！");//EL表达式
		}
		
		
		//设置跳转视图
		modelAndView.setViewName("error");
//		//添加展示数据信息
//		modelAndView.addObject("error", "未知错误！！！");
		log.info("===========执行spring mvc的自定义异常处理类结束===========");
		return modelAndView;
	}

}
