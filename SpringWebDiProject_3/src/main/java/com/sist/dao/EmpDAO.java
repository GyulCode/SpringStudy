package com.sist.dao;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;
import com.sist.vo.*;

@Repository
public class EmpDAO extends SqlSessionDaoSupport {
	
	// Autowired¿« ≈∏∞Ÿ : @Target(value={ANNOTATION_TYPE, CONSTRUCTOR, FIELD, METHOD, PARAMETER}) Di≈∏∞ŸµÈ¿”
	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		// TODO Auto-generated method stub
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	public List<String> empGetNameDate()
	{
		return getSqlSession().selectList("empGetNameDate");
		
	}
	
	public List<EmpVO> empInfoData(){
		return getSqlSession().selectList("empInfoData");
	}
	

}
