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
	
	@GetMapping(value="food/food_category_vue.do", produces="text/plain;charset=UTF-8")
	public String food_category(int cno) throws Exception{
		Map map=new HashMap();
		map.put("cno", cno);
		List<CategoryVO> list=dao.foodCategoryListData(map);
		ObjectMapper mapper=new ObjectMapper();
		
		String json=mapper.writeValueAsString(list); //Á¦ÀÌ½¼ Àè½¼À¸·Î º¯°æ??
		return json;
		
	}
	
	@GetMapping(value="food/food_category_info_vue.do", produces="text/plain;charset=UTF-8")
	public String food_category_info(int cno) throws Exception{
		CategoryVO vo=dao.foodCategoryInfoData(cno);
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(vo);
		return json;
	}
	
	@GetMapping(value="food/food_list_vue.do", produces="text/plain;charset=UTF-8")
	public String food_list(int cno) throws Exception{
		
		List<FoodVO> list=dao.foodListData(cno);
		System.out.println(list);
		for(FoodVO vo:list) {
			String poster=vo.getPoster();
			poster=poster.substring(0,poster.indexOf("^"));
			poster=poster.replace("#", "&");
			vo.setPoster(poster);
			
			String address=vo.getAddress();
			address=address.substring(0,address.indexOf("Áö¹ø"));
			vo.setAddress(address);;
		}
		
		ObjectMapper mapper=new ObjectMapper(); //Àè½¼¹ý ÀÌÀüÀº array
		String json=mapper.writeValueAsString(list);
		return json;
	}
	

}
