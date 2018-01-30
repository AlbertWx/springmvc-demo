package com.wx.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
		
		//异常位置
		int i = 1/0;
		
		return null;
	}
	
}
