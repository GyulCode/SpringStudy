package com.sist.dao2;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper2.EmpDeptMapper;
import com.sist.vo.*;

@Repository
public class EmpDAO {
	@Autowired //반객체에서 empDeptMapper에 해당되는 주소를 리턴해서 mapper에 넣어줌
	private EmpDeptMapper mapper;
	
	/*
	@Results({
		@Result(column = "dname", property="dvo.name"),
		@Result(column = "loc", property="dvo.loc")
	})
	
	
	@Select("SELECT empno, ename, job, TO_CHAR(hiredate,'YYYY-MM-DD') ad dbday, sal, dname, loc "
			+ "FROM emp, dept "
			+ "WHERE emp.deptno=dept.deptno "
			+ "ORDER BY sal DESC")
			*/
	public List<EmpVO> empdeptListData(){
		return mapper.empdeptListData();
	};
	
	/*@Select("SELECT empno, ename, job, TO_CHAR(hiredate,'YYYY-MM-DD') ad dbday, sal, dname, loc "
			+ "FROM emp, dept "
			+ "WHERE emp.deptno=dept.deptno "
			+ "AND empno=#{empno}")*/
	public EmpVO empdeptDetailData(int empno){
		return mapper.empdeptDetailData(empno);
	};

}
