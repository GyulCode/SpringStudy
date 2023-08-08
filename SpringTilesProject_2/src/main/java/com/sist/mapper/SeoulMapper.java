package com.sist.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import com.sist.vo.SeoulVO;

public interface SeoulMapper {
	@Select(value="{CALL seoulListData(#{pNo, mode=IN, javaType=java.lang.Integer},#{pStart, mode=IN, javaType=java.lang.Integer},#{pEnd, mode=IN, javaType=java.lang.Integer},#{pResult, mode=OUT, jdbcType=CURSOR, resultMap=seoulMap})}")
	@Options(statementType = StatementType.CALLABLE)
	public List<SeoulVO> seoulListData(Map map);
	
	@Select(value="{CALL seoulTotalPage(#{pNo, mode=IN, javaType=java.lang.Integer},#{pTotal, mode=OUT, jdbcType=INTEGER})}")
	@Options(statementType = StatementType.CALLABLE)
	public Integer seoulTotalPage(Map map);
	
	/*<select id="seoulDetailData" resultType="com.sist.vo.SeoulVO" parameterType="hahsmap">
		SELECT no, title, poster, msg, address
		FROM ${total_name}
		WHERE no=#{no}
	</select>*/
	public SeoulVO seoulDetailData(Map map);


}
