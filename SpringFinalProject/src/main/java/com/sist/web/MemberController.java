package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.sist.dao.*;

import com.sist.vo.MemberVO;

@Controller //페이지관리
public class MemberController {
	
	@Autowired
	private MemberDAO dao;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@GetMapping("member/join.do")
	public String member_join(Model model) {
		model.addAttribute("main_jsp","../member/join.jsp");
		return "main/main";
	}
	
	@PostMapping("member/join_ok.do")
	public String member_join_ok(MemberVO vo)
	{
		System.out.println("id: " +vo.getId());
		vo.setPhone(vo.getPhone()+"-"+vo.getPhone1());
		String enPwd=encoder.encode(vo.getPwd());
		/* String enId=encoder.encode(vo.getId()); */
		
		vo.setPwd(enPwd);
		/* vo.setId(enId); */
		dao.memberInsert(vo);
		return "redirect:../main/main.do";
	}
}
