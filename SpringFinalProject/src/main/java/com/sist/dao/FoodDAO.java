package com.sist.dao;

import java.util.*;
import com.sist.vo.*;
import com.sist.mapper.*;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FoodDAO {
	
	@Autowired
	FoodMapper mapper;
	
	public List<CategoryVO> foodCategoryListData(){
		return mapper.foodCategoryListData();
	}
	
	public CategoryVO foodCategoryInfoData(int cno) {
		return mapper.foodCategoryInfoData(cno);
	}
	
	public List<FoodVO> foodFindData(Map map){
		return mapper.foodFindData(map);
	}
	public int foodFindTotalPage(Map map) {
		return mapper.foodFindTotalPage(map);
	}
	
	/*@Select("SELECT fno, name, tel, address, type, time, parking, menu, price, score "
			+ "FROM food_location "
			+ "WHERE fno=#{fno}")*/
	public FoodVO foodDetailData(int fno) {
		return mapper.foodDetailData(fno);
	}

}
