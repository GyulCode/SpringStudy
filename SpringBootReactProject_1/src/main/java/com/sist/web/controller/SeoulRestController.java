package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.dao.SeoulDAO;
import com.sist.web.dao.SeoulNatureDAO;
import com.sist.web.entity.SeoulLocationEntity;
import com.sist.web.entity.SeoulNatureEntity;

@RestController
@RequestMapping("seoul/")
@CrossOrigin("http://localhost:3000")
public class SeoulRestController {
	
	@Autowired
	private SeoulDAO dao;
	@Autowired
	private SeoulNatureDAO ndao;
	
	@GetMapping("location_info_react")
	public SeoulLocationEntity location_info(int no) {
		SeoulLocationEntity vo= dao.findByNo(no);
		return vo;
	}
	
	@GetMapping("nature_info_react")
	public SeoulNatureEntity nature_info(int no) {
		SeoulNatureEntity vo= ndao.findByNo(no);
		return vo;
	}

}
