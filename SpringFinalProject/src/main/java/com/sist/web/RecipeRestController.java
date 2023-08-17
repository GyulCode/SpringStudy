package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.dao.RecipeDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.dao.*;

import java.text.DecimalFormat;
import java.util.*;
import com.sist.vo.*;


@RestController
public class RecipeRestController {

	@Autowired
	private RecipeDAO dao;
	
	@GetMapping(value="recipe/recipe_list_vue.do", produces="text/plain;charset=UTF-8")
	public String recipe_list(int page) throws Exception {
		Map map=new HashMap();
		int rowSize=20;
		int start=(rowSize*page)-(rowSize-1);
		int end=rowSize*page;
		
		map.put("start", start);
		map.put("end", end);
		
		List<RecipeVO> list=dao.recipeListData(map);
		ObjectMapper obj=new ObjectMapper();
		String json= obj.writeValueAsString(list);
		return json;
		// ¿ËΩº ∂Û¿Ã∫Í∑Ø∏Æ
		
	}
	
	@GetMapping(value="recipe/page_list_vue.do", produces="text/plain;charset=UTF-8")
	public String page_list(int page) throws Exception{
		int count=dao.recipeRowCount();
		int totalpage=(int)(Math.ceil(count/20.0));
		DecimalFormat df=new DecimalFormat("###,###,###");
		String strCount=df.format(count);
		
		final int BLOCK=10;
		int startPage=((page-1)/BLOCK*BLOCK)+1;
		int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
		
		PageVO vo= new PageVO();
		vo.setTotalpage(totalpage);
		vo.setCurpage(page);
		vo.setStartPage(startPage);
		vo.setEndPage(endPage);
		vo.setCount(strCount);
				
		ObjectMapper obj=new ObjectMapper();
		String json= obj.writeValueAsString(vo);
		return json;
	}
	
	@GetMapping(value="recipe/chef_list_vue.do" , produces="text/plain;charset=UTF-8")
	public String chef_list(int page) throws Exception {
		Map map=new HashMap();
		int rowSize=20;
		int start=(rowSize*page)-(rowSize-1);
		int end=rowSize*page;
		
		map.put("start", start);
		map.put("end", end);
		
		List<ChefVO> list=dao.chefListData(map);
		
		ObjectMapper obj=new ObjectMapper();
		String json= obj.writeValueAsString(list);
		return json;
		// ¿ËΩº ∂Û¿Ã∫Í∑Ø∏Æ
		
	}
	
	@GetMapping(value="recipe/chef_page_vue.do", produces="text/plain;charset=UTF-8")
	public String chef_page_list(int page) throws Exception{
		
		int totalpage=dao.chefTotalPage();
		
		final int BLOCK=10;
		int startPage=((page-1)/BLOCK*BLOCK)+1;
		int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
		
		PageVO vo= new PageVO();
		vo.setTotalpage(totalpage);
		vo.setCurpage(page);
		vo.setStartPage(startPage);
		vo.setEndPage(endPage);
				
		ObjectMapper obj=new ObjectMapper();
		String json= obj.writeValueAsString(vo);
		return json;
	}
	
	@GetMapping(value="recipe/chef_info_vue.do", produces="text/plain;charset=UTF-8")
	public String chef_info(int page) throws Exception{
		ChefVO vo=dao.chefInfoData(chef);
		ObjectMapper obj=new ObjectMapper();
		String json= obj.writeValueAsString(vo);
		return json;
	}
	
	
	
}
