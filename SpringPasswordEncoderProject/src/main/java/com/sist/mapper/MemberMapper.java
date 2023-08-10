package com.sist.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.sist.vo.MemberVO;

public interface MemberMapper {
	
	//회원가입
	@Select("SELECT COUNT(*) FROM springTestMember "
			+ "WHERE id=#{id}")
	public int memberIdCheck(String id);
	@Insert("INSERT INTO springTestMember(id, pwd, name, sex) VALUES( "
			+ "#{id}, #{pwd}, #{name}, #{sex} )")
	public void memberInsert(MemberVO vo);
	
	//로그인
	@Select("SELECT pwd, name, sex FROM springTestMember "
			+ "WHERE id=#{id}")
	public MemberVO memberLogin(String id);
}
