package com.sist.web;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

import com.mongodb.util.JSON;
import com.sist.service.*;
import com.sist.vo.*;

@RestController
public class GoodsRestController {
	@Autowired
	private GoodsService service;
	
	@GetMapping(value="goods/list_vue.do",produces="text/plain;charset=UTF-8")
	public String goods_list(int page) {
		String result="";
		try {
			int curpage=page;
			Map map=new HashMap();
			map.put("start", (curpage*12)-11);
			map.put("end", curpage*12);
			List<GoodsVO> list=service.GoodsListData(map);
			int totalpage=service.goodsTotalPage();
			
			//블록 처리

			final int BLOCK=10;
			int startPage=((curpage-1)/BLOCK*BLOCK)+1;
			int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
			
			if(endPage>totalpage) {
				endPage=totalpage;
			}
			
			int i=0;
			JSONArray arr=new JSONArray();
			for(GoodsVO vo:list) {
				JSONObject obj=new JSONObject();
				
				obj.put("no", vo.getNo());
				obj.put("name", vo.getName());
				obj.put("poster", vo.getPoster());
				obj.put("price", vo.getPrice());
				
				if(i==0) { //JSONArray=>0
					obj.put("curpage", curpage);
					obj.put("totalpage", totalpage);
					obj.put("startPage", startPage);
					obj.put("endPage", endPage);
					
				}
				arr.add(obj);
				i++;
			}
				result=arr.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	@GetMapping(value="goods/detail_vue.do",produces = "text/plain;charset=UTF-8")
	public String goods_detail(int no) {
		String result="";
		try {
			GoodsVO vo=service.GoodsDetailData(no);
			JSONObject obj=new JSONObject();
			//{} -> vo
			obj.put("no", vo.getNo());
			obj.put("sub", vo.getSub());
			obj.put("name", vo.getName());
			obj.put("poster", vo.getPoster());
			obj.put("price", vo.getPrice());
			obj.put("fp", vo.getFirst_price());
			obj.put("discount", vo.getDiscount());
			obj.put("delivery", vo.getDelivery());
			obj.put("account", vo.getAccount());
			String rp=vo.getPrice().replaceAll("[^0-9]",""); //숫자빼고 나머지 공백
			System.out.println("rp : "+rp);
			obj.put("rp", Integer.parseInt(rp));
			result=obj.toJSONString();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	
}
