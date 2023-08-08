package com.sist.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.dao.GoodsDAO;
import com.sist.vo.GoodsVO;

@Service
public class GoodsServiceImpl implements GoodsService{
	@Autowired
	private GoodsDAO dao;

	@Override
	public List<GoodsVO> GoodsListData(Map map) {
		// TODO Auto-generated method stub
		return dao.GoodsListData(map);
	}

	@Override
	public GoodsVO GoodsDetailData(int no) {
		// TODO Auto-generated method stub
		return dao.GoodsDetailData(no);
	}

	@Override
	public int goodsTotalPage() {
		// TODO Auto-generated method stub
		return dao.goodsTotalPage();
	}
	
	
	
	
	

}
