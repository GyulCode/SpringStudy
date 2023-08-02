package com.sist.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.vo.*;
import com.sist.mapper.*;


/*
 * client 
 * -> DispatcherServlet(������� ��� ��û�� ����(Front Controller)�����
 *    �̹� ���������� ���̺귯�� ���� -> ����� web.xml���� servlet������ ��Ĺ
 *    handler
 *    mapping 
 * |
 * �ش�� (Controller) : ������ ���� ���� -> DAO
 * request�� ��û ó������ ��´�.
 * jsp���ϸ��� �����Ѵ�.
 * java -> jsp �������� �Ұ���
 * jsp -> jsp �Ǵ� servlet -> jsp�� ����
 * |
 * DispatcherServlet�� ������ �����ؼ� �긦 ��ġ�°��� �׷��� �Ʒ��� ���� �������
 * 1) JSPã��
 * 2) Request ����
 *    ------------ViewResolver
 * View (JSP)
 *  | request�� ��� �����͸� ���(JSTL/EL)
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
