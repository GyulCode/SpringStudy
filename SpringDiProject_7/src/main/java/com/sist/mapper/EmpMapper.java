package com.sist.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.*;
public interface EmpMapper {
	/*
	 * <select id="empListData" resultType="empVO" parameterType="int">
	 *    SELECT~
	 * </select>
	 * 
	 * 이걸 이제 Annotation으로 바꿔서
	 * id=Method명 중복x
	 * resultType -> 리턴형
	 * parameterType-> 매개변수
	 * 
	 *  public list<empVO> empListData(int empno)와 같은 형식으로 나옴
	 */
	@Select("SELECT empno, ename, job, TO_CHAR(hiredate,'YYYY-MM-DD') as dbday,"
			+ "TO_CHAR(sal,'$999,999')as dbsal, deptno, comm "
			+ "FROM emp")
	public List<EmpVO> empListData();
	
	@Select("SELECT empno, ename, job, mgr,TO_CHAR(hiredate,'YYYY-MM-DD') as dbday,"
			+ "TO_CHAR(sal,'$999,999')as dbsal, NVL(comm,0) as comm,deptno "
			+ "FROM emp "
			+ "WHERE empno=#{empno}")
	public EmpVO empDetailData(int empno);
	
	@Select("SELECT empno, ename, job, mgr,TO_CHAR(hiredate,'YYYY-MM-DD') as dbday,"
			+ "TO_CHAR(sal,'$999,999')as dbsal, NVL(comm,0) as comm,deptno "
			+ "FROM emp "
			+ "WHERE ename LIKE '%'||#{ename}||'%'")
	public List<EmpVO> empDetailSearch(String name);
	
	

}
