package com.sist.dao;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import java.util.*;
import com.sist.vo.*;
public class LocationDAO extends SqlSessionDaoSupport {
	/*
	 * <sql id="select-sql">
 		SELECT no, title, address, msg
 		FROM seoul_location
 	</sql>
 	
 	<select id="LocationListData" resultType="LocationVO" >
 		<include refid="select-sql"></include>
 		ORDER BY no ASC
 	</select>
 	<select id="LocationDetailData" resultType="LocationVO" parameter>
 		<include refid="select-sql"></include>
 		WHERE no=#{no}
 	</select>
	 */
	
	public List<SeoulLocationVO> LocationListData(){
		return getSqlSession().selectList("LocationListData");
	}
	
	public SeoulLocationVO LocationDetailData(int no) {
		return getSqlSession().selectOne("LocationDetailData",no);
	}
	

}
