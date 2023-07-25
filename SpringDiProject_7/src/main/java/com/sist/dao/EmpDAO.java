package com.sist.dao;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.*;
import com.sist.vo.*;

@Repository("dao")
public class EmpDAO {
	@Autowired //app.getBean이걸로 메소드 찾았음 이거 대신사용 //<select id="LocationListData" resultType="LocationVO" > 이게 생성되는듯?
	private EmpMapper mapper;
	public List<EmpVO> empListData(){
		return mapper.empListData();
	}
	
	public EmpVO empDetailData(int empno) {
		return mapper.empDetailData(empno);
	}
	
	public List<EmpVO> empDetailSearch(String name){
		return mapper.empDetailSearch(name);
	}

}
