package com.sist.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sist.web.entity.FoodEntity;
import com.sist.web.entity.SeoulLocationEntity;

public interface SeoulLocationDAO extends JpaRepository<SeoulLocationEntity, Integer>{
	//#{address} 이형식이 아님 :address  주의! 또한 사용자가 작성한 쿼리는 native Query를 줘야함
	@Query(value="SELECT * FROM seoul_location "
			+ "WHERE address LIKE CONCAT('%',:address,'%') "
			+ "ORDER BY no ASC "
			+ "LIMIT :start, 12", nativeQuery = true)
	public List<SeoulLocationEntity> locationFindData(@Param("address") String address, @Param("start") Integer start);
	
	@Query(value="SELECT CEIL(COUNT(*)/12.0) FROM seoul_location "
			+ "WHERE address LIKE CONCAT('%',:address,'%')", nativeQuery = true )
	public int locationFindTotalPage(String address);
	
	public SeoulLocationEntity findByno(@Param("no") Integer no); 

}
