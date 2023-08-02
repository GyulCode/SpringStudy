package com.sist.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.vo.*;
import com.sist.mapper.*;


/*
 * client 
 * -> DispatcherServlet(사용자의 모든 요청을 받음(Front Controller)라고함
 *    이미 스프링에서 라이브러리 제작 -> 등록은 web.xml에서 servlet구동은 톰캣
 *    handler
 *    mapping 
 * |
 * 해당모델 (Controller) : 개발자 직접 제작 -> DAO
 * request에 요청 처리값을 담는다.
 * jsp파일명을 전송한다.
 * java -> jsp 값전송이 불가함
 * jsp -> jsp 또는 servlet -> jsp만 가능
 * |
 * DispatcherServlet만 전송이 가능해서 얘를 거치는거임 그래서 아래와 같은 기능을함
 * 1) JSP찾기
 * 2) Request 전송
 *    ------------ViewResolver
 * View (JSP)
 *  | request에 담긴 데이터를 출력(JSTL/EL)
 * client
 *    
 * 
 */

@Repository
public class FoodDAO {
	@Autowired
	private FoodMapper mapper;
	/*@Select ("SELECT cno, title, poster, subject "
			+ "FROM food_category "
			+ "ORDER BY cno ASC")*/
	public List<CategoryVO> foodCategoryListData(){
		return mapper.foodCategoryListData();
	}
	
	/*@Select ("SELECT fno, name, score, address, phone, type, poster "
			+ "FROM food_house "
			+ "WHERE cno=#{cno}")*/
	public List<FoodVO> foodListData(int cno){
		return mapper.foodListData(cno);
	}
	

}
