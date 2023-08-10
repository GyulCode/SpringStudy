package com.sist.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {
	@Autowired
	private BCryptPasswordEncoder encoder; //��ȣȭ, ��ȣȭ Ŭ����
	
	@GetMapping("member/login.do")
	public String member_login() {
		return "member/login";
	}
	
	@GetMapping("member/join.do")
	public String member_join() {
		return "member/join";
	}
	
	@GetMapping("member/logout.do")
	public String member_logout(HttpSession session) {
		session.invalidate(); //������ �ʱ�ȭ
		return "redirect:../member/login.do";
	}

}
