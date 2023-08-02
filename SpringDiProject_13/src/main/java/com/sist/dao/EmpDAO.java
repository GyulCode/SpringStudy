package com.sist.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sist.vo.EmpVO;

/*
 * ������ service(�������̽�)���ļ� �����ϴ°� �Ϲ���(looselyĿ�ø�) -> serviceImpl -> repository
 */
public class EmpDAO extends SqlSessionDaoSupport{
	
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
	
	public List<EmpVO> empdeptListData(){
		return getSqlSession().selectList("empdeptListData");
	}
	
	public EmpVO empdeptDetailData(int empno) {
		return getSqlSession().selectOne("empdeptDetailData",empno);
	}
	
	
	

}
