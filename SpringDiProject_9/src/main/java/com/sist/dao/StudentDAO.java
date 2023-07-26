package com.sist.dao;

import java.util.*;

import org.mybatis.spring.support.SqlSessionDaoSupport;
public class StudentDAO extends SqlSessionDaoSupport{
	
	//����б�
	public List<StudentVO> studentListData(){
		return getSqlSession().selectList("studentListData");
	}
	
	//�� ����
	public StudentVO studentDetailData(int hakbun) {
		return getSqlSession().selectOne("studentDetailData",hakbun);
	}
	 
	//�˻�
	public List<StudentVO> studentFindData(String name){
		return getSqlSession().selectList("studentFindData",name);
	}
	
	//�߰�
	public void studentInsert(StudentVO vo) {
		getSqlSession().insert("studentInsert",vo);
	}
	
	//����
	public void studentUpdate(StudentVO vo) {
		getSqlSession().update("studentUpdate",vo);
	}
	
	//����
	public void studentDelete(int hakbun) {
		getSqlSession().delete("studentDelete",hakbun);
	}

}
