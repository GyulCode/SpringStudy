package com.sist.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;




//SqlSessionDaoSupport �ȿ� �������丮�� ���ԵǾ� �ְ� ���� ���丮�� sql���� �����ؼ� ����� �޾ƿ�
public class SeoulDAO extends SqlSessionDaoSupport{
	
	/*
	 * <select id="seoulListData" resultType="SeoulLocationVO" parameterType="hashmap"> <!-- java.util.Map �Ѵ� ����-->
	 		SELECT no, title, address, num 
	 		FROM (SELECT no, title, address, rownum as num
	 		FROM (no, title, address
	 		FROM seoul_location ORDER BY no ASC))
	 		WHERE num BETWEEN #{start} AND #{end}
	 	</select>
	 */
	public List<SeoulLocationVO> seoulListData(Map map){
		return getSqlSession().selectList("seoulListData",map);
	}
	/*
	 <select id="seoulTotalPage" resultType="int">
 		SELECT CEIL(CONUT(*)/10.0) 
 		FROM seoul_location
 	 </select>
	 */
	public int seoulTotalPage() {
		return getSqlSession().selectOne("seoulTotalPage");
	}
	
	/*
	 <select id="seoulFindData" resultType="seoulLocationVO" parameterType="String"><!-- ���� String�� ��ҹ��� ����x  -->
 		SELECT no, title, address, msg
 		FROM seoul_location
 		WHERE title LIKE '%'||#{title}||'%'
 	</select>
	 */
	public List<SeoulLocationVO> seoulFindData(String title){
		return getSqlSession().selectList("seoulFindData",title);
	}

}
