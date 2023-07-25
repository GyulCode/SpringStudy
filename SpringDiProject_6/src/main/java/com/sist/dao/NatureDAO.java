package com.sist.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sist.vo.SeoulNatureVO;

public class NatureDAO extends SqlSessionDaoSupport{
	/*
	 * <select id="natureListData" resultType="SeoulNatureVO">
 		SELECT no, title, address, msg, rownum
 		FROM (SELECT no, title, address, msg FROM seoul_nature ORDER BY no ASC)
 		WHERE rownum &lt;=10 
 		</select>
	 */
	public List<SeoulNatureVO> natureListData(){
		return getSqlSession().selectList("natureListData");
	}

}
