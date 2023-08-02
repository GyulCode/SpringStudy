package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.cookie;

import java.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.*;
import com.sist.vo.*;
@Controller
public class FoodController {
   @Autowired
   private FoodDAO dao;
   // food/food_list.do?cno=${vo.cno }
   @GetMapping("food/food_list.do")
   public String food_list(int cno,Model model)
   {
	   // �����ͺ��̽����� ������ �б� => food_list.jsp�� ���� => ȭ�� ��� 
	   CategoryVO vo=dao.foodCategoryInfoData(cno);
	   List<FoodVO> list=dao.foodListData(cno);
	   
	   model.addAttribute("cvo", vo);
	   model.addAttribute("list", list);// request => ���۰�ü 
	   model.addAttribute("main_jsp", "../food/food_list.jsp");
	   return "main/main";
   }
   
   // food/food_before_detail.do
   @GetMapping("food/food_before_detail.do")
   public String food_before_detail(int fno, RedirectAttributes ra, HttpServletResponse response ) {
	   /*
	    * before�� ���� �༭ �ϴ������� ��Ű�� html������ �ѹ��� ���� �� ����.
	    * Model : forward�϶� �� ����
	    * RedirectAtrributes : sendRedirect�϶� ���� ����
	    * addFlashAttribute ���� ���ܼ� ����
	    */
	   Cookie cookie=new Cookie("food_"+fno, String.valueOf(fno));
	   cookie.setPath("/");
	   cookie.setMaxAge(60*60*24);
	   //�������� ����
	   response.addCookie(cookie);
	   ra.addAttribute("fno",fno); //redirect
	   return  "redirect:../food/food_detail.do?fno="+fno;
   }
   
   
   // food/food_detail.do?fno=${vo.fno }
   @GetMapping("food/food_detail.do")
   public String food_detail(int fno,Model model)
   {
	   // �󼼺��⿡ �ʿ��� �����͸� ����Ŭ���� �о� �´� 
	   // ���� 
	   FoodVO vo=dao.foodDetailData(fno);
	   model.addAttribute("vo", vo);
	   model.addAttribute("main_jsp", "../food/food_detail.jsp");
	   return "main/main";
   }
}