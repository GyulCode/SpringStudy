package com.sist.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.GoodsVO;

/*
 * NO                NOT NULL NUMBER         
GOODS_NAME        NOT NULL VARCHAR2(1000) 
GOODS_SUB                  VARCHAR2(1000) 
GOODS_PRICE       NOT NULL VARCHAR2(50)   
GOODS_DISCOUNT             NUMBER         
GOODS_FIRST_PRICE          VARCHAR2(20)   
GOODS_DELIVERY    NOT NULL VARCHAR2(20)   
GOODS_POSTER               VARCHAR2(260)  
HIT                        NUMBER         
ACCOUNT                    NUMBER  
 */
public interface GoodsMapper {
	
	@Select("SELECT no, goods_name as name, goods_poster as poster, goods_price as price, num "
			+ "FROM ( SELECT no, goods_name, goods_poster, goods_price, rownum as num "
			+ "FROM ( SELECT no, goods_name, goods_poster, goods_price "
			+ "FROM goods_all) ORDER BY no ASC) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<GoodsVO> goodsListData(Map<String, Object> map);
	
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM goods_all")
	public int goodsTotalPage();
	
	@Update("UPDATE GOODS_ALL SET "
			+ "hit = hit+1 "
			+ "WHERE no=#{no}")
	public void hitIncrement(int no);
	@Select("SELECT no, goods_name as name, goods_sub as sub, goods_price as price, goods_discount as discount, "
			+ "goods_first_price as first_price, goods_delivery as delivery, goods_poster as poster, "
			+ "hit, account "
			+ "FROM goods_all "
			+ "WHERE no=#{no}")
	public GoodsVO goodsDetailData(int no);
	

}
