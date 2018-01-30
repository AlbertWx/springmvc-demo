package com.wx.springmvc.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.wx.springmvc.entity.Items;
import com.wx.springmvc.entity.ItemsExample;
import com.wx.springmvc.entity.QueryVo;
import com.wx.springmvc.service.ItemsService;

@Controller
@RequestMapping(value = "item") // value可以省略
public class ItemsController {

	private Logger logger = LoggerFactory.getLogger(ItemsController.class);

	@Autowired
	private ItemsService itemService;

	@RequestMapping(value = "list", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView itemList() {
		ItemsExample itemsExample = new ItemsExample();
		List<Items> list = itemService.selectByExample(itemsExample);
		for (Items items : list) {
			logger.info("=================" + items.toString());
		}
		ModelAndView modelAndView = new ModelAndView();
		// 填充数据
		modelAndView.addObject("itemList", list);
		// 1.没有手动配置视图解析器（包括前缀后缀等属性）前的写法，完整的jsp路径
		// modelAndView.setViewName("/WEB-INF/jsp/itemList.jsp");
		// 2.手动配置视图解析器（包括前缀后缀等属性）后的写法，只需写jsp文件的名称
		modelAndView.setViewName("itemList");
		return modelAndView;
	}

	@RequestMapping(value = "list2", method = RequestMethod.GET)
	public String itemList2() {
		return "/WEB-INF/jsp/itemList.jsp";
	}

	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public ModelAndView selectById(Integer id, HttpServletRequest request, HttpServletResponse response,
			HttpSession session, Model model) {
 //方式一
 // public ModelAndView selectById(HttpServletRequest request, HttpServletResponse response, 
		// HttpSession session, Model model) {
		/*
		 * 方式一,传统servlet时代开发，通过request获取请求（/item/edit?id=）中的参数id String id =
		 * request.getParameter("id");
		 */
		// Items item = itemService.selectById(Integer.parseInt(id));

		/* 方式二，直接在参数中添加Integer id,但是该方式需要方法声明中的参数id和jsp页面请求（/item/edit?id=）中的参数id名称一致 */
		Items item = itemService.selectById(id);

 //方式三
 // public ModelAndView selectById(@RequestParam(value = "id", required = false, defaultValue = "1") Integer idaa,
				// HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model) {
		/*
		 * 方式三，如果在方法声明的参数为Integer idaa，也就是jsp页面请求（/item/edit?id=）中传参绑定的参数id名称不一致，
		 * 这种情况，可以在 方法声明的参数Integer idaa前加注解@RequestParam("id"),这样就把jsp页面
		 * 请求（/item/edit?id=）传参的id和方法声明中的idaa绑定起来了
		 * 但是使用该@RequestParam注解标记参数时，默认所传的参数不能为空，否则如果传参为null会报错，因此需要在@RequestParam中添加
		 * required=false时该参数为非必须，或者添加defaultValue="xxx"设置缺省值默认值
		 */
		// Items item = itemService.selectById(idaa);
		logger.info("=============" + item.toString());
		
		/*
		 * 如果方法声明的参数中有Model model 或 ModelMap model，则可以不使用ModelAndView ，这时需要方法声明的返回值类型为String,
		 * 使用model.addAttribute("item", item) 或 model.addAttribute("item", item)添加绑定数据，
		 * 然后返回值为具体jsp页面名称的字符串（return "itemEdit"）来进行页面跳转
		 * 
		 * */
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("item", item);
		modelAndView.setViewName("editItem");
//		modelAndView.setViewName("editItemQueryVo");
		return modelAndView;
	}
	
	/*
	 * 普通的pojo对象中的属性名和表单中input的name属性一致
	 * 请求的参数名称name和pojo的属性名称一致，会自动将请求参数赋值给pojo的属性
	 * editItem.jsp
	 * 转发是服务器发起的，url地址栏无变化，重定向是客户端发起的，相当于请求了两次，url会变化
	 * 注意转发和重定向url时有斜线/和没有斜线的区别，有斜线是项目级别，无斜线是类级别下的
	 * 需要注意，转发的时候请求方式method,重定向不需要区分请求的method，但是转发需要转发发起的方法的method与转发到的方法的method一致
	 */
	@RequestMapping(value="update",method=RequestMethod.POST)
	public String update(Items items,Model model) {
		itemService.update(items);
		//直接返回到success.jsp页面
//		return "success";
		//以重定向的方式跳转页面(当前类级别下重定向)
		return "redirect:list";
		//项目级别下，不同的controller之间重定向
//		return "redirect:/item/list";
		//在当前类级别下转发
//		return "forward:list";
		//从项目级别的转发，不同的controller类的RequestMapping级别开始的
//		return "forward:/item/list";
	}
	
	/*
	 * 使用包装的poji，QueryVo包含属性Items，页面input中的name属性应该改为QueryVo中的属性items.xxx,xxx为Items类中的属性
	 * editItemQueryVo.jsp
	 */
	@RequestMapping(value="updateByQueryVo",method=RequestMethod.POST)
	public String updateByQueryVo(QueryVo queryVo) {
		itemService.update(queryVo.getItems());
		return "success";
	}
	
	/*
	 * <input type="checkbox" name="ids" value="${item.id }">
	 * 删除页面checkbox选中的商品,选中多条，以数组方式传递选中的多个ids参数
	 */
	@RequestMapping(value="delete",method=RequestMethod.POST)
	public String deleteByIds(Integer[] ids) {
		logger.info("=============ids :" + ids.toString());
		List<Integer> list = Arrays.asList(ids);
		itemService.deleteByIds(list);
		return "redirect:list";
	}
	
	@RequestMapping(value="deleteByQueryVo",method=RequestMethod.POST)
	public String deleteByIdsQueryVo(QueryVo queryVo) {
		logger.info("=============ids :" + queryVo.getIds().toString());
		List<Integer> list = Arrays.asList(queryVo.getIds());
		itemService.deleteByIds(list);
		return "redirect:list";
	}
}
