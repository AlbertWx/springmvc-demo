package com.wx.springmvc.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wx.springmvc.dao.ItemsDao;
import com.wx.springmvc.entity.Items;
import com.wx.springmvc.entity.ItemsExample;
import com.wx.springmvc.mapper.ItemsMapper;

@Repository
public class ItemsDaoImpl implements ItemsDao {
	
	@Autowired
	private ItemsMapper itemsMapper;
	
	public List<Items> selectByExample(ItemsExample itemsExample){
		return itemsMapper.selectByExample(itemsExample);
	}
	
	public Items selectById(Integer id) {
		return itemsMapper.selectByPrimaryKey(id);
	}

	@Override
	public void update(Items items) {
		// TODO Auto-generated method stub
		ItemsExample example = new ItemsExample();
		example.createCriteria().andIdEqualTo(items.getId());
		itemsMapper.updateByExampleSelective(items, example);
	}
	
}
