package com.sist.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.web.dao.SeoulLocationDAO;
import com.sist.web.entity.FoodEntity;
import com.sist.web.entity.SeoulLocationEntity;

@Controller
public class SeoulController {
	@Autowired
	private SeoulLocationDAO dao;
	
	@RequestMapping("seoul/location")
	public String locationFindData(String page, String fd, Model model) {
		if(fd==null)
			fd="서울";
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
		List<SeoulLocationEntity> list=dao.locationFindData(fd, start);
		int totalpage = dao.locationFindTotalPage(fd);
		
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
		
		
		return "seoul/location";
	}

}
