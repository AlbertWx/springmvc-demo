package com.wx.springmvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wx.springmvc.dao.ItemsDao;
import com.wx.springmvc.entity.Items;
import com.wx.springmvc.entity.ItemsExample;
import com.wx.springmvc.service.ItemsService;

@Service
@Transactional
public class ItemsServiceImpl implements ItemsService {

	@Autowired
	private ItemsDao itemsDao;

	public List<Items> selectByExample(ItemsExample itemsExample) {
		// TODO Auto-generated method stub
		return itemsDao.selectByExample(itemsExample);
	}
	
	public Items selectById(Integer id) {
		return itemsDao.selectById(id);
	}
}
