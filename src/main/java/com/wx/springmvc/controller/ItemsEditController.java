package com.wx.springmvc.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wx.springmvc.entity.Items;
import com.wx.springmvc.entity.ItemsExample;
import com.wx.springmvc.entity.QueryVo;
import com.wx.springmvc.service.ItemsService;

@Controller
@RequestMapping(value="/itemEdit")
public class ItemsEditController {
	
	private Logger logger = LoggerFactory.getLogger(ItemsEditController.class);
	
	@Resource
	private ItemsService itemsService;
	
	@RequestMapping(value="list",method=RequestMethod.GET)
	public String editByQueryVoList(Model model) {
		ItemsExample itemsExample = new ItemsExample();
		List<Items> list = itemsService.selectByExample(itemsExample);
		for (Items items : list) {
			logger.info("=================" + items.toString());
		}
		model.addAttribute("itemsList", list);
		return "itemQueryVoListEdit";
	}
	
	/*
	 * https://www.cnblogs.com/liusonglin/p/4895694.html
	 * 通过在QueryVo类中声明List<Items> itemsList；然后在页面的c:forEach标签中添加varStatus="s"作为list的索引，
	 * 然后name="itemsList[${s.index}].id"以list集合索引下标的方法选中去list中的对象
	 */
	@RequestMapping(value="update",method=RequestMethod.POST)
	public String updateByQueryVoList(QueryVo queryVo) {
		
		List<Items> itemsList = queryVo.getItemsList();
		itemsService.updateByBatch(itemsList);
		return "redirect:list";
	}
	
}
