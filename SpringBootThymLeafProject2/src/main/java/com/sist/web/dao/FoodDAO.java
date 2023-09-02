package com.sist.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.FoodEntity;

//FoodEntity 객체 이름, Integer 는 @id의 자료형과 일치해야 함
@Repository
public interface FoodDAO extends JpaRepository<FoodEntity, Integer> {
	//public FoodEntity findByFno(int fno);
	//SELECT * FROM food_location WHERE fno=1
	//insert, update, delete
	//#{address} 이형식이 아님 :address  주의! 또한 사용자가 작성한 쿼리는 native Query를 줘야함
	@Query(value="SELECT * FROM food_location "
			+ "WHERE address LIKE CONCAT('%',:address,'%') "
			+ "ORDER BY fno ASC "
			+ "LIMIT :start, 12", nativeQuery = true)
	public List<FoodEntity> foodFindData(@Param("address") String address, @Param("start") Integer start);
	
	@Query(value="SELECT CEIL(COUNT(*)/12.0) FROM food_location "
			+ "WHERE address LIKE CONCAT('%',:address,'%')", nativeQuery = true )
	public int foodFindTotalPage(String address);
	
	//select * from food_location WHERE fno=#{fno}
	public FoodEntity findByFno(@Param("fno") Integer fno);
	
	

}
