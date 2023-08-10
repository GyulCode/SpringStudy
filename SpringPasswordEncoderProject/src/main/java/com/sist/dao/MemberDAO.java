package com.sist.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.MemberMapper;
import com.sist.vo.*;

@Repository
public class MemberDAO {
	@Autowired
	private MemberMapper mapper;
	
	/*@Insert("INSERT INTO sprintTestMember VALUES( "
			+ "#{id}, #{pwd}, #{name}, #{sex})")*/
	public void memberInsert(MemberVO vo) {
		mapper.memberInsert(vo);
	}
	
	//·Î±×ÀÎ
	/*@Select("SELECT COUNT(*) FROM springTestMember "
			+ "WHERE id=#{id}")*/
	public int memberIdCheck(String id) {
		return mapper.memberIdCheck(id);
	}
	
	public MemberVO memberLogin(String id) {
		return mapper.memberLogin(id);
	}
	
	

	
	
	/*@Select("SELECT pwd, name, sex, FROM springTestMember "
			+ "WHERE id=#{id}")*/
	/*
	public MemberVO memberLogin(String id, String pwd) {
		
		int count=mapper.memberIdCheck(id);
		MemberVO vo=new MemberVO();
		
		if(count==0) {
			vo.setMsg("NOID");
		}else {
			MemberVO dbvo=mapper.memberLogin(id);
			if(pwd.equals(dbvo.getPwd())) {
				vo.setMsg("OK");
				vo.setId(dbvo.getId());
				vo.setName(dbvo.getName());
				vo.setSex(dbvo.getSex());
			}
			
		}
			return vo;
	}
	*/

}

