package com.sist.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


/*
 *  						 	 ���ͼ�Ʈ(preHandle)	   ���ͼ�Ʈ(postHandle)	  ���ͼ�Ʈ(afterCompletion)   
 *  ��û(.do) -> dispatcherServlet ----> handlermapping	 ----> 	ViewResolver 	-----> 		JSP
 *  								@Controller/@RestController	 (return���� �޾Ƽ� ȭ�� ����)
 *  								-> @GetMapping/@PostMapping
 *  Spring
 *  	setting : AOP/DI -> Ŭ���� ���
 *  	ORM(MyBatis) -> ���(JPA)
 *  		1) XML
 *  		2) Annotation
 *  		3) XML+Annotation(�߿�***)
 *  	Transaction
 *  	----------�Թ�
 *  	WebSocket
 *  	Security (���Ѻο�(�ð��� ����), �н����� �Ϻ�ȣȭ)
 *  	Task
 *  	----------�ɼ�
 *  	MVC
 *  	
 *  	����� ����...
 *  
 */
public class AutoIntercepter extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("AutoIntercepter call...");
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("postHandle call...");
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("afterCompletion call...");
		super.afterCompletion(request, response, handler, ex);
	}
	

}
