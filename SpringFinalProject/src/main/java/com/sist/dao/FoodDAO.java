package com.sist.dao;

import java.util.*;
import com.sist.vo.*;
import com.sist.mapper.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.vo.CategoryVO;

@Repository
public class FoodDAO {
	
	@Autowired
	FoodMapper mapper;
	
	public List<CategoryVO> foodCategoryListData(){
		return mapper.foodCategoryListData();
	}

}
