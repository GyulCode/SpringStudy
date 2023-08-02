package com.sist.mapper2;

import java.util.*;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.sist.vo.EmpVO;
public interface EmpDeptMapper {
	
	/*
	 * <resultMap type="com.sist.vo.EmpVO" id="empMap"> <!-- �÷��� ������ ��Ī = resultMap -->
	  	<result column="dname" property="dvo.dname"/> <!-- ������  -->
	  	<result column="loc" property="dvo.loc"/>
	   </resultMap>
	 *  �̰� ������̼� �������� �ٲٴ°��� JOIN / emp-mapper.xml����
	 */
	@Results({
		@Result(column = "dname", property="dvo.dname"),
		@Result(column = "loc", property="dvo.loc")
	})
	
	
	/*
	 *   <sql id="select-emp">
  	SELECT empno, ename, job, TO_CHAR(hiredate,'YYYY-MM-DD') ad dbday, sal, dname, loc
  	FROM emp, dept
  	WHERE emp.deptno=dept.deptno
  </sql>
  
  <select id="empdeptListData" resultMap="empMap" > <!-- statementType="STATEMENT"  -->
	<include refid="select-emp"/>
  	ORDER BY sal DESC
  </select>
  
  <select id="empdeptDetailData" resultMap="empMap" parameterType="int"> 
	<include refid="select-emp"/>
  	AND empno=#{empno}
  </select>
	 */
	
	@Select("SELECT empno, ename, job, TO_CHAR(hiredate,'YYYY-MM-DD') as dbday, sal, dname, loc "
			+ "FROM emp, dept "
			+ "WHERE emp.deptno=dept.deptno "
			+ "ORDER BY sal DESC")
	public List<EmpVO> empdeptListData();
	
	@Results({
		@Result(column = "dname", property="dvo.dname"),
		@Result(column = "loc", property="dvo.loc")
	})
	
	@Select("SELECT empno, ename, job, TO_CHAR(hiredate,'YYYY-MM-DD') as dbday, sal, dname, loc "
			+ "FROM emp, dept "
			+ "WHERE emp.deptno=dept.deptno "
			+ "AND empno=#{empno}")
	public EmpVO empdeptDetailData(int empno);

}
