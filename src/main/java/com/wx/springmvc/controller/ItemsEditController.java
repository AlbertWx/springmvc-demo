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
	
	/**
	 * controller内方法的返回值类型：
	 * 1.ModelAndView  该返回类型为万能的，包括数据和返回视图的路径   (一般不建议)
	 * 2.String  返回视图的路径名，但是这种方式需要在方法声明的形参上声明Model model或者ModelMap modelMap,然后在方法中向model或
	 * 		modelMap中添加数据，以把数据显示到页面视图中     (官方推荐此种方式  解耦  数据和视图分离，MVC)
	 * 3.void  该中方式需要在controller方法声明的参数中添加HttpServletRequest request, HttpServletResponse response,
	 * 		Model model等参数，通过Model model添加数据，通过request和response以转发或者重定向的方式跳转到对应的页面视图,此种
	 * 		方式一般用于ajax异步请求中
	 * @param model
	 * @return
	 */
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
