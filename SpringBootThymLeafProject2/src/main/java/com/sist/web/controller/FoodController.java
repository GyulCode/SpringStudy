package com.sist.web.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//import com.sist.web.service.*;
//import com.sist.web.vo.*;
import com.sist.web.entity.*;
import com.sist.web.dao.*; 

@Controller
public class FoodController {
	@Autowired
	//private FoodService service;
	private FoodDAO dao;
	
	//임의 실행시 get방식 검색해서 넘길때는 post 동시에 사용할려고 requestmapping
	@RequestMapping("food/find")
	public String foodFindData(String page, String fd, Model model) {
		
		if(fd==null)
			fd="마포";
		if(page==null)
			page="1";
		
		int curpage=Integer.parseInt(page);
		int rowSize=12;
		int start=(rowSize*curpage)-(rowSize);
		int end=(rowSize*curpage);
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("start", start);
		map.put("address", fd);
		
		//List<FoodVO> list=service.foodFindData(map);
		//int totalpage = service.foodFindTotalPage(fd);
		List<FoodEntity> list=dao.foodFindData(fd, start);
		int totalpage = dao.foodFindTotalPage(fd);
		
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		
		if(endPage>totalpage)
			endPage=totalpage;
		
		model.addAttribute("curpage",curpage);
		model.addAttribute("endPage",endPage);
		model.addAttribute("startPage",startPage);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("list",list);
		model.addAttribute("fd",fd);
		
		
		return "food/find";
	}
	
	@GetMapping("food/detail")
	public String food_detail(int fno, Model model) {
		FoodEntity vo =dao.findByFno(fno);
		String poster= vo.getPoster();
		String[] temp=poster.split("\\^");
		List<String> plist= Arrays.asList(temp);
				
		model.addAttribute("plist",plist);
		model.addAttribute("vo",vo);
		return "food/detail";
	}

}
