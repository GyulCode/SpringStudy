package com.sist.dao;

import java.util.*;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.*;
import com.sist.vo.*;

@Repository
public class FoodDAO {
	@Autowired
	private FoodMapper mapper;
	
	// ��� -> �󼼺������ �ѹ���
	/*@Select("SELECT fno, name, poster, score, num "
			+ "FROM (SELECT fno, name, poster, score, rownum as num"
			+ "FROM (SELECT fno, name, poster, score "
			+ "FROM food_location ORDER BY fno ASC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")*/
	public List<FoodLocationVO> foodListData(Map map){
		return mapper.foodListData(map);
	}
	/*@Select("SELECT CEIL(COUNT(*)/12.0) FROM food_location")*/
	public int foodTotalPage() {
		return mapper.foodTotalPage();
	}
	
	
	// ��
	/*@Select("SELECT fno, name, poster, socre, parking, time, type, address, phone, price, menu "
			+ "FROM food_location "
			+ "WHERE fno=#{fno}")*/
	public FoodLocationVO foodDetailData(int fno) {
		return mapper.foodDetailData(fno);
	}
	
	// �˻� => Vue/React()
	/*@Select("SELECT fno, name, poster, score, num "
			+ "FROM (SELECT fno, name, poster, score, rownum as num"
			+ "FROM (SELECT fno, name, poster, score "
			+ "FROM food_location ORDER BY fno ASC)) "
			+ "WHERE address LIKE '%'||#{address}||'%'")*/
	public List<FoodLocationVO> foodFindData(Map map){
		return mapper.foodFindData(map);
	}
	
}
