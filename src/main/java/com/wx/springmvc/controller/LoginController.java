package com.wx.springmvc.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="login")
public class LoginController {
	
	private Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping(value="out",method=RequestMethod.GET)
	public String logOut() {
		//跳转登录页面
		return "login";
	}
	
	@RequestMapping(value="in",method= {RequestMethod.POST,RequestMethod.GET})
	public String logIn(String name,String password,HttpSession httpSession) {
		log.info("======name : "+ name +"========password : "+ password);
		httpSession.setAttribute("USER_SESSION", name);
		return "redirect:/item/list";
	}
}
