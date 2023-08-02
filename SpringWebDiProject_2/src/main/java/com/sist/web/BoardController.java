package com.sist.web;


import java.util.*;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.dao.*;
import com.sist.vo.BoardVO;

@Controller
@RequestMapping("board/")
public class BoardController {
	@Autowired
	private BoardDAO dao;
	
	//���
	@GetMapping("list.do")
	public String board_list(String page, Model model) {
		//������� ��û���� �� string���� ���� �� �ִ�
		//�� ������ �������� ó�� ��û�� null���� 
		//Model model->���� ��ü(request��� ���)
		if(page==null) {
			page="1";
		}
		int curpage=Integer.parseInt(page);
		Map<String,Object> map=new HashMap<String,Object>();
		int rowsize=10;
		int start=(rowsize*curpage)-(rowsize-1);
		int end=(rowsize*curpage);
		map.put("start",start);
		map.put("end", end);
		List<BoardVO> list=dao.boardListData(map);
		int totalpage=dao.boardTotalPage();
		
		model.addAttribute("curpage",curpage);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("list",list);
		
		return "board/list";
	}
	
	//�߰�
	@GetMapping("insert.do")
	public String board_insert() {
		return "board/insert";
	}
	
	
	//�߰� form ��û���� �����Ե� �̶��� @PostMapping��� ����ġ ������ 412����***********
	@PostMapping("insert_ok.do")
	public String board_insert_ok(BoardVO vo) {
		dao.boardInsert(vo);
		return "redirect:list.do"; //jsp ����
	}
	
	//����
	@GetMapping("update.do")  //�� ����� �ִ��� @Responsebody -> �°� restcontroller
	public String board_update(int no, Model model) {
		BoardVO vo=dao.boardUpdateData(no);
		model.addAttribute("vo",vo);
		return "board/update";
	}
	
	
	//���� 
	
	
	//�󼼺��� 
	/*
	 * 
	 * public void addAtrribute(string key, Object obj)
	 * {
	 * 		request.setAttribute(key, obj);
	 * }
	 */
	@GetMapping("detail.do")
	public String board_detail(int no, Model model) {
		BoardVO vo=dao.boardDetailData(no);
		model.addAttribute("vo",vo);
		return "board/detail";
	}
	
	@GetMapping("delete.do")
	public String board_delete(int no, Model model) {
		model.addAttribute("no",no);
		return "board/delete";
	}
	
	@PostMapping("delete_ok.do")
	public String board_delete_ok(int no,String pwd ,Model model) {
		boolean bCheck=dao.boardDelete(no, pwd);
		model.addAttribute("bCheck",bCheck);
		return "board/delete_ok";
	}
	
	/*
	 * 1. Spring MVC
	 * 	1) DispatcherServlet ��� -> web.xml
	 * 		-> Ŭ������ ����� ���� ����(Ŭ���� ����)
	 * 		-> �ѱۺ�ȯ
	 * 	2) Ŭ���� ����
	 * 		vo -> mapper -> dao -> model(controller)
	 * 	3) application.xml (Ŭ���� ���)
	 * 	4) JSP
	 * �ڹٿ����� �޸� ������ ������ ������ new�� �����Ȱ� ������ �÷��Ͱ� ȸ���Ҷ����� ���
	 * ������������ �����̳ʷ� ���������� ��������
	 * 
	 */
		
	
	//�˻� -> ��������
	
	
	
	
}
