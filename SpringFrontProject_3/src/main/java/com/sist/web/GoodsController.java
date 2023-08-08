package com.sist.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/*
 * �ڹ�
 * 	= �⺻�ڷ���(��������), ����, ������, ���, �迭(1��)
 * 	= ��ü���� ���α׷�(Ŭ����/��ü) ��ü���� 3�� ��� (ĸ��ȭ,���,����,������)
 * 		** ĸ��ȭ VS ����ȭ
 * 		** ��� VS ����
 * 		** �������̵� VS �����ε�
 *  = �߻�Ŭ����/�������̽�
 * 		** �߻�Ŭ���� VS �������̽�
 * 		** �������̽��� ���� �� �� ����ϴ°�
 *  = ����ó��
 * 		** ����ó���� ���� : ������ ���� ����, ���α׷��� ������� �ʰ� �ϱ� ����
 *  = ���̺귯�� (java.lang, java.util)
 *  	= Collection, ���׸���
 *  	  ------------------��������
 *  	= IO
 * ����Ŭ
 * 	= DQL(SELECT) -> JOIN/SubQuery
 * 	  *** JOIN�� ����
 * 	  *** SubQeury�� ����
 * 	= DML(INSERT, UPDATE, DELETE)
 * 	= DDL(CREATE, ALTER, DROP, TRUNCATE) -> ��������
 * 	  *** �������� ����
 * 	= TCL(COMMIT, ROLLBACK)
 *  = PL/SQL(���ν���)
 * 	  *** ���ν����� Ʈ���� ������
 *  = JDBC (DBCP/ORM(MyBatis,JPA)
 * 	  *** DAO�� service�� ������
 * HTML5/CSS(���� ����)
 * 	  *** GET / POST�� ����
 * JavaScript (�⺻) -> ����(let, const), ���/������
 * 					-> �Լ�
 * 					-> �̺�Ʈ
 * 					-> �±� ����(DOM)
 * 					-> ���̺귯�� (Jqeury,VueJS,React)
 * 	  *** var / let / const
 * 	  *** Ŭ����
 * 	  *** prototype
 * 	  *** VueJS, React�� ����
 * JSP : ������(page,taglib), ���尴ü(request,response,session,application), 
 * 		�׼��±�(<jsp:include>,<jsp:)
 * 		TagLib(<c:~~>), EL(${})
 * 		MVC
 * 			*** MVC���� �� ���� ����
 * 
 * Spring : DI, AOP, MVC - �⺻ 
 * 		�ɼ��߰����� : websocket, task, security, spring-boot 
 * 		*** DI�� ����
 * 		*** AOP�� ����
 * 
 * AWS : ȣ����
 * ---------------------���� ����~
 * 
 */
@Controller
public class GoodsController {
	@GetMapping("goods/list.do")
	public String goods_list() {
		return "good/list";
	}
	
	@GetMapping("goods/detail.do")
	public String goods_detail(int no, Model model) {
		model.addAttribute("no",no);
		return "goods/detail";
	}
	
	

}
