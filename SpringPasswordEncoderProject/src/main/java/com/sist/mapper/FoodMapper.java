package com.sist.mapper;

import java.util.*;
import java.util.Locale.Category;

import com.sist.vo.CategoryVO;
import com.sist.vo.FoodVO;

public interface FoodMapper {
	public List<CategoryVO> foodCategoryListData(Map map);


	//<select id="foodCategoryInfoData" resultType="CategoryVO" parameterType="int">
	public CategoryVO foodCategoryInfoData(int cno);
	
	//<select id="foodListData" resultType="FoodVO" parameterType="int">
	public List<FoodVO> foodListData(int cno);
	
	//<select id="foodDetailData" resultType="FoodVO" parameterType="int">
	public FoodVO foodDetailData(int fno);

}
