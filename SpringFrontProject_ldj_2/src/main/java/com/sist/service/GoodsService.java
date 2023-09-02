package com.sist.service;

import java.util.List;
import java.util.Map;

import com.sist.vo.GoodsVO;

public interface GoodsService {
	public List<GoodsVO> goodslistData(Map<String, Object> map);
	
	public GoodsVO goodsDetailData(int no);
	
	public int goodsTotalPage();

}
