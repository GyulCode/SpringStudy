package com.sist.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.GoodsMapper;
import com.sist.vo.*;

@Repository
public class GoodsDAO {
	@Autowired
	private GoodsMapper mapper;
	
	public List<GoodsVO> goodsListData(Map map){
		return mapper.goodsListData(map);
	}
	
	public GoodsVO goodsDetailData(int no) {
		mapper.hitIncrement(no);
		return mapper.goodsDetailData(no);
	}
	
	public int goodsTotalPage() {
		return mapper.goodsTotalPage();
	}

}
