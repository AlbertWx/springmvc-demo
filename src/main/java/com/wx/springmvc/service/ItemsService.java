package com.wx.springmvc.service;

import java.util.List;

import com.wx.springmvc.entity.Items;
import com.wx.springmvc.entity.ItemsExample;

public interface ItemsService {
	
	public List<Items> selectByExample(ItemsExample itemsExample);
	
	public Items selectById(Integer id); 
	
}
