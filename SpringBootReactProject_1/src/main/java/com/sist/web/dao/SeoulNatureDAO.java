package com.sist.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.SeoulNatureEntity;

@Repository
public interface SeoulNatureDAO extends JpaRepository<SeoulNatureEntity,Integer> {
	
	public SeoulNatureEntity findByNo(int no);

}
