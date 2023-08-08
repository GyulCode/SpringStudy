package com.sist.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.*;

// Service��� ���� ���� -> ������ ��
public interface BoardMapper {
	//���
	@Select("SELECT no, subject, name, TO_CHAR(regdate,'YYYY-MM-DD') as dbday, hit, group_tab, num "
			+ "FROM (SELECT no, subject, name, regdate, hit, group_tab, rownum as num "
			+ "FROM (SELECT no, subject, name, regdate, hit, group_tab "
			+ "FROM springReplyBoard ORDER BY group_id DESC, group_step ASC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<BoardVO> boardListData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/10.0) FROM springReplyBoard")
	public int boardTotalPage();
	
	//�߰� sequence ���, ���ϸ� key������ @KetProperty
	@Insert("INSERT INTO springReplyBoard(no, name, subject, content, pwd, group_id) "
			+ "VALUES(srb_no_seq.nextval,#{name},#{subject},#{content},#{pwd},"
			+ "(SELECT NVL(MAX(group_id)+1,1) FROM springreplyboard))")
	public void boardInsert(BoardVO vo);
	
	//�󼼺���
	@Update("UPDATE springreplyboard SET "
			+ "hit = hit+1 "
			+ "WHERE no=#{no}")
	public void hitIncrement(int no);
	@Select("SELECT no, name, subject, content, TO_CHAR(regdate,'YYYY-MM-DD') as dbday, hit "
			+ "FROM springreplyBoard "
			+ "WHERE no=#{no}")
	public BoardVO boardDetailData(int no);
	
	//�亯 === Ʈ�����
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
			
	
	//����
	@Select("SELECT pwd FROM springReplyBoard "
			+ "WHERE no=#{no}")
	public String boardGetPassword(int no);
	@Update("UPDATE springReplyBoard SET "
			+ "name=#{name}, subject=#{subject}, content=#{content} "
			+ "Where no=#{no}")
	public void boardUpdate(BoardVO vo);
	
	
	//���� === Ʈ�����(������ AOP)
	@Select("SELECT root, depth FROM springReplyBoard "
			+ "WHERE no=#{no}")
	public BoardVO boardInfoData(int no);
	@Update("UPDATE springReplyBoard SET( "
			+ "+subject='������ �Խù��Դϴ�.', content='������ �Խù��Դϴ�.') "
			+ "WHERE no=#{no}")
	public void boardSubjectUpdate(int no);
	@Delete("DELETE FROM springReplyBoard "
			+ "WHERE no=#{no}")
	public void boardDelete(int no);
	@Update("UPDATE springreplyBoard SET "
			+ "depth=depth-1 "
			+ "where no=#{no}")
	public void boardDepthDecrement(int no);
	
	
	
	//���߰˻� (��������) <trim prefixOverride=\"OR\"> name LIKE OR content LIKE 7���� ��쿡�� OR�� �Ǿտ� �ִ°� ���� 
	// fd=='N'.toString() *����* OR�� prefix�� ��� ���̰� override�� �Ǿ� ����
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