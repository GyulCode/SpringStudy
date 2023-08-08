package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;


@Controller
public class FoodController {
	@Autowired
	private FoodDAO dao;
	
	@GetMapping("food/food_list.do")
	public String food_list(int cno, Model model) {
		List<FoodVO> list=dao.foodListData(cno);
		for(FoodVO vo:list) {
			String address=vo.getAddress();
			address=address.substring(0,address.lastIndexOf("����"));
			vo.setAddress(address);
			
			String poster=vo.getPoster();
			poster=poster.substring(0,poster.indexOf("^"));
			poster=poster.replace("#", "&");
			vo.setPoster(poster);
			
		}
		CategoryVO cvo=dao.foodCategoryInfoData(cno);
		model.addAttribute("cvo",cvo);
		model.addAttribute("list",list);
		return "food/food_list";
	}
	
	@GetMapping("food/food_find.do")
	public String food_find(String fd, String page, Model model) {
		if(fd==null)
			fd="����";
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		//dao����
		Map map=new HashMap();
		int rowsize=20;
		int start=(rowsize*curpage)-(rowsize-1);
		int end=rowsize*curpage;
		
		map.put("start", start);
		map.put("end", end);
		map.put("fd", fd);
		List<FoodVO> list=dao.foodFindData(map);
		for(FoodVO vo:list) {
			String poster=vo.getPoster();
			poster=poster.substring(0,poster.indexOf("^"));
			poster=poster.replace("#", "&");
			vo.setPoster(poster);
		}
		int totalPage=dao.foodFindTotalPage(fd);

		//��� ������
		final int BLOCK=5;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		
		if(endPage>totalPage)
			endPage=totalPage;
		
		model.addAttribute("curpage",curpage);
		model.addAttribute("totalPage",totalPage);
		model.addAttribute("startPage",startPage);
		model.addAttribute("endPage",endPage);
		model.addAttribute("list",list);
		model.addAttribute("fd",fd);
			
		return "food/food_find";
	}
	
	
	
	

}
