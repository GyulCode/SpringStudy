package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import javax.servlet.http.HttpSession;

import com.sist.vo.*;
import com.sist.dao.*;

//Security 5 : �������� �ݵ�� BCryptPasswordEncoder�� �߰��� ����Ѵ�.
@RestController
public class MemberRestController {
	@Autowired
	private BCryptPasswordEncoder encoder;
	@Autowired
	private MemberDAO dao;
	
	@PostMapping(value="member/join_ok.do", produces ="text/plain;charset=UTF-8")
	public String member_join(MemberVO vo) {
		
		System.out.println("id:"+vo.getId()+vo.getPwd()+vo.getName()+vo.getSex());
		String result="";
		
		try {
			
			int id=dao.memberIdCheck(vo.getId());
			if(id==0) {
				System.out.println("id������ Ȯ���մϴ� : "+id);
				String en=encoder.encode(vo.getPwd()); //��� ��ȣȭ
				vo.setPwd(en);
				dao.memberInsert(vo);
				result="YES";
			}else {
				System.out.println("�ߺ��� id�� �ֽ��ϴ�. : "+id);
				result="NO";
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}
	
	@PostMapping(value="member/login_ok.do", produces ="text/plain;charset=UTF-8")
	//�Ű������� ������ ���� -> dispatcherServlet�� ���� ���� ����
	//�ʿ��� ��ü -> �Ű������� �̿��ؼ� �޴´�.
	//�Ű������� ������ ����
	//����� ����(��û) -> request.getparameter()
	//���� �� �ִ� Ŭ���� : ���� ��ü�� ����, Model(���� ��ü) -> forward������ ���
	//sendRedirect -> RedirectAttributes
	//@restcontroller -> �ٸ����� ����(JavaScript, Kotlin)
	//JSON, script, 
	public String member_login_ok(String id, String pwd, HttpSession session) {
		
		String result="";
		int count=dao.memberIdCheck(id);
		
		if(count==0) {
			result="NOID";
		}else {
			MemberVO vo=dao.memberLogin(id);
			if(encoder.matches(pwd, vo.getPwd())) { //�������� ��ȣ�� �����ȣ�� ���Ѵ�.
				//�α���
				result="OK";
				//���ǿ� ����
				session.setAttribute("id", vo.getId());
				session.setAttribute("name", vo.getName());
				session.setAttribute("sex", vo.getSex());
			}else {
				//��й�ȣ�� Ʋ�� ���� ==> ���� ���ε� ��ȣȭ�� �ٸ���
				System.out.println("��й�ȣ�� Ʋ���ϴ�");
				result="NOPWD";
			}
		}
		
		return result;
	}

}
