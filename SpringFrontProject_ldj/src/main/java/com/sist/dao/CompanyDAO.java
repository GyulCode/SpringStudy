package com.sist.dao;

import java.util.*;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.*;
import com.sist.vo.*;

@Repository
public class CompanyDAO {
	@Autowired
	private CompanyMapper mapper;
	
	// 목록 -> 상세보기까지 한번에
	/*@Select("SELECT com_id, com_name, poster,time num "
		  + "FROM (SELECT com_id, com_name, poster,time,rownum as num "
		  + "FROM (SELECT com_id, com_name, poster,time "
		  + "FROM company ORDER BY com_id ASC)) "
		  + "WHERE num BETWEEN #{start} AND #{end}")*/
	public List<CompanyVO> companyListData(Map map){
		return mapper.companyListData(map);
	}
	/*@Select("SELECT CEIL(COUNT(*)/12.0) FROM company")*/
	public int companyTotalPage() {
		return mapper.companyTotalPage();
	}
	
	
	// 상세
	/*@Select("SELECT fno, name, poster, socre, parking, time, type, address, phone, price, menu "
			+ "FROM food_location "
			+ "WHERE fno=#{fno}")*/
	public CompanyVO companyDetailData(int fno) {
		return mapper.companyDetailData(fno);
	}
	
	// 검색 => Vue/React()
	/*@Select("SELECT fno, name, poster, score, num "
			+ "FROM (SELECT fno, name, poster, score, rownum as num"
			+ "FROM (SELECT fno, name, poster, score "
			+ "FROM food_location ORDER BY fno ASC)) "
			+ "WHERE address LIKE '%'||#{address}||'%'")*/
	public List<CompanyVO> companyFindData(Map map){
		return mapper.companyFindData(map);
	}
	
}
