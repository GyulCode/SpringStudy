package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 *   Spring : �ڹٶ��̺귯�� 
 *   -------
 *     DI : Spring�� ���ؼ� ��ü���� ~ ��ü�Ҹ�
 *                        ------
 *                         �ʿ��� �����Ͱ� �ִ� ��쿡 �����͸� ���� (��������� �ʱ�ȭ)
 *                         ������ �ʱ�ȭ 
 *                         ----------
 *                         1. ����� �ʱ�ȭ => Ŭ������ ���۽ÿ� ������ ���� ���� ä���ִ� ���
 *                         public class A
 *                         {
 *                           private String name="ȫ�浿";
 *                         }
 *                         2. ������ => ������ DI
 *                         3. setter�� �̿��ϴ� ��� => setter DI
 *                         ----------------------------------- XML�̿� , �ڹٸ� �̿�
 *     AOP : ���α׷� (�ٽ� �ڵ� , ���� �ڵ�) 
 *                            --------- getConnection(), disConnection()
 *           => Ŭ�������� �������� ���Ǵ� �޼ҵ� ��Ҵٰ� �ʿ��� �ñ⿡ �ڵ� ȣ���� ���� 
 *           => Ʈ������, LOG , ���� 
 *     MVC : �� ���� (View/Model) 
 *           Model : ������ ���� (�ڹ�) 
 *           View  : ������ ��� ���� 
 *           Controller : ��û�� �ް� , �����͸� �����ϴ� ���� 
 *           ----------------------------------------- �̹� ���̺귯���� ���� 
 *                     DispatcherServlet=> ��Ĺ�� ���� ���� 
 *                                         ------------- web.xml
 *                     1) DispatcherServlet��� 
 *                     2) DispatcherServlet�� ã�� ��� 
 *                        <url-pattern>*.do</url-pattern>
 *                        <url-pattern>/</url-pattern>
 *                        => PathValiable (admin/hong/1234)
 *           *** ��û�� �޴� ��� (��û ������ ����)
 *               => request�ȿ� ��ܼ� ���´� => request.getParameter();
 *                  ** request�� ����� ���� (IP,PORT) ������ �ִ� (���Ȼ� ����) 
 *                     => �������̸� ������� �ʴ´� 
 *                     => ����� ���� �޼ҵ带 ���鶧 �Ű������� �����ϸ� DispatcherServlet�� ���� 
 *                        �Ű������� ä������ 
 *                     => JSP ������� ���� => Model
 *           Model�� �ۼ� 
 *           1) @Controller �����ؾ� �޸� �Ҵ��� �����ϴ� (�޼ҵ� ã�� ���)
 *           2) �޼ҵ� 
 *              = ������ : String , void 
 *                       ------- ȭ�� �̵��� ���õ� JSP����/�����̷�Ʈ ���� 
 *              = �Ű����� : JSP���� �����ϴ� ���� ��ü ���� 
 *                         => HttpServletRequest , HttpServletResponse , HttpSession
 *                         @GetMapping()
 *                         public String display(HttpSession session)
 *                         => VO���� , String[] , �⺻�� (int , double , boolean)
 *                         => List���� 
 *                         *** ������ ������� 
 *           3) ã�� (�޼ҵ�) 
 *              @GetMapping => Get������� ��û (default) => <a> ajax , axios...
 *              @PostMapping => <form> , ajax , axios
 *                                              ------ ajax��ü => Vue,React
 *                              axios.get() , axios.post()
 *               ------------------------- Spring 4.3���ĺ��� ������ �ִ� 
 *              @RequestMapping => GET/POST�� ���ÿ� ó���� ���� 
 *           4) return => �ݵ�� JSP��(Ȯ���ڴ� ������� �ʴ´�)
 *                        ��ȣ�� : "redirect:.do"
 *                        
 *           => MVC ���� ��� 
 *           1) ����� ��û => .do
 *           2) DispatcherServlet�� ��û�� �޴´� 
 *           3) DispatcherServlet => HandlerMapping
 *                             Ŭ������ ã�Ƽ� �޼ҵ� ȣ���϶�� ���� 
 *                             ������̼� (GetMapping,PostMapping)
 *           ***4) Model���� ó�� ===> ����� ���� (������) 
 *           5) Model���� ó���� ����� DispatcherServlet�� �޴´� 
 *           6) ViewResolver�� ���� 
 *              ------------ JSP�� ã�Ƽ� request�� �����ϴ� ���� 
 *           ***7) JSP���� request�� ��� �����͸� ��� 
 *           8) DispatcherServlet�� ���� ������ ȭ�� ���� 
 *       => ���ͼ��� , AOP , Ʈ����� ��� 
 *       => ���� , ������ , ��ġ , Ŭ���� ...
 */
import java.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.sist.dao.*;
import com.sist.vo.*;
@Controller
public class MainController {
   // ��ü�ּҸ� ������ ���� Ȯ�� 
   @Autowired
   private FoodDAO dao;
   
   // ����� ��û�� ó�� 
   @GetMapping("main/main.do") // main.do?cno=1
   public String main_main(String cno,Model model, HttpServletRequest request)
   {
	   if(cno==null)
		   cno="1";
	   Map map=new HashMap();
	   map.put("cno", Integer.parseInt(cno));
	   List<CategoryVO> list=dao.foodCategoryData(map);
	   model.addAttribute("list", list);
	   
	   Cookie[] cookies=request.getCookies();
	   List<FoodVO> cList=new ArrayList<FoodVO>();
	   if(cookies!=null) {
		   for(int i=cookies.length-1;i>=0;i--) {
			   if(cookies[i].getName().startsWith("food_")) {
				   String no=cookies[i].getValue();
				   FoodVO vo=dao.foodDetailData(Integer.parseInt(no));
				   cList.add(vo);				   
			   }
		   }
	   }
	   model.addAttribute("cList",cList);
	   model.addAttribute("main_jsp", "../main/home.jsp");
	   return "main/main";
   }
}







