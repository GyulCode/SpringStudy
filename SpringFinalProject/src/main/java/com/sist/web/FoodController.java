package com.sist.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FoodController {
   @GetMapping("food/food_list.do")
   public String food_list(int cno,Model model)
   {
	   model.addAttribute("cno", cno);
	   model.addAttribute("main_jsp", "../food/food_list.jsp");
	   return "main/main";
   }
   @GetMapping("food/food_find.do")
   public String food_find(Model model)
   {
	   model.addAttribute("main_jsp", "../food/food_find.jsp");
	   return "main/main";
   }
   @GetMapping("food/food_detail_before.do")
   // ������ => �Ű������� �̿��ؼ� �ʿ��� �����ͳ� ���� ��ü ��û 
   //                  ----------------- ����ڰ� ������ ������ 
   // ������ ������� 
   public String food_detail_before(int fno,RedirectAttributes ra,
		   HttpServletResponse response)
   {
	   Cookie cookie=new Cookie("food_"+fno, String.valueOf(fno));
	   // ��Ű => Ŭ���̾�Ʈ (����:���� ��� , ���ڿ��� ������ ����)
	   // ���� ��ü�� �ƴϴ� 
	   // ��Ű ���� => ���� ��� ���� => �Ⱓ ���� => ���� 
	   cookie.setPath("/");
	   cookie.setMaxAge(60*60*24);// �ʴ����� ���� 
	   response.addCookie(cookie);
	   ra.addAttribute("fno", fno);// sendRedirect => request�� �ʱ�ȭ 
	   return "redirect:../food/food_detail.do";
   }
   @GetMapping("food/food_detail.do")
   public String food_detail(int fno,Model model,HttpSession session)
   {
	   String id=(String)session.getAttribute("id");
	   if(id==null)
		   id="";
	   model.addAttribute("id", id);
	   model.addAttribute("fno", fno);
	   model.addAttribute("main_jsp", "../food/food_detail.jsp");
	   return "main/main";// forward => request�� ���� 
   }
}