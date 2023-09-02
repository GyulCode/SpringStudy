package com.sist.web.dao;


import java.util.*;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.sist.web.vo.*;


@Repository
@Mapper
//mapperFactoryBean
public interface FoodMapper {
	public List<CategoryVO> categoryListData();

}
