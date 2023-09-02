package com.sist.web.dao;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.*;

//JpaRepository<Entity명, Id로 쓰고있는 변수의 자료형>

@Repository
public interface FoodCategoryDAO extends JpaRepository<CategoryVO, Integer>{
	@Query(value="SELECT cno, title, link, poster, subject FROM food_category", nativeQuery = true)
	public List<CategoryVO> categoryListData();
	
	
}
