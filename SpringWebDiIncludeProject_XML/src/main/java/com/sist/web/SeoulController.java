package com.sist.web;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sist.dao.SeoulDAO;
import com.sist.mapper.*;
import com.sist.vo.*;

@Controller
public class SeoulController {
	
	@Autowired
	private SeoulDAO dao;
	
	@GetMapping("seoul/location.do") //
	public String seoul_location(String page, Model model) {
		if(page==null)
			page="1";
		
		int curpage=Integer.parseInt(page);
		int rowsize=12;
		int start=(rowsize*curpage)-(rowsize-1);
		int end=rowsize*curpage;
		
		Map map=new HashMap();
		map.put("start", start);
		map.put("end",end);
		List<SeoulVO> list=dao.seoulLocationListData(map);
		int totalpage=dao.seoulLocationTotalPage();
		
		 final int BLOCK=5;
		 int startpage=((curpage-1)/BLOCK*BLOCK)+1;
		 int endpage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		
		if(endpage>totalpage)
			endpage=totalpage;
		
		model.addAttribute("curpage",curpage);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("startpage",startpage);
		model.addAttribute("endpage",endpage);
		model.addAttribute("list",list);
		
		model.addAttribute("main_jsp","../seoul/location.jsp");
		
		return "main/main";
	}
	
	@GetMapping("seoul/nature.do")
	public String seoul_nature(String page, Model model) {
		if(page==null)
			page="1";
		
		int curpage=Integer.parseInt(page);
		int rowsize=9;
		int start=(rowsize*curpage)-(rowsize-1);
		int end=rowsize*curpage;
		
		Map map=new HashMap();
		map.put("start", start);
		map.put("end",end);
		List<SeoulVO> list=dao.seoulNatureListData(map);
		int totalpage=dao.seoulNatureTotalPage();
		
		 final int BLOCK=6;
		 int startpage=((curpage-1)/BLOCK*BLOCK)+1;
		 int endpage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		
		if(endpage>totalpage)
			endpage=totalpage;
		
		model.addAttribute("curpage",curpage);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("startpage",startpage);
		model.addAttribute("endpage",endpage);
		model.addAttribute("list",list);
		
		model.addAttribute("main_jsp","../seoul/nature.jsp");
		
		return "main/main";
	}	
	
	@GetMapping("seoul/shop.do")
	public String seoul_shop(String page, Model model) {
		if(page==null)
			page="1";
		
		int curpage=Integer.parseInt(page);
		int rowsize=12;
		int start=(rowsize*curpage)-(rowsize-1);
		int end=rowsize*curpage;
		
		Map map=new HashMap();
		map.put("start", start);
		map.put("end",end);
		List<SeoulVO> list=dao.seoulShopListData(map);
		int totalpage=dao.seoulShopTotalPage();
		
		 final int BLOCK=5;
		 int startpage=((curpage-1)/BLOCK*BLOCK)+1;
		 int endpage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		
		if(endpage>totalpage)
			endpage=totalpage;
		
		model.addAttribute("curpage",curpage);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("startpage",startpage);
		model.addAttribute("endpage",endpage);
		model.addAttribute("list",list);
		
		model.addAttribute("main_jsp","../seoul/shop.jsp");
		
		return "main/main";
	}	
	

}
