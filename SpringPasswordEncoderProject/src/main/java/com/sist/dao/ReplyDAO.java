package com.sist.dao;

import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;
import com.sist.vo.*;
import com.sist.mapper.*;

/*
 * 데이터 저장 -> mounted -> render() => html에 데이터 출력
 * 				|
 * 			데이터가 변경 : updated => render() => html변경
 * 							|
 * 						 data:{} => 안에 있는 변수값이 변경될때 호출된다.
 * 
 */
@Repository
public class ReplyDAO {
	@Autowired
	private ReplyMapper mapper;
	
	public List<ReplyVO> replyListData(int fno){
		return mapper.replyListData(fno);
	}
	
	public void replyInsert(ReplyVO vo) {
		mapper.replyInsert(vo);
	}
	
	//@Delete("DELETE FROM springTestReply WEHRE no=#{no}")
	public void replyDelete(int no) {
		mapper.replyDelete(no);
	}
	
	public void replyUpdate(ReplyVO vo) {
		mapper.replyUpdate(vo);
	}
	

}
