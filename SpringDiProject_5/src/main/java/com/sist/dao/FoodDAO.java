package com.sist.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

public class FoodDAO extends SqlSessionDaoSupport{
	/*
	 * 
 	<select id="foodFindData" resultType="foodVO" parameterType="String"><!-- 여기 String은 대소문자 구분x  -->
 		SELECT cno, title, subject, poster, link
 		FROM food_category
 		WHERE address LIKE '%'||#{address}||'%'
 	</select>
	 */
	
	/*
	 * <select id="foodListData" resultType="FoodVO" parameterType="hashmap"> <!-- java.util.Map 둘다 가능-->
 		SELECT cno, title, subject, poster, link 
 		FROM (SELECT cno, title, subject, poster, link, rownum as num
 		FROM (SELECT cno, title, subject, poster, link
 		FROM food_category ORDER BY cno ASC))
 		WHERE num BETWEEN #{start} AND #{end}
 	</select>
	 */
	public List<FoodVO> foodListData(Map map){
		return getSqlSession().selectList("foodListData",map);
	}
	
	/*
	 * <select id="foodTotalPage" resultType="int">
 		SELECT CEIL(COUNT(*)/10.0) 
 		FROM food_category
 	</select>
	 */
	public int foodTotalPage() {
		return getSqlSession().selectOne("foodTotalPage");
	}
	
	
	
	

}
