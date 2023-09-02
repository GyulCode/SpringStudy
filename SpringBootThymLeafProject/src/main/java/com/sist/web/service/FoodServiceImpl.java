package com.sist.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import com.sist.web.dao.FoodMapper;
import com.sist.web.vo.*;

@Service
public class FoodServiceImpl implements FoodService{
	@Autowired
	private FoodMapper mapper;
	
	public List<CategoryVO> categoryListData(){
		return mapper.categoryListData();
	}
}
