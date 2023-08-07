package com.sist.web;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.dao.CompanyDAO;
import com.sist.vo.*;
import java.util.*;

@RestController
public class CompanyRestController {
	@Autowired
	private CompanyDAO dao;
	
	@GetMapping(value = "company/list_vue.do", produces = "text/plain;charset=UTF-8")
	public String company_list_vue(String page)
	{
		String result="";
		try
		{
			if(page==null)
				page="1";
			int curpage=Integer.parseInt(page);
			Map map=new HashMap();
			map.put("start", (curpage*12)-11);
			map.put("end", curpage*12);
			List<CompanyVO> list=dao.companyListData(map);
			int totalpage=dao.companyTotalPage();
			
			// List => [] 변환 필요 ==> JSONArray
			// FoodLocationVO => {} ==> JSONObject
			// [{},{},{}, ...]
			JSONArray arr=new JSONArray();
			int i=0;
			// fno,name,psoter,score
			/*
			 	{fno:1,name:"",poster:"",score:5.0} ...
			 */
			
			for(CompanyVO vo:list)
			{
				JSONObject obj=new JSONObject();
				double star = Math.round(((double)vo.getCom_star_sum()/(double)vo.getCom_star_cnt())*100)/100;
				
				obj.put("cno", vo.getCom_id());
				obj.put("name", vo.getCom_name());
				obj.put("poster", vo.getPoster());
				obj.put("star", star);
				
				if(i==0)
				{
					obj.put("curpage", curpage);
					obj.put("totalpage",totalpage);
				}
				
				arr.add(obj);
				i++;
			}
			result=arr.toJSONString();
			
		}catch(Exception ex) {}
		return result;
	}
	
}