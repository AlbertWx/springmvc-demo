package com.wx.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wx.springmvc.exception.CustomException;

/**
 * spring mvc的自定义异常的测试类
 * @author Administrator
 *
 */
@Controller
@RequestMapping("error")
public class ExceptionTestController{
	
	@RequestMapping("error")
	public String exceptionTest() throws Exception {
		
		//异常位置,模拟未知异常
		//int i = 1/0;
		
		//模拟已知的预期异常
		if(1 == 1) {
			//测试抛出自定义的异常，然后有自定义的异常处理器CustomExceptionResolver处理
			throw new CustomException("已知的异常信息。。。");
		}
		return null;
	}
	
}
