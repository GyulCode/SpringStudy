package com.sist.dao;

import java.util.List;
import java.util.Map;
import java.util.*;
import com.sist.mapper.*;
import com.sist.vo.*;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.RecipeMapper;

@Repository
public class RecipeDAO {
	
	@Autowired
	private RecipeMapper mapper;
	
	public List<RecipeVO> recipeListData(Map map){
		return mapper.recipeListData(map);
	}
	
	//@Select("SELECT COUNT(*) FROM recipe")
	public int recipeRowCount() {
		return mapper.recipeRowCount();
	}
	
	public List<ChefVO> chefListData(Map map){
		return mapper.chefListData(map);
	}

	public int chefTotalPage() {
		return mapper.chefTotalPage();
	}
	
	
	public ChefVO chefInfoData(String chef) {
		return mapper.chefInfoData(chef);
	}

}
