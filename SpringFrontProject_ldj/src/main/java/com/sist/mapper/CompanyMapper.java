package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Select;
import com.sist.vo.*;
public interface CompanyMapper {
	// ��� 
	@Select("SELECT com_id, com_name, poster,time, com_star_sum, com_star_cnt, num "
		  + "FROM (SELECT com_id, com_name, poster,time, com_star_sum, com_star_cnt, rownum as num "
		  + "FROM (SELECT com_id, com_name, poster,time, com_star_sum, com_star_cnt "
		  + "FROM company ORDER BY com_id ASC)) "
		  + "WHERE num BETWEEN #{start} AND #{end}")
	public List<CompanyVO> companyListData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM company")
	public int companyTotalPage();
	
	// ��
	@Select("SELECT com_id,com_name,poster,content,time,phone,address,price "
		  + "FROM food_location "
		  + "WHERE fno=#{fno}")
	public CompanyVO companyDetailData(int fno);
	// �˻� ==> Vue/React => �ǽð�
	@Select("SELECT com_id, com_name, poster,time, com_star_sum, com_star_cnt, num "
			  + "FROM (SELECT com_id, com_name, poster,time, com_star_sum, com_star_cnt, rownum as num "
			  + "FROM (SELECT com_id, com_name, poster,time, com_star_sum, com_star_cnt "
			  + "FROM company ORDER BY com_id ASC WHERE address LIKE '%'||#{name}||'%')) "
			  + "WHERE num BETWEEN #{start} AND #{end}")
	public List<CompanyVO> companyFindData(Map map);	
	
}