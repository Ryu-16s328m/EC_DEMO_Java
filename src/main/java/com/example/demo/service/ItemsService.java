package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ItemsEntity;
import com.example.demo.repository.ItemsRepository;

@Service
public class ItemsService {

	@Autowired
	ItemsRepository itemsRepository;
	
	public Page<ItemsEntity> search(String freeword , Integer genre , Integer fromPrice , Integer toPrice , Integer brand , int sort , int pageNum){
		Page<ItemsEntity> itemsEntityList = itemsRepository.search(freeword, genre, fromPrice, toPrice, brand, sort, pageNum);
		return itemsEntityList;
	}
	
	public ItemsEntity getOne(int id) {
		ItemsEntity itemsEntity = itemsRepository.getOne(id);
		return itemsEntity;
	}
}
