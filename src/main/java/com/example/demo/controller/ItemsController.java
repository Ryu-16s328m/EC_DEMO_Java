package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.ItemsEntity;
import com.example.demo.form.SearchForm;
import com.example.demo.service.ItemsService;
import com.example.demo.session.ItemSession;

@Controller
public class ItemsController {
	
	@Autowired
	ItemsService itemsService;
	
	@Autowired
	ItemSession itemsession;
	
	@RequestMapping(value = "/ec/index" , method = RequestMethod.GET)
	public ModelAndView index(@RequestParam(value="page", defaultValue="0") int pageNum , SearchForm form,BindingResult result) {
		ModelAndView modelAndView = new ModelAndView();
		Page<ItemsEntity> itemsEntityList = itemsService.search(form.getFreeword(), form.getGenre(), form.getFromPrice(), form.getToPrice(), form.getBrand(), form.getSort(), pageNum);
		modelAndView.addObject("itemsEntityList" , itemsEntityList);
		modelAndView.setViewName("index.html");
		modelAndView.addObject("freeword" , form.getFreeword());
		modelAndView.addObject("genre" , form.getGenre());
		modelAndView.addObject("fromPrice" , form.getFromPrice());
		modelAndView.addObject("toPrice" , form.getToPrice());
		modelAndView.addObject("brand" , form.getBrand());
		modelAndView.addObject("sort" , form.getSort());
		
		
		return modelAndView;
	}
	

	
	@RequestMapping(value = "/ec/detail" , method = RequestMethod.GET)
	public ModelAndView showDetails(@RequestParam("id") int id) {
		ModelAndView modelAndView = new ModelAndView();
		
		ItemsEntity itemsEntity = itemsService.getOne(id);
		int[] stockOption = new int[itemsEntity.getStock()];
		for(int i = 0; i < stockOption.length; i++) {
			stockOption[i] = i + 1;
		}
		
		modelAndView.addObject("stockOption" , stockOption);
		modelAndView.addObject("itemEntity" , itemsEntity);
		
		modelAndView.setViewName("/detail.html");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/ec/items" , method = RequestMethod.GET)
	public ModelAndView showCart() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/items.html");
		
		modelAndView.addObject("itemsCount" , itemsession.getItemsCount());
		modelAndView.addObject("registedItems" , itemsession.getRegistedItems());
		modelAndView.addObject("stockOptions" , itemsession.getStockOptions());
		return modelAndView;
		
		
	}
	
	@RequestMapping(value = "/ec/purchaseConfirm" , method = RequestMethod.GET)
	public ModelAndView purchaseConfirm(@RequestParam(value = "count") int[] itemsCount) {
		ModelAndView modelAndView = new ModelAndView();
		List<ItemsEntity> registedItems = itemsession.getRegistedItems();
		modelAndView.addObject("registedItems" , registedItems);
		
		int totalCount = itemsession.getRegistedItems().size();
		int totalPrice = 0;
		int i = 0;
		for(ItemsEntity registedItem : registedItems) {
			totalPrice = registedItem.getPrice() * itemsCount[i];
			i++;
		}
		modelAndView.addObject("totalPrice" , totalPrice);
		modelAndView.addObject("totalCount" , totalCount);
		modelAndView.addObject("itemsCount" , itemsCount);
		
		modelAndView.setViewName("purchaseConfirm.html");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/ec/regist" , method = RequestMethod.POST)
	public String registToCart(@RequestParam(name = "count") int count , @RequestParam(name = "itemId") int itemId) {
		
		
		
		itemsession.setRegistedItems(itemsService.getOne(itemId), count);
		
		return "redirect:/ec/items";
	}
	
	@RequestMapping(value = "/ec/delete" , method = RequestMethod.POST)
	public String deleteItems(@RequestParam(name = "itemId") int itemId) {
		int index = 0;
		for(ItemsEntity registedItem : itemsession.getRegistedItems()) {
			if (registedItem.getItemId()==itemId) {
				break;
			}
			index++;
		}
			itemsession.getRegistedItems().remove(index);
			itemsession.getItemsCount().remove(index);
			itemsession.getStockOptions().remove(index);
		
		
		return "redirect:/ec/items";
	}
}
