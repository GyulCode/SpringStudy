package com.sist.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.SeoulLocationEntity;

@Repository
public interface SeoulDAO extends JpaRepository<SeoulLocationEntity,Integer> {
	
	public SeoulLocationEntity findByNo(int no);

}
