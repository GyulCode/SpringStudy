package com.sist.dao;

import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sist.mapper.*;
import com.sist.vo.*;

@Repository
public class BoardDAO {
	
	@Autowired
	private BoardMapper mapper;
	
	/*@Select("SELECT no, subject, name, TO_CHAR(regdate,'YYYY-MM-DD') as dbday, hit, num "
			+ "FROM (SELECT no, subject, name, regdate, hit, rownum as num "
			+ "FROM (SELECT no, subject, name, regdate, hit "
			+ "FROM springReplyBoard ORDER BY group_id DESC, group_step ASC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")*/
	public List<BoardVO> boardListData(Map map){
		return mapper.boardListData(map);
	}
	public int boardTotalPage() {
		return mapper.boardTotalPage();
	}
	
	//추가 sequence 사용, 안하면 key값으로 @KetProperty
	/*@Insert("INSERT INTO springReplyBoard(no, name, subject, content, pwd, group_id) "
			+ "VALUES(srb_no_seq.nextval,#{name},#{subject},#{content},#{pwd},"
			+ "(SELECT NVL(MAX(group_id)+1,1) FROM springreplyboard))")*/
	public void boardInsert(BoardVO vo) {
		mapper.boardInsert(vo);
	}
	
	//상세보기
	/*@Update("UPDATE springreplyboard SET "
			+ "hit = hit+1 "
			+ "WHERE no=#{no}")
	public void hitIncrement(int no) {
		
	}
	
	@Select("SELECT no, name, subject, content, TO_CHAR(regdate,'YYYY-MM-DD') as dbday, hit "
			+ "FROM springreplyBoard "
			+ "WEHRE no=#{no}")*/
	public BoardVO boardDetailData(int no) {
		mapper.hitIncrement(no);
		return mapper.boardDetailData(no);
		
		
	}
	//답변 === 트랜잭션
	/*@Select("SELECT group_id, group_step, group_tab "
			+ "FROM springReplyBoard "
			+ "WHERE no=#{no}")
	public BoardVO boardParentInfoData(int no);
	
	@Update("UPDATE springreplyboard SET "
			+ "group_step=group_step+1 "
			+ "WHERE group_id=#{group_id} AND group_step>#{group_step}")
	public void boardGroupStepIncrement(BoardVO vo);
	
	@Insert("INSERT INTO springReplyBoard(no, name, subject, content, pwd, group_id, group_step, group_tab, root) "
			+ "VALUES(srb_no_seq.nextval,#{name},#{subject},#{content},#{pwd},"
			+ "#{group_id}, #{group_step}, #{group_tab}, #{root})")
	public void boardReplyInsert(BoardVO vo);
	
	@Update("UPDATE springreplyBoard SET "
			+ "depth=depth+1 "
			+ "WHERE no=#{no}") 
	public void boardDepthIncrement(int no) {
		
	}*/
	
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public void boardReplyInsert(int root, BoardVO vo) {
		BoardVO pvo=mapper.boardParentInfoData(root);
		mapper.boardGroupStepIncrement(pvo);
		vo.setGroup_id(pvo.getGroup_id());
		vo.setGroup_step(pvo.getGroup_step()+1);
		vo.setGroup_tab(pvo.getGroup_tab()+1);
		vo.setRoot(root);
		mapper.boardReplyInsert(vo);
		mapper.boardDepthIncrement(root);
	}
	
	//수정하기 위한 상세보기
	public BoardVO boardUpdateData(int no) {
		return mapper.boardDetailData(no);
	}
	
	/*@Select("SELECT pwd FROM springReplyBoard "
			+ "WHERE no=#{no}")
	public String boardGetPassword(int no);
	
	@Update("UPDATE springReplyBoard SET "
			+ "name=#{name}, subject=#{subject}, content=#{content} "
			+ "Where no=#{no}")*/
	public boolean boardUpdate(BoardVO vo) {
		
		boolean bCheck=false;
		String db_pwd=mapper.boardGetPassword(vo.getNo());
		if(db_pwd.equals(vo.getPwd())) {
			bCheck=true;
			mapper.boardUpdate(vo);
		}
		return bCheck;
	}
	
	/*
	@Select("SELECT root, depth, FROM, springReplyBoard "
			+ "WHERE no=#{no}")
	public BoardVO boardInfoData(int no);
	@Update("UPDATE springReplyBoard SET "
			+ "+subject='삭제된 게시물입니다.', content='삭제된 게시물입니다.' "
			+ "WHERE no=#{no}")
	public void boardSubjectUpdate(int no);
	@Delete("DELETE FROM springReplyBoard "
			+ "WHERE no=#{no}")
	public void boardDelete(int no);
	@Update("UPDATE springreplyBoard SET "
			+ "depth=depth-1 "
			+ "where no=#{no}")
			
	
	1. 트랜잭션 application-datasource.xml에 추가해줘야함
		여러개의 sql문(DML 문장들의 집합)을 하나로 처리
	2.<!-- 트랜잭션 선언 -->
		<tx:annotation-driven/>
		<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager"
			p:dataSource-ref="ds"
		/>
	3. 전파
		propagation = Propagation.REQUIRED - 기본형
		트랜잭션을 사용중이라면 -> 다음에 다시 재사용 가능하게 만든다 -> 시작할때만 한번 생성
		public void delete(){
			try{
				conn.setAutoCommit(false)
				--------------------
				개발자 소스코드
				--------------------
				conn.Commit()
			}catch{
				conn.rollback()
			}finally{
				conn.setAutoCommit(true)
			}
		}
		
		
		
		Propagation.REQUIRED_NEW : 트랜잭션을 새롭게 생성
		Propagation.NEVER : 트랜잭션을 무조건 설정
		
		타겟객체 대신 프록시사용(AOP에서 클래스 대용을 하나 만듬)
		
		 rollbackFor = Exception.class 에러발생시 에러 내용 보여달라 요청
	
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public boolean boardDelete(int no, String pwd) {
		boolean bCheck;
		// 비밀번호 읽기
		String db_pwd=mapper.boardGetPassword(no);
		if(db_pwd.equals(pwd)) {
			//depth 게시물 갯수
			bCheck=true; 
			BoardVO vo=mapper.boardInfoData(no);
			if(vo.getDepth()==0)
			{
				mapper.boardDelete(no);;
			} else{
				mapper.boardSubjectUpdate(no);
				mapper.boardDepthDecrement(vo.getRoot());
			}
		}
		else {
			bCheck=false;
		}
		return bCheck;
		
	}
	
	public List<BoardVO> boardFindData(Map map){
		return mapper.boardFindData(map);
	}

}
