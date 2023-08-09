package com.sist.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.dao.FoodDAO;
import com.sist.vo.CategoryVO;
import com.sist.vo.FoodVO;

@RestController
public class FoodRestController {
	@Autowired
	private FoodDAO dao;
	
	@GetMapping(value="food/category.do",produces="text/plain;charset=UTF-8")
	public String food_category(int cno) throws Exception
	{
		Map<String, Object> map= new HashMap();
		map.put("cno", (int)cno);
		List<CategoryVO> list=dao.foodCategoryListData(map);
		ObjectMapper mapper= new ObjectMapper();
		String json=mapper.writeValueAsString(list);
		return json;
	}
	
	@GetMapping(value="food/food_list.do",produces="text/plain;charset=UTF-8")
	public String food_list(int cno) throws Exception
	{
		List<FoodVO> list=dao.foodListData(cno);
		for(FoodVO vo:list) {
			String poster=vo.getPoster();
			poster=poster.substring(0,poster.indexOf("^"));
			poster=poster.replace("#", "&");
			vo.setPoster(poster);
			
			String address=vo.getAddress();
			address=address.substring(0,address.indexOf("Áö¹ø"));
			vo.setAddress(address);
		}
		
		ObjectMapper mapper= new ObjectMapper();
		String json=mapper.writeValueAsString(list);
		return json;
	}
	

}
