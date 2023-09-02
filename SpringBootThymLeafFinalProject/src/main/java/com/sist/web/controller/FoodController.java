package com.sist.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.web.dao.FoodDAO;
import com.sist.web.entity.FoodEntity;
import com.sist.web.entity.SeoulEntity;

@Controller
@RequestMapping("food/")
public class FoodController {
	@Autowired
	private FoodDAO dao;
	
	@RequestMapping("food_find")
	public String food_find(String page, String fd, Model model) {
		if(fd==null)
			fd="마포";
		if(page==null)
			page ="1";
		
		int curpage=Integer.parseInt(page);
		int rowsize=20;
		int start=(rowsize*curpage)-rowsize;  //mySQL은 0번부터 시작한다는걸 주의한다.
		int end=(rowsize*curpage);
		List<FoodEntity> list=dao.foodFindData(fd, start);
		int totalpage=dao.foodFindTotalPage(fd);
		
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		
		if(endPage>totalpage)
			endPage=totalpage;

		model.addAttribute("curpage",curpage);
		model.addAttribute("endPage",endPage);
		model.addAttribute("startPage",startPage);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("fd",fd);
		model.addAttribute("list",list);
		model.addAttribute("main_html","food/food_find");
		return "main";
	}

}
