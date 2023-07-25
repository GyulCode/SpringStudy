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
	 * �̰� ���� Annotation���� �ٲ㼭
	 * id=Method�� �ߺ�x
	 * resultType -> ������
	 * parameterType-> �Ű�����
	 * 
	 *  public list<empVO> empListData(int empno)�� ���� �������� ����
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
