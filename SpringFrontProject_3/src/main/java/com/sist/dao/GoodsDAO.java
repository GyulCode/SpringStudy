package com.sist.dao;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.GoodsMapper;
import com.sist.vo.*;

@Repository
public class GoodsDAO {
	@Autowired
	private GoodsMapper mapper;
	
	/*@Select("SELECT no, goods_name as name, goods_poster as poster, goods_price as price, num "
	+ "FROM SELECT(no, goods_name, goods_poster, goods_price, rownum as num "
	+ "FROM SELECT(no, goods_name, goods_poster, goods_price "
	+ "FROM goods_all) ORDER BY no ASC)"
	+ "WHERE num BETWEEN #{start} AND #{end}")*/
	//인라인뷰 -> TOP-N : 포트폴리오 -> 인라인뷰 이용한 페이징 기반 사용
	public List<GoodsVO> GoodsListData(Map map){
		return mapper.GoodsListData(map);
	}

	//상세보기 -> Session(장바구니)
	/*@Update("UPDATE GOODS_ALL SET "
		+ "hit = hit+1 "
		+ "WHERE no=#{no}")*/

	/*@Select("SELECT no, goods_name as name, goods_sub as sub, goods_price as price, goods_discount as discount, "
		+ "goods_first_price as first_price, goods_delivery as delivery, goods_poster as poster "
		+ "hit, account "
		+ "FROM goods_all "
		+ "WHERE no=#{no}")*/
	public GoodsVO GoodsDetailData(int no) {
		mapper.hitIncrement(no);
		return mapper.GoodsDetailData(no);
	}
	
	/*@Select("SELECT CEIL(COUNT(*)/12.0) FROM goods_all")*/
	public int goodsTotalPage() {
		return mapper.goodsTotalPage();
	}
	

}
