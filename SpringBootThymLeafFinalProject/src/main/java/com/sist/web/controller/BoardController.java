package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;
import com.sist.web.*;
import com.sist.web.dao.BoardDAO;
import com.sist.web.entity.*;

@Controller
@RequestMapping("board/")
public class BoardController {
	@Autowired
	private BoardDAO dao;
	
	@GetMapping("board_list")
	public String board_list(String page, Model model) {
		
		if(page==null)
			page ="1";
		int curpage=Integer.parseInt(page);
		int rowsize=10;
		int start=(rowsize*curpage)-rowsize;  //mySQL은 0번부터 시작한다는걸 주의한다.
		int end=(rowsize*curpage);
		List<BoardEntity> list=dao.boardLidstData(start);
		for(BoardEntity vo:list) {
			String s=vo.getRegdate();
			String[] ss=s.split(" ");
			vo.setRegdate(ss[0].trim());
		}
		int totalpage=dao.boardTotalPage();
			
		model.addAttribute("curpage",curpage);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("list",list);
		model.addAttribute("main_html","board/board_list");
		return "main";
	}
	
	@GetMapping("board_detail")
	public String board_detail(int no, Model model) {
		BoardEntity vo=dao.findByNo(no);
		vo.setHit(vo.getHit()+123);
		dao.save(vo); //update -> save() : update, insert 이 두가지 기능이 다 있음
		vo=dao.findByNo(no);
		model.addAttribute("vo",vo);
		model.addAttribute("main_html","board/board_detail");
		return "main";
	}
	
	@GetMapping("board_insert")
	public String board_insert(Model model) {
		model.addAttribute("main_html","board/board_insert");
		return "main";
	}
	
	@PostMapping("board_insert_ok")
	public String board_insert_ok(BoardEntity vo) {
		dao.save(vo);
		return "redirect:/board/board_list";
	}
	
	@GetMapping("board_update")
	public String board_update(int no, Model model) {
		BoardEntity vo=dao.findByNo(no);
		model.addAttribute("vo",vo);
		model.addAttribute("main_html","board/board_update");
		return "main";
	}
	
	@PostMapping("board_update_ok")
	public String board_update_ok(BoardEntity vo, RedirectAttributes ra) {
		
		//조회수를 update할때 안넘김 -> 0으로 초기화된다.
		BoardEntity tvo=dao.findByNo(vo.getNo());
		vo.setHit(tvo.getHit());
		
		dao.save(vo);
		//원래 detail?no=no해야 하는데 요 메소드 사용
		ra.addAttribute("no",vo.getNo());
		return "redirect:/board/board_detail";
	}
	
	@GetMapping("board_delete")
	public String board_delete(int no, Model model) {
		
		model.addAttribute("no",no);
		model.addAttribute("main_html","board/board_delete");
		return "main";
	}
	
	@PostMapping("board_delete_ok")
	public String board_delete_ok(String pwd, int no) {
		BoardEntity vo = dao.findByNo(no);
		dao.delete(vo);
		//int pwdd = vo.getPwd();
		
		return "redirect:/board/board_list";
	}
	
	

}
