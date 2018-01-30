package com.wx.springmvc.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class QueryVo implements Serializable {
	
	@Override
	public String toString() {
		return "QueryVo [items=" + items + ", ids=" + Arrays.toString(ids) + "]";
	}

	private Items items;

	public Items getItems() {
		return items;
	}

	public void setItems(Items items) {
		this.items = items;
	}
	
	private Integer[] ids;

	public Integer[] getIds() {
		return ids;
	}

	public void setIds(Integer[] ids) {
		this.ids = ids;
	}
	
	private List<Items> itemsList;

	public List<Items> getItemsList() {
		return itemsList;
	}

	public void setItemsList(List<Items> itemsList) {
		this.itemsList = itemsList;
	}
	
}
