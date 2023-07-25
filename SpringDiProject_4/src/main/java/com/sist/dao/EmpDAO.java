package com.sist.dao;

import java.util.*;

import org.mybatis.spring.support.SqlSessionDaoSupport;
public class EmpDAO extends SqlSessionDaoSupport {
	
	public List<EmpVO> empListData(){
		return getSqlSession().selectList("empListData"); // empListData 이게 sql문장
	}
	
	public EmpVO empDetailData(int empno) {
		return getSqlSession().selectOne("empDetailData",empno);
		//값을 하나 받을때는 select One, 다수 List
	}
	

}
