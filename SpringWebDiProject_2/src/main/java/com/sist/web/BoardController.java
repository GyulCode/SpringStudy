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
	
	//목록
	@GetMapping("list.do")
	public String board_list(String page, Model model) {
		//사용자의 요청값은 다 string으로 받을 수 있다
		//단 지금은 페이지라 처음 요청시 null값임 
		//Model model->전송 객체(request대신 사용)
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
	
	//추가
	@GetMapping("insert.do")
	public String board_insert() {
		return "board/insert";
	}
	
	
	//추가 form 요청으로 보내게됨 이때는 @PostMapping사용 형일치 오류시 412에러***********
	@PostMapping("insert_ok.do")
	public String board_insert_ok(BoardVO vo) {
		dao.boardInsert(vo);
		return "redirect:list.do"; //jsp 생략
	}
	
	//수정
	@GetMapping("update.do")  //뭔 방법도 있다함 @Responsebody -> 승격 restcontroller
	public String board_update(int no, Model model) {
		BoardVO vo=dao.boardUpdateData(no);
		model.addAttribute("vo",vo);
		return "board/update";
	}
	
	
	//삭제 
	
	
	//상세보기 
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
	 * 	1) DispatcherServlet 등록 -> web.xml
	 * 		-> 클래스를 등록한 파일 셋팅(클래스 관리)
	 * 		-> 한글변환
	 * 	2) 클래스 제작
	 * 		vo -> mapper -> dao -> model(controller)
	 * 	3) application.xml (클래스 등록)
	 * 	4) JSP
	 * 자바에서는 메모리 누적시 서버가 느려짐 new로 생성된걸 가비지 컬렉터가 회수할때까지 대기
	 * 스프링에서는 컨테이너로 각각을따로 관리해줌
	 * 
	 */
		
	
	//검색 -> 동적쿼리
	
	
	
	
}
