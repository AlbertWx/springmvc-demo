package com.wx.springmvc.dao;

import java.util.List;

import com.wx.springmvc.entity.Items;
import com.wx.springmvc.entity.ItemsExample;

public interface ItemsDao {
	
	public List<Items> selectByExample(ItemsExample itemsExample);
	
	public Items selectById(Integer id); 
	
	public void update(Items items);
	
	public void deleteByIds(List<Integer> list);
	
	public void updateByBatch(List<Items> itemsList);
	
}
