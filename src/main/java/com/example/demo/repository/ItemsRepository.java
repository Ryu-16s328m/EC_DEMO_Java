package com.example.demo.repository;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.ItemsEntity;
import com.example.demo.interFace.ItemsInterface;
import com.fasterxml.jackson.annotation.JsonFormat.Feature;

@Repository
public class ItemsRepository {
	
	@Autowired
	ItemsInterface itemsInterface;
	
	public Page<ItemsEntity> search(String freeword , Integer genre , Integer fromPrice , Integer toPrice , Integer brand , int sort , int pageNum){
		Sort sortFlg;
		if(sort == 2) {
			sortFlg = new Sort(Direction.ASC, "price");
		}else {
			sortFlg = new Sort(Direction.DESC, "price");
		}
		Pageable pageable = PageRequest.of(pageNum, 9, sortFlg);
		
        Specification<ItemsEntity> searchConditions = Specification.where((Specification<ItemsEntity>) null);
        if((freeword != null) && (!freeword.isEmpty())) {
        	freeword = freeword.replace("　", " ");
    		String[] words = freeword.split(" ");
    		for(int i = 0; i < words.length; i++) {
    			words[i] = words[i].trim();
    			String word = words[i];
    			Specification<ItemsEntity> searchByWordFromName = (root, query, cb) -> cb.like(root.get("name"), "%"+ word + "%");
            	searchConditions = searchConditions.or(searchByWordFromName);
            	Specification<ItemsEntity> searchByWordFromDetail = (root, query, cb) -> cb.like(root.get("detail"), "%"+ word + "%");
            	searchConditions = searchConditions.or(searchByWordFromDetail);
            	Specification<ItemsEntity> searchByWordFromBrand = (root, query, cb) -> cb.like(root.get("brandsEntity").get("brandName"), "%"+ word + "%");
            	searchConditions = searchConditions.or(searchByWordFromBrand);
    		}
        }
        if((genre != null)) {
        	Specification<ItemsEntity> searchByGenre = (root, query, cb) -> cb.equal(root.get("genreId"), genre);
        	searchConditions = searchConditions.and(searchByGenre);
        }
        if((brand != null)) {
        	Specification<ItemsEntity> searchByBrand = (root, query, cb) -> cb.equal(root.get("brandId"), brand);
        	searchConditions = searchConditions.and(searchByBrand);
        }
        if((fromPrice != null)) {
        	Specification<ItemsEntity> searchFromPrice = (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("price"), fromPrice);
        	searchConditions = searchConditions.and(searchFromPrice);
        }
        if((toPrice != null)) {
        	Specification<ItemsEntity> searchToPrice = (root, query, cb) -> cb.lessThanOrEqualTo(root.get("price"), toPrice);
        	searchConditions = searchConditions.and(searchToPrice);
        }
        
		Page<ItemsEntity> itemsEntityList = itemsInterface.findAll(searchConditions , pageable);
		
		return itemsEntityList;
	}
	
	public ItemsEntity getOne(int id) {
		ItemsEntity itemsEntity = itemsInterface.getOne(id);
		return itemsEntity;
	}
}
