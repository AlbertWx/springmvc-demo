package com.wx.springmvc.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wx.springmvc.entity.Items;
import com.wx.springmvc.entity.ItemsExample;
import com.wx.springmvc.service.ItemsService;

@Controller
@RequestMapping("json")
public class JsonController {
	
	private Logger log = LoggerFactory.getLogger(JsonController.class);
	
	@Autowired
	private ItemsService itemsService;
	
	@RequestMapping(value="list")
	public String showList(ModelMap modelMap) {
		ItemsExample itemsExample = new ItemsExample();
		List<Items> itemList = itemsService.selectByExample(itemsExample);
		modelMap.addAttribute("itemList", itemList);
		return "itemListJson";
	}
	
	@RequestMapping(value="edit")
	public String edit(Integer id,Model model) {
		Items item = itemsService.selectById(id);
		model.addAttribute("item", item);
		return "editItemJson";
	}
	
	/*
	 * json数据交互
	 * http://localhost:8080/springmvc-demo/json/edit?id=2
	 * 页面初始化或者刷新的时候会调用ajax请求与后台交互，传递json格式的字符串到后台，
	 * 然后通过@RequestBody被解析为Items对象
	 * 
	 * 通过在方法的返回类型前加@ResponseBody，可以将返回的对象格式化为json格式的数据，返回到前端（通过ajax中的回调函数） 
	 */
	@RequestMapping(value="json",method=RequestMethod.POST)
	public @ResponseBody Items json(@RequestBody Items items) {
		log.info(items.toString());
		return items;
	}
}
