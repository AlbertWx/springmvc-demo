package com.wx.springmvc.controller;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

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
	 * 
	 * 上传文件的form表单类型必须设置为 enctype="multipart/form-data"
	 * 
	 */ 
	 
	@RequestMapping(value="update",method=RequestMethod.POST)
	public String updateByQueryVoList(Items items,MultipartFile pictureFile) throws Exception {
		//pictureFile为页面中input type="file"  name="pictureFile",如果想设置形参名称与页面input的name值不一样，可以
		//MultipartFile pictureFile加@RequestParam("页面input的name值")进行绑定
		
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateString = df.format(new Date());
		
		String originalFilename = pictureFile.getOriginalFilename();
		File file = new File("F:\\pic"+File.separator+dateString+originalFilename);
		//将上传的文件保存到磁盘的目录
		pictureFile.transferTo(file);
		logger.info("================图片路径："+file.getAbsolutePath());
		items.setPic(file.getName());
		itemsService.update(items);
		return "redirect:edit?id="+items.getId();
	}
	
	/*
	 * 批量修改 
	 * https://www.cnblogs.com/liusonglin/p/4895694.html
	 * 通过在QueryVo类中声明List<Items> itemsList；然后在页面的c:forEach标签中添加varStatus="s"作为list的索引，
	 * 然后name="itemsList[${s.index}].id"以list集合索引下标的方法选中去list中的对象
	 */
	@RequestMapping(value="updateBatch",method=RequestMethod.POST)
	public String updateByQueryVoListBatch(QueryVo queryVo) throws Exception {
		
		List<Items> itemsList = queryVo.getItemsList();
		itemsService.updateByBatch(itemsList);
		return "redirect:list";
	}
	
	/*
	 * 跳转到到修改页面，并上传图片
	 */
	@RequestMapping("edit")
	public String edit(Integer id,Model model) {
		Items item = itemsService.selectById(id);
		model.addAttribute("item", item);
		return "editItemFile";
	}
}
