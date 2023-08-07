package com.sist.web;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.sist.dao.*;
import com.sist.vo.*;


@Controller
public class CompanyController {
	@Autowired
	private CompanyDAO dao;
	
	@GetMapping("company/list.do")
	public String companyList() {
		return "company/list";
	}

}
