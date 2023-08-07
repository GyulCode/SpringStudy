package com.sist.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.*;

// Service사용 없이 구현 -> 의존성 높
public interface BoardMapper {
	//목록
	@Select("SELECT no, subject, name, TO_CHAR(regdate,'YYYY-MM-DD') as dbday, hit, group_tab, num "
			+ "FROM (SELECT no, subject, name, regdate, hit, group_tab, rownum as num "
			+ "FROM (SELECT no, subject, name, regdate, hit, group_tab "
			+ "FROM springReplyBoard ORDER BY group_id DESC, group_step ASC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<BoardVO> boardListData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/10.0) FROM springReplyBoard")
	public int boardTotalPage();
	
	//추가 sequence 사용, 안하면 key값으로 @KetProperty
	@Insert("INSERT INTO springReplyBoard(no, name, subject, content, pwd, group_id) "
			+ "VALUES(srb_no_seq.nextval,#{name},#{subject},#{content},#{pwd},"
			+ "(SELECT NVL(MAX(group_id)+1,1) FROM springreplyboard))")
	public void boardInsert(BoardVO vo);
	
	//상세보기
	@Update("UPDATE springreplyboard SET "
			+ "hit = hit+1 "
			+ "WHERE no=#{no}")
	public void hitIncrement(int no);
	@Select("SELECT no, name, subject, content, TO_CHAR(regdate,'YYYY-MM-DD') as dbday, hit "
			+ "FROM springreplyBoard "
			+ "WHERE no=#{no}")
	public BoardVO boardDetailData(int no);
	
	//답변 === 트랜잭션
	@Select("SELECT group_id, group_step, group_tab "
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
	public void boardDepthIncrement(int no);
			
	
	//수정
	@Select("SELECT pwd FROM springReplyBoard "
			+ "WHERE no=#{no}")
	public String boardGetPassword(int no);
	@Update("UPDATE springReplyBoard SET "
			+ "name=#{name}, subject=#{subject}, content=#{content} "
			+ "Where no=#{no}")
	public void boardUpdate(BoardVO vo);
	
	
	//삭제 === 트랜잭션(스프링 AOP)
	@Select("SELECT root, depth FROM springReplyBoard "
			+ "WHERE no=#{no}")
	public BoardVO boardInfoData(int no);
	@Update("UPDATE springReplyBoard SET( "
			+ "+subject='삭제된 게시물입니다.', content='삭제된 게시물입니다.') "
			+ "WHERE no=#{no}")
	public void boardSubjectUpdate(int no);
	@Delete("DELETE FROM springReplyBoard "
			+ "WHERE no=#{no}")
	public void boardDelete(int no);
	@Update("UPDATE springreplyBoard SET "
			+ "depth=depth-1 "
			+ "where no=#{no}")
	public void boardDepthDecrement(int no);
	
	
	
	//다중검색 (동적쿼리) <trim prefixOverride=\"OR\"> name LIKE OR content LIKE 7가지 경우에서 OR가 맨앞에 있는거 제거 
	// fd=='N'.toString() *주의* OR를 prefix로 모두 붙이고 override로 맨앞 제거
	@Select({
		"<script>"
			+ "SELECT no, subject, name, content, TO_CHAR(regdate,'YYYY-MM-DD') as dbday, hit "
			+ "FROM springReplyBoard "
			+ "WHERE "
			+ "<trim prefixOverride=\"OR\">"
				+ "<foreach collection=\"fsArr\" item=\"fd\">"
				+ "<trim prefix=\"OR\">"
				+ "<choose>"
				+ "<when test=\"fd=='N'.toString()\">"
				+ "name LIKE '%'||#{ss}||'%'"
				+ "</when>"
				+ "<when test=\"fd=='S'.toString()\">"
				+ "subject LIKE '%'||#{ss}||'%'"
				+ "</when>"
				+ "<when test=\"fd=='C'.toString()\">"
				+ "content LIKE '%'||#{ss}||'%'"
				+ "</when>"
				+ "</choose>"
				+ "</trim>"
				+ "</foreach>"
			+ "</trim>"
		+ "</script>"
	}) 
	public List<BoardVO> boardFindData(Map map);
	
}
