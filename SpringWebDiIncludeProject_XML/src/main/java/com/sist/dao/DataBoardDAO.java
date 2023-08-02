package com.sist.dao;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.builder.xml.XMLMapperEntityResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.mapper.*;
import com.sist.vo.*;

import oracle.net.aso.b;

@Repository
public class DataBoardDAO {
	
	@Autowired
	private DataBoardMapper mapper;
	
	public List<DataBoardVO> databoardListData(Map map){
		return mapper.databoardListData(map);
	}
	
	//총페이지
	public int databoardTotalPage() {
		return mapper.databoardTotalPage();
	}
	
	// 글작성
	public void databoardInsert(DataBoardVO vo) {
		mapper.databoardInsert(vo);
	}
	
	// 상세보기
	public DataBoardVO databoardDetailData(int no) {
		mapper.hitIncrement(no);
		return mapper.databoardDetailData(no);
	}
	
	//찾기
	/*
 		@Select("SELECT no, subject, name, TO_CHAR(regdate,'YYYY-MM-DD') as dbday, hit "
			+ "FROM springdataboard "
			+ "WHERE ${fs} LIKE '%'||#{ss}||'%'")
			fs, ss VO에 없음 -> MAP사용
	 */
	public List<DataBoardVO> databoardFindData(Map map){
		return mapper.databoardFindData(map);
	}
	
	//업데이트
	public DataBoardVO databoardUpdateData(int no) {
		return mapper.databoardDetailData(no);
	}
	
	public boolean databoardUpdate(DataBoardVO vo) {
		boolean bCheck=false;
		String db_pwd=mapper.databoardGetPassword(vo.getNo());
		if(db_pwd.equals(vo.getPwd())) {
			bCheck=true;
			mapper.databoardUpdate(vo);
		}
			
		return bCheck;
	}
	
	//삭제
	public boolean databoardDelete(int no,String pwd) {
		boolean bCheck=false;
		String db_pwd=mapper.databoardGetPassword(no);
		if(db_pwd.equals(pwd)) {
			bCheck=true;
			mapper.databoard_delete_ok(no);
		}
		return bCheck;
		
	}
	
	
	
	

}
