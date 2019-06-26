package com.example.demo.session;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.example.demo.entity.ItemsEntity;

@Component
@Scope(value = "session" , proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ItemSession implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4923007440656573289L;
	
	private List<ItemsEntity> registedItems = new ArrayList<>();
	private List<Integer[]> stockOptions = new ArrayList<>();
	private List<Integer> itemsCount = new ArrayList<>();
	
	
	public List<ItemsEntity> getRegistedItems() {
		return registedItems;
	}
	public List<Integer[]> getStockOptions() {
		return stockOptions;
	}
	public List<Integer> getItemsCount() {
		return itemsCount;
	}
	private void setStockOptions(int stock) {
		Integer[] stockOption = new Integer[stock];
		for(int i = 0; i < stockOption.length ; i++) {
			stockOption[i] = i + 1;
		}
		this.stockOptions.add(stockOption);
	}
	
	public void setRegistedItems(ItemsEntity registItems , int count) {
		this.registedItems.add(registItems);
		this.setStockOptions(registItems.getStock());
		this.setItemsCount(count);
	}
	public void setItemsCount(int count) {
		this.itemsCount.add(count);
	}
	
	public void removeFromRegistedItems(int index) {
		this.registedItems.remove(index);
	}
	public void removeFromStockOptions(int index) {
		this.stockOptions.remove(index);
	}
	public void removeFromItemsCount(int index) {
		this.itemsCount.remove(index);
	}
	
	
	
	
}